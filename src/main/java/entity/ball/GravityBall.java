package entity.ball;

import org.apache.pdfbox.util.Vector;

import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import utils.GameConstants;

public class GravityBall extends Ball {
    private double LooseSpeed = 0.6;

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES.clone(), GameConstants.DEFAULT_BALL_START_DIRECTION.clone(),
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
        super.getPhysicSetting().setGravite(0.3);
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
        if (GameConstants.LIMITE_SPEED_MAGNET) {
            double speedY = newY - this.getC().getY();
            double speedX = newX - this.getC().getX();
            if (speedY > GameConstants.VITESSE_MAX_MAGNET) {
                newY = this.getC().getY() + GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedY < -GameConstants.VITESSE_MAX_MAGNET) {
                newY = this.getC().getY() - GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedY > -1.5 && speedY <= 0) {
                newY -= 0.2;
            }
            if (speedY < 1.5 && speedY >= 0) {
                newY += 0.2;
            }
            if (speedX > GameConstants.VITESSE_MAX_MAGNET) {
                newX = this.getC().getX() + GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedX < -GameConstants.VITESSE_MAX_MAGNET) {
                newX = this.getC().getX() - GameConstants.VITESSE_MAX_MAGNET;
            }
            if (speedX > -1.5 && speedX <= 0) {
                newX -= 0.2;
            }
            if (speedX < 1.5 && speedX >= 0) {
                newX += 0.2;
            }
        }

        if (newY > h - this.getRadius()) {
            super.setDelete(true);
        }

        if (getRa.getC().getY() - this.getRadius() < this.getC().getY()) { // bug qui fait que la balle transperce la
                                                                           // sraquette
            this.setC(new Coordinates(newX, getRa.getC().getY() - this.getRadius()));
        } else {
            if (newX < 0)
                newX += LooseSpeed;
            if (newX > 0)
                newX -= LooseSpeed;
            if (newY > 0)
                newY += LooseSpeed;
            if (newY < 0)
                newY -= LooseSpeed;
        }

         
        if (newX < 0) { // bug qui fait que la balle transperce le mur de gauche
            newX = -newX;
            if (newX < 1.5){
                newX = 4;
            }
            if (this.getDirection().getX() <= 0)
                this.getDirection().setX(-this.getDirection().getX());
            
        }
        // if (newX < 2) {
        //     newX = 5;
        //     if (this.getDirection().getX() < 0){
        //         System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
        //         this.getDirection().setX(-this.getDirection().getX());
        //     }
        // }
        System.out.println("newX: " + newX + " DIRECTTIONX: " + this.getDirection().getX());

        this.setC(new Coordinates(newX, newY));
        this.getDirection().add(super.getPhysicSetting().getWind());
        super.getPhysicSetting().checkGravity(getC(), getDirection());
        this.normalizeDirection();
    }
}
