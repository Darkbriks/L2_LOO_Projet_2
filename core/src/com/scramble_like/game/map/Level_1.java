package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.factory.SoundFactory;

public class Level_1 extends AbstractLevel
{
    public Level_1()
    {
        super("Level_1", 1, new Vector2(9160, 60));

        DynamicObjectLoader.getInstance().loadAll(this, "Level_1_DynamicObject.txt");
        SoundFactory.getInstance().playBackgroundMusicWithFade("Audio/Music/Reach for the Summit.mp3", 1, 10);
    }
}
