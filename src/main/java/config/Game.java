package config;

import java.util.Vector;

import config.Map.BricksArrangement;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.brick.Brick;
import entity.brick.BrickClassic;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import utils.GameConstants;

public class Game {

    private Ball ball;
    private Racket racket;
    private Map map;
    private boolean lost = false;
    private int score = 0;
    private int life = 3;
    private boolean collide;

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {
        this.ball = ball;
        this.racket = racket;
        this.map = new Map(arrangement);
    }

    // Setters/getters
    public Ball getBall() {
        return ball;
    }

    public boolean getCollide() {
        return collide;
    }

    public Racket getRacket() {
        return racket;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public void update(long deltaT) {

        //Vérifie si la balle touche une brique
        map.handleCollisionBricks(ball); //gérer la collision des briques
        map.updateBricksStatus();
        // Si la balle touche la raquette
        if (racket.CollisionRacket(ball)) {
            ball.setCollisionR(true);
        }
        // Gere les conditions de perte
        if (!ball.movement()) {
            life--;
            ball.reset();
        }
        if (life == 0) {
            lost = true;
        }
    }

    public void lost() {
        System.exit(0);
    }

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }

    public Map getMap() {
        return map;
    }
}
