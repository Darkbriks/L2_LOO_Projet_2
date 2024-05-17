package com.scramble_like.game;

public class GameConstant
{
    // Screen
    public static int WIDTH = 1600;
    public static int HEIGHT = 900;

    // Chunk
    public static int CHUNK_SIDE = 50;
    public static int SQUARE_SIDE = 10;
    public static int CHUNK_SIZE = CHUNK_SIDE * SQUARE_SIDE;
    public static int LOADED_CHUNK_DISTANCE = 1500; // In scene units
    public static int RENDERED_CHUNK_DISTANCE = 1050; // In scene units
    public static int CHUNK_SIMULATING_DISTANCE = 500; // In scene units
    public static int AIR_BLOCK = 32;
}
