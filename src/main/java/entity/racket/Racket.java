package entity.racket;

import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import org.checkerframework.checker.units.qual.cd;

import config.Game;
import utils.GameConstants;

import entity.Entity;
import entity.ball.Ball;

/***************************************************************************
 * Explication de classe pour la raquette *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 * Base:
 * 
 * @var Coordonnee c : Coordonnée de la raquette
 * @var Vector direction : Direction de la raquette
 * @var int type : Type de raquette
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
 *      creation de la raquette:
 * @param type : type de raquette
 * @error : si le type de raquette n'est pas connu
 * 
 *        chaque type de raquette a des variables différentes
 *        charger les variables de la raquette en fonction du type
 * 
 *        il y a pour l'instant 2 types de raquette:
 *        -1 : raquette de base
 *        -2 : raquette de base pouvant se déplacer en y
 * 
 *        GET et SET:
 *        je pense pas qui'il y ait besoin d'expliquer ;)
 * 
 *        Collision:
 * @param c : Coordonnée de l'objet avec lequel on veut vérifier la collision
 * @return : true si il y a collision, false sinon
 * 
 *         Mouvement a l'appui des touches: :
 * @param keysPressed : toutes les touches appuyées
 *
 *                    Mouvement au relachement des touches: *
 * @param event:      touche relachée
 *                    (pas utiliser pour l'instant)
 * 
 *                    Saut:
 *                    pas fini pour l'instant
 *
 * @author Rayan Belhasse
 **************************************************************************/

public class Racket {

    // base
    Coordinates c;
    Vector direction;
    int type;
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
    public Racket(Coordinates c, int height, int width, int speed, int type, Vector direction, boolean fixeY,
            boolean jump) {
        this.c = c;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.type = type;
        this.direction = direction;
        this.fixeY = fixeY;
        this.jump = jump;
    }

    public Racket(int type) {
        if (type == 1) { // raquette de base
            this.c = new Coordinates(GameConstants.DEFAULT_WINDOW_WIDTH / 2.5,
                    GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
            this.height = 200;
            this.width = 20;
            this.speed = 8;
            this.type = type;
            this.direction = new Vector(c);
            this.fixeY = true;
            this.jump = true;
        } else if (type == 2) { // raquette de base pouvant se déplacer en y
            this.c = new Coordinates(GameConstants.DEFAULT_WINDOW_WIDTH / 2.5,
                    GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
            this.height = 200;
            this.width = 20;
            this.speed = 8;
            this.type = type;
            this.direction = new Vector(c);
            this.fixeY = false;
            this.jump = true;
        } else {
            System.err.println("type de racket inconnu");

        }

    }

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

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
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

    public void setFixeY(boolean fixeY) {
        this.fixeY = fixeY;
    }

    public boolean getFixeY() {
        return fixeY;
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

    // Collision
    public boolean CollisionRacket(Coordinates c) {
        if (c.getX() > this.c.getX() && c.getX() < this.c.getX() + this.height &&
                c.getY() > this.c.getY()
                && c.getY() < this.c.getY() + this.getWidth()) {
            return true;
        }
        return false;
    }

    // Mouvement a l'appui des touches
    public void handleKeyPress(Set<KeyCode> keysPressed) {
        for (KeyCode key : keysPressed) {
            switch (key) {
                case Q:
                case LEFT:
                    if (this.mX() > -height / 2)
                        this.mX(this.mX() - speed);
                    break;
                case D:
                case RIGHT:
                    if (this.mX() < GameConstants.DEFAULT_WINDOW_WIDTH - width - 70)
                        this.mX(this.mX() + speed);
                    break;
                case Z:
                case UP:
                    if (this.mY() > 50 && !fixeY)
                        this.mY(this.mY() - speed);
                    break;
                case S:
                case DOWN:
                    if (this.mY() < GameConstants.DEFAULT_WINDOW_HEIGHT - 50 && !fixeY)
                        this.mY(this.mY() + speed);
                    break;
                case SPACE:
                    if (jump) {
                        long jumpStartTime = System.nanoTime();
                        handleJump();
                        break;
                    }
            }
        }
    }

    // Mouvement au relachement des touches
    public void handleKeyRelease(KeyCode event) {
        switch (event) {
        }
    }

    // saut (pas fini)
    public void handleJump() {
        long jumpElapsedTime = System.nanoTime() - jumpStartTime;
        double jumpProgress = (double) jumpElapsedTime / (JUMP_DURATION * 1_000_000_000.0);

        if (jumpProgress <= 1.0) {
            double jumpHeight = JUMP_HEIGHT * (1 - 4 * Math.pow(jumpProgress - 0.5, 2));
            this.mY((int) (jumpHeight - JUMP_HEIGHT / 2));
        }

    }

}
