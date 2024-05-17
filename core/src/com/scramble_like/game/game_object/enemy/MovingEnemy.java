package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.EnemiePatrol;
import com.scramble_like.game.essential.GameObject;
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

    public MovingEnemy(String name, Scene scene, String spritePath, float shootSpeed, boolean canShoot, GameObject target) throws SceneIsNullException
    {
        super(name, scene, spritePath, shootSpeed, canShoot, target);
        this.waypoints = null;
        this.movementSpeed = 100;
    }

    public MovingEnemy(String name, Scene scene, String spritePath, float shootSpeed, boolean canShoot, GameObject target, Vector2[] waypoints, float movementSpeed) throws SceneIsNullException
    {
        super(name, scene, spritePath, shootSpeed, canShoot, target);
        this.waypoints = waypoints;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();

        if (waypoints == null) { this.AddComponent(new EnemiePatrol(2, 100, movementSpeed)); }
        else { this.AddComponent(new EnemiePatrol(waypoints, movementSpeed)); }

    }
}
