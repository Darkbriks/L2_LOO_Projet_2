package com.scramble_like.game;

public class GameConstant
{
    // Screen
    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    // Chunk
    public static int CHUNK_SIDE = 50;
    public static int SQUARE_SIDE = 25;
    public static int CHUNK_SIZE = CHUNK_SIDE * SQUARE_SIDE;
    public static int LOADED_CHUNK_DISTANCE = 2000; // In scene units
    public static int RENDERED_CHUNK_DISTANCE = 1500; // In scene units
    public static int CHUNK_SIMULATING_DISTANCE = 1250; // In scene units
    public static int AIR_BLOCK = 32;

    // Background
    public static int BACKGROUD_SPEED = 250;

    // Animation
    public static float ANIMATION_FRAME_DURATION = 0.1f;
}
