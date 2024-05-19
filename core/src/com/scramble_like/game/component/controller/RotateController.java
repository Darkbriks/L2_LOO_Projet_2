package com.scramble_like.game.component.controller;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.scramble_like.game.essential.Component;

public class RotateController extends Component {
    private int currentPoint;
    private float currentDistance;
    private final Vector2[] points;
    private final float speed;
    private float elapsedTime;
    private Interpolation xInterpolation;
    private Interpolation yInterpolation;
    private boolean movingRight;

    public RotateController(Vector2[] patrol, float speed) {
        super();
        this.currentPoint = -1;
        this.currentDistance = -1;
        this.speed = speed;
        this.elapsedTime = 0;
        this.points = patrol;
        this.xInterpolation = Interpolation.linear;
        this.yInterpolation = Interpolation.linear;
        this.movingRight = true;
    }

    @Override
    public void BeginPlay() {
        super.BeginPlay();
        this.updateTargetPosition();
    }

    public void setInterpolation(Interpolation xInterpolation, Interpolation yInterpolation) {
        this.xInterpolation = xInterpolation;
        this.yInterpolation = yInterpolation;
    }

    private void updateTargetPosition() {
        this.currentPoint++;
        if (this.currentPoint >= this.points.length) {
            this.currentPoint = 0;
        }
        this.currentDistance = this.points[this.currentPoint].dst(this.points[(this.currentPoint + 1) % this.points.length]);

        // Determine direction and rotate if necessary
        Vector2 currentPosition = this.points[this.currentPoint];
        Vector2 nextPosition = this.points[(this.currentPoint + 1) % this.points.length];
        boolean newMovingRight = nextPosition.x > currentPosition.x;
        if (newMovingRight != this.movingRight) {
            this.getOwner().getTransform().Rotate(180, 0);
            this.movingRight = newMovingRight;
        }
    }

    @Override
    public void Update(double DeltaTime) {
        if (!this.IsActive()) {
            return;
        }

        this.elapsedTime += (float) DeltaTime * this.speed;
        float alpha = this.elapsedTime / this.currentDistance;

        if (alpha >= 1) {
            this.elapsedTime = 0;
            this.updateTargetPosition();
            alpha = 0;
        }

        float x = this.xInterpolation.apply(this.points[this.currentPoint].x, this.points[(this.currentPoint + 1) % this.points.length].x, alpha);
        float y = this.yInterpolation.apply(this.points[this.currentPoint].y, this.points[(this.currentPoint + 1) % this.points.length].y, alpha);

        this.getOwner().getTransform().setLocation(x, y);
    }
}
