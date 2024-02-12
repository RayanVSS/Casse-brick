package config;

import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;

public class Game {

    private Ball ball;
    private Racket racket;
    private Map map;

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {

        this.ball = ball;
        this.racket = racket;
        this.map = new Map(arrangement);
    }

    public Ball getBall() {
        return ball;
    }

    public void update(long deltaT) {

        //Vérifie si la balle touche une brique
        map.handleCollisionBricks(ball); //gérer la collision des briques

        // Quand la balle arrive au sud on perds (tres primaire comme code)
        if (!ball.movement()) {
            lost();
        }
        // Si la balle touche la raquette
        if (collisionRacket(ball.getC())) {
            ball.setCollisionR(true);
            System.out.println("collisionX");
        }

        map.updateBricksStatus();
    }

    public void lost() {
        System.exit(0);
    }

    public Racket getRacket() {
        return racket;
    }

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }

    public Map getMap() {
        return map;
    }
}
