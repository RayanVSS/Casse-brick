package entity.racket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
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
                    this.mX(this.mX() - speed);
                    this.getDirection().setX(-1);
            }
            if (key == GameConstants.RIGHT) {
                if (this.mX() + largeur < super.getWidth())
                    this.mX(this.mX() + speed);
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