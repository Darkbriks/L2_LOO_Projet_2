package com.scramble_like.game.game_object.boss_fight.pattern;

import com.scramble_like.game.game_object.boss_fight.boss.Boss;
import com.scramble_like.game.map.AbstractLevel;

public interface Pattern
{
    void Start(AbstractLevel level, Boss boss);
    void Stop();
    float GetDuration();
}
