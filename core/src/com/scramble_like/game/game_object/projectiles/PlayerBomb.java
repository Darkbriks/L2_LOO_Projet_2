package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class PlayerBomb extends Projectile
{
    public PlayerBomb(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("Player Bomb", scene, location, new Vector2(0.5f, -2.5f), 750, 300, true);
        this.damage = 1000;
        this.AddComponent(new Sprite("Tileset/utile/étoile.png"));
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.GetFirstComponentFromClass(AABBCollider.class).setHeight(30);
        this.GetFirstComponentFromClass(AABBCollider.class).setWidth(30);
        this.GetFirstComponentFromClass(ProjectileController.class).setInterpolation(Interpolation.exp10Out, Interpolation.exp5);
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        this.projectileController.setStart(this.projectileController.getStart().add(GameConstant.CAMERA_SPEED * GameConstant.CAMERA_SPEED_MULTIPLIER * DeltaTime, 0).cpy());
        this.projectileController.setEnd(this.projectileController.getEnd().add(GameConstant.CAMERA_SPEED * GameConstant.CAMERA_SPEED_MULTIPLIER * DeltaTime, 0).cpy());
    }
}
