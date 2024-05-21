package com.scramble_like.game.component.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Flipbook;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.chaos.Collider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.PlayerDieEvent;
import com.scramble_like.game.essential.utils.DebugRenderer;
import com.scramble_like.game.essential.utils.Utils;
import com.scramble_like.game.map.GameOver;

public class PlayerController extends Component
{
    private final AnimationController animationController;
    private final Flipbook flipbook;
    private final float speed;
    private float hitCooldown;
    private float hitCooldownTimer;
    private int life, score;
    private GameCamera camera;

    public PlayerController(AnimationController animationController, Flipbook flipbook)
    {
        super();
        this.animationController = animationController;
        this.flipbook = flipbook;
        this.speed = GameConstant.PLAYER_SPEED;
        life = GameConstant.PLAYER_LIFE;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        camera = this.getOwner().getScene().getGame().getCamera();
        hitCooldown = 0;
        hitCooldownTimer = 0;
        score = 0;
    }

    public int getScore(){ return score; }
    public float getSpeed() { return speed; }
    public int getLife() { return life; }

    public boolean isAlive() { return life > 0; }

    public void takeDamage(int damage, float hitCooldown)
    {
        animationController.setState(AnimationController.AnimationState.HURT, 1);
        if(hitCooldownTimer >= this.hitCooldown) { life -= damage; hitCooldownTimer = 0; this.hitCooldown = hitCooldown; if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); } }
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        this.score+=5;
        hitCooldownTimer += DeltaTime;

        scroll(DeltaTime);
        move(DeltaTime);

        this.getOwner().GetFirstComponentFromClass(Collider.class).setPositionInGrid();

        if(!this.isAlive()) { this.die(); }
    }

    private void scroll(float dt)
    {
        camera.addPosition(dt * GameConstant.CAMERA_SPEED, 0);
        this.getOwner().getTransform().Translate(dt * GameConstant.CAMERA_SPEED, 0);
    }

    private void move(float dt)
    {
        Controller controller = Controllers.getCurrent();
        float dx = 0;
        float dy = 0;

        if (controller != null)
        {
            dx = controller.getAxis(controller.getMapping().axisLeftX);
            dy = -controller.getAxis(controller.getMapping().axisLeftY);
            if (Math.abs(dx) < 0.1) { dx = 0; } else { dx *= speed * dt; }
            if (Math.abs(dy) < 0.1) { dy = 0; } else { dy *= speed * dt; }
        }

        if (dx == 0 && dy == 0)
        {
            for (int key : GameConstant.MOVE_RIGHT) { if (Gdx.input.isKeyPressed(key)) { dx = speed * dt; break; } }
            for (int key : GameConstant.MOVE_LEFT) { if (Gdx.input.isKeyPressed(key)) { dx = -speed * dt; break; } }
            for (int key : GameConstant.MOVE_UP) { if (Gdx.input.isKeyPressed(key)) { dy = speed * dt; break; } }
            for (int key : GameConstant.MOVE_DOWN) { if (Gdx.input.isKeyPressed(key)) { dy = -speed * dt; break; } }
        }

        if (dx != 0 || dy != 0)
        {
            animationController.setState(AnimationController.AnimationState.WALK, -1);

            this.getOwner().getTransform().getLocation().x = (float) Utils.clamp(this.getOwner().getTransform().getLocation().x + dx, camera.getPosition().x - (double) GameConstant.LEFT_LIMIT, camera.getPosition().x + (double) GameConstant.RIGHT_LIMIT);
            this.getOwner().getTransform().getLocation().y = (float) Utils.clamp(this.getOwner().getTransform().getLocation().y + dy, camera.getPosition().y - (double) GameConstant.HEIGHT / 2, camera.getPosition().y + (double) GameConstant.HEIGHT / 2);

            if (dy != 0)
            {
                if (this.getOwner().getTransform().getLocation().y < GameConstant.BOTTOM_LIMIT) { camera.setY(this.getOwner().getTransform().getLocation().y - GameConstant.BOTTOM_LIMIT); }
                else if (this.getOwner().getTransform().getLocation().y > GameConstant.TOP_LIMIT) { camera.setY(this.getOwner().getTransform().getLocation().y - GameConstant.TOP_LIMIT); }
                else { camera.getPosition().y = 0; }
            }
        }
        else { animationController.setState(AnimationController.AnimationState.IDLE, -1); }

        //this.flipbook.setFlipX(dx < 0);

        //Vector2 newRotation = new Vector2(dx, dy).nor();
        //float angle = (float) Math.toDegrees(Math.atan2(newRotation.y, newRotation.x));
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        this.getOwner().getTransform().setRotation(angle, 0);
    }

    @Override
    public void Render()
    {
        DebugRenderer.DrawDebugCircle(this.getOwner().getTransform().getLocation(), 5, Color.CORAL, this.getOwner().getScene().getGame().getCamera().getCombined());
    }

    private void die()
    {
        this.getOwner().getScene().getEventDispatcher().DispatchEvent(EventIndex.DIE,new PlayerDieEvent(this.getOwner()));
        this.getOwner().getScene().getGame().setScreen(new GameOver(score));
        this.getOwner().getScene().dispose();
    }
}

