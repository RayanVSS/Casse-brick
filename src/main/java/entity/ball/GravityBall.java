package entity.ball;

import java.util.Random;

import entity.racket.Racket;
import geometry.Coordinates;
import gui.GameView;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

public class GravityBall extends Ball {
    private double gravity = 0.01;

    public GravityBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
    }

    public GravityBall(int d) {
        super(d);
    }

    public boolean movement() {
        boolean lost = true;
        Random rand = new Random();
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double w = GameConstants.DEFAULT_WINDOW_WIDTH;
        if(this.getDirection().getY()>1){
            this.getDirection().setY(1);
        }
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();

        if (getCollisionR()) {
            if (GameView.BougePColision) {
                double d=rand.nextDouble()-0.5;
                this.getDirection().setY(-(this.getDirection().getY())+d);
                newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                CollisionR = false;
            } else {
                for (KeyCode key : GameView.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            System.out.println("droite");
                            this.getDirection().setX(1);
                            this.getDirection().setY(-1);
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;

                            break;
                        case LEFT:
                        case Q:
                            System.out.println("gauche");
                            this.getDirection().setX(-1);
                            this.getDirection().setY(-1);
                            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                            CollisionR = false;
                            break;
                    }
                }
            }
        }

        if (newX < 0 || newX > w - this.getRadius()) {
            this.getDirection().setX(-this.getDirection().getX());
            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        }
        if (newY < 0 || CollisionR) {
            if (newY < 0 || CollisionR) {
                this.getDirection().setY(-this.getDirection().getY());
                newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                CollisionR = false;
            }
            if (newY > h - this.getRadius()) {
                lost = false;
            }
        }
        setGravity();
        this.setC(new Coordinates(newX, newY));
        return lost;
    }

    // Methode qui permet de définir la gravité
    public void setGravity() {
        this.getDirection().setY(this.getDirection().getY() + gravity);
    }

}
