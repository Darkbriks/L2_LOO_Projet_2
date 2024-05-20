package com.scramble_like.game.chunk;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkHelper
{
    private static final Map<Integer, String> tileMap;
    private static final int[] noColliderBlockIndexes;
    private static final List<String[][]> chunkListByLevel;

    private static final String[][] level0;
    private static final String[][] level1;
    private static final String[][] level2;

    static
    {
        level0 = new String[][]
        {
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
            { GameConstant.CHUNK_PATH("text_art (3)", 0) },
        };

        level1 = new String[][]
        {
            { "Map/text_x0y0.txt", "Map/text_x0y1.txt"},
            { "Map/text_x0y0.txt", "Map/text_x0y1.txt"},
        };

        level2 = new String[][]
        {
                { "Map/Level_1/0.txt"},
                { "Map/Level_1/1.txt"},
                { "Map/Level_1/2.txt"},
                { "Map/Level_1/3.txt"},
                { "Map/Level_1/4.txt"},
                { "Map/Level_1/5.txt"},
                { "Map/Level_1/6.txt"},
                { "Map/Level_1/7.txt"},
        };

        chunkListByLevel = new ArrayList<>();
        chunkListByLevel.add(level0);
        chunkListByLevel.add(level1);
        chunkListByLevel.add(level2);
    }

    static {
        //String tilePath = "Tileset/MedievalTileset/Tiles/";
        String propsPath = "Tileset/MedievalTileset/Objects/";
        String propesTilePath1 = "Tileset/essai/";
        String propesTilePath2 = "Tileset/essai2/";
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
        tileMap.put(48, propesTilePath1 +"tile113.png");//0
        tileMap.put(49, propesTilePath2 +"tile142.png");//1
        tileMap.put(50, propesTilePath2 +"tile143.png");//2
        tileMap.put(51, propesTilePath2 +"tile143.png");//3
        tileMap.put(52, propesTilePath1 +"tile056.png");//4
        tileMap.put(53, propesTilePath1 +"tile057.png");//5
        tileMap.put(54, propesTilePath1 +"tile058.png");//6
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
        tileMap.put(97, propesTilePath2 +"tile126.png");//a
        tileMap.put(98, propesTilePath1 +"tile089.png");//b
        tileMap.put(99, propesTilePath2 +"tile127.png");//c
        tileMap.put(100, propesTilePath1 +"tile082.png");//d
        tileMap.put(101, propesTilePath2 +"tile111.png");//e
        tileMap.put(102, propesTilePath1 +"tile073.png");//f
        tileMap.put(103, propesTilePath2 +"tile110.png");//g
        tileMap.put(104, propesTilePath1 +"tile080.png");//h
        tileMap.put(105, propesTilePath1 +"tile027.png");//i
        tileMap.put(106, propesTilePath1 +"tile088.png");//j
        tileMap.put(107, propesTilePath1 +"tile090.png");//k
        tileMap.put(108, propesTilePath1 +"tile074.png");//l
        tileMap.put(109, propesTilePath1 +"tile072.png");//n
        tileMap.put(110, propesTilePath2 +"tile202.png");//m
        tileMap.put(111, propesTilePath2 +"tile200.png");//o
        tileMap.put(112, propesTilePath2 +"tile184.png");//p
        tileMap.put(113, propesTilePath2 +"tile186.png");//q
        tileMap.put(114, propesTilePath2 +"tile153.png");//r
        tileMap.put(115, propesTilePath2 +"tile120.png");//s
        tileMap.put(116, propesTilePath2 +"tile121.png");//t
        tileMap.put(117, propesTilePath2 +"tile154.png");//u
        tileMap.put(118, propesTilePath2 +"tile168.png");//v
        tileMap.put(119, propesTilePath2 +"tile123.png");//w
        tileMap.put(120, propesTilePath2 +"tile107.png");//x
        tileMap.put(121, "");
        tileMap.put(122, "");
        tileMap.put(123, "");
        tileMap.put(124, "");
        tileMap.put(125, "");
        tileMap.put(126, "");
        tileMap.put(127, "");

        noColliderBlockIndexes = new int[] { 32,97,98,99,100,101,102,103,104,105,106,107,108,109 };
    }


    public static String getChunk(int level, Vector2 chunkPosition)
    {
        if (level < 0 || level >= chunkListByLevel.size()) return "";
        if ((int) chunkPosition.x < 0 || (int) chunkPosition.x >= chunkListByLevel.get(level).length) return "";
        if ((int) chunkPosition.y < 0 || (int) chunkPosition.y >= chunkListByLevel.get(level)[0].length) return "";
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

    public static float getChunkSquaredDistanceWithPosition(Vector2 chunkPosition, float cameraX, int numberOfChunksVertical)
    {
        Vector2 chunkPositionInSceneUnits = getChunkPositionInSceneUnits(chunkPosition, numberOfChunksVertical);
        return (cameraX - chunkPositionInSceneUnits.x) * (cameraX - chunkPositionInSceneUnits.x) + ( chunkPositionInSceneUnits.y) * (-chunkPositionInSceneUnits.y);
    }

    public static int getTileMapSize() { return tileMap.size(); }

    public static String getTilePath(int tileIndex)
    {
        if (tileIndex < 0 || tileIndex >= tileMap.size()) return "Tileset/MedievalTileset/Tiles/tile34.png";
        return tileMap.get(tileIndex);
    }

    public static boolean isNoColliderBlock(int blockIndex) { for (int noColliderBlockIndex : noColliderBlockIndexes) { if (blockIndex == noColliderBlockIndex) { return true; } } return false; }
}
