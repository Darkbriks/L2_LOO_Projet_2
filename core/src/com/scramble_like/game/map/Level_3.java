package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.map.AbstractLevel;

public class Level_3 extends AbstractLevel
{
    public Level_3()
    {
        super("Level_3", 3,200, new Vector2(9160, 60));
        //DynamicObjectLoader.getInstance().loadAll(this, "Level_2_DynamicObject.txt");
        SoundFactory.getInstance().playBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_3.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 10);
    }
}