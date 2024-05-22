package com.scramble_like.game.essential;

public class CoreConstant
{
    // Rendering
    public static final int MIN_Z_INDEX = 0;
    public static final int MAX_Z_INDEX = 10;

    // Physics
    public static float UPDATE_MULTIPLIER = 1f;
    // 1 = Vitesse normale ; 2 = Vitesse x2 ; 0.5 = Vitesse / 2 ; 0 = Pause

    public static final int SPATIAL_GRID_SIZE = 250;
    public static final int SPATIAL_GRID_MIN_VALUE_AXIS = -1000;

    // Animation
    public static final float ANIMATION_FRAME_DURATION = 0.1f;

    // Chunk
    public static final int CHUNK_SIDE = 50;
    public static final int SQUARE_SIDE = 25;
    public static final int CHUNK_SIZE = CHUNK_SIDE * SQUARE_SIDE;
    public static final int SQUARED_LOADED_CHUNK_DISTANCE = 1500 * 1500; // In scene units. Squared number is used for performance reasons
    public static final int SQUARED_RENDERED_CHUNK_DISTANCE = 1425 * 1425; // In scene units. Squared number is used for performance reasons
    public static final int SQUARED_CHUNK_SIMULATING_DISTANCE = 1250 * 1250; // In scene units. Squared number is used for performance reasons
    public static final int CHUNKS_PROCESSED_PER_FRAME = 2;
}
