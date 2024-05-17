package com.scramble_like.game.utils;

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
    private Map<String, Vector4> tileList; // Vector4: x, y, i, j
    private List<Tile> tiles;
    private List<TileCollider> colliders;
    private boolean isLoaded;
    private boolean isRendered;
    protected boolean isSimulated;
    private final EventDispatcher eventDispatcher;
    private final ChunkManager chunkManager;

    public Chunk(String fileName, ChunkManager chunkManager)
    {
        this.chunkManager = chunkManager;
        this.fileName = fileName;
        this.chunk = null;
        this.tiles = null;
        this.tileList = null;
        colliders = null;
        isLoaded = false;
        isRendered = false;
        isSimulated = false;
        eventDispatcher = new EventDispatcher();
    }

    public boolean isLoaded() { return isLoaded; }
    public boolean isRendered() { return isRendered; }
    public boolean isSimulated(){ return isSimulated;}
    public EventDispatcher getEventDispatcher() { return eventDispatcher; }

    protected void load() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(this.fileName));

        this.chunk = new char[GameConstant.CHUNK_SIDE][GameConstant.CHUNK_SIDE];
        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                this.chunk[j][i] = line.charAt(j);
            }
        }

        this.isLoaded = true;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_LOADED, new ChunkLoadedEvent(this));
    }

    public void loadAsynchronously()
    {
        new Thread(() -> {try { load(); } catch (IOException e) { System.err.println("Error: " + e.getMessage()); } }).start();
    }

    public void unload()
    {
        this.unRender();
        this.chunk = null;
        this.isLoaded = false;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_UNLOADED, new ChunkLoadedEvent(this));
    }

    private void render(Vector2 position)
    {
        this.tileList = new HashMap<>();
        this.tiles = new ArrayList<>();

        for (int i = 0; i < GameConstant.CHUNK_SIDE; i++)
        {
            for (int j = 0; j < GameConstant.CHUNK_SIDE; j++)
            {
                if (this.chunk[j][i] != GameConstant.AIR_BLOCK)
                {
                    Vector4 tileData = new Vector4(
                            j * GameConstant.SQUARE_SIDE - ((float) (GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
                            - i * GameConstant.SQUARE_SIDE + ((float) (GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
                            i, j);

                    this.tileList.put(i + " " + j, tileData);
                    Tile tile = new Tile("Tileset/MedievalTileset/Tiles/tile34.png", tileData.x + (int) position.x, tileData.y + (int) position.y);
                    this.tiles.add(tile);
                    this.chunkManager.AddComponent(tile);
                }
            }
        }

        this.isRendered = true;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_RENDERED, new ChunkLoadedEvent(this));
    }

    public void renderAsynchronously(Vector2 position)
    {
        if (!this.isLoaded) return;
        //new Thread(() -> {this.render(position); }).start();
        this.render(position);
    }

    public void unRender()
    {
        if (tiles != null) { for (Tile tile : tiles) { chunkManager.RemoveComponent(tile); } }
        this.tileList = null;
        this.tiles = null;
        this.isRendered = false;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_UNRENDERED, new ChunkLoadedEvent(this));
    }

    /*private int[][] obtenirVoisins(int[][] tableau, int x, int y) {
        List<int[]> voisinsList = new ArrayList<>();

        // Les déplacements possibles pour atteindre les voisins (haut, bas, gauche, droite, et diagonales)
        int[] deplacementsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] deplacementsY = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < deplacementsX.length; i++) {
            int voisinX = x + deplacementsX[i];
            int voisinY = y + deplacementsY[i];

            // Vérifiez si le voisin est dans les limites du tableau
            if (voisinX >= 0 && voisinX < tableau.length && voisinY >= 0 && voisinY < tableau[0].length) {
                voisinsList.add(new int[]{voisinX, voisinY});
            }
        }

        // Convertir la liste en tableau 2D
        int[][] voisinsArray = new int[voisinsList.size()][2];
        for (int i = 0; i < voisinsList.size(); i++) {
            voisinsArray[i] = voisinsList.get(i);
        }

        return voisinsArray;
    }

    private boolean asAnyAirBlockInNeighbour(int x, int y)
    {
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0) continue;
                if (x + i < 0 || x + i >= this.chunk.length || y + j < 0 || y + j >= this.chunk[0].length) return true;
                if (this.chunk[x + i][y + j] == GameConstant.AIR_BLOCK) return true;
            }
        }
        return false;
    }*/

    private void simulate(Vector2 position)
    {
        colliders = new ArrayList<>();

        for (Vector4 tileData : tileList.values())
        {
            //if (asAnyAirBlockInNeighbour((int) tileData.z, (int) tileData.w))
            //{
                TileCollider collider = new TileCollider(tileData.x + (int) position.x, tileData.y + (int) position.y);
                colliders.add(collider);
                this.chunkManager.AddComponent(collider);
            //}
        }

        this.isSimulated = true;
    }

    public void simulateAsynchronously(Vector2 position)
    {
        if (!this.isLoaded || !this.isRendered) return;
        //new Thread(() -> {this.simulate(position); }).start();
        this.simulate(position);
    }

    public void unSimulate()
    {
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
            for (Vector4 rectangle : this.tileList.values())
            {
                shapeRenderer.rect(rectangle.x + centerX + (int) position.x, rectangle.y + centerY + (int) position.y, GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE);
            }
        }

        shapeRenderer.end();
        spriteBatch.begin();
    }
}
