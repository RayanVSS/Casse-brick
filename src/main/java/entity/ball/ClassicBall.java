package entity.ball;

import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import physics.geometry.Vector;
import utils.GameConstants;
import utils.Key;

/**
 * ClassicBall est une ball qui n'a rien de spécial
 * 
 * @see Ball
 * 
 */

public class ClassicBall extends Ball {

    public Key key = new Key();

    public ClassicBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public ClassicBall(int d) {
        super(d);
    }

    public ClassicBall(Coordinates coordinates, Vector vector) {
        super(coordinates, vector, GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
    }

    @Override

    /**
     * Cette méthode gère le mouvement de la balle dans le jeu.
     *
     * @return un booléen indiquant si la balle est perdue ou non.
     *         Retourne `true` si la balle est toujours en jeu, `false` sinon.
     */
    public void movement(long deltaT) {
        double h = getZoneHeight();
        for (Segment s : GameConstants.LIMIT_GAME_ROOT) {
            if (this.checkCollision(s)) {
                break;
            }
        }
        double newX = this.getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getY() + this.getDirection().getY() * this.getSpeed();

        if (GameConstants.LIMITE_SPEED_CLASSIC) {
            double speedY = newY - this.getC().getY();
            double speedX = newX - this.getC().getX();
            if (speedY > GameConstants.VITESSE_MAX_CLASSIC) {
                newY = this.getC().getY() + GameConstants.VITESSE_MAX_CLASSIC;
            }
            if (speedY < -GameConstants.VITESSE_MAX_CLASSIC) {
                newY = this.getC().getY() - GameConstants.VITESSE_MAX_CLASSIC;
            }
            if (speedY > GameConstants.VITESSE_MIN_CLASSIC && speedY <= 0) {
                newY -= 0.2;
            }
            if (speedY < GameConstants.VITESSE_MIN_CLASSIC && speedY >= 0) {
                newY += 0.2;
            }
            if (speedX > GameConstants.VITESSE_MAX_CLASSIC) {
                newX = this.getC().getX() + GameConstants.VITESSE_MAX_CLASSIC;
            }
            if (speedX < -GameConstants.VITESSE_MAX_CLASSIC) {
                newX = this.getC().getX() - GameConstants.VITESSE_MAX_CLASSIC;
            }
            if (speedX > -GameConstants.VITESSE_MIN_CLASSIC && speedX <= 0) {
                newX -= 0.2;
            }
            if (speedX < GameConstants.VITESSE_MIN_CLASSIC && speedX >= 0) {
                newX += 0.2;
            }
        }

        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }

        this.setNewX(newX);
        this.setNewY(newY);
        
        this.getDirection().add(super.getPhysicSetting().getWind());
        this.normalizeDirection();
    }

}
