package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.utils.Transform;

import java.awt.*;

public class EnemiePatrol extends Component
{
    private int currentPoint;
    private final Vector2[] points;
    private final float speed;

    public EnemiePatrol(int nb_points, int sideLength, float speed)
    {
        super();
        this.currentPoint = 0;
        this.speed = speed;
        this.points = this.initializePoints(nb_points, sideLength);
    }

    public EnemiePatrol(Vector2[] patrol, float speed)
    {
        super();
        this.currentPoint = 0;
        this.speed = speed;
        this.points = patrol;
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

    private void updateTargetPosition()
    {
        this.currentPoint++;
        if (this.currentPoint >= this.points.length) {this.currentPoint = 0;}
    }

    private void move(double dt)
    {
        Transform transform = this.getOwner().getTransform();
        Vector2 currentPosition = new Vector2(this.getOwner().getTransform().getLocation().x, this.getOwner().getTransform().getLocation().y);
        Vector2 targetPosition = this.points[this.currentPoint];
        Vector2 direction = new Vector2(targetPosition).sub(currentPosition).nor();
        float remainingDistance = currentPosition.dst(targetPosition);

        if (remainingDistance <= speed * dt)
        {
            transform.setLocation(targetPosition);
            updateTargetPosition();
        }
        else
        {
            Vector2 movement = direction.scl((float) (speed * dt), (float) (speed * dt));
            transform.Translate(movement);
        }
    }

    @Override
    public void Update(double DeltaTime)
    {
        if (!this.IsActive()) { return; } move(DeltaTime);
    }
}
