package com.scramble_like.game.game_object.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.component.ReachTarget;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.component.ProjectilMovement;

public class ProjectileDirect extends GameObject {
    private Vector2 direction;
    public ProjectileDirect(String name, Scene scene, GameObject target) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setScale(new Vector2(0.2f, 0.2f));
        this.AddComponent(new Sprite());

    }

}
