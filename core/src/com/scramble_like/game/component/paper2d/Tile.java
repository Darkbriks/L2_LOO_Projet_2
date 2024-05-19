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

    public Tile(String path, float x, float y)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = GameConstant.SQUARE_SIDE;
        this.height = GameConstant.SQUARE_SIDE;
    }

    public Tile(String path, float x, float y, float width, float height)
    {
        super(path);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x){
        this.x=x;
    }

    public float getWidth() {
        return width;
    }

    public void addOffset(Vector2 offset) { x -= offset.x; y -= offset.y; }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        this.getOwner().getBatch().draw(ImageFactory.getTexture(this.fileName), this.x, this.y, width, height);
    }
}
