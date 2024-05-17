package com.scramble_like.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.GameConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkHelper
{
    private static final Map<Integer, String> tileMap;
    private static final List<String[][]> chunkListByLevel;

    private static final String[][] level0;
    private static final String[][] level1;
    // ...

    static {
        level0 = new String[][]
                {
                          // Line 1                       // Line 2
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
                        { /*"Map/Level_1/text_art (2).txt",*/ "Map/Level_1/text_art (3).txt"},
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

    static {
        String path = "Tileset/MedievalTileset/Tiles";
        tileMap = new HashMap<>();
        tileMap.put(0, "");
        tileMap.put(1, "");
        tileMap.put(2, "");
        tileMap.put(3, "");
        tileMap.put(4, "");
        tileMap.put(5, "");
        tileMap.put(6, "");
        tileMap.put(7, "");
        tileMap.put(8, "");
        tileMap.put(9, "");
        tileMap.put(10, "");
        tileMap.put(11, "");
        tileMap.put(12, "");
        tileMap.put(13, "");
        tileMap.put(14, "");
        tileMap.put(15, "");
        tileMap.put(16, "");
        tileMap.put(17, "");
        tileMap.put(18, "");
        tileMap.put(19, "");
        tileMap.put(20, "");
        tileMap.put(21, "");
        tileMap.put(22, "");
        tileMap.put(23, "");
        tileMap.put(24, "");
        tileMap.put(25, "");
        tileMap.put(26, "");
        tileMap.put(27, "");
        tileMap.put(28, "");
        tileMap.put(29, "");
        tileMap.put(30, "");
        tileMap.put(31, "");
        tileMap.put(32, "");
        tileMap.put(33, "");
        tileMap.put(34, "");
        tileMap.put(35, "");
        tileMap.put(36, "");
        tileMap.put(37, "");
        tileMap.put(38, "");
        tileMap.put(39, "");
        tileMap.put(40, "");
        tileMap.put(41, "");
        tileMap.put(42, "");
        tileMap.put(43, "");
        tileMap.put(44, "");
        tileMap.put(45, "");
        tileMap.put(46, "");
        tileMap.put(47, "");
        tileMap.put(48, "");
        tileMap.put(49, "");
        tileMap.put(50, "");
        tileMap.put(51, "");
        tileMap.put(52, "");
        tileMap.put(53, "");
        tileMap.put(54, "");
        tileMap.put(55, "");
        tileMap.put(56, "");
        tileMap.put(57, "");
        tileMap.put(58, "");
        tileMap.put(59, "");
        tileMap.put(60, "");
        tileMap.put(61, "");
        tileMap.put(62, "");
        tileMap.put(63, "");
        tileMap.put(64, "");
        tileMap.put(65, "");
        tileMap.put(66, "");
        tileMap.put(67, "");
        tileMap.put(68, "");
        tileMap.put(69, "");
        tileMap.put(70, "");
        tileMap.put(71, "");
        tileMap.put(72, "");
        tileMap.put(73, "");
        tileMap.put(74, "");
        tileMap.put(75, "");
        tileMap.put(76, "");
        tileMap.put(77, "");
        tileMap.put(78, "");
        tileMap.put(79, "");
        tileMap.put(80, "");
        tileMap.put(81, "");
        tileMap.put(82, "");
        tileMap.put(83, "");
        tileMap.put(84, "");
        tileMap.put(85, "");
        tileMap.put(86, "");
        tileMap.put(87, "");
        tileMap.put(88, "");
        tileMap.put(89, "");
        tileMap.put(90, "");
        tileMap.put(91, "");
        tileMap.put(92, "");
        tileMap.put(93, "");
        tileMap.put(94, "");
        tileMap.put(95, "");
        tileMap.put(96, "");
        tileMap.put(97, "");
        tileMap.put(98, "");
        tileMap.put(99, "");
        tileMap.put(100, "");
        tileMap.put(101, "");
        tileMap.put(102, "");
        tileMap.put(103, "");
        tileMap.put(104, "");
        tileMap.put(105, "");
        tileMap.put(106, "");
        tileMap.put(107, "");
        tileMap.put(108, "");
        tileMap.put(109, "");
        tileMap.put(110, "");
        tileMap.put(111, "");
        tileMap.put(112, "");
        tileMap.put(113, "");
        tileMap.put(114, "");
        tileMap.put(115, "");
        tileMap.put(116, "");
        tileMap.put(117, "");
        tileMap.put(118, "");
        tileMap.put(119, "");
        tileMap.put(120, "");
        tileMap.put(121, "");
        tileMap.put(122, "");
        tileMap.put(123, "");
        tileMap.put(124, "");
        tileMap.put(125, "");
        tileMap.put(126, "");
        tileMap.put(127, "");
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
