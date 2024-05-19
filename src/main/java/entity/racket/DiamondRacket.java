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
 * Raquette en forme de le losange
 *
 * @see RaketGraphics
 */
public class DiamondRacket extends Racket {

    public DiamondRacket() {
        super(200, 40, "losange", 8, false, true);
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

    public void handleKeyPress(Set<KeyCode> keysPressed,List<Ball> balls) {
        for (KeyCode key : keysPressed) {
            if (key == GameConstants.LEFT) {
                if (this.mX() > -largeur / 2)
                    this.mX(this.mX() - speed);
                    this.getDirection().setX(-1);
                    for (Ball ball : balls) {
                        if(PhysicTools.checkCollision(ball,this.getSegments().get(2))||PhysicTools.checkCollision(ball,this.getSegments().get(3))){
                            ball.getDirection().setX(ball.getDirection().getX()+this.getDirection().getX());
                            ball.getC().setX(ball.getC().getX()-speed/Math.abs(ball.getDirection().getX()));
                        }
                    }
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() + largeur < super.getWidth())
                    this.mX(this.mX() + speed);
                    this.getDirection().setX(1);
                    for (Ball ball : balls) {
                        if(PhysicTools.checkCollision(ball,this.getSegments().get(0))||PhysicTools.checkCollision(ball,this.getSegments().get(1))){
                            ball.getDirection().setX(ball.getDirection().getX()+this.getDirection().getX());
                            ball.getC().setX(ball.getC().getX()+speed/Math.abs(ball.getDirection().getX()));
                        }
                    }
            }
            if (key == GameConstants.SPACE) {
                setlargeurP(true);
                setVitesseP(true);
            }
        }
    }

    public void handleKeyRelease(KeyCode event) {
    }
}