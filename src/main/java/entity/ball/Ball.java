package entity.ball;

import config.Game;
import entity.Entity;
import entity.racket.Racket;
import entity.brick.Brick;
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

    private Vector direction;
    private int radius;
    private double speed;

    // colision avec racket
    boolean CollisionR = false;

    public Ball(int r) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.radius = r;
    }

    public Ball(Coordinates c, Vector direction, int speed, int d) {
        super(c);
        this.direction = direction;
        this.speed = speed;
        this.radius = d;
    }
    // Setters/getters

    public int getRadius() {
        return this.radius;
    }

    public Vector getDirection() {
        return this.direction;
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

    public boolean getCollisionR() {
        return this.CollisionR;
    }

    public void setDirection(Vector d) {
        this.direction = d;
    }

    public void setSpeed(int v) {
        this.speed = v;
    }

    public boolean intersectBrick(Brick b) {

        // Coordonnées du centre du cercle
        double circleCenterX = getC().getX() + getRadius();
        double circleCenterY = getC().getY() + getRadius();

        double rectLeft = b.getC().getX();
        double rectTop = b.getC().getY();

        double rectRight = b.getC().getX() + GameConstants.BRICK_WIDTH;
        double rectBottom = b.getC().getY() + GameConstants.BRICK_HEIGHT;

        // Distance horizontale entre le centre du cercle et le rectangle
        double distX = Math.abs(circleCenterX - (rectLeft + rectRight) / 2);
        double halfRectWidth = GameConstants.BRICK_WIDTH / 2;

        // Distance verticale entre le centre du cercle et le rectangle
        double distY = Math.abs(circleCenterY - (rectTop + rectBottom) / 2);
        double halfRectHeight = GameConstants.BRICK_HEIGHT / 2;

        // Intersection si la distance horizontale est inférieure à la moitié de la largeur du rectangle plus le rayon du cercle,
        // et la distance verticale est inférieure à la moitié de la hauteur du rectangle plus le rayon du cercle

        return distX <= halfRectWidth + getRadius() && distY <= halfRectHeight + getRadius();
    }

    public abstract boolean movement();

    public boolean isOverlap(Racket r) {
        // Trouver le point le plus proche du cercle dans le rectangle
        double closestX = clamp(this.getC().getX(), r.getC().getX(), this.getC().getX() + r.getLargeur());
        double closestY = clamp(this.getC().getY(), r.getC().getY(), this.getC().getY() + r.getLongueur());

        // Calculer la distance entre le centre du cercle et ce point
        double distanceX = this.getC().getX() - closestX;
        double distanceY = this.getC().getY() - closestY;

        // Si la distance est inférieure au rayon du cercle, ils se chevauchent
        return (distanceX * distanceX + distanceY * distanceY) < (GameConstants.DEFAULT_BALL_RADIUS
                * GameConstants.DEFAULT_BALL_RADIUS);
    }

    public boolean isOverlap2(Racket r) {

        double deltaX = this.getC().getX() - Math.max(r.getC().getX(), Math.min(this.getC().getX(), r.getC().getX() + r.getLongueur()));
        double deltaY = this.getC().getY() - Math.max(r.getC().getY(), Math.min(this.getC().getY(), r.getC().getY() + r.getLargeur()));

        return (deltaX * deltaX + deltaY * deltaY) < (this.getRadius() * this.getRadius());
    }

    public double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
    public void reset(){
        this.setC(GameConstants.DEFAULT_BALL_START_COORDINATES);
    }
}
