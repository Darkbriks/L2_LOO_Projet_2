package com.scramble_like.game.game_object.boss_fight.pattern.flashlight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.ScrambleLikeApplication;
import com.scramble_like.game.essential.TickableObject;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.projectiles.Flashlight;
import com.scramble_like.game.map.AbstractLevel;

public abstract class FlashlightPattern implements Pattern, TickableObject
{
    protected float angle = 0;
    protected int numberOfTurns = 1;
    protected float timeBetweenFires = 0.25f;
    protected float duration = 3;
    protected float speed = 350;
    protected float range = 500;
    protected boolean isRunning = false;
    protected float elapsedTime = 0;
    protected float timeSinceLastFire = 0;
    private AbstractLevel level;
    private Boss boss;

    public FlashlightPattern(int numberOfTurns, float timeBetweenFires, float duration, float speed, float range)
    {
        this.numberOfTurns = numberOfTurns;
        this.timeBetweenFires = timeBetweenFires;
        this.duration = duration;
        this.speed = speed;
        this.range = range;
        this.isRunning = false;
        ScrambleLikeApplication.getInstance().AddTickableObject(this);
    }

    @Override
    public void Start(AbstractLevel level, Boss boss)
    {
        this.isRunning = true;
        this.level = level;
        this.boss = boss;
        this.elapsedTime = 0;
        this.timeSinceLastFire = 0;
    }

    @Override
    public void Stop() { this.isRunning = false; }

    @Override
    public void Tick(float DeltaTime)
    {
        if (isRunning)
        {
            elapsedTime += DeltaTime;
            timeSinceLastFire += DeltaTime;
            if (elapsedTime >= duration) { Stop(); elapsedTime = 0; }
            else
            {
                if (timeSinceLastFire >= timeBetweenFires)
                {
                    try
                    {
                        timeSinceLastFire = 0;
                        Flashlight flashlight = new Flashlight(level, boss.getTransform().getLocation().cpy(), new Vector2((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))), range, speed);
                        level.AddGameObject(flashlight);
                    }
                    catch (Exception e) { Gdx.app.error("FlashlightPattern", "Error while creating rockets: " + e.getMessage()); }
                }
                float alpha = elapsedTime / duration;
                angle = 360 * alpha * numberOfTurns;
            }
        }
    }

    @Override
    public float GetDuration() { return duration; }
}
