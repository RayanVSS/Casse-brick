package entity.ball;

import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import utils.GameConstants;

public class GravityBall extends Ball {

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setGravite(0.1);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH,GameConstants.DEFAULT_WINDOW_HEIGHT);
        getPhysicSetting().changeGravity();
    }

    public GravityBall(int d) {
        super(d);
    }

    public void movement(long deltaT) {
        double h = getZoneHeight();
        for(Segment s : GameConstants.LIMIT_GAME_ROOT){
            if(this.checkCollision(s)){
                break;
            }
        }
        double newX = this.getX() + this.getDirection().getX() * this.getSpeed()/2 ;
        double newY = this.getY() + this.getDirection().getY() * this.getSpeed()/2 ;
        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        this.normalizeDirection();
    }
}
