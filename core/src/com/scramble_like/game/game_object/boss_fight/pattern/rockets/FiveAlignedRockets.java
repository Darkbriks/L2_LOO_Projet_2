package com.scramble_like.game.game_object.boss_fight.pattern.rockets;

import com.badlogic.gdx.math.Vector2;

public class FiveAlignedRockets extends Rockets
{
    public FiveAlignedRockets()
    {
        super(new Vector2[] {
          new Vector2(-100, 100),
          new Vector2(-100, 50),
          new Vector2(-100, 0),
          new Vector2(-100, -50),
          new Vector2(-100, -100)
        },
        new Vector2[] {
        new Vector2(-1, 0),
        new Vector2(-1, 0),
        new Vector2(-1, 0),
        new Vector2(-1, 0),
        new Vector2(-1, 0)
        }, 1, 400, 750);
    }
}
