package entity.racket;

import java.util.List;
import java.util.Set;
import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.PhysicTools;
import utils.GameConstants;

/**
 * raquette qui peut aller a droite et a gauche
 * 
 * @see RaketGraphics
 * @see Racket
 * @author Belhassen rayan
 */

public class ClassicRacket extends Racket {
    private boolean BallFrontRacket = false;

    // creation de la raquette
    public ClassicRacket() {
        super(200, 20, "rectangle", 8, false, true);
    }

    // Mouvement a l'appui des touches

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

    public void handleKeyPress(Set<KeyCode> keysPressed, List<Ball> balls) {
        for (KeyCode key : keysPressed) {
            if (key == GameConstants.LEFT) {
                if (this.mX() > 0)
                    this.deplaceX(-speed);
                this.getDirection().setX(-1);
                for (Ball ball : balls) {
                    if (PhysicTools.checkCollision(ball, this.getSegments().get(3))) {
                        if (ball.getC().getX() - speed > ball.getRadius()) {
                            ball.getC().setX(ball.getC().getX() - speed);
                            ball.getDirection()
                                    .setX(ball.getDirection().getX() - speed / Math.abs(ball.getDirection().getX()));
                        } else {
                            int m = 1;
                            if (ball.getC().getY() > this.getC().getY() + this.longueur) {
                                m = -1;
                            }
                            this.deplaceX(speed);
                            ball.getC().setY(ball.getC().getY() - m * speed);
                            ball.getDirection().setY(
                                    ball.getDirection().getY() - m * speed / Math.abs(ball.getDirection().getY()));
                        }
                    }
                }
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() + largeur < super.getWidth())
                    this.deplaceX(speed);
                this.getDirection().setX(1);
                for (Ball ball : balls) {
                    if (PhysicTools.checkCollision(ball, this.getSegments().get(1))) {
                        if (ball.getC().getX() + speed < super.getWidth() - ball.getRadius()) {
                            ball.getC().setX(ball.getC().getX() + speed);
                            ball.getDirection()
                                    .setX(ball.getDirection().getX() + speed / Math.abs(ball.getDirection().getX()));
                        } else {
                            int m = 1;
                            if (ball.getC().getY() > this.getC().getY() + this.longueur) {
                                m = -1;
                            }
                            this.deplaceX(-speed);
                            ball.getC().setY(ball.getC().getY() - m * speed);
                            ball.getDirection().setY(
                                    ball.getDirection().getY() - m * speed / Math.abs(ball.getDirection().getY()));
                        }
                    }
                }
            }
            if (key == GameConstants.SPACE) {
                setJump(jump);

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

    public boolean getBallFrontRacket() {
        return BallFrontRacket;
    }

    public void setBallFrontRacket(boolean ballFrontRacket) {
        BallFrontRacket = ballFrontRacket;
    }

}
