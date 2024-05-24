package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.map.AbstractLevel;
import com.scramble_like.game.ui.AE_Label;

public class Checkpoint extends PowerUp
{

    public Checkpoint(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
        /*this.AddComponent(new Sprite("badlogic.jpg"));*/
    }

    @Override
    protected void Triggered(Player player)
    {
        if (getScene() instanceof AbstractLevel)
        {
            getScene().getStage().addActor(new AE_Label("Checkpoint activated !", getScene().getSkin(), 2, getScene().getEventDispatcher(), true));
            ((AbstractLevel) getScene()).setLastCheckpoint(Checkpoint.this);
        }
    }
}
