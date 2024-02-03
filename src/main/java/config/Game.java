package config;

import java.util.ArrayList;

import entity.ball.Ball;
import entity.brick.Brick;
import entity.racket.Racket;

public class Game {

    private Ball ball;
    private Racket racket;
    private ArrayList<Brick> bricks;

    public Game(Ball ball, Racket racket) {
        this.ball = ball;
        this.racket = racket;
    }

    public void createBricks() {}

    public Ball getBall() {
        return ball;
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
}
