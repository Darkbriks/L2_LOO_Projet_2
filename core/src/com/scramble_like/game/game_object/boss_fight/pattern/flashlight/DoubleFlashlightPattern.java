package com.scramble_like.game.game_object.boss_fight.pattern.flashlight;

import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.map.AbstractLevel;

public class DoubleFlashlightPattern implements Pattern
{
    HalfTurnFlashlight flashlight_1;
    HalfTurnFlashlight flashlight_2;

    public DoubleFlashlightPattern()
    {
        flashlight_1 = new HalfTurnFlashlight(true);
        flashlight_2 = new HalfTurnFlashlight();
    }

    @Override
    public void Start(AbstractLevel level, Boss boss)
    {
        flashlight_1.Start(level, boss);
        flashlight_2.Start(level, boss);
    }

    @Override
    public void Stop()
    {
        flashlight_1.Stop();
        flashlight_2.Stop();
    }

    @Override
    public float GetDuration()
    {
        return flashlight_1.duration;
    }
}
