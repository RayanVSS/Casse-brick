package entity.ball;

import config.Game;
import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

import utils.Key;
import gui.GameView;

public class ClassicBall extends Ball {

    public Key key = new Key();

    public ClassicBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
        GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS
);
    }

    public ClassicBall(int d) {
        super(d);
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
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double w = GameConstants.DEFAULT_WINDOW_WIDTH;
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
        if (newX < 0 || newX > w- this.getRadius()) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.speed;
        }
        if (newY < 0 || CollisionR) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.speed;
            CollisionR = false;
        }
        if (newY > h - this.getRadius()) {
            lost = false;
        }

        this.setC(new Coordinates(newX, newY));
        return lost;
    }

}
