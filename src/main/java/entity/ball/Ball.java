package entity.ball;

import entity.Entity;
import entity.racket.Racket;
import geometry.*;
import utils.GameConstants;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public abstract class Ball extends Entity {

    Vector direction;
    int radius;
    double speed;

    // colision avec racket
    boolean CollisionR = false;

    public Ball(int d) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.radius = d;
    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        super(c);
        this.direction = direction;
        this.speed = speed;
        this.radius = d;
    }

    public int getRadius() {
        return this.radius;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public Vector setDirection(Vector v) {
        return this.direction = v;
    }

    public void setSpeed(double v) {
        this.speed = v;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setRadius(int d) {
        this.radius = d;
    }

    public void setCollisionR(boolean b) {
        this.CollisionR = b;
    }

    public Boolean getCollisionR() {
        return this.CollisionR;
    }

    public abstract boolean movement();

    public boolean isOverlap(Racket r) {
        // Trouver le point le plus proche du cercle dans le rectangle
        double closestX = clamp(this.getC().getX(),r.getC().getX(), this.getC().getX() + r.getWidth());
        double closestY = clamp(this.getC().getY(),r.getC().getY(), this.getC().getY() + r.getHeight());

        // Calculer la distance entre le centre du cercle et ce point
        double distanceX = this.getC().getX() - closestX;
        double distanceY = this.getC().getY() - closestY;

        // Si la distance est inférieure au rayon du cercle, ils se chevauchent
        return (distanceX * distanceX + distanceY * distanceY) < (GameConstants.DEFAULT_BALL_RADIUS
                * GameConstants.DEFAULT_BALL_RADIUS);
    }

    public double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
