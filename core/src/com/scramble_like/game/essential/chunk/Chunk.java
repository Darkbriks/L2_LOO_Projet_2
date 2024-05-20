package com.scramble_like.game.essential.chunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.chaos.Collider;
import com.scramble_like.game.essential.chaos.TileCollider;
import com.scramble_like.game.essential.CoreConstant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Chunk
{
    private final String fileName;
    private char[][] chunk;
    private Map<String, Vector4> tilesData; // Vector4: x, y, i, j
    private List<Tile> tiles;
    private List<TileCollider> colliders;
    private boolean isLoaded;
    private boolean isRendered;
    protected boolean isSimulated;
    protected boolean inLoading;
    protected boolean inRendering;
    protected boolean inSimulating;
    private final ChunkManager chunkManager;

    public Chunk(String fileName, ChunkManager chunkManager)
    {
        this.chunkManager = chunkManager;
        this.fileName = fileName;
        this.chunk = null;
        this.tiles = null;
        this.tilesData = null;
        colliders = null;
        isLoaded = false;
        isRendered = false;
        isSimulated = false;
        inLoading = false;
        inRendering = false;
        inSimulating = false;
    }

    public boolean isLoaded() { return isLoaded; }
    public boolean isRendered() { return isRendered; }
    public boolean isSimulated(){ return isSimulated;}

    protected void load() throws IOException
    {
        if (this.isLoaded) return;
        this.inLoading = true;
        List<String> lines = Files.readAllLines(Paths.get(this.fileName));

        this.chunk = new char[CoreConstant.CHUNK_SIDE][CoreConstant.CHUNK_SIDE];
        for (int i = 0; i < lines.size(); i++) { String line = lines.get(i); for (int j = 0; j < line.length(); j++) { this.chunk[j][i] = line.charAt(j); } }

        this.isLoaded = true;
        this.inLoading = false;
    }

    public void loadAsynchronously()
    {
        if (this.inLoading) { return; }
        new Thread(() -> {try { load(); } catch (IOException e) { Gdx.app.error("Chunk","Error: " + e.getMessage()); } }).start();
    }

    public void unload()
    {
        this.unSimulate();
        this.unRender();
        this.chunk = null;
        this.isLoaded = false;
    }

    private void render(Vector2 position)
    {
        if (!this.isLoaded || this.isRendered) return;
        this.inRendering = true;
        this.tilesData = new HashMap<>();
        this.tiles = new ArrayList<>();

        for (int i = 0; i < CoreConstant.CHUNK_SIDE; i++)
        {
            for (int j = 0; j < CoreConstant.CHUNK_SIDE; j++)
            {
                String tilePath = ChunkHelper.getTilePath(this.chunk[i][j]);
                if (!Objects.equals(tilePath, ""))
                {
                    Vector4 tileData = new Vector4(
                            i * CoreConstant.SQUARE_SIDE - ((float) (CoreConstant.CHUNK_SIDE * CoreConstant.SQUARE_SIDE) / 2),
                            - j * CoreConstant.SQUARE_SIDE + ((float) (CoreConstant.CHUNK_SIDE * CoreConstant.SQUARE_SIDE) / 2),
                            i, j);

                    this.tilesData.put(i + " " + j, tileData);
                    Tile tile = new Tile(tilePath, tileData.x + (int) position.x, tileData.y + (int) position.y);
                    this.tiles.add(tile);
                    this.chunkManager.AddComponent(tile);
                }
            }
        }

        this.isRendered = true;
        this.inRendering = false;
    }

    public void renderAsynchronously(Vector2 position)
    {
        if (!this.isLoaded || this.inRendering) { return; }
        //new Thread(() -> {this.render(position); }).start();
        this.render(position);
    }

    public void unRender()
    {
        this.unSimulate();
        if (tiles != null) { for (Tile tile : tiles) { chunkManager.RemoveComponent(tile); } }
        this.tilesData = null;
        this.tiles = null;
        this.isRendered = false;
    }

    private boolean asAnyNoCollideBlockInNeighbour(int x, int y)
    {
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0) continue;
                if (x + i < 0 || x + i >= this.chunk.length || y + j < 0 || y + j >= this.chunk[0].length) return true;
                if (ChunkHelper.isNoColliderBlock(this.chunk[x + i][y + j])) return true;
            }
        }
        return false;
    }

    private void simulate(Vector2 position)
    {
        if (!this.isLoaded || !this.isRendered || this.isSimulated) return;
        this.inSimulating = true;
        colliders = new ArrayList<>();

        for (Vector4 tileData : tilesData.values())
        {
            if (!ChunkHelper.isNoColliderBlock(this.chunk[(int) tileData.z][(int) tileData.w]) && asAnyNoCollideBlockInNeighbour((int) tileData.z, (int) tileData.w))
            {
                TileCollider collider = new TileCollider(tileData.x + (int) position.x, tileData.y + (int) position.y);
                colliders.add(collider);
                this.chunkManager.AddComponent(collider);
            }
        }

        this.isSimulated = true;
        this.inSimulating = false;
    }

    public void simulateAsynchronously(Vector2 position)
    {
        if (!this.isLoaded || !this.isRendered || this.inSimulating) { return; }
        new Thread(() -> this.simulate(position)).start();
    }

    public void unSimulate()
    {
        if (colliders == null) return;
        for(Collider c : colliders) { this.chunkManager.RemoveComponent(c); }
        colliders = null;
        this.isSimulated = false;
    }
}