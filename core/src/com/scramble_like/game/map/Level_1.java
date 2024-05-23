package com.scramble_like.game.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.DynamicObjectLoader;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.effect.Checkpoint;
import com.scramble_like.game.game_object.effect.GoldenStrawberry;

public class Level_1 extends AbstractLevel
{
    public Level_1()
    {
        super("Level_1", 1, 150, -50, new Vector2(9160, 60));

        DynamicObjectLoader.getInstance().loadAll(this, "Level_1_DynamicObject.txt");
        SoundFactory.getInstance().changeBackgroundMusicWithFade(GameConstant.MUSIC_PATH("level_1.mp3"), GameConstant.SOUND_MUSIC_VOLUME, 3);

        try
        {
            Checkpoint checkpoint1 = new Checkpoint("Checkpoint", this, 300, -295);
            Checkpoint checkpoint2 = new Checkpoint("Checkpoint", this, 3000, -318);
            Checkpoint checkpoint3 = new Checkpoint("Checkpoint", this, 6065, -140);
            AddGameObject(checkpoint1);
            AddGameObject(checkpoint2);
            AddGameObject(checkpoint3);

            AddGameObject(new GoldenStrawberry("GoldenStrawberry", this, 500, 10));
        }
        catch (SceneIsNullException e) { throw new RuntimeException(e); }
    }
    @Override
    protected Vector4 getInitPlayerAndCameraLocation() { return new Vector4(-350,0,500,0); }
}
