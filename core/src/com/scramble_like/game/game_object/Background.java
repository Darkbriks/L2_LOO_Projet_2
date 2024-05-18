package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Sprite;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;


public class Background extends GameObject {
    Tile background, samebackground;

    private int speed, fix;//fix est le x pour lequel l'image se boucle

    public Background(String name, Scene scene, String path, int fix) throws SceneIsNullException {
        super(name, scene);
        this.background = new Tile(path, -800, -450, 2000, 900);
        //this.getTransform().setScale(new Vector2(4,5));
        this.AddComponent(background);
        speed=4;
        this.samebackground = new Tile(path, 535, -450, 2000, 900);
        this.AddComponent(samebackground);
        this.fix = fix;
    }

    @Override
    public void Update(double DeltaTime) {
        if (!this.IsActive()) {
            return;
        }
        background.addOffset(new Vector2(speed, 0));
        System.out.println(background.getX());
        samebackground.addOffset(new Vector2(speed, 0));
        if(background.getX()<=-(background.getWidth()+400)){
            background.setX((samebackground.getX())+fix);
        }
        if(samebackground.getX()<=-(background.getWidth()+400)){
            samebackground.setX((background.getX())+fix);
        }
    }
}