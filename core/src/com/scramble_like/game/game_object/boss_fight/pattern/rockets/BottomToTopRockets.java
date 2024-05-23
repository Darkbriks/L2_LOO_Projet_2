package com.scramble_like.game.game_object.boss_fight.pattern.rockets;

import com.badlogic.gdx.math.Vector2;

public class BottomToTopRockets extends Rockets
{
    public BottomToTopRockets()
    {
        super(new Vector2[] {
                        new Vector2(-1100, -500),
                        new Vector2(-1000, -500),
                        new Vector2(-900, -500),
                        new Vector2(-800, -500),
                        new Vector2(-700, -500),
                        new Vector2(-600, -500),
                        new Vector2(-500, -500),
                        new Vector2(400, -500),
                        new Vector2(300, -500),
                        new Vector2(200, -500),
                        new Vector2(100, -500),
                        new Vector2(0, -500),
                        new Vector2(100, -500),
                        new Vector2(-200, -500),
                        new Vector2(-300, -500)
                },
                new Vector2[] {
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2),
                        new Vector2(-0.25f, 2),
                        new Vector2(0.25f, 2)
                }, 1, 750, 1500);
    }
}