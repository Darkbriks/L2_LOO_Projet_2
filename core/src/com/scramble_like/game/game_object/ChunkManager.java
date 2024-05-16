package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.component.collider.Collider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.utils.Chunk;
import com.scramble_like.game.utils.ChunkHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkManager extends GameObject
{
    protected int level;
    protected Vector2 levelChunkCount;
    protected Chunk[][] chunks;
    protected List<String> drawnChunks;
    protected boolean isLoaded;
    //protected Map<Integer, Vector2> listCollision;

    protected double xOffSet = 0;

    public ChunkManager(String name, Scene scene) throws SceneIsNullException
    {
        super(name, scene);
        level = 0;
        levelChunkCount = ChunkHelper.getLevelChunkCount(0);
        chunks = new Chunk[(int) levelChunkCount.x][(int) levelChunkCount.y];
        drawnChunks = new ArrayList<>();
        //listCollision = new HashMap<>();
        isLoaded = false;

        xOffSet = 0;
    }

    boolean isLoaded() { return isLoaded; }


    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        new Thread(() ->
        {
            for (int i = 0; i < levelChunkCount.x; i++)
            {
                for (int j = 0; j < levelChunkCount.y; j++)
                {
                    chunks[i][j] = new Chunk(ChunkHelper.getChunk(level, new Vector2(i, j)),this);
                }
            }
            isLoaded = true;
            System.out.println("Chunks loaded");
        }).start();
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }
        if (!isLoaded) { return; }
        super.Update(DeltaTime);

        xOffSet += DeltaTime * 50;

        // On calcule la distance entre this et chaque chunk
        for (int i = 0; i < levelChunkCount.x; i++)
        {
            for (int j = 0; j < levelChunkCount.y; j++)
            {
                Vector2 referencePosition = new Vector2(this.getTransform().getLocation().x + (float) xOffSet, this.getTransform().getLocation().y);
                float distance = ChunkHelper.getChunkDistanceWithPosition(new Vector2(i, j), referencePosition, (int) levelChunkCount.y);
                if (distance < GameConstant.RENDERED_CHUNK_DISTANCE)
                {
                    if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        chunks[i][j].renderAsynchronously();
                        drawnChunks.add(i + " " + j);
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (distance < GameConstant.LOADED_CHUNK_DISTANCE)
                {
                    if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                    int index = indexOfRenderedChunk(i, j);
                    if (chunks[i][j].isRendered() && index != -1)
                    {
                        drawnChunks.remove(index);
                        chunks[i][j].unRender();
                    }
                }
                else if (chunks[i][j].isLoaded())
                {
                    chunks[i][j].unload();
                }
                if (distance < GameConstant.CHUNK_SIMULATING_DISTANCE){
                    if (chunks[i][j].isLoaded()&&!chunks[i][j].isSimulated()){
                        chunks[i][j].Simulate();
                    }
                }
                else{
                    if (chunks[i][j].isLoaded()&&chunks[i][j].isSimulated()){
                        chunks[i][j].unSimulate();
                    }
                }
            }
        }
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        super.Render();
        for (String chunk : drawnChunks)
        {
            String[] chunkPosition = chunk.split(" ");
            int x = (int) Float.parseFloat(chunkPosition[0]);
            int y = (int) Float.parseFloat(chunkPosition[1]);
            if (chunks[x][y].isRendered())
            {
                Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(x, y), (int) levelChunkCount.y);
                chunkPositionInSceneUnits.x -= (float) xOffSet + GameConstant.SQUARE_SIDE * x;
                chunks[x][y].draw(this.getShapeRenderer(), this.getBatch(), chunkPositionInSceneUnits);
            }
        }
    }

    protected int indexOfRenderedChunk(int i, int j)
    {
        for (String chunk : drawnChunks) { if (chunk.equals(i + " " + j)) { return drawnChunks.indexOf(chunk); } } return -1;
    }
}
