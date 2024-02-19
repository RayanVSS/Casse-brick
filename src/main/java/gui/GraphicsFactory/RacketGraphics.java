package gui.GraphicsFactory;

import javafx.scene.shape.Rectangle;
import entity.racket.*;
import javafx.scene.paint.Color;

/**
 * Classe RacketGraphics qui étend Rectangle pour représenter graphiquement une raquette.
 * @author Benmalek Majda
 */
public class RacketGraphics extends Rectangle {
    private Racket racket;

    /**
     * Constructeur de RacketGraphics.
     * @param racket L'instance de Racket à représenter graphiquement.
     */
    public RacketGraphics(Racket racket) {
        this.racket = racket;
        setX(racket.getC().getX());
        setY(racket.getC().getY());
        setWidth(racket.getLargeur());
        setHeight(racket.getLongueur());
        setArcWidth(20);
        setArcHeight(20);

        if (racket instanceof ClassicRacket)
            setFill(Color.RED);
        else if (racket instanceof YNotFixeRacket)
            setFill(Color.BLUE);
        else
            setFill(Color.BLACK);
    }

    /**
     * Méthode pour mettre à jour les coordonnées et les dimensions de la raquette.
     */
    public void update() {
        setX(racket.getC().getX());
        setY(racket.getC().getY());
        setWidth(racket.getLargeur());
        setHeight(racket.getLongueur());
    }
}