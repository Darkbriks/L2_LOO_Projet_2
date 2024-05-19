package com.scramble_like.game.essential;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
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

    public Vector2 getPosition() { return new Vector2(camera.position.x, camera.position.y); }
    public void setPosition(float x, float y) { this.camera.position.set(x, y, 0); this.camera.update(); }
    public void addPosition(float x, float y) { this.camera.position.add(x, y, 0); this.camera.update(); }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
        camera.update();
    }

    public void dispose() { camera = null; viewport = null; }

    public void update(int width, int height) { viewport.update(width, height); }
}
