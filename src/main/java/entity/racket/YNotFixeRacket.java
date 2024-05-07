package entity.racket;

import java.util.Set;

import javafx.scene.input.KeyCode;
import physics.entity.Racket;
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
    public void handleKeyPress(Set<KeyCode> keysPressed) {
        for (KeyCode key : keysPressed) {
            switch (key) {
                case Q:
                case LEFT:
                    if (this.mX() > -longueur / 2)
                        this.deplaceX(-speed);
                    break;
                case D:
                case RIGHT:
                    if (this.mX() < super.getWidth() - largeur - 70)
                        this.deplaceX(speed);
                    break;
                case Z:
                case UP:
                    if (this.mY() > 50)
                        this.deplaceY(-speed);
                    break;
                case S:
                case DOWN:
                    if (this.mY() < GameConstants.DEFAULT_WINDOW_HEIGHT - 50)
                        this.deplaceY(speed);
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
