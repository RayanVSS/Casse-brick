package entity.racket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.s;

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
                                if(ball.getC().getX()-speed > ball.getRadius()){
                                    ball.getC().setX(ball.getC().getX()-speed);
                                    ball.getDirection().setX(ball.getDirection().getX()-speed);
                                }
                                else{
                                    int m=1;
                                    if(ball.getC().getY() > this.getC().getY()+this.longueur){
                                        m=-1;
                                    }
                                    this.deplaceX(speed);
                                    ball.getC().setY(ball.getC().getY()-m*speed);
                                    ball.getDirection().setY(ball.getDirection().getY()-m*speed/2);
                                }
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
                                if(ball.getC().getX()+speed < super.getWidth()-ball.getRadius()){
                                    ball.getC().setX(ball.getC().getX()+speed);
                                    ball.getDirection().setX(ball.getDirection().getX()+speed);
                                }
                                else{
                                    int m=1;
                                    if(ball.getC().getY() > this.getC().getY()+this.longueur){
                                        m=-1;
                                    }
                                    this.deplaceX(-speed);
                                    ball.getC().setY(ball.getC().getY()-m*speed);
                                    ball.getDirection().setY(ball.getDirection().getY()-m*speed/2);
                                }
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
                                if(ball.getC().getY()-speed > ball.getRadius()){
                                    ball.getC().setY(ball.getC().getY()-speed);
                                    ball.getDirection().setY(ball.getDirection().getY()-speed);
                                }
                                else{
                                    int m=1;
                                    if(ball.getC().getX() > this.getC().getX()+this.largeur){
                                        m=-1;
                                    }
                                    this.deplaceY(speed);
                                    ball.getC().setX(ball.getC().getX()-m*speed);
                                    ball.getDirection().setX(ball.getDirection().getX()-m*speed/2);
                                }
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
                                if(ball.getC().getY()+speed < GameConstants.DEFAULT_WINDOW_HEIGHT-ball.getRadius()){
                                    ball.getC().setY(ball.getC().getY()+speed);
                                    ball.getDirection().setY(ball.getDirection().getY()+speed);
                                }
                                else{
                                    int m=1;
                                    if(ball.getC().getX() > this.getC().getX()+this.largeur){
                                        m=-1;
                                    }
                                    this.deplaceY(-speed);
                                    ball.getC().setX(ball.getC().getX()-m*speed/2);
                                }
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
