package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.chunk.Chunk;
import com.scramble_like.game.chunk.ChunkHelper;

public class ChunkManager extends GameObject
{
    protected int level;
    protected Vector2 levelChunkCount;
    protected Chunk[][] chunks;
    protected boolean isLoaded;

    protected Player player;
    protected GameCamera camera;

    public ChunkManager(String name, Scene scene, int level) throws SceneIsNullException
    {
        super(name, scene);
        this.level = level;
        levelChunkCount = ChunkHelper.getLevelChunkCount(level);
        chunks = new Chunk[(int) levelChunkCount.x][(int) levelChunkCount.y];
        isLoaded = false;
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
        camera = ScrambleLikeApplication.getInstance().getCamera();
        new Thread(() ->
        {
            for (int i = 0; i < levelChunkCount.x; i++)
            {
                for (int j = 0; j < levelChunkCount.y; j++)
                {
                    String chunkPath = ChunkHelper.getChunk(level, new Vector2(i, j));
                    if (chunkPath == null || chunkPath.isEmpty()) { continue; }
                    chunks[i][j] = new Chunk(chunkPath,this);
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

        // On calcule la distance entre le joueur et chaque chunk
        for (int i = 0; i < levelChunkCount.x; i++)
        {
            for (int j = 0; j < levelChunkCount.y; j++)
            {
                if (chunks[i][j] == null) { continue; }

                float squaredDistance = ChunkHelper.getChunkSquaredDistanceWithPosition(new Vector2(i, j), camera.getPosition(), (int) levelChunkCount.y);
                if (squaredDistance < GameConstant.SQUARED_CHUNK_SIMULATING_DISTANCE)
                {
                    if (chunks[i][j].isLoaded() && chunks[i][j].isRendered() && !chunks[i][j].isSimulated())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunks[i][j].simulateAsynchronously(chunkPositionInSceneUnits);
                    }
                    else if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunks[i][j].renderAsynchronously(chunkPositionInSceneUnits);
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (squaredDistance < GameConstant.SQUARED_RENDERED_CHUNK_DISTANCE)
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        Vector2 chunkPositionInSceneUnits = ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y);
                        chunks[i][j].renderAsynchronously(chunkPositionInSceneUnits);
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (squaredDistance < GameConstant.SQUARED_LOADED_CHUNK_DISTANCE)
                {
                    if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered()) { chunks[i][j].unRender(); }
                }
                else
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered()/* && index != -1*/) { chunks[i][j].unRender(); /*drawnChunks.remove(index);*/ }
                    if (!chunks[i][j].isRendered() && chunks[i][j].isLoaded()) { chunks[i][j].unload(); }
                }
            }
        }
    }
}