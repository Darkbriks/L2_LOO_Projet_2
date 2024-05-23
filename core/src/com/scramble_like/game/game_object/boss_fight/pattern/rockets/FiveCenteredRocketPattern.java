package com.scramble_like.game.game_object.boss_fight.pattern.rockets;

import com.badlogic.gdx.math.Vector2;

public class FiveCenteredRocketPattern extends RocketPattern
{
    public FiveCenteredRocketPattern()
    {
        super(new Vector2[] {
                        new Vector2(-100, 30),
                        new Vector2(-100, 15),
                        new Vector2(-100, 0),
                        new Vector2(-100, -15),
                        new Vector2(-100, -30)
                },
                new Vector2[] {
                        new Vector2(-1, 0.50f),
                        new Vector2(-1, 0.25f),
                        new Vector2(-1, 0),
                        new Vector2(-1, -0.25f),
                        new Vector2(-1, -0.50f)
                }, 1, 300, 500);
    }
}
