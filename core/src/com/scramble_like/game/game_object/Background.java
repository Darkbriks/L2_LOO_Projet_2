package com.scramble_like.game.game_object;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.component.paper2d.Tile;
import com.scramble_like.game.essential.GameCamera;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.Scene;
import com.scramble_like.game.essential.exception.SceneIsNullException;


public class Background extends GameObject
{
    private final Tile background_1;
    private final Tile background_2;
    private final int length;
    private final GameCamera camera;

    public Background(String name, Scene scene, String path, int width, int height) throws SceneIsNullException
    {
        super(name, scene);
        float aspectRatio = (float) height / width;
        this.background_1 = new Tile(path, -(GameConstant.HEIGHT / aspectRatio) / 2, (float) -GameConstant.HEIGHT / 2, GameConstant.HEIGHT / aspectRatio, GameConstant.HEIGHT);
        this.background_2 = new Tile(path, GameConstant.HEIGHT / aspectRatio / 2, (float) -GameConstant.HEIGHT / 2, GameConstant.HEIGHT / aspectRatio, GameConstant.HEIGHT);
        this.length = (int) (GameConstant.HEIGHT / aspectRatio);
        this.camera = scene.getCamera();
        this.AddComponent(background_1);
        this.AddComponent(background_2);
        getTransform().setZIndex(GameConstant.MAX_Z_INDEX);
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; }

        this.getTransform().Translate((float) (DeltaTime * GameConstant.CAMERA_SPEED), 0);

        background_1.addOffset(new Vector2((float) (DeltaTime * GameConstant.BACKGROUND_SPEED), 0));
        background_2.addOffset(new Vector2((float) (DeltaTime * GameConstant.BACKGROUND_SPEED), 0));

        if (camera.getPosition().x - background_1.getX() > length * 1.5f) { background_1.setX(background_2.getX() + length); }
        if (camera.getPosition().x - background_2.getX() > length * 1.5f) { background_2.setX(background_1.getX() + length); }
    }
}