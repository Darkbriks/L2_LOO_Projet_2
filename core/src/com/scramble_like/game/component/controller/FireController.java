package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.game_object.projectiles.Projectile;

public class FireController extends Component
{
    protected float timeBetweenShoots;
    protected float elapsedTime;
    protected Class<? extends Projectile> projectile;

    public FireController(float timeBetweenShoots, Class<? extends Projectile> projectile)
    {
        super();
        this.timeBetweenShoots = timeBetweenShoots;
        this.elapsedTime = 0;
        this.projectile = projectile;
    }

    public void spawnProjectile()
    {
        try
        {
            Projectile projectile = this.projectile.getConstructor(Scene.class, Vector2.class).newInstance(this.getOwner().getScene(), this.getOwner().getTransform().getLocation().cpy());
            this.Owner.getScene().AddGameObject(projectile);
        }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        this.elapsedTime += (float) DeltaTime;
        if (this.elapsedTime >= this.timeBetweenShoots)
        {
            this.spawnProjectile();
            this.elapsedTime = 0;
        }
    }
}