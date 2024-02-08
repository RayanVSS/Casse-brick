package entity.racket;

import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import utils.GameConstants;
import java.util.Set;

/***************************************************************************
 * Explication de classe pour la raquette *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 *      Base: 
 * @var Coordonnee c : Coordonnée de la raquette 
 * @var Vector direction : Direction de la raquette 
 * @var int speed : Vitesse de la raquette 
 * @var int height : height de la raquette 
 * @var int width : width de la raquette 
 * @var boolean fixeY : Si la raquette est fixe en y 
 * @var boolean jump : Si la raquette peut sauter 
 *      
 *      boost: 
 * @var Boolean vitesseP :raquette a un boost de vitesse 
 * @var Boolean vitesseM :raquette a un malus de vitesse 
 * @var Boolean widthP :raquette a un boost de width 
 * @var Boolean widthM :raquette a un malus de width 
 * @var boolean freeze :le temps est freeze 
 *      
 *      GET et SET: 
 *je pense pas qui'il y ait besoin d'expliquer ;) 
 *        
 *      Collision: 
 * @param c : Coordonnée de l'objet avec lequel on veut vérifier la collision
 * @return : true si il y a collision, false sinon
 * 
 *         Mouvement a l'appui des touches: :
 * @param keysPressed : toutes les touches appuyées
 *
 *      Mouvement au relachement des touches: 
 * @param event: touche relachée 
 *                    
 *      Saut: 
 *pas fini pour l'instant 
 *
 * @author Rayan Belhasse
 **************************************************************************/

public abstract class Racket {

    // base
    Coordinates c =  new Coordinates(GameConstants.DEFAULT_WINDOW_WIDTH/2.5, GameConstants.DEFAULT_WINDOW_HEIGHT-50);
    Vector direction = new Vector(c);
    double speed;
    int height;
    int width;
    boolean fixeY;
    boolean jump;

    // boost
    Boolean vitesseP = false;
    Boolean vitesseM = false;
    Boolean widthP = false;
    Boolean widthM = false;
    boolean freeze = false;

    // variables pour le saut
    private static final double JUMP_DURATION = 1.0; // Durée du saut en secondes
    private static final double JUMP_HEIGHT = 100.0; // Hauteur du saut en pixels
    private long jumpStartTime;

    // creation de la raquette
    public Racket(int height, int width, int speed,boolean fixeY, boolean jump) {
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.fixeY = fixeY;
        this.jump = jump;
    }

    // Collision
    public boolean CollisionRacket(Coordinates c) {
        if (c.getX() > this.c.getX() && c.getX() < this.c.getX() + this.height && c.getY() > this.c.getY()
                && c.getY() < this.c.getY() + this.width) {
            return true;
        }
        return false;
    }

    //fonction obligatoire
    public abstract void handleKeyPress(Set<KeyCode> keysPressed) ;    
    public abstract void handleKeyRelease(KeyCode event) ;

    
    // GET et SET
    public Boolean getJump() {
        return jump;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Coordinates getC() {
        return c;
    }

    public void setC(Coordinates c) {
        this.c = c;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
    
    public void mMouseMove(MouseEvent e) {
        this.mOnMouseMoved(e);
    }

    private void mOnMouseMoved(MouseEvent e) {
        this.mX((int) e.getX());
    }

    public void mX(double pX) {
        this.c.setX(pX);
    }

    public void mY(double pY) {
        this.c.setY(pY);
    }

    public double mX() {
        return this.c.getX();
    }

    public double mY() {
        return this.c.getY();
    }

    public long getJumpStartTime() {
        return jumpStartTime;
    }

    public void setJumpStartTime(long jumpStartTime) {
        this.jumpStartTime = jumpStartTime;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setVitesseP(Boolean vitesseP) {
        this.vitesseP = vitesseP;
    }

    public Boolean getVitesseP() {
        return vitesseP;
    }

    public void setVitesseM(Boolean vitesseM) {
        this.vitesseM = vitesseM;
    }

    public Boolean getVitesseM() {
        return vitesseM;
    }

    public void setWidthP(Boolean widthP) {
        this.widthP = widthP;
    }

    public Boolean getWidthP() {
        return widthP;
    }

    public void setWidthM(Boolean widthM) {
        this.widthM = widthM;
    }

    public Boolean getWidthM() {
        return widthM;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public boolean getFreeze() {
        return freeze;
    }



}
