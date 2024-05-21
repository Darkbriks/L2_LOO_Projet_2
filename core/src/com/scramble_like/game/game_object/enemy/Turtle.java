package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Turtle extends MovingEnemy
{
    public Turtle(String name, Scene scene, Vector2[] waypoints) throws SceneIsNullException
    {
        super(name, scene, "Characters/Fish/Turtle/Walk.png", 0, waypoints, 100);
        System.out.println("Turtle created");
    }
}
