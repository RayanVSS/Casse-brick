package physics;

import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;
import entity.ball.Ball;
import java.util.Random;

public class Outline {

    Vector vitesse;
    int radius;
    Vector direction;
    Vector vent;
    
    public static int Vitesse_Vent = 0;
    public static int Direction_Vent = 0;
    public static double Gravite = 1;
    public static double Mass = 1;
    public static final double stop_bounce = 0.3;
    public static final double retention = 0.8;
    public static final double friction = 0.1;
    
    public Outline(Vector vitesse , int radius , double gravite , Vector vent , double mass){
        this.vitesse = vitesse;
        this.radius = radius;
        this.direction = randomDirection();
        Gravite = gravite;
        Mass = mass;
        this.vent = vent;
    }

    public Outline(){
        this.vitesse = new Vector(new Coordinates(1,1));
        this.radius = GameConstants.DEFAULT_BALL_RADIUS/2;
        this.direction = randomDirection();
        this.vent = vectorVent(Vitesse_Vent, Direction_Vent);
    }
    /* 
    public void checkGravity(Ball ball) {
        if (ball.getC().getY() < GameConstants.DEFAULT_WINDOW_HEIGHT - radius) {
            vitesse.setY(vitesse.getY() + Gravite);
        } else {
            if (vitesse.getY() > stop_bounce) {
                vitesse.setY(-vitesse.getY()* retention);
            } else {
                if (Math.abs(vitesse.getY()) <= stop_bounce) {
                    vitesse.setY(0);
                }
            }
            if ((ball.getC().getX() < radius && vitesse.getX() < 0) || (ball.getC().getX() > GameConstants.DEFAULT_WINDOW_WIDTH - radius && vitesse.getX() > 0)) {
                vitesse.setX(-vitesse.getX()* retention);
                if (Math.abs(vitesse.getX()) < stop_bounce) {
                    vitesse.setX(0);
                }
            }
            if (vitesse.getY() == 0 && vitesse.getX() != 0) {
                if (vitesse.getX() > 0) {
                    vitesse.setX(vitesse.getX()-friction);
                } else if (vitesse.getX() < 0) {
                    vitesse.setX(vitesse.getX()+friction);
                }
            }
        }
    }
    */

    public Vector vectorVent(int vitesse , int direction){
       switch (direction) {
        case 1:
            return new Vector(new Coordinates(vitesse, 0));
        case 2:
            return new Vector(new Coordinates(0, vitesse));
        case 3:
            return new Vector(new Coordinates(-vitesse, 0));
        case 4:
            return new Vector(new Coordinates(0, -vitesse));
        default:
            return new Vector(new Coordinates(0, 0));
       }
    }

    public Vector getDirection() {
        return this.direction;
    }

    public Vector getVitesse() {
        return this.vitesse;
    }

    public int getDiametre() {
        return this.radius;
    }

    public double getGravite() {
        return Gravite;
    }

    public double getMass() {
        return Mass;
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

    public void setVitesse(Vector vitesse) {
        this.vitesse = vitesse;
    }
   
}
