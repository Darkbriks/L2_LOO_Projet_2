package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;


public class Background extends GameObject
{
    Tile background_1, background_2;

    private int fix;//fix est le x pour lequel l'image se boucle

    public Background(String name, Scene scene, String path, int fix) throws SceneIsNullException
    {
        super(name, scene);
        this.background_1 = new Tile(path, -800, -450, 2000, 900);
        this.AddComponent(background_1);
        this.background_2 = new Tile(path, 535, -450, 2000, 900);
        this.AddComponent(background_2);
        this.fix = fix;
        getTransform().setZIndex(GameConstant.MAX_Z_INDEX);
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        background_1.addOffset(new Vector2((float) (DeltaTime * GameConstant.WALLPAPER_SPEED), 0));
        background_2.addOffset(new Vector2((float) (DeltaTime * GameConstant.WALLPAPER_SPEED), 0));

        if(background_1.getX()<=-(background_1.getWidth()+400)) { background_1.setX((background_2.getX())+fix); }
        if(background_2.getX()<=-(background_1.getWidth()+400)) { background_2.setX((background_1.getX())+fix); }
    }
}