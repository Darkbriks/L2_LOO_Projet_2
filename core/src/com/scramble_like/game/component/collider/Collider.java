package com.scramble_like.game.component.collider;

import com.badlogic.gdx.math.Rectangle;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.GameObject;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventEndOverlap;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Collider extends Component
{
    ////////// Attributes //////////
    protected boolean generateOverlappedEvent;
    protected boolean simulatePhysics;
    protected ArrayList<Collider> overlappedCollider;

    ////////// Constructor //////////
    public Collider()
    {
        super();
        generateOverlappedEvent = false;
        simulatePhysics = false;
        overlappedCollider = new ArrayList<Collider>();
    }

    public Collider(boolean generateOverlappedEvent)
    {
        super();
        this.generateOverlappedEvent = generateOverlappedEvent;
        simulatePhysics = false;
        overlappedCollider = new ArrayList<Collider>();
    }

    public Collider(boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super();
        this.generateOverlappedEvent = generateOverlappedEvent;
        this.simulatePhysics = simulatePhysics;
        overlappedCollider = new ArrayList<Collider>();
    }

    ////////// Getters //////////
    public boolean IsGenerateOverlappedEvent() { return this.generateOverlappedEvent; }
    public boolean IsSimulatePhysics() { return simulatePhysics; }

    public float getOwnerX() { return this.getOwner().getTransform().getLocation().x; }
    public float getOwnerY() { return this.getOwner().getTransform().getLocation().y; }

    ////////// Setters //////////
    public void setGenerateOverlappedEvent(boolean generateOverlappedEvent) { this.generateOverlappedEvent = generateOverlappedEvent; }
    public void setSimulatePhysics(boolean simulatePhysics) { this.simulatePhysics = simulatePhysics; }


    ////////// Methods //////////
    //protected abstract boolean Collide(Collider other);

    protected boolean Collide(Collider other)
    {
        if (this instanceof SphereCollider && other instanceof SphereCollider) { return Collider.Collide((SphereCollider) this, (SphereCollider)  other); }
        if (this instanceof AABBCollider && other instanceof AABBCollider) { return Collider.Collide((AABBCollider) this, (AABBCollider)  other); }
        if (this instanceof SphereCollider && other instanceof AABBCollider) { return Collider.Collide((SphereCollider) this, (AABBCollider)  other); }
        if (this instanceof AABBCollider && other instanceof SphereCollider) { return Collider.Collide((SphereCollider) other, (AABBCollider)  this); }
        return false;
    }

    private static boolean Collide(SphereCollider _this, SphereCollider other)
    {
        return Math.sqrt(Math.pow(_this.getX() - other.getX(), 2) + Math.pow(_this.getY() - other.getY(), 2)) < _this.getRadius() + other.getRadius();
    }

    private static boolean Collide(AABBCollider _this, AABBCollider other)
    {
        return !((other.getX1() >= _this.getX2()) || (other.getX2() <= _this.getX1()) || (other.getY1() >= _this.getY2()) || (other.getY2() <= _this.getY1()));
    }

    private static boolean Collide(float x, float y, SphereCollider sphere)
    {
        return Math.sqrt(Math.pow(x - sphere.getX(), 2) + Math.pow(y - sphere.getY(), 2)) < sphere.getRadius();
    }

    private static boolean Collide(float x, float y, AABBCollider box)
    {
        return (x >= box.getX1() && x <= box.getX2() && y >= box.getY1() && y <= box.getY2());
    }

    private static boolean Collide(SphereCollider sphere, AABBCollider box)
    {
        if ((sphere.getX1() >= box.getX2()) || (sphere.getX2() <= box.getX1()) || (sphere.getY1() >= box.getY2()) || (sphere.getY2() <= box.getY1())) { return false; }
        if (Collider.Collide(box.getX1(), box.getY1(), sphere)) { return true; }
        if (Collider.Collide(box.getX2(), box.getY1(), sphere)) { return true; }
        if (Collider.Collide(box.getX2(), box.getY2(), sphere)) { return true; }
        if (Collider.Collide(box.getX1(), box.getY2(), sphere)) { return true; }
        if (Collider.Collide(sphere.getX(), sphere.getY(), box)) { return true; }
        boolean projvertical = Utils.ProjectionSurSegment(sphere.getX(), sphere.getY(), box.getX1(), box.getY1(), box.getX1(), box.getY1() + box.getHeight());
        boolean projhorizontal = Utils.ProjectionSurSegment(sphere.getX(), sphere.getY(), box.getX1(), box.getY1(), box.getX1() + box.getWidth(), box.getY1());
        if (projvertical || projhorizontal) { return true; }
        return false;
    }

    protected void Hit(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.HIT, new EventHit(this.getOwner(), this, other.getOwner(), other));
    }

    protected void BeginOverlap(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.BEGIN_OVERLAP, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    protected void EndOverlap(Collider other)
    {
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.END_OVERLAP, new EventEndOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    public void Update(double DeltaTime)
    {
        if (!simulatePhysics) { return; }
        ArrayList<Collider> tempOverlappedCollider = new ArrayList<Collider>();

        for(GameObject go : this.getOwner().getScene().GetGameObjects())
        {
            if (go == this.getOwner()) { continue; }
            for(Collider other : go.GetAllComponentsFromClass(Collider.class))
            {
                if (Collide(other)) { tempOverlappedCollider.add(other); }
            }
        }

        for (Collider other : tempOverlappedCollider)
        {
            if (!overlappedCollider.contains(other))
            {
                overlappedCollider.add(other);
                if (generateOverlappedEvent) { BeginOverlap(other); }
                else { Hit(other); }
            }
        }

        for (int i = 0; i < overlappedCollider.size(); i++)
        {
            if (!tempOverlappedCollider.contains(overlappedCollider.get(i)))
            {
                if (generateOverlappedEvent) { EndOverlap(overlappedCollider.get(i)); }
                overlappedCollider.remove(overlappedCollider.get(i));
            }
        }
    }
}