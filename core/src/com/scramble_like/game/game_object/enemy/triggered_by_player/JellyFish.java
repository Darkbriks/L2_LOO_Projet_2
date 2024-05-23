package com.scramble_like.game.game_object.enemy.triggered_by_player;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class JellyFish extends PlayerDeclanchedEnemy
{
    public JellyFish(Scene scene, float x, float y) throws SceneIsNullException
    {
        super("JellyFish", scene, GameConstant.CHARACTER_PATH("Fish", "Jellyfish"), 5, 0, new int[]{ 4, 4, 2, 4, 6 }, new Vector2[]{}, 100, 0, new Vector2(75, 750));
        this.collisionDamage = 15;
        this.getTransform().setLocation(x, y);
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.enemyController.setWaypoints(new Vector2[]{ this.getTransform().getLocation(), new Vector2(this.getTransform().getLocation().x, this.getTransform().getLocation().y + 1000) });
        this.enemyController.SetActive(false);
    }

    @Override
    protected void Triggered(EventBeginOverlap e)
    {
        this.enemyController.SetActive(true);
        this.animationController.setState(AnimationController.AnimationState.WALK, -1);
    }
}
