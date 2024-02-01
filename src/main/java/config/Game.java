package config;

import java.util.ArrayList;

import entity.*;
import entity.ball.Ball;
import entity.brick.Brick;
import entity.racket.Racket;
import geometry.*;

public abstract class Game {

    private Ball ball;
    private Racket racket;
    private ArrayList<Brick> bricks;

    protected Game(Ball ball, Racket racket) {

        this.ball = ball;
        this.racket = racket;
    }

    public void createBricks() {

    }

    public void update(long deltaT) {
        // TODO implement here

    }
}
