package config;

import entity.ball.Ball;
import entity.racket.Racket;
import geometry.Coordinates;
import utils.GameConstants;
import java.util.Timer;
import java.util.TimerTask;
import entity.ball.MagnetBall;

public class Game {

    private Ball ball;
    private Racket racket;
    private Map map;
    private boolean lost = false;
    private int score = 0;
    private int life = 3;
    private boolean collide;
    private GameRules rules;
    private Timer inGameTimer;
    private int timeElapsed = 0; // en secondes

    public Game(Ball ball, Racket racket, GameRules rules) {
        this.ball = ball;
        this.racket = racket;
        this.rules = rules;
        this.map = new Map(rules);
        rules.initRules(this);
        inGameTimer = new Timer();
    }

    public void start() {
        inGameTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() { // chaque seconde
                timeElapsed++;
                rules.updateRemainingTime();
            }
        }, 0, 1000);
    }

    public void stop() {
        inGameTimer.cancel();
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

    public Timer getInGameTimer() {
        return inGameTimer;
    }

    public void update(long deltaT) {

        // Vérifie si la balle touche une brique
        map.handleCollisionBricks(ball, rules); // gérer la collision des briques
        map.updateBricksStatus();
        // Si la balle touche la raquette
        if (racket.CollisionRacket(ball)) {
            ball.setCollisionR(true);
            rules.updateRemainingBounces();
            rules.updateBricksTransparency(map);
            rules.shuffleBricks(map.getBricks());
            map.displayBricksTransparencyInTerminal();
        }
        // Gere les conditions de perte
        if (!ball.movement()) {
            life--;
            ball.reset();
        }
        if (life == 0 || !rules.check()) {
            lost = true;
            inGameTimer.cancel();
        }

    }

    public void lost() {
        System.exit(0);
    }

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }

    // Vérifie si la balle est devant la raquette
    public boolean BallFrontRacket() {
        if (racket.getC().getX() - ball.getC().getX() < 0
                && (racket.getC().getX() + racket.getLargeur()) - ball.getC().getX() > 0) {
            return true;
        }
        return false;
    }

    public Map getMap() {
        return map;
    }

    public void setRa() {
        MagnetBall.getRa = racket.getC();
    }
}
