package com.scramble_like.game.game_object.enemy.triggered_by_player;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class DeepDeaMonster extends PlayerDeclanchedEnemy
{
    public DeepDeaMonster(Scene scene, float x, float y) throws SceneIsNullException
    {
        super("DeepDeaMonster", scene, GameConstant.CHARACTER_PATH("Fish", "DeepDeaMonster"), 25, 0, new int[]{4, 4, 2, 6, 6}, new Vector2[]{}, 50, 0, new Vector2(150, 750));
        this.collisionDamage = 100;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.enemyController.setInterpolation(Interpolation.pow2In, Interpolation.pow2In);
        this.enemyController.setWaypoints(new Vector2[]{ this.getTransform().getLocation(), new Vector2(this.getTransform().getLocation().x - 1000, this.getTransform().getLocation().y + 1000) });
        this.enemyController.SetActive(false);
        this.animationController.setState(AnimationController.AnimationState.WALK, -1);
    }

    @Override
    protected void Triggered(EventBeginOverlap e)
    {
        this.enemyController.SetActive(true);
    }
}