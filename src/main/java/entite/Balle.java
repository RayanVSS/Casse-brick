package entite;

import geometry.*;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public class Balle extends Entite {

    private Coordonnee c;
    private int type;
    private Vector direction;
    private double vitesse;
    private int diametre;

    public Balle(int d) {
        this.c = new Coordonnee(1, 1);
        this.diametre = d;
        this.type = 1;
        this.direction = new Vector(new Coordonnee(1, 1));
        this.vitesse = 1;
    }

    public Balle(Coordonnee c, int type, int degat, Vector direction, int vitesse) {
        this.c = c;
        this.type = type;
        this.direction = direction;
        this.vitesse = vitesse;
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
     *
     * @param l La largeur de la fenêtre.
     * @param h La hauteur de la fenêtre.
     */
    public void deplacer(double l, double h) {

        double newX = this.c.getX() + this.direction.getX() * this.vitesse;
        double newY = this.c.getY() + this.direction.getY() * this.vitesse;

        if (newX < 0 || newX > l - this.diametre) {
            this.direction.setX(-this.direction.getX());
            newX = this.c.getX() + this.direction.getX() * this.vitesse; // Recalculer newX
        }
        if (newY < 0 || newY > h - this.diametre) {
            this.direction.setY(-this.direction.getY());
            newY = this.c.getY() + this.direction.getY() * this.vitesse; // Recalculer newY
        }

        // Mettre à jour la position
        this.c.setX(newX);
        this.c.setY(newY);
        System.out.println("largeur : " + l + " hauteur : " + h);
    }
}
