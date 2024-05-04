package entity.racket;

import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Racket;
import utils.GameConstants;

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
                if (this.mX() > -largeur / 2)
                    this.mX(this.mX() - speed);
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() < super.getWidth() - longueur - 70)
                    this.mX(this.mX() + speed);
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