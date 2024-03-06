package physics.config;

import java.util.Random;

import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;

/***************************************************************************
 *                  Explication de classe PhysicSetting  :  
 * 
 * Cette classe contient toutes les informations de la partie physique de la 
 * simulation    
 *        
 * @lien https://www.irif.fr/~emiquey/stuff/TIPE.pdf   
 * 
 * @var Ball ball : La balle pour la simulation 
 * @var Racket racket : La raquette pour la simulation
 * 
 * @param Speed : la vitesse de la balle
 * @param Radius : le rayon de la balle
 * @param Direction : la direction de la balle
 * @param Speed_Wind : la vitesse du vent
 * @param Direction_Wind : la direction du vent
 * @param Wind : le vecteur vent qui influe sur la balle (vitesse et direction)
 * @param Gravite : la gravite de la simulation
 * @param Mass : la masse de la balle
 * @param stop_bounce : le maximum pour que la balle arrete de rebondir
 * @param retention : le coefficient de retention de la balle
 * @param friction : le coefficient de friction de la balle
 * 
 * @fonction checkGravity : permet de verifier si la balle est en contact avec le sol ou les murs 
 * et de lui appliquer la gravite de façon réaliste
 * @fonction vectorWind : permet de creer un vecteur vent en fonction de la vitesse et de la direction
 * @fonction randomDirection : permet de creer un vecteur direction aleatoire 
 * 
 * @author Ilias Bencheikh 
 * @version 1.0
 **************************************************************************/

public class PhysicSetting {

    // variables pour la balle
    private int Radius;
    private Vector Wind;

    // variables par défaut pour la simulation
    public static int Speed_Ball = 0;
    public static int Speed_Wind = 0;
    public static int Direction_Wind = 0;
    public static double Gravite = 0;
    public static double Mass = 1;

    // variables pour la simulation
    public static final double stop_bounce = 0.3;
    public static double retention = 1;
    public static final double friction_sol = 0.1;
    public static final double friction_air = 0.01;
    public static final double MAX_VELOCITY = 10;

    public PhysicSetting(){
        Radius = GameConstants.DEFAULT_BALL_RADIUS/2;
        this.Wind = vectorWind(Speed_Wind, Direction_Wind);
    }

    public PhysicSetting(int R , double gravite , Vector Wind , double mass){
        this.Radius = R;
        Gravite = gravite;
        Mass = mass;
        this.Wind = Wind;
        retention = 0.8;
    }

    public void CalculateAngle(Vector d){
        double angle = Math.atan2(d.getY(), d.getX());
        if(angle < 0){
            angle += 2*Math.PI;
        }
        angle = Math.toDegrees(angle);
        System.out.println("Angle : " + angle);
    }

    public void checkGravity(Coordinates c, Vector d) {
        if (c.getY() < PhysicEngine.DEFAULT_WINDOW_HEIGHT - Radius) {
            d.setY(d.getY() + Gravite*Mass);
        } else {
            if (d.getY() > stop_bounce) {
                d.setY(-d.getY()* retention);
            } else {
                if (Math.abs(d.getY()) <= stop_bounce) {
                    d.setY(0);
                }
            }
            if ((c.getX() < Radius && d.getX() < 0) || (c.getX() > PhysicEngine.DEFAULT_WINDOW_WIDTH - Radius && d.getX() > 0)) {
                d.setX(-d.getX()* retention);
                if (Math.abs(d.getX()) < stop_bounce) {
                    d.setX(0);
                }
            }
            if (d.getY() == 0 && d.getX() != 0) {
                if (d.getX() > 0) {
                    d.setX(d.getX()-friction_sol);
                } else if (d.getX() < 0) {
                    d.setX(d.getX()+friction_sol);
                }
            }
        }
    }

    public static void UpdateFrictionRacket(Vector f){
        if(f.getX() > 0){
            f.setX(f.getX()-0.2);
        }else if(f.getX() < 0){
            f.setX(f.getX()+0.2);
        }
    }


    public Vector vectorWind(int Speed , int Direction){
       switch (Direction) {
        case 1:
            return new Vector(new Coordinates(Speed, 0));
        case 2:
            return new Vector(new Coordinates(0, Speed));
        case 3:
            return new Vector(new Coordinates(-Speed, 0));
        case 4:
            return new Vector(new Coordinates(0, -Speed));
        default:
            return new Vector(new Coordinates(0, 0));
       }
    }

    public Vector randomDirection() {
        Random random = new Random();
        double i = -1 + (1 - (-1)) * random.nextDouble();
        double j = -1 + (1 - (-1)) * random.nextDouble();
        return new Vector(new Coordinates(i, j));
    }

    public boolean checkCollisionRacket(Racket r, Coordinates c, Vector d){
        boolean verifX = c.getX() > r.getC().getX() && c.getX() < r.getC().getX() + r.largeur;
        boolean verifY = c.getY() > r.getC().getY() && c.getY() < r.getC().getY() + r.longueur;
        boolean verifX1 = c.getX()<= r.getC().getX() && c.getX() > r.getC().getX() - Radius || c.getX() >= r.getC().getX() + r.largeur && c.getX() < r.getC().getX() + r.largeur + Radius;
        boolean verifY1 =  c.getY() >= r.getC().getY() && c.getY() < r.getC().getY() + r.longueur;
        if(verifX1 && verifY1){
            d.setX(-d.getX());
            PhysicEngine.CollisionR_Side = true;
            return true;
        }
        return verifX && verifY;
    }

    // Getters and Setters

    public int getRadius() {
        return this.Radius;
    }

    public double getGravite() {
        return Gravite;
    }

    public double getMass() {
        return Mass;
    }

    public Vector getWind() {
        return this.Wind;
    }

    public void setWind(Vector Wind) {
        this.Wind = Wind;
    }

    public void setRadius(int Radius) {
        this.Radius = Radius;
    }

    public double getRetention() {
        return retention;
    }
    
}
