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
    private static final String[][] level1;
    // ...

    static {
        level0 = new String[][]
                {
                          // Line 1                       // Line 2
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                        { "Map/Level_1/text_art (2).txt", "Map/Level_1/text_art (3).txt"},
                };
        level1 = new String[][]
                {
                        // Line 1                       // Line 2
                        { "Map/Level_1/text_x0y0.txt", "Map/Level_1/text_x0y1.txt"},
                        { "Map/Level_1/text_x1y0.txt", "Map/Level_1/text_x1y1.txt"},
                        { "Map/Level_1/text_x2y0.txt", "Map/Level_1/text_x2y1.txt"},
                        { "Map/Level_1/text_x3y0.txt", "Map/Level_1/text_x3y1.txt"},
                        { "Map/Level_1/text_x4y0.txt", "Map/Level_1/text_x4y1.txt"},
                        { "Map/Level_1/text_x5y0.txt", "Map/Level_1/text_x5y1.txt"},
                        { "Map/Level_1/text_rempli.txt", "Map/Level_1/text_rempli.txt"},
                };

        chunkListByLevel = new ArrayList<>();
        chunkListByLevel.add(level0);
        chunkListByLevel.add(level1);
    }

    public static String getChunk(int level, Vector2 chunkPosition)
    {
        return chunkListByLevel.get(level)[(int) chunkPosition.x][(int) chunkPosition.y];
    }

    public static Vector2 getLevelChunkCount(int level)
    {
        return new Vector2(chunkListByLevel.get(level).length, chunkListByLevel.get(level)[level].length);
    }

    public static Vector2 getChunkPositionInSceneUnits(Vector2 chunkPosition, int numberOfChunksVertical)
    {
        int x = (int) chunkPosition.x * GameConstant.CHUNK_SIZE;
        int y = GameConstant.CHUNK_SIZE / 2 + (int) chunkPosition.y * GameConstant.CHUNK_SIZE - (numberOfChunksVertical * GameConstant.CHUNK_SIZE) / 2;
        return new Vector2(x, y);
    }

    public static float getChunkDistanceWithPosition(Vector2 chunkPosition, Vector2 position, int numberOfChunksVertical)
    {
        Vector2 chunkPositionInSceneUnits = getChunkPositionInSceneUnits(chunkPosition, numberOfChunksVertical);
        return (float) Math.sqrt(Math.pow(position.x - chunkPositionInSceneUnits.x, 2) + Math.pow(position.y - chunkPositionInSceneUnits.y, 2));
    }
}
