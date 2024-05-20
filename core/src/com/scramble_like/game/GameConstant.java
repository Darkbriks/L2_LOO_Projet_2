package com.scramble_like.game;

import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.map.LevelMap;
import com.scramble_like.game.map.MainMenu;
import com.scramble_like.game.map.TestMap;

import java.util.Map;

public class GameConstant
{
    // Screen
    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    // Grid ; Determines la taille du monde. En aucun cas, un GameObject possèdant une collision ne doit sortir de cette grille.
    public static final int GRID_CELL_X = 1000;
    public static final int GRID_CELL_Y = 5;

    // Background
    public static final int BACKGROUND_SPEED = -150;

    // Player
    public static float CAMERA_SPEED = 250;
    public static float PLAYER_SPEED = 500;
    public static final int PLAYER_LIFE = 50;
    public static final int BOTTOM_LIMIT = -250;
    public static final int TOP_LIMIT = 250;
    public static final int LEFT_LIMIT = WIDTH / 2 - 50;
    public static final int RIGHT_LIMIT = WIDTH / 2 - 50;

    // Path
    public static String CHARACTER_PATH(String pack, String name) { return "Characters/" + pack + "/" + name; }
    public static String SOUND_PATH(String name) { return "Audio/Sound/" + name; }
    public static String MUSIC_PATH(String name) { return "Audio/Music/" + name; }
    public static String CHUNK_PATH(String name, int level) { return "Map/Level_" + level + "/" + name + ".txt"; }

    // Level
    public static final Map<Integer, Class<? extends Scene>> LEVEL_LIST = Map.of(
            0, MainMenu.class,
            1, LevelMap.class,
            2, TestMap.class);

    // Debug
    public static boolean DEBUG = true;
    public static boolean GOD_MODE = true;
}
