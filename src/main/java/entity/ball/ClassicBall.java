package entity.ball;

import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GameConstants;

import utils.Key;
import gui.GameRoot;

public class ClassicBall extends Ball {

    public Key key = new Key();

    public ClassicBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
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
    public boolean movement() {
        boolean lost = true;
        double w = getZoneWidth();
        double h = getZoneHeight();
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
        if (CollisionR) {
            if (GameRoot.BougePColision || CollisionR_Side) {
                this.getDirection().setY(-this.getDirection().getY()
                        + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
                this.getRotation().Collision();
                newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                CollisionR = false;
                CollisionR_Side = false;
            } else {
                for (KeyCode key : GameRoot.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            this.getDirection().setY(-this.getDirection().getY()
                                    + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
                            this.getRotation().addEffect('d');
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;
                            break;
                        case LEFT:
                        case Q:
                            this.getDirection().setY(-this.getDirection().getY()
                                    + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
                            this.getRotation().addEffect('g');
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if (newX < 0 || newX > w - this.getRadius()) {
            this.getDirection().setX(-this.getDirection().getX());
            this.getDirection().setY(
                    this.getDirection().getY() + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
            this.getRotation().Collision();
            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
            this.getDirection().setX(this.getDirection().getX() * super.getPhysicSetting().getRetention());
        }
        if (newY < 0) {
            this.getDirection().setY(
                    -this.getDirection().getY() + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
            this.getRotation().Collision();
            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
            this.getDirection().setY(this.getDirection().getY() * super.getPhysicSetting().getRetention());
        }
        if (newY > h - this.getRadius()) {
            lost = false;
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        return lost;
    }

}
