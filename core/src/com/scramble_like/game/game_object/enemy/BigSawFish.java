package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class BigSawFish extends Enemy
{
    public BigSawFish(String name, Scene scene, Vector2[] waypoints) throws SceneIsNullException
    {
        super(name, scene, GameConstant.CHARACTER_PATH("Fish", "BigSawFish"), 20, 0, new int[]{ 4, 4, 2, 6, 6 }, waypoints, 150, 2);
        this.enemyController.setInterpolation(Interpolation.exp5, Interpolation.exp5);
    }
}

