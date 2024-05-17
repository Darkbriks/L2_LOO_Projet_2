package com.scramble_like.game.game_object.enemy;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.Shoot;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Enemy extends GameObject
{
    protected boolean canShoot;
    protected float shootSpeed;
    protected GameObject target;
    protected String spritePath;

    public Enemy(String name, Scene scene, String spritePath) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = 100;
        this.canShoot = false;
        this.target = null;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
    }

    public Enemy(String name, Scene scene, String spritePath,  float shootSpeed, boolean canShoot, GameObject target) throws SceneIsNullException
    {
        super(name, scene);
        this.spritePath = spritePath;
        this.shootSpeed = shootSpeed;
        this.canShoot = canShoot;
        this.target = target;
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.AddComponent(new AABBCollider(100, 100, false, true));
        this.AddComponent(new Sprite(spritePath));

        if (canShoot)
        {
            Shoot shootComponent = new Shoot(shootSpeed, target);
            this.AddComponent(shootComponent);
        }
    }
}
