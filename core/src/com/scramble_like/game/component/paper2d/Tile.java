package com.scramble_like.game.component.paper2d;

import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.factory.ImageFactory;

public class Tile extends Sprite
{
    private float x, y;

    public Tile(String path, float x, float y)
    {
        super(path);
        this.x = x;
        this.y = y;
    }

    public void addOffset(Vector2 offset) { x -= offset.x; y -= offset.y; }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        this.getOwner().getBatch().draw(ImageFactory.getTexture(this.fileName), this.x, this.y, GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE);
    }
}
