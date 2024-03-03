package entity.ball;

import java.util.Random;

import entity.racket.Racket;
import geometry.Coordinates;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

import utils.Key;
import gui.GameView;

public class ClassicBall extends Ball {

    public Key key = new Key();

    public ClassicBall() {
        super(GameConstants.DEFAULT_BALL_START_COORDINATES, GameConstants.DEFAULT_BALL_START_DIRECTION,
                GameConstants.DEFAULT_BALL_SPEED, GameConstants.DEFAULT_BALL_RADIUS);
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
        Random rand = new Random();
        double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        double w = GameConstants.DEFAULT_WINDOW_WIDTH;
        if(this.getDirection().getY()>1){
            this.getDirection().setY(1);
        }
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
        
        if (this.getCollisionR()) {
            if (GameView.BougePColision) { //Colision sans bouger la raquette
                double d=rand.nextDouble()-0.5;
                this.getDirection().setY(-(this.getDirection().getY())+d);
                newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                CollisionR = false;
            }
            if (!GameView.BougePColision) { //Colision pendant le mouvement de la balle
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
            this.getDirection().setY(-this.getDirection().getY());
            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
            CollisionR = false;
        }
        if (newY > h - this.getRadius()) {
            lost = false;
        }

        //System.out.println("newX: " + this.getDirection().getX() + " newY: " + this.getDirection().getY());
        this.setC(new Coordinates(newX, newY));
        return lost;
    }

}
