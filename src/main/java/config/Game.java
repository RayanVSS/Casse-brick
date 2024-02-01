package config;

import java.util.ArrayList;
import entite.*;
import geometry.Coordonnee;
import geometry.Vector;

public class Game {

    ArrayList<Entite> listebriques;
    Balle balle;
    Racket racket;

    public Game(int level, Coordonnee centre) {
        // TODO implement here
        listebriques = new ArrayList<Entite>();
        balle = new Balle(new Coordonnee(centre.getX(), centre.getY()), 1, 1, new Vector(new Coordonnee(1, -1)), 2, 5);
        racket = new Racket(1);

    }

    
    public void update(long deltaT, double l, double h) {
        // TODO implement here
        // Quand la balle arrive au sud on perds (tres primaire comme code)
        if (!balle.deplacer(l, h)) {
            perdu();
        }
    }

    public void perdu() {
        System.exit(0);
    }

    public Balle getBalle() {
        return balle;
    }
}
