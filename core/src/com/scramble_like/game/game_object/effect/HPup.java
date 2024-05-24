package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.map.AbstractLevel;
import com.scramble_like.game.ui.AE_Label;

public class HPup extends PowerUp
{
    public HPup(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
    }

    @Override
    protected void Triggered(Player player)
    {
        if (getScene() instanceof AbstractLevel)
        {
            GameConstant.PLAYER_HP_MULTIPLIER+=0.5f;
            SoundFactory.getInstance().playSound("PowerUp",GameConstant.SOUND_EFFECT_VOLUME);
            getScene().getPlayer().getPlayerController().reset();
            getScene().getStage().addActor(new AE_Label("Life Increased to " + GameConstant.PLAYER_LIFE() * 100 + "% !"
                    , getScene().getSkin(), 2, getScene().getEventDispatcher(), true));
            Destroy();
        }
    }
}
