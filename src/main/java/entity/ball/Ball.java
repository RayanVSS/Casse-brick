package entity.ball;

import entity.Entity;
import entity.brick.Brick;
import geometry.*;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public abstract class Ball extends Entity {

    protected Vector direction;
    protected int rayon;
    protected double vitesse;

    //colision avec racket
    boolean CollisionR = false;

    public Ball(int r) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.vitesse = 0;
        this.rayon = r;
    }

    public Ball(Coordinates c, Vector direction, int vitesse, int r) {
        super(c);
        this.direction = direction;
        this.vitesse = vitesse;
        this.rayon = r;
    }

    public int getRayon() {
        return this.rayon;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public double getVitesse() {
        return this.vitesse;
    }

    public void setRayon(int r) {
        this.rayon = r;
    }

    public void setCollisionR(boolean b) {
        this.CollisionR = b;
    }

    public boolean getCollisionR() {
        return this.CollisionR;
    }

    public boolean intersectBrick(Brick b) {
        return false;
    }

    public abstract boolean movement();
}
