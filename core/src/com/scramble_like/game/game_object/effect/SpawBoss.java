package com.scramble_like.game.game_object.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.game_object.Player;
import com.scramble_like.game.game_object.boss_fight.boss.Boss_1;

public class SpawBoss extends PowerUp
{
    private final Vector2 bossLocation;

    public SpawBoss(String name, Scene scene, float x, float y, Vector2 bossLocation) throws SceneIsNullException
    {
        super(name, scene, x, y, 100);
        this.bossLocation = bossLocation;
    }

    @Override
    protected void Triggered(Player player)
    {
        try
        {
            Boss_1 boss = new Boss_1(getScene());
            boss.getTransform().setLocation(bossLocation);
            getScene().AddGameObject(boss);
        }
        catch (SceneIsNullException e) { Gdx.app.error("InverseScrolling", "Error: " +e.getMessage()); }
        DestroyThisInScene();
    }
}