package com.scramble_like.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.utils.Utils;

public class PlayerController extends Component {
    private float speed;

    public PlayerController(float speed) {
        super();
        this.speed = speed;
    }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    @Override
    public void Update(double DeltaTime) {
        GameObject owner = getOwner();

        float newX = owner.getTransform().getLocation().x;
        float newY = owner.getTransform().getLocation().y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {newX += (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {newX -= (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {newY += (float) (speed * DeltaTime);}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {newY -= (float) (speed * DeltaTime);}

        owner.getTransform().getLocation().x = (float) Utils.Clamp(newX, (double) -GameConstant.WIDTH / 2, (double) GameConstant.WIDTH / 2);
        owner.getTransform().getLocation().y = (float) Utils.Clamp(newY, (double) -GameConstant.HEIGHT / 2, (double) GameConstant.HEIGHT / 2);
    }
}

