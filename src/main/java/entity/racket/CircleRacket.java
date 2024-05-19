package entity.racket;

import java.lang.reflect.Array;
import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
import utils.GameConstants;
import java.util.List;

/**
 * Raquette en forme de rond
 *
 * @see RaketGraphics
 */
public class CircleRacket extends Racket {

    public CircleRacket() {
        super(180, 40, "rond", 8, false, true);
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
                setlargeurP(true);
                setVitesseP(true);
            }
        }
    }

    public void handleKeyPress(Set<KeyCode> keysPressed, List<Ball> balls) {
        for (KeyCode key : keysPressed) {
            if (key == GameConstants.LEFT) {
                if (this.mX() > -largeur / 2)
                    this.deplaceX(-speed);
                    this.getDirection().setX(-1);
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() < super.getWidth() - longueur - 70)
                    this.deplaceX(speed);
                    this.getDirection().setX(1);
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