package com.scramble_like.game.essential;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameCamera implements TickableObject
{
    private OrthographicCamera camera;
    private ScreenViewport viewport;

    public GameCamera()
    {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply();
        camera.position.set(0, 0, 0);
    }

    public OrthographicCamera getCamera() { return camera; }
    public ScreenViewport getViewport() { return viewport; }
    public Matrix4 getCombined() { return camera.combined; }

    public Vector2 getPosition() { return new Vector2(camera.position.x, camera.position.y); }
    public void setPosition(float x, float y) { this.camera.position.set(x, y, 0); }
    public void addPosition(float x, float y) { this.camera.position.add(x, y, 0); }
    public void setX(float x) { this.camera.position.x = x; }
    public void setY(float y) { this.camera.position.y = y; }

    public void resize(int width, int height) { viewport.update(width, height); }

    public void dispose() { camera = null; viewport = null; }

    public void update(int width, int height) { viewport.update(width, height); }

    @Override
    public void Tick(float deltaTime) { camera.update();}
}
