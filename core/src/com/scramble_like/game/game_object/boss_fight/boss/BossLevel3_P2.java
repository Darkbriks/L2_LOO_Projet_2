package com.scramble_like.game.game_object.boss_fight.boss;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.boss_fight.pattern.Pattern;
import com.scramble_like.game.game_object.boss_fight.pattern.flashlight.ThreeHundredAndSixtyFlashlight;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.BottomToTopRocketPattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveAlignedRocketPattern;
import com.scramble_like.game.game_object.boss_fight.pattern.rockets.FiveCenteredRocketPattern;

public class BossLevel3_P2 extends Boss
{
    public BossLevel3_P2(Scene scene) throws SceneIsNullException
    {
        super("P2",
                scene,
                200,
                new Pattern[] { new FiveAlignedRocketPattern(), new FiveCenteredRocketPattern(), new BottomToTopRocketPattern(), new ThreeHundredAndSixtyFlashlight()},
                0,
                new Sprite("Characters/Boss/Ship5/Ship5.png"),
                new AABBCollider(350, 175, false, true),
                BossLevel3_P3.class);
    }
}