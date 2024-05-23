package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;

public class GoldenStrawberry extends PowerUp
{
    public GoldenStrawberry(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
        this.AddComponent(new Sprite("badlogic.jpg"));
        this.getTransform().setScale(0.1f, 0.1f);
        this.GetFirstComponentFromClass(SphereCollider.class).setRadius(25);
    }

    @Override
    protected void Triggered(Player player)
    {
        player.getPlayerController().setGoldenStrawberry(true);
        DestroyThisInScene();
    }
}