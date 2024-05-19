package com.scramble_like.game.component.collider;

import com.badlogic.gdx.graphics.Color;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.utils.DebugRenderer;

public class TileCollider extends AABBCollider
{
    private final float x;
    private final float y;

    public TileCollider(float x, float y)
    {
        super(GameConstant.SQUARE_SIDE, GameConstant.SQUARE_SIDE, false, false);
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX1() { return x; }

    @Override
    public float getY1() { return y; }

    @Override
    public float getX2() { return x + getWidth(); }

    @Override
    public float getY2() { return y + getHeight(); }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        if (!GameConstant.DEBUG) { return; }
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY1(), this.getX2(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY1(), this.getX2(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY2(), this.getX1(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY2(), this.getX1(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
    }
}
