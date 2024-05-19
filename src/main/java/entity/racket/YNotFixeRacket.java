package entity.racket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.PhysicTools;
import utils.GameConstants;

/**
 * raquette qui peut monter déscendre et aller a droite et a gauche
 * 
 * @author Belhassen rayan
 */

public class YNotFixeRacket extends Racket {

    // creation de la raquette
    public YNotFixeRacket() {
        super(200, 20, "rectangle", 8, true, true);
    }

    // Mouvement a l'appui des touches
    public void handleKeyPress(Set<KeyCode> keysPressed,List<Ball> balls) {
        for (KeyCode key : keysPressed) {
            switch (key) {
                case Q:
                case LEFT:
                    if (this.mX() > -longueur / 2)
                        this.deplaceX(-speed);
                        this.getDirection().setX(-1);
                        for (Ball ball : balls) {
                            if(PhysicTools.checkCollision(ball,this.getSegments().get(3))){
                                ball.getDirection().setX(ball.getDirection().getX()+this.getDirection().getX());
                                ball.getC().setX(ball.getC().getX()-speed);
                            }
                        }
                    break;
                case D:
                case RIGHT:
                    if (this.mX() < super.getWidth() - largeur - 70)
                        this.deplaceX(speed);
                        this.getDirection().setX(1);
                        for (Ball ball : balls) {
                            if(PhysicTools.checkCollision(ball,this.getSegments().get(1))){
                                ball.getDirection().setX(ball.getDirection().getX()+this.getDirection().getX());
                                ball.getC().setX(ball.getC().getX()+speed);
                            }
                        }
                    break;
                case Z:
                case UP:
                    if (this.mY() > 50)
                        this.deplaceY(-speed);
                        this.getDirection().setY(-1);
                        for (Ball ball : balls) {
                            if(PhysicTools.checkCollision(ball,this.getSegments().get(0))){
                                ball.getDirection().setY(ball.getDirection().getY()+this.getDirection().getY());
                                ball.getC().setY(ball.getC().getY()-speed);
                            }
                        }
                    break;
                case S:
                case DOWN:
                    if (this.mY() < GameConstants.DEFAULT_WINDOW_HEIGHT - 50)
                        this.deplaceY(speed);
                        this.getDirection().setY(1);
                        for (Ball ball : balls) {
                            if(PhysicTools.checkCollision(ball,this.getSegments().get(2))){
                                ball.getDirection().setY(ball.getDirection().getY()+this.getDirection().getY());
                                ball.getC().setY(ball.getC().getY()+speed);
                            }
                        }
                    break;
                case SPACE:
                    if (jump) {
                        // long jumpStartTime = System.nanoTime();
                        break;
                    }
                default:
                    break;
            }
            
        }
    }

    // Mouvement au relachement des touches
    public void handleKeyRelease(KeyCode event) {
        /*
         * switch (event) {
         * }
         */
    }
}
