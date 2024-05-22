package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class JellyFish extends PlayerDeclanchedProjectile
{
    /*public JellyFish(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("JellyFish", scene, "Characters/Fish/JellyFish/Walk.png", location, new Vector2(0, 1), 1000, 300, 4);
        this.getTransform().setScale(new Vector2(1, 1));
        this.AddComponent(new AABBCollider(150,1200,true,true));
        this.damage = 10;
    }*/

    public JellyFish(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("JellyFish", scene, location, new Vector2(0, 1), 1000, 300, 10, 0.1f, 150);
    }
}
