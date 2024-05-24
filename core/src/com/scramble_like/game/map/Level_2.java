package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.effect.Checkpoint;
import com.scramble_like.game.game_object.effect.SpeedUp;
import com.scramble_like.game.game_object.effect.GoldenStrawberry;

public class Level_2 extends AbstractLevel
{
    public Level_2()
    {
        super("Level_2", 2, 100, -100, new Vector2(6900, 275));
        DynamicObjectLoader.getInstance().loadAll(this, "Level_2_DynamicObject.txt");
        SoundFactory.getInstance().changeBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_2.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);

        try
        {
            Checkpoint checkpoint1 = new Checkpoint("Checkpoint", this, 1250, -340);
            Checkpoint checkpoint2 = new Checkpoint("Checkpoint", this, 3950, -365);
            AddGameObject(new SpeedUp("SpeedUp", this, 650, -400));
            AddGameObject(checkpoint1);
            AddGameObject(checkpoint2);

            AddGameObject(new GoldenStrawberry("GoldenStrawberry", this, 500, 10));
        }
        catch (SceneIsNullException e) { throw new RuntimeException(e); }
    }

    @Override
    protected Vector4 getInitPlayerAndCameraLocation() { return new Vector4(-350,0,500,0); }

}
