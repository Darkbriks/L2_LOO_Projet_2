package com.scramble_like.game.component;

import com.scramble_like.game.essential.Component;

import java.awt.*;

public class EnemiePatrol extends Component {
    private int nb_points;
    private int currentPoint;
    private Point[] points;

    public EnemiePatrol(int nb_points) {
        super();
        this.nb_points = nb_points;
        this.currentPoint = 0;
        this.points = this.initializePoints(nb_points);
    }

    private Point[] initializePoints(int nbPoints) {
        Point[] points = new Point[nb_points];
        for (int i = 0; i < nb_points; i++) {points[i] = new Point(i, i);}
        return points;
    }
    public Point move() {
        this.currentPoint++;
        if (this.currentPoint >= this.nb_points) {this.currentPoint = 0;}
        return this.points[this.currentPoint];
    }
    @Override
    public void Update(double DeltaTime) {Point newPosition = this.move();}

}
