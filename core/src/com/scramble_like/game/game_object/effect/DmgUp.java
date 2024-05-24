package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.SoundFactory;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.map.AbstractLevel;

public class DmgUp extends PowerUp {
    public DmgUp(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
        /*this.AddComponent(new Sprite("badlogic.jpg"));*/
    }

    @Override
    protected void Triggered(Player player)
    {
        if (getScene() instanceof AbstractLevel)
        {
            GameConstant.PLAYER_ATTACK_MULTIPLIER+=0.2f;
            SoundFactory.getInstance().playSound("PowerUp",GameConstant.SOUND_EFFECT_VOLUME);
            System.out.println(GameConstant.PLAYER_ATTACK_MULTIPLIER);
            DestroyThisInScene();
        }
    }
}
