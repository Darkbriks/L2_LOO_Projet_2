package com.scramble_like.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector4;
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
    public static final Vector4 PLAYER_IDLE_COLLIDER = new Vector4(30, 50, -7.5f, 0);
    public static final Vector4 PLAYER_WALK_COLLIDER = new Vector4(50, 50, 0, 0);

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

    // UI Position
    public static final int SCORE_X = WIDTH / 2 - 70;
    public static final int SCORE_Y = HEIGHT / 2 - 50;
    public static final int LIFE_X = - (WIDTH / 2 - 100);
    public static final int LIFE_Y = HEIGHT / 2 - 60;

    // Input
    public static final int TOGGLE_PAUSE = Input.Keys.ESCAPE;
    public static final int TOGGLE_DEBUG = Input.Keys.F1;
    public static final int TOGGLE_GOD_MODE = Input.Keys.F2;
    public static final int[] MOVE_UP = { Input.Keys.W, Input.Keys.UP };
    public static final int[] MOVE_DOWN = { Input.Keys.S, Input.Keys.DOWN };
    public static final int[] MOVE_LEFT = { Input.Keys.A, Input.Keys.LEFT };
    public static final int[] MOVE_RIGHT = { Input.Keys.D, Input.Keys.RIGHT };
    public static final int[] SHOOT = { Input.Keys.SPACE };

    public static boolean KeyIsPressed(int[] keys) { for (int key : keys) { if (Gdx.input.isKeyPressed(key)) { return true; } } return false; }

    // Gamepad Input
    public static float X_AXIS_VALUE = 0;
    public static float Y_AXIS_VALUE = 0;
    public static boolean SHOOT_BUTTON = false;
    public static boolean PAUSE_BUTTON = false;

    // Debug
    public static boolean DEBUG = true;
    public static boolean GOD_MODE = true;
}
