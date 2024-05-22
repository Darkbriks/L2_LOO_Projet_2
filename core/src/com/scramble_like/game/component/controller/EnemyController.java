package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.chaos.AABBCollider;
import com.scramble_like.game.essential.chaos.Collider;

import java.util.ArrayList;

public class EnemyController extends CharacterController
{
    private int currentPoint;
    private float currentDistance;
    private final Vector2[] points;
    private float elapsedTime;
    private float timeBetweenWaypoints;
    private float elapsedWaitingTime;
    private boolean isWaiting;
    private Interpolation xInterpolation;
    private Interpolation yInterpolation;

    public EnemyController(AnimationController animationController, AABBCollider collider, float speed, int life, Vector2[] patrol, float timeBetweenWaypoints)
    {
        super(animationController, collider, speed, life);
        this.currentPoint = -1;
        this.currentDistance = -1;
        this.elapsedTime = 0;
        this.timeBetweenWaypoints = timeBetweenWaypoints;
        this.elapsedWaitingTime = 0;
        this.isWaiting = false;
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

    @Override
    public void BeginPlay()
    {
        super.BeginPlay();
        this.isWaiting = false;
    }

    private void updateTargetPosition()
    {
        this.currentPoint++;
        if (this.currentPoint >= this.points.length) {this.currentPoint = 0;}
        this.currentDistance = this.points[this.currentPoint].dst(this.points[(this.currentPoint + 1) % this.points.length]);

        if (this.timeBetweenWaypoints != 0)
        {
            this.isWaiting = true; this.elapsedWaitingTime = 0;
            animationController.setState(AnimationController.AnimationState.IDLE, -1);
        }
    }

    @Override
    public void Update(float DeltaTime)
    {
        if (!this.IsActive()) { return; }
        super.Update(DeltaTime);

        this.elapsedWaitingTime += DeltaTime;
        if (this.isWaiting)
        {
            if (this.elapsedWaitingTime >= this.timeBetweenWaypoints)
            {
                this.isWaiting = false;
                animationController.setState(AnimationController.AnimationState.WALK, -1);
            }
            return;
        }

        this.elapsedTime += DeltaTime * this.speed;
        float alpha = this.elapsedTime / this.currentDistance;

        if (alpha >= 1) { this.elapsedTime = 0; this.updateTargetPosition(); alpha = 0; }

        float x = this.xInterpolation.apply(this.points[this.currentPoint].x, this.points[(this.currentPoint + 1) % this.points.length].x, alpha);
        float y = this.yInterpolation.apply(this.points[this.currentPoint].y, this.points[(this.currentPoint + 1) % this.points.length].y, alpha);

        this.getOwner().getTransform().setLocation(x, y);
        ArrayList<Collider> ownersColliders = this.getOwner().GetAllComponentsFromClass(Collider.class);
        for (int i = 0; i < ownersColliders.size(); i++) { ownersColliders.get(i).setPositionInGrid(); }

        super.Update(DeltaTime);
    }

    @Override
    protected void die()
    {
        super.die();
        this.getOwner().DestroyThisInScene();
    }
}
