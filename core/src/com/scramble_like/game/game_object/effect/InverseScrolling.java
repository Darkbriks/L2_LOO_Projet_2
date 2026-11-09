package com.scramble_like.game.game_object.effect;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.controller.PlayerController;
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
        PlayerController controller = player.getPlayerController();
        controller.setInverseScroll(!controller.isInverseScroll());

        // La caméra est recentrée sur la ligne du déclencheur, quel que soit le moment où le joueur l'a touché
        Vector2 location = getTransform().getLocation();
        Vector2 origin = getCamera().getPosition();
        if (controller.isInverseScroll()) { origin.x = location.x; } else { origin.y = location.y; }
        controller.setOrigin(origin);
        controller.alignCamera(controller.isInverseScroll() ? origin.x : origin.y, GameConstant.CAMERA_ALIGNMENT_DURATION);
        DestroyThisInScene();
    }
}
