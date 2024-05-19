package entity.ball;

import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import utils.GameConstants;

/**
 * HyperBall est une ball qui a une vitesse qui augmente à chaque frame
 * 
 * @see Ball
 */

public class HyperBall extends Ball {
    private double speedBoost = 0.1;

    public HyperBall(int d) {
        super(d);
    }

    public HyperBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setWindow(GameConstants.DEFAULT_GAME_ROOT_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    }

    @Override
    public void movement(long deltaT) {
        double h = getZoneHeight();
        for (Segment s : GameConstants.LIMIT_GAME_ROOT) {
            if (this.checkCollision(s)) {
                break;
            }
        }
        double newX = this.getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getY() + this.getDirection().getY() * this.getSpeed();
        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }
        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        this.normalizeDirection();
        setBoost();
    }

    public void setBoost() {
        this.setSpeed(this.getSpeed() + this.speedBoost);
    }

}
