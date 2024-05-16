package com.scramble_like.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.GameConstant;

import java.util.ArrayList;
import java.util.List;

public class ChunkHelper
{
    private static final List<String[][]> chunkListByLevel;

    private static final String[][] level0;
    // ...

    static {
        level0 = new String[][]
                {
                          // Line 1                       // Line 2                       // Line 3                       // Line 4
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" }, // Column 1
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" }, // Column 2
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" }, // Column 3
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" }, // ...
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                        { "Map/Level_1/text_art (1).txt", "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt", "Map/Level_1/text_art (4).txt" },
                };

        chunkListByLevel = new ArrayList<>();
        chunkListByLevel.add(level0);
    }

    public static String getChunk(int level, Vector2 chunkPosition)
    {
        return chunkListByLevel.get(level)[(int) chunkPosition.x][(int) chunkPosition.y];
    }

    public static Vector2 getLevelChunkCount(int level)
    {
        return new Vector2(chunkListByLevel.get(level).length, chunkListByLevel.get(level)[0].length);
    }

    public static Vector2 getChunkPositionInSceneUnits(Vector2 chunkPosition, int numberOfChunksVertical)
    {
        int x = (int) chunkPosition.x * GameConstant.CHUNK_SIZE;
        int y = GameConstant.CHUNK_SIZE / 2 + (int) chunkPosition.y * GameConstant.CHUNK_SIZE - (numberOfChunksVertical * GameConstant.CHUNK_SIZE) / 2;
        return new Vector2(x, y);
    }

    public static Vector2 getChunkIndex(Vector2 positionInSceneUnits, int numberOfChunksVertical)
    {
        int x = (int) positionInSceneUnits.x / GameConstant.CHUNK_SIZE;
        int y = (int) positionInSceneUnits.y / GameConstant.CHUNK_SIZE;
        System.out.println("Start: " + positionInSceneUnits + " End: " + new Vector2(x, y));// TODO
        return new Vector2(x, y);
    }

    public static float getChunkDistanceWithPosition(Vector2 chunkPosition, Vector2 position, int numberOfChunksVertical)
    {
        Vector2 chunkPositionInSceneUnits = getChunkPositionInSceneUnits(chunkPosition, numberOfChunksVertical);
        return (float) Math.sqrt(Math.pow(position.x - chunkPositionInSceneUnits.x, 2) + Math.pow(position.y - chunkPositionInSceneUnits.y, 2));
    }
}
