package com.scramble_like.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.event_dispatcher.EventDispatcher;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.chunk.ChunkLoadedEvent;
import org.w3c.dom.css.Rect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Chunk
{
    /*public int x=0,y=1;
    public ArrayList<String> chunkOriginal;
    public ArrayList<Rectangle> chunk = new ArrayList<>();

    public boolean actif = false;

    public Chunk(){
    }
    public void convertion(ArrayList<String> co){
        int yy =0;
        for (String ligne : co) {
            for (int xx = 0; xx < ligne.length(); xx++) {
                if (ligne.charAt(xx) != ' ') {
                    chunk.add(new Rectangle((xx * GameConstant.SQUARE_SIDE)+(x*GameConstant.CHUNK_SIZE) , -(yy * GameConstant.SQUARE_SIDE)+(y*GameConstant.CHUNK_SIZE)-(55), GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE)); // Exemple de coordonnées et taille
                }
            }
            yy++;
        }
    }

    public void convertion(ArrayList<String> co,int X,int Y){
        int yy =0;
        for (String ligne : co) {
            for (int xx = 0; xx < ligne.length(); xx++) {
                if (ligne.charAt(xx) != ' ') {
                    chunk.add(new Rectangle((xx * GameConstant.SQUARE_SIDE)+(x*GameConstant.CHUNK_SIZE) , -(yy * GameConstant.SQUARE_SIDE)+(y*GameConstant.CHUNK_SIZE)-(55), GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE)); // Exemple de coordonnées et taille
                }
            }
            yy++;
        }
    }

    public void wave(ArrayList<Rectangle> r){
        for (Rectangle w : r) {
            w.x-=3;
        }
    }*/

    private final String fileName;
    private char[][] chunk;
    private List<RectangleData> rectanglesList;
    private boolean isLoaded;
    private boolean isRendered;
    private final EventDispatcher eventDispatcher;

    public Chunk(String fileName)
    {
        this.fileName = fileName;
        this.chunk = null;
        this.rectanglesList = null;
        isLoaded = false;
        isRendered = false;
        eventDispatcher = new EventDispatcher();
    }

    public boolean isLoaded() { return isLoaded; }
    public boolean isRendered() { return isRendered; }
    public EventDispatcher getEventDispatcher() { return eventDispatcher; }

    protected void load() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(this.fileName));

        this.chunk = new char[GameConstant.CHUNK_SIDE][GameConstant.CHUNK_SIDE];
        for (int i = 1; i < lines.size(); i++)
        {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                this.chunk[j][i - 1] = line.charAt(j);
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

    private void render()
    {
        this.rectanglesList = new ArrayList<>();

        for (int i = 0; i < GameConstant.CHUNK_SIDE; i++)
        {
            for (int j = 1; j < GameConstant.CHUNK_SIDE; j++)
            {
                if (this.chunk[j][i] != ' ')
                {
                    this.rectanglesList.add(new RectangleData(
                            j * GameConstant.SQUARE_SIDE - ((GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
                            - i * GameConstant.SQUARE_SIDE + ((GameConstant.CHUNK_SIDE * GameConstant.SQUARE_SIDE) / 2),
                            GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE));
                }
            }
        }

        this.isRendered = true;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_RENDERED, new ChunkLoadedEvent(this));
    }

    public void renderAsynchronously()
    {
        if (!this.isLoaded) return;
        new Thread(this::render).start();
    }

    public void unRender()
    {
        this.rectanglesList = null;
        this.isRendered = false;
        this.eventDispatcher.DispatchEvent(EventIndex.CHUNK_UNRENDERED, new ChunkLoadedEvent(this));
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
            for (RectangleData rectangle : this.rectanglesList)
            {
                shapeRenderer.rect(rectangle.x + centerX + (int) position.x, rectangle.y + centerY + (int) position.y, rectangle.width, rectangle.height);
            }
        }

        shapeRenderer.end();
        spriteBatch.begin();
    }

    public void ToString()
    {
        System.out.println("Chunk: " + this.fileName);
        System.out.println("Loaded: " + this.isLoaded);
        System.out.println("Rendered: " + this.isRendered);
        for (int i = 0; i < GameConstant.CHUNK_SIDE; i++)
        {
            for (int j = 0; j < GameConstant.CHUNK_SIDE; j++)
            {
                System.out.print(this.chunk[j][i]);
            }
            System.out.println();
        }
    }
}
