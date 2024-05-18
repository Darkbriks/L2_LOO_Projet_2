package com.scramble_like.game;

public class GameConstant
{
    // Screen
    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    // Rendering
    public static int MIN_Z_INDEX = 0;
    public static int MAX_Z_INDEX = 10;

    // Chunk
    public static int CHUNK_SIDE = 50;
    public static int SQUARE_SIDE = 25;
    public static int CHUNK_SIZE = CHUNK_SIDE * SQUARE_SIDE;
    public static int LOADED_CHUNK_DISTANCE = 2000; // In scene units
    public static int RENDERED_CHUNK_DISTANCE = 1500; // In scene units
    public static int CHUNK_SIMULATING_DISTANCE = 1250; // In scene units

    // Background
    public static int BACKGROUD_SPEED = 250;

    // Animation
    public static float ANIMATION_FRAME_DURATION = 0.1f;

    // PLayer
    public static float PLAYER_SPEED = 350;
    public static int PLAYER_LIFE = 50;

    // Path
    public static String CHARACTER_PATH(String pack, String name) { return "Characters/" + pack + "/" + name; }
    public static String SOUND_PATH(String name) { return "Audio/Sound/" + name; }
    public static String MUSIC_PATH(String name) { return "Audio/Music/" + name; }
    public static String CHUNK_PATH(String name, int level) { return "Map/Level_" + level + "/" + name + ".txt"; }

    // Debug
    public static boolean DEBUG = false;
    public static boolean GOD_MODE = false;
}
