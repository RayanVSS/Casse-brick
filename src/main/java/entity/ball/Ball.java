package entity.ball;

import entity.Entity;
import geometry.*;

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
    double vitesse;

    //colision avec racket
    boolean CollisionR = false;

    public Ball(int d) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.vitesse = 0;
        this.radius = d;
    }

    public Ball(Coordinates c, Vector direction, int vitesse, int d) {
        super(c);
        this.direction = direction;
        this.vitesse = vitesse;
        this.radius = d;
    }

    public int getRadius() {
        return this.radius;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public double getVitesse() {
        return this.vitesse;
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
}
