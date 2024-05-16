package com.scramble_like.game.essential;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameCamera
{
    private OrthographicCamera camera;
    private ScreenViewport viewport;

    public GameCamera(int width, int height)
    {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply();
        camera.position.set(0, 0, 0);
        camera.update();
    }

    public OrthographicCamera getCamera() { return camera; }
    public ScreenViewport getViewport() { return viewport; }
    public Matrix4 getCombined() { return camera.combined; }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    public void dispose() { camera = null; viewport = null; }

    public void update(int width, int height) { viewport.update(width, height); }
}
