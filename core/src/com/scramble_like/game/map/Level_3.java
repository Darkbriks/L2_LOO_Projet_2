package com.scramble_like.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.power_up.InverseScrolling;

public class Level_3 extends AbstractLevel
{
    public Level_3()
    {
        super("Level_3", 3,100, 0, new Vector2(9160, 60));
        DynamicObjectLoader.getInstance().loadAll(this, "Level_3_DynamicObject.txt");
        SoundFactory.getInstance().playBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_3.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);
        getPlayer().getPlayerController().setInverseScroll(true);

        try
        {
            AddGameObject(new InverseScrolling("InverseScrolling", this, 1150, 5000));
        }
        catch (Exception e) { Gdx.app.error("Level_3", "Error while adding InverseScrolling"); }
    }

    @Override
    protected Vector4 getPosition(){
        return new Vector4(175,-5250,0,0);
    }
}