package com.scramble_like.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.scramble_like.game.GameConstant;
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

        if (newY > (float) -GameConstant.HEIGHT / 2 + 645) { newY = (float) -GameConstant.HEIGHT / 2 + 645; }
        if (newY > (float) -GameConstant.HEIGHT / 2 - 100) { newY = (float) -GameConstant.HEIGHT / 2 - 100; }

        System.out.println("after limit check, newX: " + newX);
        System.out.println("after limit check, newY: " + newY);
        owner.getTransform().getLocation().x = newX;
        owner.getTransform().getLocation().y = newY;


    }
}

