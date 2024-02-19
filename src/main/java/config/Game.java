package config;

import entity.ball.Ball;
import entity.racket.Racket;
import geometry.Coordinates;

public class Game {

    private static Ball ball;
    private Racket racket;
    private Map map;
    private boolean lost = false;
    private int score = 0;
    private int life = 3;
    private static boolean collide;

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {
        this.ball = ball;
        this.racket = racket;
        this.map = new Map(arrangement);
    }

    // Setters/getters
    public static Ball getBall() {
        return ball;
    }

    public static Boolean getCollide() {
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
        // Vérifie si la balle touche une brique
        map.handleCollisionBricks(ball); // gérer la collision des briques
        if (map.updateBricksStatus()) {
            score += 10;
        }
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

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }

    public Map getMap() {
        return map;
    }
}
