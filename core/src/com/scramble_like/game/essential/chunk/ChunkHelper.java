package com.scramble_like.game.essential.chunk;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.CoreConstant;

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
    private static final String[][] level3;

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
            { GameConstant.CHUNK_PATH("0", 1) },
            { GameConstant.CHUNK_PATH("1", 1) },
            { GameConstant.CHUNK_PATH("2", 1) },
            { GameConstant.CHUNK_PATH("3", 1) },
            { GameConstant.CHUNK_PATH("4", 1) },
            { GameConstant.CHUNK_PATH("5", 1) },
            { GameConstant.CHUNK_PATH("6", 1) },
            { GameConstant.CHUNK_PATH("7", 1) },
        };

        level2 = new String[][]
        {
            { GameConstant.CHUNK_PATH("0", 2) },
            { GameConstant.CHUNK_PATH("1", 2) },
            { GameConstant.CHUNK_PATH("2", 2) },
            { GameConstant.CHUNK_PATH("3", 2) },
            { GameConstant.CHUNK_PATH("4", 2) },
            { GameConstant.CHUNK_PATH("5", 2) },
            { GameConstant.CHUNK_PATH("6", 2) },
        };

        level3 = new String[][]
                {
                        { GameConstant.CHUNK_PATH("0", 3),GameConstant.CHUNK_PATH("1", 3),GameConstant.CHUNK_PATH("2", 3),GameConstant.CHUNK_PATH("3", 3),GameConstant.CHUNK_PATH("4", 3),GameConstant.CHUNK_PATH("5", 3),GameConstant.CHUNK_PATH("6", 3),GameConstant.CHUNK_PATH("7", 3),GameConstant.CHUNK_PATH("8", 3) },
                        { GameConstant.CHUNK_PATH("0_0", 3),GameConstant.CHUNK_PATH("1_1", 3),GameConstant.CHUNK_PATH("2_2", 3),GameConstant.CHUNK_PATH("3_3", 3),GameConstant.CHUNK_PATH("4_4", 3),GameConstant.CHUNK_PATH("5_5", 3),GameConstant.CHUNK_PATH("6_6", 3),GameConstant.CHUNK_PATH("7_7", 3),GameConstant.CHUNK_PATH("8_8", 3)},
                        /*{ "","","","","","","","",GameConstant.CHUNK_PATH("B1", 3)},
                        { "","","","","","","","",GameConstant.CHUNK_PATH("B2", 3)},
                        { "","","","","","","","",GameConstant.CHUNK_PATH("B3", 3)},
                        { "","","","","","","","",GameConstant.CHUNK_PATH("B4", 3)},
                        { "","","","","","","","",GameConstant.CHUNK_PATH("B5", 3)},*/
                };

        chunkListByLevel = new ArrayList<>();
        chunkListByLevel.add(level0);
        chunkListByLevel.add(level1);
        chunkListByLevel.add(level2);
        chunkListByLevel.add(level3);
    }

    static {
        //String tilePath = "Tileset/MedievalTileset/Tiles/";
        String propsPath = "Tileset/MedievalTileset/Objects/";
        String propesTilePath1 = "Tileset/level_1/";
        String propesTilePath2 = "Tileset/utile/";
        String propesTilePath3 = "Tileset/level_2/";
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
        tileMap.put(55, propesTilePath3 +"tile113.png");//7
        tileMap.put(56, propesTilePath3 +"tile056.png");//8
        tileMap.put(57, propesTilePath3 +"tile058.png");//9
        tileMap.put(58, "");
        tileMap.put(59, "");
        tileMap.put(60, "");
        tileMap.put(61, "");
        tileMap.put(62, "");
        tileMap.put(63, "");
        tileMap.put(64, "");
        tileMap.put(65, "");//A
        tileMap.put(66, "");//B
        tileMap.put(67, "");//C
        tileMap.put(68, "");//D
        tileMap.put(69, "");//E
        tileMap.put(70, "");//F
        tileMap.put(71, "");//G
        tileMap.put(72, "");//H
        tileMap.put(73, "");//I
        tileMap.put(74, propesTilePath3 +"tile048.png");//J
        tileMap.put(75, propesTilePath3 +"tile050.png");//K
        tileMap.put(76, propesTilePath3 +"tile032.png");//L
        tileMap.put(77, propesTilePath3 +"tile034.png");//N
        tileMap.put(78, "");//M
        tileMap.put(79, "");//O
        tileMap.put(80, propesTilePath2 +"tile136.png");//P
        tileMap.put(81, propesTilePath2 +"tile138.png");//Q
        tileMap.put(82, propesTilePath2 +"tile153.png");//R
        tileMap.put(83, propesTilePath2 +"tile120.png");//S
        tileMap.put(84, propesTilePath2 +"tile121.png");//T
        tileMap.put(85, propesTilePath2 +"tile152.png");//U
        tileMap.put(86, propesTilePath2 +"tile154.png");//V
        tileMap.put(87, propesTilePath2 +"tile169.png");//W
        tileMap.put(88, propesTilePath2 +"tile168.png");//X
        tileMap.put(89, propesTilePath2 +"tile170.png");//Y
        tileMap.put(90, propesTilePath2 +"tile137.png");//Z
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

        noColliderBlockIndexes = new int[] { 32,74,75,76,77,97,98,99,100,101,102,103,104,105,106,107,108,109 };
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
        int x = (int) chunkPosition.x * CoreConstant.CHUNK_SIZE;
        int y = CoreConstant.CHUNK_SIZE / 2 + (int) chunkPosition.y * CoreConstant.CHUNK_SIZE - (numberOfChunksVertical * CoreConstant.CHUNK_SIZE) / 2;
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
