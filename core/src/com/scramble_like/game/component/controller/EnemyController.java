package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.Collider;
import com.scramble_like.game.essential.event_dispatcher.EventIndex;
import com.scramble_like.game.essential.event_dispatcher.event.game.PlayerDieEvent;
import com.scramble_like.game.map.GameOver;

import java.util.ArrayList;
import java.util.HashSet;

public class EnemyController extends CharacterController
{
    private int currentPoint;
    private float currentDistance;
    private final Vector2[] points;
    private float elapsedTime;
    private Interpolation xInterpolation;
    private Interpolation yInterpolation;

    public EnemyController(AnimationController animationController, AABBCollider collider, float speed, int life, Vector2[] patrol)
    {
        super(animationController, collider, speed, life);
        this.currentPoint = -1;
        this.currentDistance = -1;
        this.elapsedTime = 0;
        this.points = patrol;
        this.xInterpolation = Interpolation.linear;
        this.yInterpolation = Interpolation.linear;
        this.updateTargetPosition();
    }

    public void setInterpolation(Interpolation xInterpolation, Interpolation yInterpolation)
    {
        this.xInterpolation = xInterpolation;
        this.yInterpolation = yInterpolation;
    }

    private void updateTargetPosition()
    {
        this.currentPoint++;
        if (this.currentPoint >= this.points.length) {this.currentPoint = 0;}
        this.currentDistance = this.points[this.currentPoint].dst(this.points[(this.currentPoint + 1) % this.points.length]);
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);

        Vector2 startLocation = this.getOwner().getTransform().getLocation().cpy();

        this.elapsedTime += DeltaTime * this.speed;
        float alpha = this.elapsedTime / this.currentDistance;

        if (alpha >= 1) { this.elapsedTime = 0; this.updateTargetPosition(); alpha = 0; }

        float x = this.xInterpolation.apply(this.points[this.currentPoint].x, this.points[(this.currentPoint + 1) % this.points.length].x, alpha);
        float y = this.yInterpolation.apply(this.points[this.currentPoint].y, this.points[(this.currentPoint + 1) % this.points.length].y, alpha);

        this.getOwner().getTransform().setLocation(x, y);
        ArrayList<Collider> ownersColliders = this.getOwner().GetAllComponentsFromClass(Collider.class);
        for (int i = 0; i < ownersColliders.size(); i++) { ownersColliders.get(i).setPositionInGrid(); }

        this.dx = x - startLocation.x;
        this.dy = y - startLocation.y;

        if ((dx != 0 || dy != 0) && animationController.getState() != AnimationController.AnimationState.WALK)
        {
            animationController.setState(AnimationController.AnimationState.WALK, -1);
        }
        else if (animationController.getState() != AnimationController.AnimationState.IDLE)
        {
            animationController.setState(AnimationController.AnimationState.IDLE, -1);
        }

        super.Update(DeltaTime);
    }

    @Override
    protected void die()
    {
        super.die();
        this.getOwner().DestroyThisInScene();
    }
}
