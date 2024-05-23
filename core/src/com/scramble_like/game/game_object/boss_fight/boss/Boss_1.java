package com.scramble_like.game.game_object.boss_fight.boss;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveAlignedRockets;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveCenteredRockets;

public class Boss_1 extends Boss
{
    public Boss_1(Scene scene) throws SceneIsNullException
    {
        super("Boss_1",
                scene,
                500,
                new Pattern[] { new FiveAlignedRockets(), new FiveCenteredRockets() },
                2,
                new Sprite("Characters/Boss/Ship6/Ship6.png"),
                new AABBCollider(350, 175, false, true),
                null);
    }
}