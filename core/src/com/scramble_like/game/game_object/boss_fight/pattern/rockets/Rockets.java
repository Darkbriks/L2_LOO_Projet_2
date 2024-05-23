package com.scramble_like.game.game_object.boss_fight.pattern.rockets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.projectiles.Rocket;
import com.scramble_like.game.map.AbstractLevel;

public abstract class Rockets implements Pattern
{
    protected Vector2[] rocketsOffset;
    protected Vector2[] rocketsDirection;
    protected float duration = 3;

    public Rockets(Vector2[] rocketsOffset, Vector2[] rocketsDirection, float duration)
    {
        this.rocketsOffset = rocketsOffset;
        this.rocketsDirection = rocketsDirection;
        this.duration = duration;
    }

    @Override
    public void Start(AbstractLevel level, Boss boss)
    {
        try
        {
            for (int i = 0; i < rocketsOffset.length; i++)
            {
                Rocket rocket = new Rocket(level, boss.getTransform().getLocation().cpy().add(rocketsOffset[i]), rocketsDirection[i], 500, 250);
                level.AddGameObject(rocket);
            }
        }
        catch (Exception e) {  Gdx.app.error("Rockets", "Error while creating rockets: " + e.getMessage());}
    }

    @Override
    public void Stop() {}

    @Override
    public float GetDuration() { return duration; }
}
