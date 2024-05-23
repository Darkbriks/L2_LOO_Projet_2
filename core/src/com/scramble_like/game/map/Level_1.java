package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.boss_fight.boss.Boss_1;

public class Level_1 extends AbstractLevel
{
    public Level_1()
    {
        super("Level_1", 1, 75, -50, new Vector2(9160, 60));

        DynamicObjectLoader.getInstance().loadAll(this, "Level_1_DynamicObject.txt");
        SoundFactory.getInstance().changeBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_1.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);

        try
        {
            Boss_1 boss = new Boss_1(this);
            AddGameObject(boss);
        }
        catch (Exception e) { Gdx.app.error("Level_1", "Error while creating boss: " + e.getMessage());}
    }
}
