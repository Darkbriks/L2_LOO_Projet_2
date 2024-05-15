package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.component.PlayerController;
import com.scramble_like.game.component.ReachTarget;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class Player extends GameObject
{
    public Player(String name, Scene scene) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        this.AddComponent(new PlayerController(500));
        this.AddComponent(new Sprite());
    }

    public Player(String name, Scene scene, Vector3 location) throws SceneIsNullException
    {
        super(name, scene);
        this.getTransform().setLocation(location);
        this.getTransform().setScale(new Vector2(0.3f, 0.3f));
        this.AddComponent(new PlayerController(500));
        this.AddComponent(new Sprite());
    }
}
