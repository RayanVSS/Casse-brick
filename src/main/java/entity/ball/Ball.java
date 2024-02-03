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

    private Coordonnee c;
    private int type;
    private Vector direction;
    private double vitesse;
    private int diametre;

    public Ball(int d) {
        this.c = new Coordonnee(1, 1);
        this.diametre = d;
        this.type = 1;
        this.direction = new Vector(new Coordonnee(1, 1));
        this.vitesse = 1;
    }

    public Ball(Coordonnee c, int type, int degat, Vector direction, int vitesse, int diametre) {
        this.c = c;
        this.type = type;
        this.direction = direction;
        this.vitesse = vitesse;
        this.diametre = diametre;
    }

    public Coordonnee getC() {
        return c;
    }

    public void setC(Coordonnee c) {
        this.c = c;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getDiametre() {
        return diametre;
    }

    public Vector getDirection() {
        return direction;
    }

    public double getVitesse() {
        return vitesse;
    }

    /**
     * Déplace la balle en fonction de sa direction et de sa vitesse actuelles.
     * Si la balle atteint les limites de la fenêtre, elle rebondit en inversant sa
     * direction.
     * Si la balle atteint le bas de la fenêtre, on perd la partie.
     * 
     * @param l La largeur de la fenêtre.
     * @param h La hauteur de la fenêtre.
     * @return true si la balle est toujours en jeu, false sinon.

     */
    public boolean deplacer(double l, double h) {
        boolean res=true;

        double newX = this.c.getX() + this.direction.getX() * this.vitesse;
        double newY = this.c.getY() + this.direction.getY() * this.vitesse;

        if (newX < 0 || newX > l - this.diametre) {
            this.direction.setX(-this.direction.getX());
            newX = this.c.getX() + this.direction.getX() * this.vitesse; // Recalculer newX
            res=true;
        }
        if (newY < 0 ) {
            this.direction.setY(-this.direction.getY());
            newY = this.c.getY() + this.direction.getY() * this.vitesse; // Recalculer newY
            res=true;
        }
        if(newY > h - this.diametre){
            res=false;
        }


        // Mettre à jour la position
        this.c.setX(newX);
        this.c.setY(newY);
        return res;
    }
}
