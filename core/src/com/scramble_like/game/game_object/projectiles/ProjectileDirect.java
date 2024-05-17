package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.ReachTarget;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class ProjectileDirect extends GameObject {
    public ProjectileDirect(String name, Scene scene, GameObject target) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setScale(new Vector2(0.2f, 0.2f));
        this.AddComponent(new ReachTarget(target));
        this.AddComponent(new Sprite());
    }

}
