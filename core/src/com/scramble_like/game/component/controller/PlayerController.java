package com.scramble_like.game.component.controller;

import com.badlogic.gdx.controllers.Controllers;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.PlayerDieEvent;
import com.scramble_like.game.essential.utils.Utils;
import com.scramble_like.game.map.AbstractLevel;

public class PlayerController extends CharacterController
{
    private float hitCooldown;
    private float hitCooldownTimer;
    private int score;
    private GameCamera camera;
    private boolean inverseScroll; // false, scroll is horizontal, true, scroll is vertical

    public PlayerController(AnimationController animationController, AABBCollider collider)
    {
        super(animationController, collider, GameConstant.PLAYER_SPEED, GameConstant.PLAYER_LIFE);
        this.inverseScroll = false;
    }

    public boolean isInverseScroll() { return inverseScroll; }
    public void setInverseScroll(boolean inverseScroll) { this.inverseScroll = inverseScroll; }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        camera = this.getOwner().getScene().getGame().getCamera();
        hitCooldown = 0;
        hitCooldownTimer = 0;
        score = 0;
    }

    public int getScore() { return score; }

    @Override
    public void takeDamage(int damage, float hitCooldown)
    {
        if(hitCooldownTimer >= this.hitCooldown)
        {
            super.takeDamage(damage, hitCooldown);
            hitCooldownTimer = 0;
            this.hitCooldown = hitCooldown;
            this.score = Utils.clamp(this.score - GameConstant.SCORE_LOST_ON_HIT, 0, Integer.MAX_VALUE);
            if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); } }
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }

        this.score+=5;
        hitCooldownTimer += DeltaTime;

        scroll(DeltaTime);
        move(DeltaTime);

        super.Update(DeltaTime);
    }

    private void scroll(float dt)
    {
        if (inverseScroll)
        {
            camera.addPosition(0, dt * GameConstant.CAMERA_SPEED);
            this.getOwner().getTransform().Translate(0, dt * GameConstant.CAMERA_SPEED);
        }
        else
        {
            camera.addPosition(dt * GameConstant.CAMERA_SPEED, 0);
            this.getOwner().getTransform().Translate(dt * GameConstant.CAMERA_SPEED, 0);
        }
    }

    private void move(float dt)
    {
        this.dx = GameConstant.X_AXIS_VALUE;
        this.dy = GameConstant.Y_AXIS_VALUE;

        if (Math.abs(dx) < 0.1 && Math.abs(dy) < 0.1) { dx = 0; dy = 0; }
        else { dx *= speed * dt; dy *= -speed * dt; }

        if (dx == 0 && dy == 0)
        {
            if (GameConstant.KeyIsPressed(GameConstant.MOVE_RIGHT)) { dx = speed * dt; }
            else if (GameConstant.KeyIsPressed(GameConstant.MOVE_LEFT)) { dx = -speed * dt; }
            if (GameConstant.KeyIsPressed(GameConstant.MOVE_UP)) { dy = speed * dt; }
            else if (GameConstant.KeyIsPressed(GameConstant.MOVE_DOWN)) { dy = -speed * dt; }
        }

        if (dx != 0 || dy != 0)
        {
            if (animationController.getState() != AnimationController.AnimationState.WALK)
            {
                animationController.setState(AnimationController.AnimationState.WALK, -1);
                collider.setWidth(GameConstant.PLAYER_WALK_COLLIDER.x);
                collider.setHeight(GameConstant.PLAYER_WALK_COLLIDER.y);
                collider.setxOffSet(GameConstant.PLAYER_WALK_COLLIDER.z);
                collider.setyOffSet(GameConstant.PLAYER_WALK_COLLIDER.w);
            }

            this.getOwner().getTransform().getLocation().x = (float) Utils.clamp(this.getOwner().getTransform().getLocation().x + dx, camera.getPosition().x - (double) GameConstant.LEFT_LIMIT, camera.getPosition().x + (double) GameConstant.RIGHT_LIMIT);
            this.getOwner().getTransform().getLocation().y = (float) Utils.clamp(this.getOwner().getTransform().getLocation().y + dy, camera.getPosition().y - (double) GameConstant.BOTTOM_LIMIT, camera.getPosition().y + (double) GameConstant.TOP_LIMIT);

            if (!inverseScroll && dy != 0)
            {
                if (this.getOwner().getTransform().getLocation().y < GameConstant.BOTTOM_SCROLL_LIMIT) { camera.setY(this.getOwner().getTransform().getLocation().y - GameConstant.BOTTOM_SCROLL_LIMIT); }
                else if (this.getOwner().getTransform().getLocation().y > GameConstant.TOP_SCROLL_LIMIT) { camera.setY(this.getOwner().getTransform().getLocation().y - GameConstant.TOP_SCROLL_LIMIT); }
                else { camera.getPosition().y = 0; }
            }
            else if (inverseScroll && dx != 0)
            {
                if (this.getOwner().getTransform().getLocation().x < GameConstant.LEFT_SCROLL_LIMIT) { camera.setX(this.getOwner().getTransform().getLocation().x - GameConstant.LEFT_SCROLL_LIMIT); }
                else if (this.getOwner().getTransform().getLocation().x > GameConstant.RIGHT_SCROLL_LIMIT) { camera.setX(this.getOwner().getTransform().getLocation().x - GameConstant.RIGHT_SCROLL_LIMIT); }
                else { camera.getPosition().x = 0; }
            }
        }
        else if (animationController.getState() != AnimationController.AnimationState.IDLE)
        {
            animationController.setState(AnimationController.AnimationState.IDLE, -1);
            collider.setWidth(GameConstant.PLAYER_IDLE_COLLIDER.x);
            collider.setHeight(GameConstant.PLAYER_IDLE_COLLIDER.y);
            collider.setxOffSet(GameConstant.PLAYER_IDLE_COLLIDER.z);
            collider.setyOffSet(GameConstant.PLAYER_IDLE_COLLIDER.w);
        }

        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        this.getOwner().getTransform().setRotation(angle, 0);
    }

    @Override
    protected void die()
    {
        super.die();
        this.getOwner().getScene().getEventDispatcher().DispatchEvent(EventIndex.DIE,new PlayerDieEvent(this.getOwner()));
        AbstractLevel level = (AbstractLevel) this.getOwner().getScene(); level.GameOver(score);
    }
}