package com.scramble_like.game.game_object;

import com.scramble_like.game.component.Shoot;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.component.collider.AABBCollider;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.OwnerIsNullException;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Enemy extends GameObject {
    private AABBCollider collider;
    private Sprite sprite;
    private Shoot shootComponent;

    public Enemy(String name, Scene scene, float width, float height, boolean canShoot, GameObject target, String spritePath,float speed) throws SceneIsNullException
    {
        super(name, scene);

        this.collider = new AABBCollider(width, height,false, true);
        this.AddComponent(this.collider);

        this.sprite = new Sprite(spritePath);
        this.AddComponent(this.sprite);

        if (canShoot)
        {
            this.shootComponent = new Shoot(speed, target);
            this.AddComponent(this.shootComponent);
        }
    }

}
