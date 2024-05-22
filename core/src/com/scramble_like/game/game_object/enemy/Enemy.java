package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.EnemyController;
import com.scramble_like.game.component.controller.FireController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chunk.ChunkManager;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.projectiles.Projectile;
import com.scramble_like.game.game_object.projectiles.SimpleBullet;

import java.util.EventObject;

public class Enemy extends GameObject
{
    protected float shootSpeed;
    protected AABBCollider collider;
    protected Flipbook flipbook;
    protected AnimationController animationController;
    protected EnemyController enemyController;

    public Enemy(String name, Scene scene, String spriteFolderPath, int life, float shootSpeed, int[] animationFrames, Vector2[] waypoints, float movementSpeed) throws SceneIsNullException
    {
        super(name, scene);
        this.shootSpeed = shootSpeed;

        collider = new AABBCollider(50, 50, true, true);
        this.AddComponent(collider);

        this.flipbook = new Flipbook(spriteFolderPath + "/Idle.png", 4);
        this.AddComponent(flipbook);

        this.animationController = new AnimationController(spriteFolderPath, flipbook, animationFrames);
        this.AddComponent(animationController);

        this.enemyController = new EnemyController(animationController, collider, movementSpeed, life, waypoints);
        this.AddComponent(enemyController);

        if (shootSpeed != 0)
        {
            FireController fireController = new FireController(shootSpeed, SimpleBullet.class);
            this.AddComponent(fireController);
        }
    }
}