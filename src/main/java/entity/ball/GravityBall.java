package entity.ball;

import gui.GameRoot;
import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class GravityBall extends Ball {

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setGravite(0.1);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH,GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    public GravityBall(int d) {
        super(d);
    }

    public void movement(long deltaT) {
        double w = getZoneWidth();
        double h = getZoneHeight();
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
        if (CollisionR) {
            if (GameRoot.BougePColision || CollisionR_Side) {
                this.getDirection().setY(-this.getDirection().getY() + this.getRotation().getEffect());
                this.getRotation().Collision();
                newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                CollisionR = false;
                CollisionR_Side = false;
            } else {
                for (KeyCode key : GameRoot.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            this.getDirection().setY(-this.getDirection().getY() + this.getRotation().getEffect() / 10);
                            this.getRotation().addEffect('d');
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;
                            break;
                        case LEFT:
                        case Q:
                            this.getDirection().setY(-this.getDirection().getY() + this.getRotation().getEffect() / 10);
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
            this.getDirection().setX(this.getDirection().getX());
        } else if (newY < 0) {
            this.getDirection().setY(
                    -this.getDirection().getY() + (this.getRotation().getEffect() / 90) * this.getDirection().getY());
            this.getRotation().Collision();
            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
            this.getDirection().setY(this.getDirection().getY());
        }
        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        this.normalizeDirection();
    }
}
