package com.scramble_like.game.game_object.power_up;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.map.AbstractLevel;

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
            ((AbstractLevel) getScene()).setLastCheckpoint(Checkpoint.this);
        }
    }
}
