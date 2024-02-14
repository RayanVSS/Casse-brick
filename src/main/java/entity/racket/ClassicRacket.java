package entity.racket;

import javafx.scene.input.KeyCode;
import java.util.Set;
import utils.GameConstants;

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
            if(key==GameConstants.LEFT){
                if (this.mX() > -largeur / 2)
                    this.mX(this.mX() - speed);
            }
            if(key==GameConstants.RIGHT){
                if (this.mX() < GameConstants.DEFAULT_WINDOW_WIDTH - longueur - 70)
                    this.mX(this.mX() + speed);
            }
            if(key==GameConstants.SPACE){
                setlargeurP(true);
                setVitesseP(true);
            }
        }
    }

    // Mouvement au relachement des touches
    public void handleKeyRelease(KeyCode event) {
        switch (event) {
        }
    }
}
