package com.scramble_like.game.component.paper2d;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.factory.ImageFactory;

public class Tile extends Sprite
{
    private float x;
    private float y;
    private final float width;
    private final float height;
    //private Vector3 boundsCenter;
    //private Vector3 boundsDimensions;

    public Tile(String path, float x, float y)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = GameConstant.SQUARE_SIDE;
        this.height = GameConstant.SQUARE_SIDE;
        //this.createBounds();
    }

    public Tile(String path, float x, float y, float width, float height)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.createBounds();
    }

    /*private void createBounds()
    {
        boundsCenter = new Vector3(x + width / 2, y + height / 2, 0);
        boundsDimensions = new Vector3(width, height, 0);
    }*/

    public float getX() { return x; }

    public void setX(float x){ this.x = x; /*this.createBounds();*/ }

    public float getWidth() { return width; }

    public void addOffset(Vector2 offset) { x -= offset.x; y -= offset.y; /*this.createBounds();*/ }

    /*private boolean isVisible()
    {
        return camera.frustum.boundsInFrustum(this.boundsCenter, this.boundsDimensions);
        //return this.x + this.width >= 0 && this.camera.position.x - this.camera.viewportWidth / 2 <= this.x &&
          //      this.y + this.height >= 0 && this.camera.position.y - this.camera.viewportHeight / 2 <= this.y;
    }*/

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        //if (!isVisible()) { return; }
        this.getOwner().getBatch().draw(ImageFactory.getTexture(this.fileName), this.x, this.y, width, height);
    }
}
