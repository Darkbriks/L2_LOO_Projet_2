package com.scramble_like.game.game_object;

import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;


public class Background extends GameObject {
    Sprite background;

    public Background(String name, Scene scene,String path) throws SceneIsNullException {
        super(name, scene);
        this.background = new Sprite(path);
        //this.getTransform().setScale(new Vector2(1600,900));
        this.AddComponent(background);
    }

}
