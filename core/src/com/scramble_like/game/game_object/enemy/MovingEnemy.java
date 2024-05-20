package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.EnemyController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class MovingEnemy extends Enemy
{
    protected Vector2[] waypoints;
    protected float movementSpeed;

    public MovingEnemy(String name, Scene scene, String spritePath) throws SceneIsNullException
    {
        super(name, scene, spritePath);
        this.waypoints = null;
        this.movementSpeed = 100;
    }

    public MovingEnemy(String name, Scene scene, String spritePath, float shootSpeed) throws SceneIsNullException
    {
        super(name, scene, spritePath, shootSpeed);
        this.waypoints = null;
        this.movementSpeed = 100;
    }

    public MovingEnemy(String name, Scene scene, String spritePath, float shootSpeed, Vector2[] waypoints, float movementSpeed) throws SceneIsNullException
    {
        super(name, scene, spritePath, shootSpeed);
        System.out.println("MovingEnemy created");
        this.waypoints = waypoints;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        if (waypoints == null) { this.AddComponent(new EnemyController(2, 100, movementSpeed)); }
        else { this.AddComponent(new EnemyController(waypoints, movementSpeed)); }

    }
}
