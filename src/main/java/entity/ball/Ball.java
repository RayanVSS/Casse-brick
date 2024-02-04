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

    private Vector direction;
    private int diametre;
    private int speed;

    public Ball(int d) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.speed = 0;
        this.diametre = d;
    }

    public Ball(Coordinates c, Vector direction, int speed,int d) {
        super(c);
        this.direction = direction;
        this.speed = speed;
        this.diametre = d;
    }
    //Setters/getters

    public int getDiametre() {
        return this.diametre;
    }
    public Vector getDirection() {
        return this.direction;
    }
    public int getSpeed() {
        return this.speed;
    }
    public void setDiametre(int d) {
        this.diametre = d;
    }
    public void setDirection(Vector d) {
        this.direction = d;
    }
    public void setSpeed(int v) {
        this.speed = v;
    }


    public abstract boolean movement();
}
