package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.ProjectileController;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class PlayerBomb extends Projectile
{
    public PlayerBomb(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("Player Bomb", scene, location, new Vector2(1, -4f), 750, 300, true);
        this.getTransform().setScale(0.1f, 0.1f);
        this.damage = 1000;
        this.AddComponent(new Sprite("badlogic.jpg"));
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.GetFirstComponentFromClass(ProjectileController.class).setInterpolation(Interpolation.circleOut, Interpolation.pow2OutInverse);
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        this.projectileController.setStart(this.projectileController.getStart().add(GameConstant.CAMERA_SPEED * DeltaTime, 0).cpy());
        this.projectileController.setEnd(this.projectileController.getEnd().add(GameConstant.CAMERA_SPEED * DeltaTime, 0).cpy());
    }
}
