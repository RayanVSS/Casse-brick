package entity.ball;

import geometry.Coordinates;
import geometry.Vector;
import gui.GameView;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

public class GravityBall extends Ball {

    public GravityBall(Coordinates c, Vector direction, int speed, int d) {
        super(c, direction, speed, d);
    }

    public GravityBall(int d) {
        super(d);
    }

    public boolean movement() {
        boolean lost = true;
        double h = GameConstants.DEFAULT_WINDOW_WIDTH;
        double w = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.direction.getX() * this.speed;
        double newY = this.getC().getY() + this.direction.getY() * this.speed;

        if (this.getCollisionR()) {
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
                            this.getDirection().setX(1);
                            this.getDirection().setY(-1);
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
        }
        if (newY < 0 || CollisionR) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.speed;
            CollisionR = false;
        }
        if (newY > w - this.getRadius()) {
            lost = false;
        }

        setGravity();
        this.setC(new Coordinates(newX, newY));

        return lost;
    }

    // Methode qui permet de définir la gravité
    public void setGravity() {
        this.direction.setY(this.direction.getY() + 0.01);
    }

}
