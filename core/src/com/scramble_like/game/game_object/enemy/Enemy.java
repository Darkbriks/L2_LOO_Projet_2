package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.controller.FireController;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.projectiles.SimpleBullet;

public class Enemy extends GameObject
{
    protected float shootSpeed;
    protected String spritePath;

    protected int count;

    public Enemy(String name, Scene scene, String spritePath) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = 0;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        count=0;
    }

    public Enemy(String name, Scene scene, String spritePath, int count) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = 0;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        this.count=count;
    }

    public Enemy(String name, Scene scene, String spritePath, float shootSpeed) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = shootSpeed;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        this.count=0;
    }

    public Enemy(String name, Scene scene, String spritePath, float shootSpeed, int count) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = shootSpeed;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        this.count=count;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.AddComponent(new AABBCollider(50, 50, true, true));
        if(count!=0){
            this.AddComponent(new Flipbook(spritePath,count));
        }
        else{
            this.AddComponent(new Sprite(spritePath));
        }

        if (shootSpeed != 0)
        {
            FireController fireController = new FireController(shootSpeed, SimpleBullet.class);
            this.AddComponent(fireController);
        }
    }
}
