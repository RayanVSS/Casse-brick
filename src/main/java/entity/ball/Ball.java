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
    int diametre;
    int vitesse;

    public Ball(int d) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.vitesse = 0;
        this.diametre = d;
    }

    public Ball(Coordinates c, Vector direction, int vitesse, int d) {
        super(c);
        this.direction = direction;
        this.vitesse = vitesse;
        this.diametre = d;
    }

    public int getDiametre() {
        return this.diametre;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public void setDiametre(int d) {
        this.diametre = d;
    }

    public abstract boolean movement();
}
