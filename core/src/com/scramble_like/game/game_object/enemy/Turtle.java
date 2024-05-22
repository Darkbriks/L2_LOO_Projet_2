package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Turtle extends Enemy
{
    public Turtle(Scene scene, Vector2[] waypoints) throws SceneIsNullException
    {
        super("Turtle", scene, GameConstant.CHARACTER_PATH("Fish", "Turtle"), 15, 0, new int[]{ 4, 6, 2, 6, 6 }, waypoints, 75, 1);
    }
}
