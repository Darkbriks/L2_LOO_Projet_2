package com.scramble_like.game.component.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.PlayerDieEvent;
import com.scramble_like.game.essential.utils.Utils;
import com.scramble_like.game.map.GameOver;

public class PlayerController extends Component
{
    private final float speed;
    private float hitCooldown;
    private float hitCooldownTimer;
    private int life;
    private GameCamera camera;

    public PlayerController() { super(); this.speed = GameConstant.PLAYER_SPEED; life = GameConstant.PLAYER_LIFE; }

    @Override
    public void BeginPlay()
    {
        camera = this.getOwner().getScene().getGame().getCamera();
        hitCooldown = 0;
        hitCooldownTimer = 0;
    }

    public float getSpeed() { return speed; }
    public int getLife() { return life; }

    public boolean isAlive() { return life > 0; }

    public void takeDamage(int damage, float hitCooldown)
    {
        if(hitCooldownTimer >= this.hitCooldown) { life -= damage; hitCooldownTimer = 0; this.hitCooldown = hitCooldown; if (Controllers.getCurrent() != null) { Controllers.getCurrent().startVibration(100, 1); } }
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        hitCooldownTimer += (float) DeltaTime;

        scroll((float) DeltaTime);
        move((float) DeltaTime);

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
        float newX = this.getOwner().getTransform().getLocation().x;
        float newY = this.getOwner().getTransform().getLocation().y;
        float newX_Controller = newX;
        float newY_Controller = newY;
        boolean useController = false;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {newX += (speed * dt);}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {newX -= (speed * dt);}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {newY += (speed * dt);}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {newY -= (speed * dt);}

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { life -= 10; }

        if (controller != null && Math.abs(controller.getAxis(controller.getMapping().axisLeftX)) > 0.1f) { newX_Controller += (speed * dt * controller.getAxis(controller.getMapping().axisLeftX)); useController = true;}
        if (controller != null && Math.abs(controller.getAxis(controller.getMapping().axisLeftY)) > 0.1f) { newY_Controller -= (speed * dt * controller.getAxis(controller.getMapping().axisLeftY)); useController = true;}

        this.getOwner().getTransform().getLocation().x = (float) Utils.clamp(useController ? newX_Controller : newX, camera.getPosition().x - (double) GameConstant.WIDTH / 2, camera.getPosition().x +(double) GameConstant.WIDTH / 2);
        this.getOwner().getTransform().getLocation().y = (float) Utils.clamp(useController ? newY_Controller : newY, camera.getPosition().y - (double) GameConstant.HEIGHT / 2, camera.getPosition().y + (double) GameConstant.HEIGHT / 2);
    }

    private void die()
    {
        this.getOwner().getScene().getEventDispatcher().DispatchEvent(EventIndex.DIE,new PlayerDieEvent(this.getOwner()));
        this.getOwner().getScene().getGame().setScreen(new GameOver());
        this.getOwner().getScene().dispose();
    }
}

