package physics;

import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;

import java.util.Random;

public class Outline {

    int vitesse;
    int diametre;
    Vector direction;
    Vector gravite;
    Vector poid;
    Vector vent;
    
    public Outline(int vitesse, int diametre , Vector gravite , Vector vent){
        this.vitesse = vitesse;
        this.diametre = diametre;
        this.direction = randomDirection();
        this.gravite = gravite;
        this.poid = gravite;
        poid.multiply(diametre);
        this.vent = vent;
    }

    public Outline(){
        this.vitesse = GameConstants.DEFAULT_BALL_SPEED;
        this.diametre = GameConstants.DEFAULT_BALL_RADIUS/2;
        this.direction = randomDirection();
        this.gravite = new Vector(new Coordinates(0,0.01));
        this.poid = gravite;
        poid.multiply(diametre);
        this.vent = new Vector(new Coordinates(1, 0));
        direction.add(gravite);
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getDiametre() {
        return this.diametre;
    }

    public Vector getGravite() {
        return this.gravite;
    }

    public Vector getPoid() {
        return this.poid;
    }

    public Vector randomDirection() {
        Random random = new Random();
        double i = -1 + (1 - (-1)) * random.nextDouble();
        double j = -1 + (1 - (-1)) * random.nextDouble();
        return new Vector(new Coordinates(i, j));
    }

    public Vector getVent() {
        return this.vent;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = (int)vitesse;
    }
   
}
