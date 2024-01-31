package entite;

import geometry.*;

/**
 * Classe Balle
 * 
 * Classe abstraite qui permet de définir les balles
 * 
 * @version 1.0
 */
public class Balle extends Entite{

    Coordonnee c;
    int type;
    Vector direction;
    int vitesse;


    public Balle(){
        this.c = new Coordonnee(0,0);
        this.type = 1;
        this.direction = new Vector(c);
        this.vitesse = 0;
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

}
