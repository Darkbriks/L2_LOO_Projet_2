package com.scramble_like.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.PlayerDieEvent;
import com.scramble_like.game.essential.exception.GameIsNullException;
import com.scramble_like.game.essential.utils.Utils;
import com.scramble_like.game.map.GameOver;

public class PlayerController extends Component
{
    private final float speed;
    private int life;

    public PlayerController() { super(); this.speed = 500; life = 50;}
    public PlayerController(float speed) { super(); this.speed = speed; life = 50; }
    public PlayerController(float speed, int life) { super(); this.speed = speed; this.life = life; }

    public float getSpeed() { return speed; }
    public int getLife() { return life; }

    public boolean isAlive() { return life > 0; }

    public void takeDamage(int damage) { life -= damage; }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        float newX = this.getOwner().getTransform().getLocation().x;
        float newY = this.getOwner().getTransform().getLocation().y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {newX += (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {newX -= (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {newY += (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {newY -= (float) (speed * DeltaTime);}

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { life -= 10; }

        this.getOwner().getTransform().getLocation().x = (float) Utils.Clamp(newX, (double) -GameConstant.WIDTH / 2, (double) GameConstant.WIDTH / 2);
        this.getOwner().getTransform().getLocation().y = (float) Utils.Clamp(newY, (double) -GameConstant.HEIGHT / 2, (double) GameConstant.HEIGHT / 2);

        if(!this.isAlive())
        {
            this.getOwner().getScene().getEventDispatcher().DispatchEvent(EventIndex.DIE,new PlayerDieEvent(this.getOwner()));
            try { this.getOwner().getScene().getGame().setScreen(new GameOver(this.getOwner().getScene().getGame())); }
            catch (GameIsNullException e) { System.err.println(e.getMessage()); Gdx.app.exit(); }
        }
    }
}

