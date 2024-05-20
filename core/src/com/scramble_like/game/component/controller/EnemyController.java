package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.chaos.Collider;

import java.util.ArrayList;
import java.util.HashSet;

public class EnemyController extends Component
{
    private int currentPoint;
    private float currentDistance;
    private final Vector2[] points;
    private final float speed;
    private float elapsedTime;
    private Interpolation xInterpolation;
    private Interpolation yInterpolation;

    public EnemyController(int nb_points, int sideLength, float speed)
    {
        super();
        this.currentPoint = -1;
        this.currentDistance = -1;
        this.speed = speed;
        this.elapsedTime = 0;
        this.points = this.initializePoints(nb_points, sideLength);
        this.xInterpolation = Interpolation.linear;
        this.yInterpolation = Interpolation.linear;
        this.updateTargetPosition();
    }

    public EnemyController(Vector2[] patrol, float speed)
    {
        super();
        this.currentPoint = -1;
        this.currentDistance = -1;
        this.speed = speed;
        this.elapsedTime = 0;
        this.points = patrol;
        this.xInterpolation = Interpolation.linear;
        this.yInterpolation = Interpolation.linear;
        this.updateTargetPosition();
    }

    private Vector2[] initializePoints(int nbPoints, int sideLength)
    {
        Vector2[] points = new Vector2[nbPoints];
        for (int i = 0; i < nbPoints; i++)
        {
            float angle = (float) (i * 2 * Math.PI / nbPoints);
            points[i] = new Vector2((float) Math.cos(angle) * sideLength,(float) Math.sin(angle) * sideLength);
        }
        return points;
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

        this.elapsedTime += DeltaTime * this.speed;
        float alpha = this.elapsedTime / this.currentDistance;

        if (alpha >= 1) { this.elapsedTime = 0; this.updateTargetPosition(); alpha = 0; }

        float x = this.xInterpolation.apply(this.points[this.currentPoint].x, this.points[(this.currentPoint + 1) % this.points.length].x, alpha);
        float y = this.yInterpolation.apply(this.points[this.currentPoint].y, this.points[(this.currentPoint + 1) % this.points.length].y, alpha);

        this.getOwner().getTransform().setLocation(x, y);
        ArrayList<Collider> ownersColliders = this.getOwner().GetAllComponentsFromClass(Collider.class);
        for (int i = 0; i < ownersColliders.size(); i++) { ownersColliders.get(i).setPositionInGrid(); }
    }
}
