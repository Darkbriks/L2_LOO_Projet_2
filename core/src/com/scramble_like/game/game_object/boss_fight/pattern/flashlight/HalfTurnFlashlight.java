package com.scramble_like.game.game_object.boss_fight.pattern.flashlight;

public class HalfTurnFlashlight extends FlashlightPattern
{
    public HalfTurnFlashlight()
    {
        super(1f, 0.1f, 6, 200, 750);
    }

    public HalfTurnFlashlight(boolean reverse)
    {
        super(1f, 0.1f, 6, 200, 750, reverse);
    }
}
