package physics.config;

import java.util.Random;

import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import physics.entity.Ball;

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
    private int Speed_Ball = 0;
    private int Speed_Wind = 0;
    private int Direction_Wind = 0;

    // variables pour la physique réaliste
    private boolean Gravity = false;
    private double Gravite = 0.1;
    private double Mass = 1;
    private final double stop_bounce = 0.3;
    private double retention = 0.8;
    private final double friction_sol = 0.1;
    private final double friction_air = 0.01;
    private final double MAX_VELOCITY = 10;

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

    public static String CalculateAngle(Vector d){
        double angle = Math.atan2(d.getY(), d.getX());
        if(angle < 0){
            angle += 2*Math.PI;
        }
        angle = Math.toDegrees(angle);
        return "Angle :"+String.valueOf(angle);
    }

    public static double CalculateSpeed(Vector d){
        double speed = Math.sqrt(d.getX()*d.getX() + d.getY()*d.getY());
        return speed;
    }

    public void checkGravity(Coordinates c, Vector d) {
        if(!Gravity){
            return;
        }
        if (c.getY() < GameConstants.DEFAULT_GAME_ROOT_WIDTH - Radius) {
            d.setY(d.getY() + Gravite*Mass);
        } else {
            if (d.getY() > stop_bounce) {
                d.setY(-d.getY()* retention);
            } else {
                if (Math.abs(d.getY()) <= stop_bounce) {
                    d.setY(0);
                }
            }
            if ((c.getX() < Radius && d.getX() < 0) || (c.getX() > GameConstants.DEFAULT_WINDOW_WIDTH - Radius && d.getX() > 0)) {
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
            return true;
        }
        return verifX && verifY;
    }

    public boolean checkCollisionRacket(Racket r,Ball b){
        boolean verifX = b.getC().getX() > r.getC().getX() && b.getC().getX() < r.getC().getX() + r.largeur;
        boolean verifY = b.getC().getY() > r.getC().getY() && b.getC().getY() < r.getC().getY() + r.longueur;
        boolean verifX1 = b.getC().getX()<= r.getC().getX() && b.getC().getX() > r.getC().getX() - Radius || b.getC().getX() >= r.getC().getX() + r.largeur && b.getC().getX() < r.getC().getX() + r.largeur + Radius;
        boolean verifY1 =  b.getC().getY() >= r.getC().getY() && b.getC().getY() < r.getC().getY() + r.longueur;
        if(verifX1 && verifY1){
            b.getDirection().setX(-b.getDirection().getX());
            b.CollisionR_Side = true;
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
    
    public void setRetention(double retention) {
        this.retention = retention;
    }

    public double getFrictionSol() {
        return friction_sol;
    }

    public double getFrictionAir() {
        return friction_air;
    }

    public double getMAX_VELOCITY() {
        return MAX_VELOCITY;
    }

    public void setGravite(double gravite) {
        Gravite = gravite;
    }

    public void setMass(double mass) {
        Mass = mass;
    }

    public void setSpeed_Ball(int speed_Ball) {
        Speed_Ball = speed_Ball;
    }

    public void setSpeed_Wind(int speed_Wind) {
        Speed_Wind = speed_Wind;
    }

    public void setDirection_Wind(int direction_Wind) {
        Direction_Wind = direction_Wind;
    }

    public int getSpeed_Ball() {
        return Speed_Ball;
    }

    public int getSpeed_Wind() {
        return Speed_Wind;
    }

    public int getDirection_Wind() {
        return Direction_Wind;
    }

    public double getStop_bounce() {
        return stop_bounce;
    }

    public void changeGravity(){
        Gravity = !Gravity;
    }

    public boolean getGravity(){
        return Gravity;
    }
}
