package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.game_object.projectiles.Projectile;

public class FireController extends Component
{
    protected float timeBetweenShoots;
    protected float elapsedTime;
    protected boolean inputIsRequiered;
    protected boolean isSecondaryFire;
    protected Class<? extends Projectile> projectile;

    public FireController(float timeBetweenShoots, Class<? extends Projectile> projectile)
    {
        super();
        this.timeBetweenShoots = timeBetweenShoots;
        this.elapsedTime = 0;
        this.inputIsRequiered = false;
        this.isSecondaryFire = false;
        this.projectile = projectile;
    }

    public FireController(float timeBetweenShoots, boolean inputIsRequiered, Class<? extends Projectile> projectile)
    {
        super();
        this.timeBetweenShoots = timeBetweenShoots;
        this.elapsedTime = 0;
        this.inputIsRequiered = inputIsRequiered;
        this.projectile = projectile;
    }

    public FireController(float timeBetweenShoots, boolean inputIsRequiered, boolean isSecondaryFire, Class<? extends Projectile> projectile)
    {
        super();
        this.timeBetweenShoots = timeBetweenShoots;
        this.elapsedTime = 0;
        this.inputIsRequiered = inputIsRequiered;
        this.isSecondaryFire = isSecondaryFire;
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
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }

        this.elapsedTime += DeltaTime;
        if (this.elapsedTime >= this.timeBetweenShoots)
        {
            if (inputIsRequiered && !isSecondaryFire && !(GameConstant.KeyIsPressed(GameConstant.SHOOT) || GameConstant.SHOOT_BUTTON)) { return; }
            if (inputIsRequiered && isSecondaryFire && !(GameConstant.KeyIsPressed(GameConstant.SECONDARY_SHOOT) || GameConstant.SECONDARY_SHOOT_BUTTON)) { return; }
            this.spawnProjectile();
            this.elapsedTime = 0;
        }
    }
}