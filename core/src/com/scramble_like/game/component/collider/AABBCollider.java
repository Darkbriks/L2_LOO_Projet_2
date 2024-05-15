package com.scramble_like.game.component.collider;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventEndOverlap;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.utils.DebugRenderer;
import com.scramble_like.game.essential.utils.Utils;

import java.util.ArrayList;

public class AABBCollider extends Collider
{
    ////////// Attributes //////////
    private float width;
    private float height;

    ////////// Constructor //////////
    public AABBCollider()
    {
        super();
        this.width = 100;
        this.height = 100;
    }

    public AABBCollider(float width, float height)
    {
        super();
        this.width = width;
        this.height = height;
    }

    public AABBCollider(float width, float height, boolean generateOverlappedEvent)
    {
        super(generateOverlappedEvent);
        this.width = width;
        this.height = height;
    }

    public AABBCollider(float width, float height, boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super(generateOverlappedEvent, simulatePhysics);
        this.width = width;
        this.height = height;
    }

    ////////// Getters //////////
    public float getWidth() { return this.width; }
    public float getHeight() { return this.height; }

    public float getX1() { return Utils.lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x); }
    public float getY1() { return Utils.lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y); }
    public float getX2() { return Utils.lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x) + width; }
    public float getY2() { return Utils.lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y) + height; }

    ////////// Setters //////////
    public void setWidth(float width) { this.width = width; }
    public void setHeight(float height) { this.height = height; }

    ////////// Override Methods //////////
    @Override
    public void Render()
    {
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY1(), this.getX2(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY1(), this.getX2(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY2(), this.getX1(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY2(), this.getX1(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugCircle(new Vector2(this.getOwnerX(), this.getOwnerY()), 1, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().combined);
    }
}