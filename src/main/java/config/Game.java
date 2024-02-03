package config;

import java.util.ArrayList;
import entity.*;
import entity.ball.Ball;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;

public class Game {

    ArrayList<Entity> listebriques;
    Ball ball;
    Racket racket;

    public Game(int level, Coordinates centre) {
        // TODO implement here
        listebriques = new ArrayList<Entity>();
        ball = new Ball(new Coordinates(centre.getX(), centre.getY()), 1, 1, new Vector(new Coordinates(1, -1)), 2, 5);
        racket = new Racket(1);

    }

    
    public void update(long deltaT, double l, double h) {
        // TODO implement here
        // Quand la balle arrive au sud on perds (tres primaire comme code)
        if (!ball.deplacer(l, h)) {
            perdu();
        }
    }

    public void perdu() {
        System.exit(0);
    }

    public Ball getBalle() {
        return ball;
    }
}
