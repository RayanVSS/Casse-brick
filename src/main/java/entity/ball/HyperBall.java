package entity.ball;

import geometry.Coordinates;
import geometry.Vector;
import gui.GameView;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

public class HyperBall extends Ball {

    public HyperBall(int d) {
        super(d);
    }

    public HyperBall(Coordinates c, Vector direction,int speed ,int d) {
        super(c, direction, speed, d);
    }

    public boolean movement() {
        boolean lost = true;
        double h = GameConstants.DEFAULT_WINDOW_WIDTH;
        double w = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.direction.getX() * this.speed;
        double newY = this.getC().getY() + this.direction.getY() * this.speed;

        if (CollisionR) {
            if (GameView.BougePColision) {
                this.direction.setY(-this.direction.getY());
                newY = this.getC().getY() + this.direction.getY() * this.speed;
                CollisionR = false;
            }
            if (!GameView.BougePColision) {
                for (KeyCode key : GameView.direction) {
                    switch (key) {
                        case RIGHT:
                        case D:
                            System.out.println("droite");
                            this.direction.setX(1);
                            this.direction.setY(-1);
                            newX = this.getC().getX() + this.direction.getX() * this.speed;
                            newY = this.getC().getY() + this.direction.getY() * this.speed;
                            CollisionR = false;

                            break;
                        case LEFT:
                        case Q:
                            System.out.println("gauche");
                            this.direction.setX(-1);
                            this.direction.setY(-1);
                            newX = this.getC().getX() + this.direction.getX() * this.speed;
                            newY = this.getC().getY() + this.direction.getY() * this.speed;
                            CollisionR = false;
                            break;
                    }
                }
            }
        }
        if (newX < 0 || newX > h - this.getRadius()) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.speed;
            setBoost();
        }
        if (newY < 0 || CollisionR) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.speed;
            CollisionR = false;
            setBoost();
        }
        if (newY > w - this.getRadius()) {
            lost = false;
        }

        this.setC(new Coordinates(newX, newY));
        return lost;
    }

    public void setBoost() {
        this.speed += 0.2;
        System.out.println(this.speed);
    }

}
