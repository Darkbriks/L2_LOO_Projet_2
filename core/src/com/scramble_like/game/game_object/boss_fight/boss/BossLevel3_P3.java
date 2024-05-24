package com.scramble_like.game.game_object.boss_fight.boss;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.boss_fight.pattern.flashlight.*;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.BottomToTopRocketPattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveAlignedRocketPattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveCenteredRocketPattern;

public class BossLevel3_P3 extends Boss
{
    public BossLevel3_P3(Scene scene) throws SceneIsNullException
    {
        super("P3",
                scene,
                300,
                new Pattern[] {new ThreeHundredAndSixtyFlashlight(), new RandomFlashlight(), new NovaFlashlight(), new TenRotationFlashlight(), new DoubleFlashlightPattern()},
                -0.5f,
                new Sprite("Characters/Boss/Ship6/Ship6.png"),
                new AABBCollider(350, 175, false, true),
                null);
    }
}