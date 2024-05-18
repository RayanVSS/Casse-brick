package entity.racket;

import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Racket;
import utils.GameConstants;

/**
 * Raquette en forme de triangle
 *
 * @see RaketGraphics
 */
public class DegradeRacket extends Racket {

    public DegradeRacket() {
        super(200, 20, "triangle", 8, false, true);
    }

    public void handleKeyPress(Set<KeyCode> keysPressed) {
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
