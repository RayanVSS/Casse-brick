package config;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import entity.ball.MagnetBall;
import entity.Bonus;
import physics.entity.Ball;
import physics.entity.Racket;
import utils.GameConstants;
import gui.App;

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

    public Game(Ball ball, Racket racket, GameRules rules) {
        originalball = ball;
        balls.add(Ball.clone(ball));
        this.racket = racket;
        this.rules = rules;
        this.map = new Map(rules, GameConstants.COLUMNS_OF_BRICKS, GameConstants.ROWS_OF_BRICKS);
        rules.initRules(this);
    }

    public Game(Ball ball, Racket racket, int life, GameRules rules, int columnsBricks, int rowsBricks) {
        originalball = ball;
        balls.add(Ball.clone(ball));
        this.racket = racket;
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
                    timeElapsed++;
                    rules.updateRemainingTime();
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
        //Vérifie si la balle touche une brique
        for (Ball ball : balls) {
            ball.CollisionB=false;
            if(!ball.delete()){
                balls.remove(ball);
                break;
            }
            map.handleCollisionBricks(ball, rules); //gérer la collision des briques
            if (map.updateBricksStatus(this)) {
                //si la briques est cassée, chance d'avoir un boost
                Bonus bonus = Bonus.createBonus(ball.getC(), balls);
                if (bonus != null) {
                    bonuslist.add(bonus);
                }
            }
            // Si la balle touche la raquette
            if (racket.CollisionRacket(ball)) {
                ball.setCollisionR(true);
                App.ballSound.update();
                App.ballSound.play();
                updateRulesRacket();
            }
            ball.checkCollisionOtherBall(balls);
            ball.movement();

            if (ball instanceof MagnetBall) {
                // donne les coordonnées de la raquette a la MagnetBall
                setRa();
                // actualise l'etat de la raquette
                if (BallFrontRacket(ball)) {
                    ((MagnetBall) ball).setFront(true);
                } else {
                    ((MagnetBall) ball).setFront(false);
                }
            }
        }
        
        // Gere les conditions de perte
        if (balls.isEmpty()) {
            life--;
            balls.add(Ball.clone(originalball));
            racket.reset();
        }
        updateGameStatus();
    }

    private void updateRulesRacket() { // Vérification des règles qui s'appliquent au contact avec la raquette
        rules.updateRemainingBounces();
        rules.updateBricksTransparency(map);
        rules.updateBricksUnbreakability(map);
        rules.shuffleBricks(map.getBricks());
    }

    private void updateGameStatus() { // Vérifie & MAJ le statut de la Game, gagnée/perdue
        if (life == 0 || !rules.check()) {
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

    public void deleteBalls(){
        for (Ball b : balls) {
            if(!b.delete()){
                balls.remove(b);
                break;
            }
        }
    }

    private boolean verifyWin() {
        //return true; // décommenter ici pour tester les wins
        return map.countBricks() == 0;
    }

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
        MagnetBall.getRa = racket.getC();
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

}
