package com.scramble_like.game.essential.chaos;

import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventEndOverlap;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventHit;
import com.scramble_like.game.essential.utils.Utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public abstract class Collider extends Component
{
    ////////// Attributes //////////
    protected boolean generateOverlappedEvent; // Is used to differentiate a collision from an overlap
    protected boolean simulatePhysics; // If false, the collider no check for collision
    protected boolean isHit;
    protected Set<Collider> overlappedCollider;

    ////////// Constructor //////////
    public Collider(boolean generateOverlappedEvent, boolean simulatePhysics)
    {
        super();
        this.generateOverlappedEvent = generateOverlappedEvent;
        this.simulatePhysics = simulatePhysics;
        isHit = false;
        overlappedCollider = new HashSet<>();
    }

    @Override
    public void BeginPlay() { super.BeginPlay(); this.getOwner().getScene().getSpatialGrid().addCollider(this); }

    @Override
    public void Destroy() { super.Destroy(); this.getOwner().getScene().getSpatialGrid().removeCollider(this); }

    ////////// Getters //////////
    public boolean IsGenerateOverlappedEvent() { return this.generateOverlappedEvent; }
    public boolean IsSimulatePhysics() { return simulatePhysics; }
    public boolean IsHit() { return isHit; }

    public float getOwnerX() { return this.getOwner().getTransform().getLocation().x; }
    public float getOwnerY() { return this.getOwner().getTransform().getLocation().y; }

    ////////// Setters //////////
    public void setGenerateOverlappedEvent(boolean generateOverlappedEvent) { this.generateOverlappedEvent = generateOverlappedEvent; }
    public void setSimulatePhysics(boolean simulatePhysics) { this.simulatePhysics = simulatePhysics; }
    public void setPositionInGrid() { if (this.getOwner() != null) { this.getOwner().getScene().getSpatialGrid().updateCollider(this); } }

    ////////// Methods //////////
    boolean Collide(Collider other)
    {
        if (this instanceof SphereCollider && other instanceof SphereCollider) { return Collider.Collide((SphereCollider) this, (SphereCollider)  other); }
        if (this instanceof AABBCollider && other instanceof AABBCollider) { return Collider.Collide((AABBCollider) this, (AABBCollider)  other); }
        if (this instanceof SphereCollider && other instanceof AABBCollider) { return Collider.Collide((SphereCollider) this, (AABBCollider)  other); }
        if (this instanceof AABBCollider && other instanceof SphereCollider) { return Collider.Collide((SphereCollider) other, (AABBCollider)  this); }
        return false;
    }

    private static boolean Collide(SphereCollider _this, SphereCollider other)
    {
        return (_this.getX() - other.getX()) * (_this.getX() - other.getX()) + (_this.getY() - other.getY()) * _this.getY() - other.getY() < (_this.getRadius() + other.getRadius()) * (_this.getRadius() + other.getRadius());
    }

    private static boolean Collide(AABBCollider _this, AABBCollider other)
    {
        return !((other.getX1() >= _this.getX2()) || (other.getX2() <= _this.getX1()) || (other.getY1() >= _this.getY2()) || (other.getY2() <= _this.getY1()));
    }

    private static boolean Collide(float x, float y, SphereCollider sphere)
    {
        return (x - sphere.getX()) * (x - sphere.getX()) + (y - sphere.getY()) * (y - sphere.getY()) < sphere.getRadius() * sphere.getRadius();
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
        return projvertical || projhorizontal;
    }

    protected void Hit(Collider other)
    {
        if (this.getOwner() == null || other.getOwner() == null) return;
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.HIT, new EventHit(this.getOwner(), this, other.getOwner(), other));
    }

    protected void BeginOverlap(Collider other)
    {
        if (this.getOwner() == null || other.getOwner() == null) return;
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.BEGIN_OVERLAP, new EventBeginOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    protected void EndOverlap(Collider other)
    {
        if (this.getOwner() == null || other.getOwner() == null) return;
        this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.END_OVERLAP, new EventEndOverlap(this.getOwner(), this, other.getOwner(), other));
    }

    @Override
    public void Update(float DeltaTime)
    {
        isHit = false;
        if (!this.IsActive() || !simulatePhysics) return;

        ForkJoinPool pool = new ForkJoinPool();
        Set<Collider> potentialColliders = this.getOwner().getScene().getSpatialGrid().getPotentialCollisions(this);

        CollisionTask task = new CollisionTask(potentialColliders, this);
        Set<Collider> tempOverlappedCollider = pool.invoke(task);

        for (Collider other : tempOverlappedCollider)
        {
            if ((generateOverlappedEvent || other.generateOverlappedEvent) && !overlappedCollider.contains(other)) { overlappedCollider.add(other); BeginOverlap(other); }
            else if (!generateOverlappedEvent && !other.generateOverlappedEvent) { Hit(other); isHit = true; }
        }

        for (Collider other : new HashSet<>(overlappedCollider))
        {
            if (!tempOverlappedCollider.contains(other))
            {
                if (generateOverlappedEvent || other.generateOverlappedEvent) { EndOverlap(other); }
                overlappedCollider.remove(other);
            }
        }

        pool.shutdown();
    }
}