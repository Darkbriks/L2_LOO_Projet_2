package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class DeepDeaMonster extends PlayerDeclanchedProjectile
{
    /*public DeepDeaMonster(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("DeepDeaMonster", scene, "Characters/Fish/DeepDeaMonster/Walk.png", location, new Vector2(1, 0), 1000, 300, 4);
        this.getTransform().setScale(new Vector2(1, 1));
        this.AddComponent(new AABBCollider(1000,20,true,true));
        this.damage = 10;
    }*/

    public DeepDeaMonster(Scene scene, Vector2 location) throws SceneIsNullException
    {
        super("JellyFish", scene, location, new Vector2(-1, 0), 1000, 300, 10, 0.1f, 150);
    }
}
