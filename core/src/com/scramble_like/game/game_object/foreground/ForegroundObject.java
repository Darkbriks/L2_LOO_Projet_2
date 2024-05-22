package com.scramble_like.game.game_object.foreground;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;

public class ForegroundObject extends GameObject
{
    public ForegroundObject(Scene scene, float x, float y, String path) throws SceneIsNullException
    {
        super("Foreground", scene);
        this.getTransform().setLocation(x, y);
        this.AddComponent(new Sprite(path));
        this.getTransform().setZIndex(CoreConstant.MIN_Z_INDEX);
        this.getTransform().setScale(2, 2);
    }
}
