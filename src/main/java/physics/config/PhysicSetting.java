package physics.config;

import java.util.Random;

import physics.entity.Ball;
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
 * @lien https://youtu.be/6tbk8w3YUIg?si=tlYFOf8Xj7AYuihJ
 * @lien https://youtu.be/fUObjVMI3Jw?si=LnWxDIIRvN-Fjujk
 * @lien https://youtu.be/eED4bSkYCB8?si=ItFmIaFxVdEG7qDt
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

    public static double DEFAULT_WINDOW_WIDTH ;
    public static double DEFAULT_WINDOW_HEIGHT;
    public static Coordinates DEFAULT_BALL_START_COORDINATES;

    // variables pour la balle
    private int Radius;
    private Vector Wind;

    // variables par défaut pour la simulation
    private double Speed_Ball = 0;
    private int Speed_Wind = 0;
    private int Direction_Wind = 0;

    // variables pour la physique réaliste
    private boolean Gravity = false;
    private double Gravite = 0.1;
    private double Mass = 1;
    private final double stop_bounce = 0.02;
    private double retention = 0.9;
    private final double friction_sol = 0.1;
    private final double friction_air = 0.01;
    private final double MAX_VELOCITY = 10;

    static Random rand = new Random();
    public static Vector NEW_BALL_DIRECTION() {
        return new Vector(new Coordinates(rand.nextBoolean() ? 1 : -1, rand.nextBoolean() ? 1 : -1));
    };

    public PhysicSetting(){
        Radius = GameConstants.DEFAULT_BALL_RADIUS;
        this.Wind = vectorWind(Speed_Wind, Direction_Wind);
        DEFAULT_WINDOW_HEIGHT = GameConstants.DEFAULT_WINDOW_HEIGHT;
        DEFAULT_WINDOW_WIDTH = GameConstants.DEFAULT_WINDOW_WIDTH;
        DEFAULT_BALL_START_COORDINATES=new Coordinates(DEFAULT_WINDOW_WIDTH/ 2, DEFAULT_WINDOW_HEIGHT / 2);
    }

    public PhysicSetting(int R , double WIDTH , double HEIGHT){
        this.Radius = R;
        DEFAULT_WINDOW_HEIGHT = HEIGHT;
        DEFAULT_WINDOW_WIDTH = WIDTH;
        DEFAULT_BALL_START_COORDINATES=new Coordinates(DEFAULT_WINDOW_WIDTH/ 2, DEFAULT_WINDOW_HEIGHT / 2);
    }

    public PhysicSetting(int R , double gravite , Vector Wind , double mass){
        this.Radius = R;
        Gravite = gravite;
        Mass = mass;
        this.Wind = Wind;
    }

    public static String CalculateAngle(Ball b){
        //(90-b.getRotation().getAngle())
        return String.format("%.2f", Math.toDegrees(Math.atan2(b.getY(), b.getX())));
    }

    public static String CalculateRotation(Ball b){
        return (b.getRotation().getAngle()==0?"":b.getRotation().getAngle()>0?"Droite":" Gauche")+" "+String.format("%.2f", Math.abs(b.getRotation().getAngle()));
    }

    public static String CalculateSpeed(Vector d){
        return String.format("%.2f", Math.sqrt(d.getX()*d.getX() + d.getY()*d.getY()));
    }

    public void checkGravity(Coordinates c, Vector d) {
        if (!Gravity) {
            return;
        }
        if (c.getY() < DEFAULT_WINDOW_HEIGHT - Radius) {
            d.setY(d.getY() + Gravite*Mass);
        }
        else if(c.getY()+Radius>=DEFAULT_WINDOW_HEIGHT){
            d.setY(d.getY()*retention);
        }
        else if(d.getY()<stop_bounce && c.getY()+Radius>=DEFAULT_WINDOW_HEIGHT){
            d.setY(0);
        }
        if (d.getY() == 0 && d.getX() != 0) {
            if (d.getX() > 0) {
                d.setX(d.getX()-friction_sol);
            } else if (d.getX() < 0) {
                d.setX(d.getX()+friction_sol);
            }
        }
    }

    public Vector vectorWind(int Speed, int Direction) {
        switch (Direction) {
            case 0:
                return new Vector(new Coordinates(Speed, 0));
            case 1:
                return new Vector(new Coordinates(-Speed, 0));
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

    public boolean checkCollisionRacket(Racket r, Coordinates c, Vector d) {
        boolean verifX = c.getX() > r.getC().getX() && c.getX() < r.getC().getX() + r.largeur;
        boolean verifY = c.getY() > r.getC().getY() && c.getY() < r.getC().getY() + r.longueur;
        boolean verifX1 = c.getX() <= r.getC().getX() && c.getX() > r.getC().getX() - Radius
                || c.getX() >= r.getC().getX() + r.largeur && c.getX() < r.getC().getX() + r.largeur + Radius;
        boolean verifY1 = c.getY() >= r.getC().getY() && c.getY() < r.getC().getY() + r.longueur;
        if (verifX1 && verifY1) {
            d.setX(-d.getX());
            return true;
        }
        return verifX && verifY;
    }

    public boolean checkCollisionRacket(Racket r, Ball b) {
        boolean verifX = b.getC().getX() > r.getC().getX() && b.getC().getX() < r.getC().getX() + r.largeur;
        boolean verifY = b.getC().getY() > r.getC().getY() && b.getC().getY() < r.getC().getY() + r.longueur;
        boolean verifX1 = b.getC().getX() <= r.getC().getX() && b.getC().getX() > r.getC().getX() - Radius
                || b.getC().getX() >= r.getC().getX() + r.largeur
                        && b.getC().getX() < r.getC().getX() + r.largeur + Radius;
        boolean verifY1 = b.getC().getY() >= r.getC().getY() && b.getC().getY() < r.getC().getY() + r.longueur;
        if (verifX1 && verifY1) {
            b.getDirection().setX(-b.getDirection().getX());
            return true;
        }
        return verifX && verifY;
    }

    public void checkWind(Ball b){
        b.getDirection().setX(b.getDirection().getX()+Wind.getX());
        if(b.getC().getX()>=DEFAULT_WINDOW_WIDTH-b.getRadius() && b.getDirection().getX()<=1 && Wind.getX()>0){
            b.getDirection().setXY(0,0);
            b.getC().setX(DEFAULT_WINDOW_WIDTH-b.getRadius());
        }
        if(b.getC().getX()<=b.getRadius()&&Wind.getX()<0){
            b.getDirection().setX(0);
            b.getC().setX(b.getRadius());
        }
    }

    public void setWind(){
        this.Wind = vectorWind(Speed_Wind, Direction_Wind);
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

    public void setSpeed_Ball(double speed_Ball) {
        Speed_Ball = speed_Ball;
    }

    public void setSpeed_Wind(int speed_Wind) {
        Speed_Wind = speed_Wind;
    }

    public void setDirection_Wind(int direction_Wind) {
        Direction_Wind = direction_Wind;
    }

    public double getSpeed_Ball() {
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

    public void changeGravity() {
        Gravity = !Gravity;
        if(Gravity){
            retention = 0.8;
        }
        else{
            retention = 1;
        }   
    }

    public boolean getGravity() {
        return Gravity;
    }

    public void setWindow(double width, double height){
        DEFAULT_WINDOW_WIDTH = width;
        DEFAULT_WINDOW_HEIGHT = height;
    }

    public static void quantiteMouvement(Ball b1,Ball b2){
        double v1 = Math.sqrt(b1.getDirection().getX()*b1.getDirection().getX() + b1.getDirection().getY()*b1.getDirection().getY());
        double v2 = Math.sqrt(b2.getDirection().getX()*b2.getDirection().getX() + b2.getDirection().getY()*b2.getDirection().getY());
        double m1 = b1.getMass();
        double m2 = b2.getMass();
        double v1f = (m1*v1 + m2*v2 - m2*(v1-v2))/(m1+m2);
        double v2f = (m1*v1 + m2*v2 - m1*(v1-v2))/(m1+m2);
        System.err.println("v1f : "+v1f+" v2f : "+v2f);
        b1.getDirection().setX(v1f*b1.getDirection().getX()/v1);
        b1.getDirection().setY(v1f*b1.getDirection().getY()/v1);
        b2.getDirection().setX(v2f*b2.getDirection().getX()/v2);
        b2.getDirection().setY(v2f*b2.getDirection().getY()/v2);
    }
}
