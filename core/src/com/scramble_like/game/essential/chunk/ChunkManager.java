package com.scramble_like.game.essential.chunk;

import com.badlogic.gdx.math.Vector2;

import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

public class ChunkManager extends GameObject
{
    protected int level;
    protected Vector2 levelChunkCount;
    protected Chunk[][] chunks;
    protected boolean isLoaded;

    protected Player player;
    protected GameCamera camera;

    //Variables pour diviser le traitement des chunks sur plusieurs frames
    protected int nextI;
    protected int nextJ;
    protected int processedChunks;

    public ChunkManager(String name, Scene scene, int level) throws SceneIsNullException
    {
        super(name, scene);
        this.level = level;
        levelChunkCount = ChunkHelper.getLevelChunkCount(level);
        chunks = new Chunk[(int) levelChunkCount.x][(int) levelChunkCount.y];
        isLoaded = false;
        player = null;

        this.nextI = 0;
        this.nextJ = 0;
        this.processedChunks = 0;

        this.getTransform().setZIndex(CoreConstant.MAX_Z_INDEX - 1);
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
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);
        if (!isLoaded) { return; }

        // On calcule la distance entre le joueur et chaque chunk
        for (int i = this.nextI; i < levelChunkCount.x; i++)
        {
            for (int j = this.nextJ; j < levelChunkCount.y; j++)
            {
                if (chunks[i][j] == null) { continue; }
                this.processedChunks++;

                float squaredDistance = ChunkHelper.getChunkSquaredDistanceWithPosition(new Vector2(i, j), camera.getPosition().x, (int) levelChunkCount.y);

                if (squaredDistance < CoreConstant.SQUARED_CHUNK_SIMULATING_DISTANCE)
                {
                    if (chunks[i][j].isLoaded() && chunks[i][j].isRendered() && !chunks[i][j].isSimulated())
                    {
                        chunks[i][j].simulateAsynchronously(ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y));
                    }
                    else if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        chunks[i][j].renderAsynchronously(ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y));
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (squaredDistance < CoreConstant.SQUARED_RENDERED_CHUNK_DISTANCE)
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (chunks[i][j].isLoaded() && !chunks[i][j].isRendered())
                    {
                        chunks[i][j].renderAsynchronously(ChunkHelper.getChunkPositionInSceneUnits(new Vector2(i, j), (int) levelChunkCount.y));
                    }
                    else if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                }
                else if (squaredDistance < CoreConstant.SQUARED_LOADED_CHUNK_DISTANCE)
                {
                    if (!chunks[i][j].isLoaded()) { chunks[i][j].loadAsynchronously(); }
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered()) { chunks[i][j].unRender(); }
                }
                else
                {
                    if (chunks[i][j].isSimulated()) { chunks[i][j].unSimulate(); }
                    if (!chunks[i][j].isSimulated() && chunks[i][j].isRendered()) { chunks[i][j].unRender(); }
                    if (!chunks[i][j].isRendered() && chunks[i][j].isLoaded()) { chunks[i][j].unload(); }
                }
                this.nextJ++; if (this.nextJ >= levelChunkCount.y) { this.nextJ = 0; this.nextI = (i + 1) % (int) levelChunkCount.x; }
                if (this.processedChunks >= CoreConstant.CHUNKS_PROCESSED_PER_FRAME) { this.processedChunks = 0; return; }
            }
        }
        this.nextI = 0; this.nextJ = 0;
        this.processedChunks = 0;
    }
}