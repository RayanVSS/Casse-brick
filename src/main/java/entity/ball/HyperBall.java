package entity.ball;

import gui.GameView;
import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class HyperBall extends Ball {
    private double speedBoost = 0.1;

    public HyperBall(int d) {
        super(d);
    }

    public HyperBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
    }

    @Override
    public boolean movement() {
        boolean lost = true;
        double w = GameConstants.DEFAULT_WINDOW_WIDTH;
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed() ;
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed() ;
        if (CollisionR) {
            if (GameView.BougePColision || CollisionR_Side) {
                this.getDirection().setY(-this.getDirection().getY());
                this.getDirection().add(super.getPhysicSetting().getFrictionRacket());
                newY = this.getC().getY() + this.getDirection().getY();
                CollisionR = false;
                CollisionR_Side = false;
                super.getPhysicSetting().UpdateFrictionRacket();
            }
            else {
                for (KeyCode key : GameView.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            this.getDirection().setY(-this.getDirection().getY());
                            super.getPhysicSetting().getFrictionRacket().setX(super.getPhysicSetting().getFrictionRacket().getX() + 1);
                            this.getDirection().add(super.getPhysicSetting().getFrictionRacket());
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;
                            break;
                        case LEFT:
                        case Q:
                            this.getDirection().setY(-this.getDirection().getY());
                            super.getPhysicSetting().getFrictionRacket().setX(super.getPhysicSetting().getFrictionRacket().getX()-1);
                            this.getDirection().add(super.getPhysicSetting().getFrictionRacket());
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
            this.getDirection().add(super.getPhysicSetting().getFrictionRacket());
            super.getPhysicSetting().UpdateFrictionRacket();
            newX = this.getC().getX() + this.getDirection().getX()*this.getSpeed();
            this.getDirection().setX(this.getDirection().getX()*super.getPhysicSetting().getRetention());
        }
        if (newY < 0 ) {
            this.getDirection().add(super.getPhysicSetting().getFrictionRacket());
            this.getDirection().setY(-this.getDirection().getY());
            super.getPhysicSetting().UpdateFrictionRacket();
            newY = this.getC().getY() + this.getDirection().getY()* this.getSpeed();
            this.getDirection().setY(this.getDirection().getY()*super.getPhysicSetting().getRetention());
        } 
        if (newY > h - this.getRadius()) {
            lost = false;
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        setBoost();
        return lost;
    }

    public void setBoost() {
        this.setSpeed(this.getSpeed() + this.speedBoost);
    }

}
