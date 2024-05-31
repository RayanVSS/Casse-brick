package config;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import entity.Bonus;
import entity.Boost;
import entity.ball.ClassicBall;
import entity.ball.MagnetBall;
import entity.racket.ClassicRacket;
import entity.racket.MagnetRacket;
import gui.App;
import javafx.application.Platform;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;

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
        for (Ball ball : balls) {
            ball.movement(deltaT); // calcul des prochaines coordonnées de la balle
            updateBricks(ball); // vérifie si la balle est en collision avec une brique
            BallCollisionRacket(ball, racket); // vérifie si la balle est en collision avec la raquette
            ball.checkCollisionOtherBall(balls); // vérifie si la balle est en collision avec une autre balle
            CheckNewCoordinates(ball); // vérifie si les nouvelles coordonnées de la balle sont valides
            modificationByType(ball); // modifie les attributs de la balle en fonction de son type
            ball.setNewCoordinates(); // applique les nouvelles coordonnées de la balle
            looseBall(ball); // vérifie si la balle est perdue
        }
    }

    public void modificationByType(Ball ball){
        if(ball instanceof MagnetBall) {
            if (BallFrontRacket(ball)) {
                ((MagnetBall) ball).setFront(true);
            } else {
                ((MagnetBall) ball).setFront(false);
            }
        }
        if(racket instanceof ClassicRacket) {
            BallFrontRacket_close(ball);
            racket.Dojump(racket, balls);
        }
        if (rules.isInfinite()) {
            rules.infiniteUpdate(map, GameConstants.BRICK_SPEED);
            rules.createBrickInfinite(map);
        }
    }

    public void CheckNewCoordinates(Ball ball) {
        if (ball.getNewX() < 0) { // si la balle sort de l'écran par la gauche
            ball.setNewX(-ball.getNewX() + 5);
        } else if (ball.getNewX() > GameConstants.DEFAULT_GAME_ROOT_WIDTH) { // si la balle sort de l'écran par la droite
            ball.setNewX(GameConstants.DEFAULT_GAME_ROOT_WIDTH - 30);
        } else if (ball.getNewY() < 0) { // si la balle sort de l'écran par le haut
            ball.setNewY(-ball.getNewY() + 5);
        } else if (ball.getNewX() > racket.getC().getX() // Si la balle est dans la raquette
                && ball.getNewX() < racket.getC().getX() + racket.getLargeur() - 5
                && ball.getNewY() - ball.getRadius() > racket.getC().getY()) {
            ball.setNewX(ball.getX());
            ball.setNewY(ball.getY()-ball.getRadius());
            ball.setDirection(new Vector(ball.getDirection().getX(), -ball.getDirection().getY()));
            ball.getRotation().stopRotation();

            System.out.println("ball dans la raquette");
        } else { // si la balle a disparue
            String newYString = String.valueOf(ball.getNewY());
            String newXString = String.valueOf(ball.getNewX());
            if (newYString.contains("NaN") || newXString.contains("NaN")) {
                ball.setNewY(GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2);
                ball.setNewX(GameConstants.DEFAULT_WINDOW_HEIGHT / 2);
                ball.setDirection(new Vector(0, -1));
            }
        }

    }

    public void looseBall(Ball ball) {
        if (ball.delete()) {
            ball.setDestroyed(true);
            balls.remove(ball);
            if (balls.isEmpty()) {
                life--;
                balls.add(Ball.clone(originalball));
                bonuslist.clear();
                racket.reset();
                if (rules.isInfinite()) {
                    balls.get(0).getC().setXY(racket.getC().getX() + 20, racket.getC().getY() - 50);
                }
            }
        }
    }

    public void BallCollisionRacket(Ball ball, Racket racket) {
        if (ball.checkCollision(racket)) {
            ball.CollisionR = true;
            if(!(racket instanceof MagnetRacket)){
                App.ballSound.update();
                App.ballSound.play();  
            }
            rules.updateRulesRacket(map);
        } else {
            ball.CollisionR = false;
        }
    }

    public void updateBricks(Ball ball) {
        for (Brick brick : map.getBricks()) {
            boolean breakBrick = false;
            if (rules.canCollide(brick) && ball.checkCollision(brick)) {
                if (rules.isColorRestricted()) {
                    if (ball.getColor() == brick.getColor()) {
                        breakBrick = true;
                    }
                    ball.setColor(brick.getColor());
                } else {
                    breakBrick = true;
                }
                if (breakBrick) {
                    brick.absorb(100);
                    Bonus bonus = Bonus.createBonus(ball.getC().clone());
                    if (bonus != null) {
                        bonuslist.add(bonus);
                    }
                }
                break;
            }
        }
        map.updateBricksStatus(this);
        updateGameStatus();
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

    public void BallFrontRacket_close(Ball b) {
        if (racket.getC().getX() - b.getC().getX() < 0
                && (racket.getC().getX() + racket.getLargeur()) - b.getC().getX() > 0
                && racket.getC().getY() - b.getC().getY() < racket.getLongueur() + 20) {
            ((ClassicRacket) racket).setBallFrontRacket(true);
        } else {
            ((ClassicRacket) racket).setBallFrontRacket(false);
        }
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
        Ball.getRa = racket;
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
