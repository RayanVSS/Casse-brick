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
public class Ball extends Entity {

    private Vector direction;
    private int vitesse;

    public Ball() {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.vitesse = 0;
    }

    public Ball(Coordinates c, Vector direction, int vitesse) {
        super(c);
        this.direction = direction;
        this.vitesse = vitesse;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

}
