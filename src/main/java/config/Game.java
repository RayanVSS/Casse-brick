package config;

import java.util.ArrayList;

import entity.*;
import entity.ball.Ball;
import entity.racket.Racket;
import geometry.*;

public class Game {

    ArrayList<Entity> listebriques;
    Ball balle;
    Racket racket;

    public Game(int level) {
        listebriques = new ArrayList<Entity>();
        balle = new Ball();
        racket = new Racket(1);

        // TODO implement here

    }

    public void update(long deltaT) {
        // TODO implement here

    }
}
