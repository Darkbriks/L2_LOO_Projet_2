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

import java.util.ArrayList;

public class AABBCollider extends Component
{
    ////////// Attributes //////////
    private float width;
    private float height;
    private boolean generateOverlappedEvent;
    private boolean simulatePhysics;
    private ArrayList<AABBCollider> overlappedCollider;

    ////////// Constructor //////////
    public AABBCollider()
    {
        super();
        this.width = 100;
        this.height = 100;
        generateOverlappedEvent = false;
        simulatePhysics = false;
        overlappedCollider = new ArrayList<AABBCollider>();
    }

    public AABBCollider(float width, float height)
    {
        super();
        this.width = width;
        this.height = height;
        this.generateOverlappedEvent = false;
        this.simulatePhysics = false;
        overlappedCollider = new ArrayList<AABBCollider>();
    }

    public AABBCollider(float width, float height, boolean generateOverlappedEvent)
    {
        super();
        this.width = width;
        this.height = height;
        this.generateOverlappedEvent = generateOverlappedEvent;
        this.simulatePhysics = false;
        overlappedCollider = new ArrayList<AABBCollider>();
    }

    public AABBCollider(float width, float height, boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super();
        this.width = width;
        this.height = height;
        this.generateOverlappedEvent = generateOverlappedEvent;
        this.simulatePhysics = simulatePhysics;
        overlappedCollider = new ArrayList<AABBCollider>();
    }

    ////////// Getters //////////
    public float getWidth() { return this.width; }
    public float getHeight() { return this.height; }
    public boolean IsGenerateOverlappedEvent() { return this.generateOverlappedEvent; }
    public boolean IsSimulatePhysics() { return simulatePhysics; }

    public float getOwnerX() { return this.getOwner().getTransform().getLocation().x; }
    public float getOwnerY() { return this.getOwner().getTransform().getLocation().y; }
    public float getX1() { return lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x); }
    public float getY1() { return lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y); }
    public float getX2() { return lerp(this.getOwnerX(), getOwnerX() - width, this.getOwner().getTransform().getAlignment().x) + width; }
    public float getY2() { return lerp(this.getOwnerY(), getOwnerY() - height, this.getOwner().getTransform().getAlignment().y) + height; }

    ////////// Setters //////////
    public void setWidth(float width) { this.width = width; }
    public void setHeight(float height) { this.height = height; }
    public void setGenerateOverlappedEvent(boolean generateOverlappedEvent) { this.generateOverlappedEvent = generateOverlappedEvent; }
    public void setSimulatePhysics(boolean simulatePhysics) { this.simulatePhysics = simulatePhysics; }

    ////////// Methods //////////
    private float lerp(float a, float b, float t) { return a + (b - a) * t; }

    private boolean Collide(AABBCollider other)
    {
        return !((other.getX1() >= this.getX2())      // trop à droite
                || (other.getX2() <= this.getX1())  // trop à gauche
                || (other.getY1() >= this.getY2())  // trop en bas
                || (other.getY2() <= this.getY1())); // trop en haut
    }

    ////////// Override Methods //////////
    @Override
    public void Update(double DeltaTime)
    {
        if (!simulatePhysics) { return; }
        ArrayList<AABBCollider> tempOverlappedCollider = new ArrayList<AABBCollider>();

        for(GameObject go : this.getOwner().getScene().GetGameObjects())
        {
            if (go == this.getOwner()) { continue; }
            for(AABBCollider other : go.GetAllComponentsFromClass(AABBCollider.class))
            {
                if (Collide(other)) { tempOverlappedCollider.add(other); }
            }
        }

        for (AABBCollider other : tempOverlappedCollider)
        {
            if (!overlappedCollider.contains(other))
            {
                overlappedCollider.add(other);
                if (generateOverlappedEvent) { this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.BEGIN_OVERLAP, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other)); }
                else { this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.HIT, new EventHit(this.getOwner(), this, other.getOwner(), other)); }
            }
        }

        //if (tempOverlappedCollider.isEmpty()) { overlappedCollider.clear(); }
        for (int i = 0; i < overlappedCollider.size(); i++)
        {
            if (!tempOverlappedCollider.contains(overlappedCollider.get(i)))
            {
                if (generateOverlappedEvent) { this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.END_OVERLAP, new EventEndOverlap(this.getOwner(), this, overlappedCollider.get(i).getOwner(), overlappedCollider.get(i))); }
                overlappedCollider.remove(overlappedCollider.get(i));
            }
        }
    }

    @Override
    public void Render()
    {
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY1(), this.getX2(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY1(), this.getX2(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX2(), this.getY2(), this.getX1(), this.getY2(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugLine(this.getX1(), this.getY2(), this.getX1(), this.getY1(), !overlappedCollider.isEmpty() ? Color.RED : Color.GREEN, this.getOwner().getScene().getCamera().combined);
        DebugRenderer.DrawDebugPoint(new Vector2(this.getOwnerX(), this.getOwnerY()), 5, com.badlogic.gdx.graphics.Color.RED, this.getOwner().getScene().getCamera().combined);
    }
}