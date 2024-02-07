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
 * raquette qui peut monter déscendre et aller a droite et a gauche
 * 
 * @author Belhassen rayan
 */

public class YNotFixeRacket extends Racket{
    
 // creation de la raquette
 public YNotFixeRacket() {
    super(200, 20, 8,true, true);
}



// Mouvement a l'appui des touches
    public void handleKeyPress(Set<KeyCode> keysPressed) {
    for (KeyCode key : keysPressed) {
    switch (key) {
        case Q:
        case LEFT:
            if (this.mX() > -longueur/2)
                this.mX(this.mX() - speed);
            break;
        case D:
        case RIGHT:
            if (this.mX() < GameConstants.DEFAULT_WINDOW_WIDTH-largeur-70)
                this.mX(this.mX() + speed);
            break;
        case Z: 
        case UP:
            if (this.mY() > 50)
                this.mY(this.mY() - speed );
            break;
        case S:
        case DOWN:
            if (this.mY() < GameConstants.DEFAULT_WINDOW_HEIGHT-50 )
                this.mY(this.mY() + speed );
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