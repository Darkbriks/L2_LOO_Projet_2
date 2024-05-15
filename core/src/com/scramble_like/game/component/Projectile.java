package com.scramble_like.game.component;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;

public class Projectile extends Component {
    private float speed;

    public Projectile(float speed) {
        super();
        this.speed = speed;
    }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    @Override
    public void Update(double DeltaTime) {
        GameObject owner = getOwner();
        float newX = owner.getTransform().getLocation().x + speed * (float)DeltaTime;
        owner.getTransform().getLocation().x = newX;

    }
}
