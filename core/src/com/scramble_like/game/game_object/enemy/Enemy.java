package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.EnemyController;
import com.scramble_like.game.component.controller.FireController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.projectiles.SimpleBullet;

public class Enemy extends GameObject
{
    protected float shootSpeed;
    protected String spriteFolderPath;
    protected Flipbook flipbook;
    protected AnimationController animationController;
    protected int[] animationFrames;
    protected Vector2[] waypoints;
    protected float movementSpeed;

    public Enemy(String name, Scene scene, String spriteFolderPath, float shootSpeed, int[] animationFrames, Vector2[] waypoints, float movementSpeed) throws SceneIsNullException
    {
        super(name, scene);
        this.spriteFolderPath = spriteFolderPath;
        this.shootSpeed = shootSpeed;
        this.animationFrames = animationFrames;
        this.waypoints = waypoints;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.AddComponent(new AABBCollider(50, 50, true, true));

        this.flipbook = new Flipbook(spriteFolderPath + "/Idle.png", 4);
        this.AddComponent(flipbook);

        this.animationController = new AnimationController(spriteFolderPath, flipbook, animationFrames);
        this.AddComponent(animationController);

        this.AddComponent(new EnemyController(waypoints, movementSpeed));

        if (shootSpeed != 0)
        {
            FireController fireController = new FireController(shootSpeed, SimpleBullet.class);
            this.AddComponent(fireController);
        }
    }
}