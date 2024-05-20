package com.scramble_like.game;

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
    public static float PLAYER_SPEED = 350;
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

    // Debug
    public static boolean DEBUG = true;
    public static boolean GOD_MODE = true;
}
