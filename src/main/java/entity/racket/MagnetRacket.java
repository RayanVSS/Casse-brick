package entity.racket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.checkerframework.checker.units.qual.s;

import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.PhysicTools;
import utils.GameConstants;

public class MagnetRacket extends Racket {
    // etat de la raquette
    private static String etat = "positif";
    private boolean change = false;

    // creation de la raquette
    public MagnetRacket() {
        super(200, 20, "rectangle", 8, false, false);
    }

    public void handleKeyPress(Set<KeyCode> keysPressed) {
        for (KeyCode key : keysPressed) {
            if (key == GameConstants.LEFT) {
                if (this.mX() > 0)
                    this.deplaceX(-speed);
                this.getDirection().setX(-1);
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() + largeur < super.getWidth())
                    this.deplaceX(speed);
                this.getDirection().setX(1);
            }
            if (key == GameConstants.SPACE) {
                setJump(jump);

            }
        }
    }

    // Mouvement a l'appui des touches
    public void handleKeyPress(Set<KeyCode> keysPressed,List<Ball> balls) {
        for (KeyCode key : keysPressed) {
            if (key == GameConstants.LEFT) {
                if (this.mX() > -largeur / 2)
                    this.deplaceX(-speed);
                    this.getDirection().setX(-1);
                    for (Ball ball : balls) {
                        if(PhysicTools.checkCollision(ball,this.getSegments().get(3))){
                            if(ball.getC().getX()-speed > ball.getRadius()){
                                ball.getC().setX(ball.getC().getX()-speed);
                                ball.getDirection().setX(ball.getDirection().getX()-speed/Math.abs(ball.getDirection().getX()));
                            }
                            else{
                                int m=1;
                                if(ball.getC().getY() > this.getC().getY()+this.longueur){
                                    m=-1;
                                }
                                this.deplaceX(speed);
                                ball.getC().setY(ball.getC().getY()-m*speed);
                                ball.getDirection().setY(ball.getDirection().getY()-m*speed/Math.abs(ball.getDirection().getY()));
                            }
                        }
                    }
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() < super.getWidth() - longueur - 70)
                    this.deplaceX(speed);
                    this.getDirection().setX(1);
                    for (Ball ball : balls) {
                        if(PhysicTools.checkCollision(ball,this.getSegments().get(1))){
                            if(ball.getC().getX()+speed < super.getWidth()-ball.getRadius()){
                                ball.getC().setX(ball.getC().getX()+speed);
                                ball.getDirection().setX(ball.getDirection().getX()+speed/Math.abs(ball.getDirection().getX()));
                            }
                            else{
                                int m=1;
                                if(ball.getC().getY() > this.getC().getY()+this.longueur){
                                    m=-1;
                                }
                                this.deplaceX(-speed);
                                ball.getC().setY(ball.getC().getY()-m*speed);
                                ball.getDirection().setY(ball.getDirection().getY()-m*speed/Math.abs(ball.getDirection().getY()));
                            }
                        }
                    }
            }
            if (key == GameConstants.SPACE) {
                setChange();
            }
        }
    }

    // Mouvement au relachement des touches
    public void handleKeyRelease(KeyCode event) {
    }

    public void setChange() {
        if (!change) {
            changeEtat();
            System.out.println(etat);
            starChange();
            change = true;
        }
    }

    public void starChange() {
        Timer BoostTimer = new Timer();
        BoostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                change = false;
                BoostTimer.cancel();// Arrêter le timer
            }
        }, 100);
    }

    public void changeEtat() {
        if (etat == "negatif") {
            etat = "positif";
        } else {
            etat = "negatif";
        }
    }

    public static String getEtat() {
        return etat;
    }
}
