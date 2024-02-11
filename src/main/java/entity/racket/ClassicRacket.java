package entity.racket;

import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import utils.GameConstants;

import entity.Entity;

/**
 * raquette qui peut aller a droite et a gauche
 * 
 * @author Belhassen rayan
 */

public class ClassicRacket extends Racket {

    // creation de la raquette
    public ClassicRacket() {
        super(200, 20, 8, false, true);
    }

    // Mouvement a l'appui des touches
    public void handleKeyPress(Set<KeyCode> keysPressed) {
        for (KeyCode key : keysPressed) {
            switch (key) {
                case Q:
                case LEFT:
                    if (this.mX() > -longueur / 2)
                        this.mX(this.mX() - speed);
                    break;
                case D:
                case RIGHT:
                    if (this.mX() < GameConstants.DEFAULT_WINDOW_WIDTH - largeur - 70)
                        this.mX(this.mX() + speed);
                    break;
                case SPACE:
                    if (jump) {
                        long jumpStartTime = System.nanoTime();
                        break;
                    }
            }
        }
    }

    // Mouvement au relachement des touches
    public void handleKeyRelease(KeyCode event) {
        switch (event) {
        }
    }
}
