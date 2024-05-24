package com.scramble_like.game.game_object.effect;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.chaos.SphereCollider;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.ui.AE_Label;

public class GoldenStrawberry extends PowerUp
{
    public GoldenStrawberry(String name, Scene scene, float x, float y) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
        this.AddComponent(new Sprite("Tileset/utile/tile176.png"));
        this.getTransform().setScale(1.5f, 1.5f);
        this.GetFirstComponentFromClass(SphereCollider.class).setRadius(25);
    }

    @Override
    protected void Triggered(Player player)
    {
        player.getPlayerController().setGoldenStrawberry(true);
        getScene().getStage().addActor(new AE_Label("Golden Strawberry Collected!", getScene().getSkin(), 2, getScene().getEventDispatcher(), true));
        DestroyThisInScene();
    }
}
