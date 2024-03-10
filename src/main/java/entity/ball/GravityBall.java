package entity.ball;

import gui.GameRoot;
import javafx.scene.input.KeyCode;
import physics.config.PhysicSetting;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class GravityBall extends Ball {

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
        GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        PhysicSetting physicSetting = new PhysicSetting();
        PhysicSetting.Gravite = 0.1;
        PhysicSetting.Mass = 1;
        super.setPhysicSetting(physicSetting);
    }

    public GravityBall(int d) {
        super(d);
    }

    public boolean movement(){
        boolean lost = true;
        double w = GameConstants.DEFAULT_GAME_ROOT_WIDTH;
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed() ;
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed() ;
        if (GameRoot.BougePColision || CollisionR_Side) {
            this.getDirection().setY(-this.getDirection().getY()+this.getRotation().getEffect());
            this.getRotation().Collision();
            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
            CollisionR = false;
            CollisionR_Side = false;
        }
        else {
            for (KeyCode key : GameRoot.direction) {
                switch (key) {
                    case RIGHT:
                    case D:
                        this.getDirection().setY(-this.getDirection().getY()+this.getRotation().getEffect());
                        this.getRotation().addEffect('d');
                        newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                        newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                        CollisionR = false;
                        break;
                    case LEFT:
                    case Q:
                        this.getDirection().setY(-this.getDirection().getY()+this.getRotation().getEffect());
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
        if (newX < 0 || newX > h - this.getRadius()) {
            this.getDirection().setX(-this.getDirection().getX()+this.getRotation().getEffect());
            this.getRotation().Collision();
            newX = this.getC().getX() + this.getDirection().getX()*this.getSpeed();
            this.getDirection().setX(this.getDirection().getX()*super.getPhysicSetting().getRetention());
        }
        if (newY < 0 || newY > w - this.getRadius()) {
            this.getDirection().setY(-this.getDirection().getY()+this.getRotation().getEffect());
            this.getRotation().Collision();
            newY = this.getC().getY() + this.getDirection().getY()* this.getSpeed();
            this.getDirection().setY(this.getDirection().getY()*super.getPhysicSetting().getRetention());
        } 
        if (newY > h - this.getRadius()) {
            lost = false;
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        return lost;
    }
}
