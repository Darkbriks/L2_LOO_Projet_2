package com.scramble_like.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;

public class Player extends Component {
    private float speed;

    public Player(float speed) {
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

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {newX += speed * DeltaTime;}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {newX -= speed * DeltaTime;}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {newY += speed * DeltaTime;}
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {newY -= speed * DeltaTime;}

        owner.getTransform().getLocation().x = newX;
        owner.getTransform().getLocation().y = newY;
    }
}

