package com.scramble_like.game.component.controller;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.Collider;
import com.scramble_like.game.essential.factory.SoundFactory;

public class CharacterController extends Component
{
    protected final AnimationController animationController;
    protected final AABBCollider collider;
    protected final float speed;
    protected int life;
    protected float dx, dy;

    public CharacterController(AnimationController animationController, AABBCollider collider, float speed, int life)
    {
        super();
        this.animationController = animationController;
        this.collider = collider;
        this.speed = speed;
        this.life = life;
    }

    public AnimationController getAnimationController() { return animationController; }
    public AABBCollider getCollider() { return collider; }
    public float getSpeed() { return speed; }
    public int getLife() { return life; }

    public float getDx() { return this.dx; }
    public float getDy() { return this.dy; }
    public void setDx(float dx) { this.dx = dx; }
    public void setDy(float dy) { this.dy = dy; }

    public boolean isAlive() { return life > 0; }

    public void takeDamage(int damage, float hitCooldown)
    {
        animationController.setState(AnimationController.AnimationState.HURT, 1);
        life -= damage;
        SoundFactory.getInstance().playSound("takeDamage",GameConstant.SOUND_EFFECT_VOLUME);
    }

    protected void die()
    {
        SoundFactory.getInstance().playSound("dead", GameConstant.SOUND_EFFECT_VOLUME);
        animationController.setState(AnimationController.AnimationState.DIE, 1);
    }

    @Override
    public void Update(float DeltaTime)
    {
        this.getOwner().GetFirstComponentFromClass(Collider.class).setPositionInGrid();

        if(!this.isAlive()) { this.die(); }
    }
}
