package entity.ball;

import entity.Entity;
import entity.brick.Brick;
import geometry.*;
import utils.GameConstants;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public abstract class Ball extends Entity {

    protected Vector direction;
    protected int rayon;
    protected double vitesse;

    //colision avec racket
    boolean CollisionR = false;

    public Ball(int r) {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
        this.vitesse = 0;
        this.rayon = r;
    }

    public Ball(Coordinates c, Vector direction, int vitesse, int r) {
        super(c);
        this.direction = direction;
        this.vitesse = vitesse;
        this.rayon = r;
    }

    public int getRayon() {
        return this.rayon;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public double getVitesse() {
        return this.vitesse;
    }

    public void setRayon(int r) {
        this.rayon = r;
    }

    public void setCollisionR(boolean b) {
        this.CollisionR = b;
    }

    public boolean getCollisionR() {
        return this.CollisionR;
    }

    // public boolean intersectBrick(Brick b) {

    //     // Coordonnées du centre du cercle
    //     double circleCenterX = getC().getX() + getRayon();
    //     double circleCenterY = getC().getY() + getRayon();

    //     double rectLeft = b.getC().getX();
    //     double rectTop = b.getC().getY();

    //     double rectRight = b.getC().getX() + GameConstants.BRICK_WIDTH;
    //     double rectBottom = b.getC().getY() + GameConstants.BRICK_HEIGHT;

    //     // Distance horizontale entre le centre du cercle et le rectangle
    //     double distX = Math.abs(circleCenterX - (rectLeft + rectRight) / 2);
    //     double halfRectWidth = GameConstants.BRICK_WIDTH / 2;

    //     // Distance verticale entre le centre du cercle et le rectangle
    //     double distY = Math.abs(circleCenterY - (rectTop + rectBottom) / 2);
    //     double halfRectHeight = GameConstants.BRICK_HEIGHT / 2;

    //     // Intersection si la distance horizontale est inférieure à la moitié de la largeur du rectangle plus le rayon du cercle,
    //     // et la distance verticale est inférieure à la moitié de la hauteur du rectangle plus le rayon du cercle

    //     return distX <= halfRectWidth + getRayon() && distY <= halfRectHeight + getRayon();
    // }

    public boolean intersectBrick(Brick b) {

        // Calcul des distances horizontales et verticales entre le centre du cercle et le rectangle
        int distX = Math.abs(getC().getIntX() - b.getC().getIntX() - GameConstants.BRICK_WIDTH / 2);
        int distY = Math.abs(getC().getIntY() - b.getC().getIntY() - GameConstants.BRICK_HEIGHT / 2);

        // Si la distance horizontale est supérieure à la moitié de la largeur du rectangle + le rayon du cercle, pas de collision
        if (distX > GameConstants.BRICK_WIDTH / 2 + getRayon()) {
            return false;
        }

        // Si la distance verticale est supérieure à la moitié de la hauteur du rectangle + le rayon du cercle, pas de collision
        if (distY > GameConstants.BRICK_HEIGHT / 2 + getRayon()) {
            return false;
        }

        // Si la distance horizontale est inférieure à la moitié de la largeur du rectangle, collision
        if (distX <= GameConstants.BRICK_WIDTH / 2) {
            return true;
        }

        // Si la distance verticale est inférieure à la moitié de la hauteur du rectangle, collision
        if (distY <= GameConstants.BRICK_HEIGHT / 2) {
            return true;
        }

        // Calcul de la distance entre le centre du cercle et le coin du rectangle
        int dx = distX - GameConstants.BRICK_WIDTH / 2;
        int dy = distY - GameConstants.BRICK_HEIGHT / 2;

        // Si la distance entre le centre du cercle et le coin du rectangle est inférieure ou égale au rayon au carré, collision
        return dx * dx + dy * dy <= getRayon() * getRayon();
    }

    public abstract boolean movement();
}
