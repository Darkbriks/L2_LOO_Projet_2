package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.factory.SoundFactory;

public class Level_2 extends AbstractLevel
{
    public Level_2()
    {
        super("Level_2", 2, 200, -100, new Vector2(9160, 60));
        DynamicObjectLoader.getInstance().loadAll(this, "Level_2_DynamicObject.txt");
        SoundFactory.getInstance().changeBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_2.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);
    }
}
