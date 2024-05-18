package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.chunk.Chunk;
import com.scramble_like.game.chunk.ChunkHelper;
import java.util.ArrayList;
import java.util.List;

public class ChunkManager extends GameObject
{
    protected int level;
    protected Vector2 levelChunkCount;
    protected Chunk[][] chunks;
    protected List<String> drawnChunks;
    protected boolean isLoaded;

    protected float xOffSet;
    protected float xLastOffset;

    protected Player player;

    public ChunkManager(String name, Scene scene, int level, float startOffset) throws SceneIsNullException
    {
        super(name, scene);
        this.level = level;
        levelChunkCount = ChunkHelper.getLevelChunkCount(level);
        chunks = new Chunk[(int) levelChunkCount.x][(int) levelChunkCount.y];
        drawnChunks = new ArrayList<>();
        isLoaded = false;

        xOffSet = startOffset;
        xLastOffset = 0;

        player = null;

        this.getTransform().setZIndex(GameConstant.MAX_Z_INDEX - 1);
    }

    public boolean isLoaded() { return isLoaded; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

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
        }).start();
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);
        if (!isLoaded) { return; }

        xOffSet += (float) (DeltaTime * GameConstant.BACKGROUD_SPEED);

        // On calcule la distance entre this et chaque chunk
        for (int i = 0; i < levelChunkCount.x; i++)
        {
            for (int j = 0; j < levelChunkCount.y; j++)
            {
                if (chunks[i][j].isRendered()) { chunks[i][j].update(new Vector2(xOffSet - xLastOffset, 0)); }

                Vector2 referencePosition = new Vector2(this.getTransform().getLocation().x + xOffSet, this.getTransform().getLocation().y);
                float distance = ChunkHelper.getChunkDistanceWithPosition(new Vector2(i, j), referencePosition, (int) levelChunkCount.y);

                //float distanceToPlayer = distance;
                //if (player != null) { distanceToPlayer = ChunkHelper.getChunkDistanceWithPosition(new Vector2(i, j), new Vector2(player.getTransform().getLocation().x, player.getTransform().getLocation().y), (int) levelChunkCount.y); }

                if (distance < GameConstant.CHUNK_SIMULATING_DISTANCE)
                {
                    if (chunks[i][j].isLoaded() && chunks[i][j].isRendered() && !chunks[i][j].isSimulated())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunkPositionInSceneUnits.x -= xOffSet;
                        chunks[i][j].simulateAsynchronously(chunkPositionInSceneUnits);
                    }
                    else if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunkPositionInSceneUnits.x -= xOffSet;
                        chunks[i][j].renderAsynchronously(chunkPositionInSceneUnits);
                        drawnChunks.add(i + " " + j);
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (distance < GameConstant.RENDERED_CHUNK_DISTANCE)
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunkPositionInSceneUnits.x -= xOffSet;
                        chunks[i][j].renderAsynchronously(chunkPositionInSceneUnits);
                        drawnChunks.add(i + " " + j);
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (distance < GameConstant.LOADED_CHUNK_DISTANCE)
                {
                    if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    int index = indexOfRenderedChunk(i, j);
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered() && index != -1) { drawnChunks.remove(index); chunks[i][j].unRender(); }
                }
                else
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    int index = indexOfRenderedChunk(i, j);
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered() && index != -1) { chunks[i][j].unRender(); drawnChunks.remove(index); }
                    if (!chunks[i][j].isRendered() && chunks[i][j].isLoaded()) { chunks[i][j].unload(); }
                }
            }
        }
        xLastOffset = xOffSet;
    }

    /*@Override
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
                chunkPositionInSceneUnits.x -= xOffSet;
                chunks[x][y].draw(this.getShapeRenderer(), this.getBatch(), chunkPositionInSceneUnits);
            }
        }
    }*/

    protected int indexOfRenderedChunk(int i, int j)
    {
        for (String chunk : drawnChunks) { if (chunk.equals(i + " " + j)) { return drawnChunks.indexOf(chunk); } } return -1;
    }
}