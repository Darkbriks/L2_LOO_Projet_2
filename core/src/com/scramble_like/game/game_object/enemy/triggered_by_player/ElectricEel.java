package com.scramble_like.game.game_object.enemy.triggered_by_player;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.AnimationController;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class ElectricEel extends PlayerDeclanchedEnemy
{
    protected boolean isTriggered = false;
    protected boolean isAttacking = false;
    protected float elapsedTime = 0;
    protected float timeBeforeAttack = 0.5f;
    protected float timeOfAttack = 1.2f;

    public ElectricEel(Scene scene, Vector2[] waiponts) throws SceneIsNullException
    {
        super("ElectricEel", scene, GameConstant.CHARACTER_PATH("Fish", "ElectricEel"), 20, 0, new int[]{4, 6, 2, 6, 6}, waiponts, 100, 0, new Vector2(500, 500));
        this.collisionDamage = 10;
        this.collider.setHeight(25);
        this.collider.setWidth(50);
    }

    protected void startAttack()
    {
        this.animationController.setState(AnimationController.AnimationState.ATTACK, 2);
        this.collisionDamage = 30;
        this.collider.setHeight(this.collider.getHeight() * 6);
        this.collider.setWidth(this.collider.getWidth() * 6);
    }

    protected void stopAttack()
    {
        this.animationController.setState(AnimationController.AnimationState.IDLE, -1);
        this.collisionDamage = 10;
        this.collider.setHeight(this.collider.getHeight() / 6);
        this.collider.setWidth(this.collider.getWidth() / 6);
        this.enemyController.SetActive(true);
    }

    @Override
    protected void Triggered(EventBeginOverlap e)
    {
        this.enemyController.SetActive(false);
        isTriggered = true;
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        if (isTriggered && !isAttacking)
        {
            elapsedTime += DeltaTime;
            if (elapsedTime >= timeBeforeAttack) { isAttacking = true; elapsedTime = 0; startAttack(); }
        }
        if (isAttacking)
        {
            elapsedTime += DeltaTime;
            if (elapsedTime >= timeOfAttack) { isAttacking = false; isTriggered = false; elapsedTime = 0; stopAttack(); }
        }
    }
}