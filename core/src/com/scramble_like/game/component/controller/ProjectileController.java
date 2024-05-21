package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.chaos.Collider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.EventListener;
import com.scramble_like.game.essential.event_dispatcher.event.game.ProjectileReachDestinationEvent;
import com.scramble_like.game.essential.event_dispatcher.event.physics.EventBeginOverlap;
import com.scramble_like.game.game_object.Player;

import java.util.ArrayList;
import java.util.EventObject;

public class ProjectileController extends Component
{
    protected Vector2 start;
    protected Vector2 end;
    protected float speed;
    protected float elapsedTime;
    protected float distance;
    protected Interpolation xInterpolation;
    protected Interpolation yInterpolation;
    private int tirer =0;

    public ProjectileController(Vector2 start, Vector2 direction, float range, float speed)
    {
        super();
        this.start = start;
        this.end = computeEnd(this.start, direction, range);
        this.speed = speed;
        this.elapsedTime = 0;
        this.distance = start.dst(end);
        xInterpolation = Interpolation.linear;
        yInterpolation = Interpolation.linear;
    }

    public ProjectileController(Vector2 start, Vector2 end, float speed)
    {
        super();
        this.start = start;
        this.end = end;
        this.speed = speed;
        this.elapsedTime = 0;
        this.distance = start.dst(end);
        xInterpolation = Interpolation.linear;
        yInterpolation = Interpolation.linear;
    }

    public ProjectileController(Vector2 start, Vector2 direction, float range, float speed, boolean tirer)
    {
        super();
        this.tirer=-1;
        this.start = start;
        this.end = computeEnd(this.start, direction, range);
        this.speed = speed;
        this.elapsedTime = 0;
        this.distance = start.dst(end);
        xInterpolation = Interpolation.linear;
        yInterpolation = Interpolation.linear;
    }

    public void setInterpolation(Interpolation xInterpolation, Interpolation yInterpolation)
    {
        this.xInterpolation = xInterpolation;
        this.yInterpolation = yInterpolation;
    }

    private Vector2 computeEnd(Vector2 start, Vector2 direction, float range)
    {
        direction.nor().scl(range);
        return new Vector2(start.x + direction.x, start.y + direction.y);
    }

    private float getAlpha() { return elapsedTime / distance; }



    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }


            elapsedTime += speed * DeltaTime;
            float alpha = getAlpha();

            float x = xInterpolation.apply(start.x, end.x, alpha);
            float y = yInterpolation.apply(start.y, end.y, alpha);

            this.getOwner().getTransform().setLocation(x, y);

            ArrayList<Collider> ownersColliders = this.getOwner().GetAllComponentsFromClass(Collider.class);
            for (int i = 0; i < ownersColliders.size(); i++) { ownersColliders.get(i).setPositionInGrid(); }

            if (alpha >= 1)
            {
                this.getOwner().getEventDispatcher().DispatchEvent(EventIndex.PROJECTILE_REACHED_DESTINATION, new ProjectileReachDestinationEvent(this));
                this.SetActive(false);
            }


    }
}