package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.essential.GameObject;

public class Shoot extends Projectile{
    private GameObject target;

    public Shoot(float speed, GameObject target) {
        super(speed);
        this.target = target;
    }

    @Override
    public void Update(double DeltaTime) {
        GameObject owner = getOwner();
        Vector3 direction = new Vector3(target.getTransform().getLocation().x - owner.getTransform().getLocation().x,
                target.getTransform().getLocation().y - owner.getTransform().getLocation().y,
                target.getTransform().getLocation().z - owner.getTransform().getLocation().z);
        direction.nor();

        float newX = owner.getTransform().getLocation().x + direction.x * getSpeed() * (float)DeltaTime;
        float newY = owner.getTransform().getLocation().y + direction.y * getSpeed() * (float)DeltaTime;
        owner.getTransform().getLocation().x = newX;
        owner.getTransform().getLocation().y = newY;
    }
}