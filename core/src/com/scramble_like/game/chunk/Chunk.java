package com.scramble_like.game.chunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.component.collider.Collider;
import com.scramble_like.game.component.collider.TileCollider;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.chunk.ChunkLoadedEvent;
import com.scramble_like.game.game_object.ChunkManager;

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
    private final EventDispatcher eventDispatcher;
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
        eventDispatcher = new EventDispatcher();
    }

    public boolean isLoaded() { return isLoaded; }
    public boolean isRendered() { return isRendered; }
    public boolean isSimulated(){ return isSimulated;}
    public EventDispatcher getEventDispatcher() { return eventDispatcher; }

    protected void load() throws IOException
    {
        if (this.isLoaded) return;
        this.inLoading = true;
        List<String> lines = Files.readAllLines(Paths.get(this.fileName));

        this.chunk = new char[GameConstant.CHUNK_SIDE][GameConstant.CHUNK_SIDE];
        for (int i = 0; i < lines.size(); i++) { String line = lines.get(i); for (int j = 0; j < line.length(); j++) { this.chunk[j][i] = line.charAt(j); } }

        this.isLoaded = true;
        this.inLoading = false;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_LOADED, new ChunkLoadedEvent(this));
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
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_UNLOADED, new ChunkLoadedEvent(this));
    }

    private void render(Vector2 position)
    {
        if (!this.isLoaded || this.isRendered) return;
        this.inRendering = true;
        this.tilesData = new HashMap<>();
        this.tiles = new ArrayList<>();

        for (int i = 0; i < GameConstant.CHUNK_SIDE; i++)
        {
            for (int j = 0; j < GameConstant.CHUNK_SIDE; j++)
            {
                String tilePath = ChunkHelper.getTilePath(this.chunk[i][j]);
                if (!Objects.equals(tilePath, ""))
                {
                    Vector4 tileData = new Vector4(
                            i * GameConstant.SQUARE_SIDE - ((float) (GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
                            - j * GameConstant.SQUARE_SIDE + ((float) (GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
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
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_RENDERED, new ChunkLoadedEvent(this));
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
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_UNRENDERED, new ChunkLoadedEvent(this));
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

    public void update(Vector2 frameOffsetValue)
    {
        if (tiles == null) { return; }
        for (Tile tile : tiles) { tile.addOffset(frameOffsetValue); }
        if (colliders == null) { return; }
        for (TileCollider tileCollider : colliders) { tileCollider.addOffset(frameOffsetValue); }
    }

    public void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, Vector2 position)
    {
        spriteBatch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);

        int centerX = GameConstant.WIDTH / 2;
        int centerY = GameConstant.HEIGHT / 2;
        if (this.isRendered)
        {
            for (Vector4 rectangle : this.tilesData.values())
            {
                shapeRenderer.rect(rectangle.x + centerX + (int) position.x, rectangle.y + centerY + (int) position.y, GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE);
            }
        }

        shapeRenderer.end();
        spriteBatch.begin();
    }
}
