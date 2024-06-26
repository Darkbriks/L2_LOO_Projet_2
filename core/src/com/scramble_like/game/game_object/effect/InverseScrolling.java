package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

public class InverseScrolling extends PowerUp
{
    public InverseScrolling(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
    }

    @Override
    protected void Triggered(Player player)
    {
        player.getPlayerController().setInverseScroll(!player.getPlayerController().isInverseScroll());
        player.getPlayerController().setOrigin(getCamera().getPosition());
        DestroyThisInScene();
    }
}
