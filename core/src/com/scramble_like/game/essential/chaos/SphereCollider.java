package com.scramble_like.game.essential.chaos;

import com.badlogic.gdx.graphics.Color;
import com.scramble_like.game.GameConstant;
import com.scramble_like.game.essential.utils.DebugRenderer;
import com.scramble_like.game.essential.utils.Utils;

public class SphereCollider extends Collider
{
    ////////// Attributes //////////
    private float radius;

    ////////// Constructor //////////
    public SphereCollider(float radius, boolean generateOverlappedEvent, boolean simulatePhysics) { super(generateOverlappedEvent, simulatePhysics); this.radius = radius; }

    ////////// Getters //////////
    public float getRadius() { return this.radius; }
    public void setRadius(float radius) { this.radius = radius; }
    public float getX() { return Utils.lerp(this.getOwnerX() + radius / 2, getOwnerX() - radius / 2, this.getOwner().getTransform().getAlignment().x); }
    public float getY() { return Utils.lerp(this.getOwnerY() + radius / 2, getOwnerY() - radius / 2, this.getOwner().getTransform().getAlignment().y); }

    public float getX1() { return getX() - radius; }
    public float getY1() { return getY() - radius; }
    public float getX2() { return getX() + radius; }
    public float getY2() { return getY() + radius; }

    @Override
    public void Render()
    {
        if (!this.IsActive()) { return; }
        if (!GameConstant.DEBUG) { return; }
        DebugRenderer.DrawDebugCircle(getX(), getY(), radius, !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugCircle(this.getOwnerX(), this.getOwnerY(), 1, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugCircle(this.getX1(), this.getY1(), 1, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().getCombined());
        DebugRenderer.DrawDebugCircle(this.getX2(), this.getY2(), 1, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().getCombined());
    }
}
