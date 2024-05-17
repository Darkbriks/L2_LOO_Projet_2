package com.scramble_like.game.component;

import com.badlogic.gdx.math.Vector3;
import com.scramble_like.game.essential.Component;
import com.scramble_like.game.essential.utils.Transform;

import java.awt.*;

public class EnemiePatrol extends Component {
    private int nb_points;
    private int currentPoint;
    private Vector3[] points;
    private float speed;

    public EnemiePatrol(int nb_points, float speed) {
        super();
        this.nb_points = nb_points;
        this.currentPoint = 0;
        this.speed = speed;
        this.points = this.initializePoints(nb_points);
    }

    private Vector3[] initializePoints(int nbPoints) {
        Vector3[] points = new Vector3[nb_points];
        float sideLength = 100;
        for (int i = 0; i < nb_points; i++) {

            float angle = (float) (i * 2 * Math.PI / nb_points);
            points[i] = new Vector3((float) Math.cos(angle) * sideLength,(float) Math.sin(angle) * sideLength,0);
        }
        return points;
    }

    private void updateTargetPosition() {
        this.currentPoint++;
        if (this.currentPoint >= this.nb_points) {this.currentPoint = 0;}
    }

    private void move() {
        Transform transform = this.getOwner().getTransform();
        Vector3 currentPosition = transform.getLocation();
        Vector3 targetPosition = this.points[this.currentPoint];
        Vector3 direction = new Vector3(targetPosition).sub(currentPosition).nor();
        float remainingDistance = currentPosition.dst(targetPosition);

        if (remainingDistance <= speed) {
            transform.setLocation(targetPosition);
            updateTargetPosition();
        } else {
            Vector3 movement = direction.scl(speed);
            transform.Translate(movement);
        }
    }

    @Override
    public void Update(double DeltaTime) {
        if (!this.IsActive()) {return;}
        move();
    }
}
