package entity.ball;

import entity.racket.ClassicRacket;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import utils.GameConstants;

/**
 * GravityBall est une ball qui a une force gravitationnelle qui attire la balle
 * cette force est créer par la partie physique du jeux
 * 
 * 
 * @see Ball
 * 
 */

public class GravityBall extends Ball {
    private double LooseSpeed = GameConstants.LOOSE_SPEED;

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setGravite(GameConstants.GRAVITY_POWER);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        getPhysicSetting().changeGravity();
    }

    public GravityBall(int d) {
        super(d);
    }

    public void movement(long deltaT) {
        double h = getZoneHeight();
        for (Segment s : GameConstants.LIMIT_GAME_ROOT) {
            if (this.checkCollision(s)) {
                break;
            }
        }
        double newX = this.getX() + this.getDirection().getX() * this.getSpeed() / 2;
        double newY = this.getY() + this.getDirection().getY() * this.getSpeed() / 2;
        // limite de vitesse de la balle
        if (GameConstants.LIMITE_SPEED_GRAVITY) {
            double speedY = newY - this.getC().getY();
            double speedX = newX - this.getC().getX();
            if (speedY > GameConstants.VITESSE_MAX_GRAVITY) {
                newY = this.getC().getY() + GameConstants.VITESSE_MAX_GRAVITY;
            }
            if (speedY < -GameConstants.VITESSE_MAX_GRAVITY) {
                newY = this.getC().getY() - GameConstants.VITESSE_MAX_GRAVITY;
            }
            if (speedY > GameConstants.VITESSE_MIN_GRAVITY && speedY <= 0) {
                newY -= 0.2;
            }
            if (speedY < GameConstants.VITESSE_MIN_GRAVITY && speedY >= 0) {
                newY += 0.2;
            }
            if (speedX > GameConstants.VITESSE_MAX_GRAVITY) {
                newX = this.getC().getX() + GameConstants.VITESSE_MAX_GRAVITY;
            }
            if (speedX < -GameConstants.VITESSE_MAX_GRAVITY) {
                newX = this.getC().getX() - GameConstants.VITESSE_MAX_GRAVITY;
            }
            if (speedX > -GameConstants.VITESSE_MIN_GRAVITY && speedX <= 0) {
                newX -= 0.2;
            }
            if (speedX < GameConstants.VITESSE_MIN_GRAVITY && speedX >= 0) {
                newX += 0.2;
            }
        }

        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }

        if (newX < 0)
            newX += LooseSpeed;
        if (newX > 0)
            newX -= LooseSpeed;
        if (newY > 0)
            newY += LooseSpeed;
        if (newY < 0)
            newY -= LooseSpeed;

        if (newX <= 21) { // bug qui fait que la balle transperce le mur de gauche
            newX = 21;
        }
        if (this.getC().getX() < 22) {
            if (this.getDirection().getX() < 0) {
                this.getDirection().setX(-this.getDirection().getX());
            }
        }
        if (newX < 0) { // si la balle sort de l'écran par la gauche
            newX = -newX + 5;
        } else if (newX < -GameConstants.DEFAULT_GAME_ROOT_WIDTH) { // si la balle sort de l'écran par la droite
            newX = 20;
        } else if (newX > GameConstants.DEFAULT_GAME_ROOT_WIDTH) { // si la balle sort de l'écran par la droite
            newX = GameConstants.DEFAULT_GAME_ROOT_WIDTH - 30;
        } else if (newY < 0) { // si la balle sort de l'écran par le haut
            newY = -newY + 5;
        } else { // si la balle sort de l'écran par le haut
            String newYString = String.valueOf(newY);
            String newXString = String.valueOf(newX);
            if (newYString.contains("NaN") || newXString.contains("NaN")) {
                newY = GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2;
                newX = GameConstants.DEFAULT_WINDOW_HEIGHT / 2;
                this.getDirection().setY(-1);
            }
        }

        this.setNewX(newX);
        this.setNewY(newY);
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        this.normalizeDirection();
    }
}
