package config;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import entity.ball.MagnetBall;
import entity.Bonus;
import entity.Boost;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;
import gui.App;
import javafx.application.Platform;
import java.util.Iterator;

public class Game {

    private Ball originalball;
    private Racket racket;
    private Map map;
    private boolean lost;
    private boolean win;
    private int score = 0;
    private int life = 3;
    private boolean collide;
    private GameRules rules;
    private Timer inGameTimer;
    private int timeElapsed = 0; // en secondes
    private List<Bonus> bonuslist = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private List<Boost> boosts = new ArrayList<>();

    public Game(Ball ball, Racket racket, GameRules rules) {
        originalball = ball;
        balls.add(Ball.clone(ball));
        this.racket = racket;
        setRa(); // donne les coordonnées de la raquette a la MagnetBall
        this.rules = rules;
        this.map = new Map(rules, GameConstants.COLUMNS_OF_BRICKS, GameConstants.ROWS_OF_BRICKS);
        rules.initRules(this);
    }

    public Game(Ball ball, Racket racket, int life, GameRules rules, int columnsBricks, int rowsBricks) {
        originalball = ball;
        balls.add(Ball.clone(ball));
        this.racket = racket;
        setRa(); // donne les coordonnées de la raquette a la MagnetBall
        this.life = life;
        this.rules = rules;
        this.map = new Map(rules, columnsBricks, rowsBricks);
        rules.initRules(this);
    }

    private void start() {
        if (inGameTimer == null) {
            inGameTimer = new Timer();
            inGameTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() { // chaque seconde
                    Platform.runLater(() -> {
                        timeElapsed++;
                        rules.updateRemainingTime();
                    });
                }
            }, 0, 1000);
        }
    }

    public void stop() {
        inGameTimer.cancel();
        inGameTimer = null;
    }

    public void update(long deltaT) {
        start();

        // Vérifie si la balle est en collision avec une autre balle avant de les
        // déplacer
        for (Ball ball : balls) {
            if (!(ball instanceof MagnetBall)) {
                ball.checkCollisionOtherBall(balls);
            }
        }
        for (Ball ball : balls) {
            if (ball.delete()) {
                ball.setDestroyed(true);
                balls.remove(ball);
                break;
            }
            // map.handleCollisionBricks(ball, rules); // gérer la collision des briques
            map.updateBricksStatus(this);
            // seulement si la balle est une MagnetBall
            if (ball instanceof MagnetBall) {
                // donne les coordonnées de la raquette a la MagnetBall
                // actualise l'etat de la raquette
                if (BallFrontRacket(ball)) {
                    ((MagnetBall) ball).setFront(true);
                } else {
                    ((MagnetBall) ball).setFront(false);
                }
                if (!ball.CollisionR) {
                    if (ball.checkCollision(racket)) {
                        ball.CollisionR = true;
                        App.ballSound.update();
                        App.ballSound.play();
                        updateRulesRacket();
                    }
                }
            } else {
                // Si la balle touche la raquette
                if (ball.checkCollision(racket)) {
                    ball.CollisionR = true;
                    App.ballSound.update();
                    App.ballSound.play();
                    updateRulesRacket();
                }
            }
            for(Brick brick : map.getBricks()){
                if(ball.checkCollision(brick)){
                    Bonus bonus = Bonus.createBonus(ball.getC().clone());
                    if (bonus != null) {
                        bonuslist.add(bonus);
                    }
                    break;
                }
            }
            ball.movement(deltaT);
            ball.CollisionB = false;
        }

        // Gere les conditions de perte
        if (balls.isEmpty()) {
            life--;
            balls.add(Ball.clone(originalball));
            racket.reset();
        }
        // if (rules.isInfinite()) {
        //     if (!isInfiniteBonus()) {
        //         rules.infiniteUpdate(map, 0.60);
        //     } else {
        //         rules.infiniteUpdate(map, 0);
        //     }
        // }
        updateGameStatus();
        racket.getDirection().setX(0);

        if (racket.getJumpUP()) {
            racket.deplaceY(-5);
        }
        if (racket.getJumpDOWN()) {
            racket.deplaceY(2.9);
        }
        if (racket.getCalibrage()) {
            Coordinates c = new Coordinates(racket.getC().getX(), GameConstants.DEFAULT_WINDOW_HEIGHT - 50);
            Vector direction = new Vector(0, 0);
            racket.setC(c);
            racket.setDirection(direction);
            racket.setCalibrage(false);
            System.out.println(racket.getC().getY());

        }

    }

    private void updateRulesRacket() { // Vérification des règles qui s'appliquent au contact avec la raquette
        rules.updateRemainingBounces();
        rules.updateBricksTransparency(map);
        rules.updateBricksUnbreakability(map);
        rules.shuffleBricks(map.getBricks());
    }

    public boolean isInfiniteBonus() {
        for (Boost b : boosts) {
            if (b.getType().equals("infiniteStop")) {
                return true;
            }
        }
        return false;
    }

    private void updateGameStatus() { // Vérifie & MAJ le statut de la Game, gagnée/perdue
        if (life == 0 || !rules.check(map, racket.getC())) {
            lost = true;
            inGameTimer.cancel();
        }
        if (verifyWin()) {
            win = true;
        }
    }

    // Vérifie si la balle est devant la raquette
    public boolean BallFrontRacket(Ball b) {
        if (racket.getC().getX() - b.getC().getX() < 0
                && (racket.getC().getX() + racket.getLargeur()) - b.getC().getX() > 0) {
            return true;
        }
        return false;
    }

    public void deleteBalls() {
        for (Ball b : balls) {
            if (!b.delete()) {
                balls.remove(b);
                break;
            }
        }
    }

    private boolean verifyWin() {
        // return true; // décommenter ici pour tester les wins
        return map.countBricks() == 0;
    }

    // public Coordinates resetBallInfinite() {
    //     Coordinates c = new Coordinates(GameConstants.DEFAULT_WINDOW_WIDTH / 2, map.lastBrick() + 900 / 2);
    //     return c;
    // }

    // Setters/getters

    public List<Ball> getBalls() {
        return balls;
    }

    public void resetBalls() {
        balls.clear();
        balls.add(Ball.clone(originalball));
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

    public boolean isWin() {
        return win;
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

    public Map getMap() {
        return map;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setRa() {
        MagnetBall.getRa = racket;
    }

    public GameRules getRules() {
        return rules;
    }

    public List<Bonus> getBoosts() {
        return bonuslist;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // public boolean isInfinite() {
    // return isInfinite;
    // }
}
