package com.scramble_like.game.game_object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.component.Sprite;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;
import com.scramble_like.game.essential.factory.ImageFactory;
import com.scramble_like.game.utils.Chunk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Background extends GameObject {
    Sprite background;

    public Background(String name, Scene scene,String path) throws SceneIsNullException {
        super(name, scene);
        this.background = new Sprite(path);
        //this.getTransform().setScale(new Vector2(1600,900));
        this.AddComponent(background);
    }

}
