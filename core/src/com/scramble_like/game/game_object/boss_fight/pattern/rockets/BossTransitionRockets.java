package com.scramble_like.game.game_object.boss_fight.pattern.rockets;

import com.badlogic.gdx.math.Vector2;

public class BossTransitionRockets extends Rockets
{
    public BossTransitionRockets()
    {
        super(new Vector2[] {
                        new Vector2(-200, -400),
                        new Vector2(-200, -200),
                        new Vector2(-200, 0),
                        new Vector2(-200, 200),
                        new Vector2(-200, 400)
                },
                new Vector2[] {
                        new Vector2(-1, 1f),
                        new Vector2(-1, 0.5f),
                        new Vector2(-1, 0),
                        new Vector2(-1, -0.5f),
                        new Vector2(-1, -1f)
                }, 1, 750, 1000);
    }
}
