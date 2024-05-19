package com.scramble_like.game.component.collider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.utils.DebugRenderer;
import com.scramble_like.game.essential.utils.Utils;

public class AABBCollider extends Collider
{
    ////////// Attributes //////////
    private float width;
    private float height;
    private final float xOffSet;
    private final float yOffSet;

    ////////// Constructor //////////
    public AABBCollider(float width, float height, boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super(generateOverlappedEvent, simulatePhysics);
        this.width = width;
        this.height = height;
        this.xOffSet = 0;
        this.yOffSet = 0;
    }

    public AABBCollider(float width, float height, float xOffSet, float yOffSet, boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super(generateOverlappedEvent, simulatePhysics);
        this.width = width;
        this.height = height;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
    }

    ////////// Getters //////////
    public float getWidth() { return this.width; }
    public float getHeight() { return this.height; }

    public float getX1() { return Utils.lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x) + xOffSet; }
    public float getY1() { return Utils.lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y) + yOffSet; }
    public float getX2() { return Utils.lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x) + width + xOffSet; }
    public float getY2() { return Utils.lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y) + height + yOffSet; }

    ////////// Setters //////////
    public void setWidth(float width) { this.width = width; }
    public void setHeight(float height) { this.height = height; }

    ////////// Override Methods //////////
    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        if (!GameConstant.DEBUG) { return; }
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY1(), this.getX2(), this.getY1(), !overlappedCollider.isEmpty() || isHit ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY1(), this.getX2(), this.getY2(), !overlappedCollider.isEmpty() || isHit ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY2(), this.getX1(), this.getY2(), !overlappedCollider.isEmpty() || isHit ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY2(), this.getX1(), this.getY1(), !overlappedCollider.isEmpty() || isHit ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugCircle(new Vector2(this.getOwnerX(), this.getOwnerY()), 1, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().getCombined());
    }
}