package com.scramble_like.game.component;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.projectiles.Projectile;

public class Shoot extends Component
{
    private GameObject target;
    private float timeBetweenShoots = 1;
    private float timeSinceLastShoot = 0;

    public Shoot(float timeBetweenShoots, GameObject target)
    {
        super();
        this.timeBetweenShoots = timeBetweenShoots;
        this.timeSinceLastShoot = 0;
        this.target = target;
    }

    public void shoot()
    {
        try
        {
            Projectile projectile = new Projectile("projectile", this.Owner.getScene(), this.target);
            this.Owner.getScene().AddGameObject(projectile);
            projectile.getTransform().setLocation(this.Owner.getTransform().getLocation());
        }
        catch (SceneIsNullException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public void Update(double DeltaTime) {

        timeSinceLastShoot += (float) DeltaTime;
        if (timeSinceLastShoot >= timeBetweenShoots)
        {
            shoot();
            timeSinceLastShoot = 0;
        }
    }
}