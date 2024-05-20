package com.scramble_like.game.component.paper2d;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.CoreConstant;
import com.scramble_like.game.essential.factory.ImageFactory;

public class Tile extends Sprite
{
    private OrthographicCamera camera;
    private float x;
    private float y;
    private final float width;
    private final float height;

    public Tile(String path, float x, float y)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = CoreConstant.SQUARE_SIDE;
        this.height = CoreConstant.SQUARE_SIDE;
    }

    public Tile(String path, float x, float y, float width, float height)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.camera = this.getOwner().getScene().getCamera().getCamera();
    }

    public float getX() { return x; }

    public void setX(float x){ this.x = x; /*this.createBounds();*/ }

    public float getWidth() { return width; }

    public void addOffset(Vector2 offset) { x -= offset.x; y -= offset.y; /*this.createBounds();*/ }

    private boolean isVisible()
    {
        return this.x + this.width > this.camera.position.x - this.camera.viewportWidth / 2 +20
                && this.x < this.camera.position.x + this.camera.viewportWidth / 2 -20
                && this.y + this.height > this.camera.position.y - this.camera.viewportHeight / 2 +20
                && this.y < this.camera.position.y + this.camera.viewportHeight / 2 -20;
    }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        if (!isVisible()) { return; }
        this.getOwner().getBatch().draw(ImageFactory.getTexture(this.fileName), this.x, this.y, width, height);
    }
}
