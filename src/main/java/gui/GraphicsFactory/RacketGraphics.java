package gui.GraphicsFactory;

import javafx.scene.shape.Rectangle;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.EntityColor;
import entity.ball.MagnetBall;
import entity.racket.*;
import javafx.scene.paint.Color;

/**
 * Classe RacketGraphics qui étend Rectangle pour représenter graphiquement une
 * raquette.
 * 
 * @author Benmalek Majda
 */
public class RacketGraphics extends Rectangle {
    private Racket racket;

    /**
     * Constructeur de RacketGraphics.
     * 
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
            getStyleClass().add("racket");
        else if (racket instanceof YNotFixeRacket)
            getStyleClass().add("ynotfixeracket");
        else if (racket instanceof MagnetRacket)
            getStyleClass().add("magnetracket");
    }

    /**
     * Méthode pour mettre à jour les coordonnées et les dimensions de la raquette.
     */
    public void update() {
        setX(racket.getC().getX());
        setY(racket.getC().getY());
        setWidth(racket.getLargeur());
        setHeight(racket.getLongueur());
        if (racket instanceof MagnetRacket) {
            if (((MagnetRacket) racket).getEtat().equals("positif")) {
                setFill(Color.YELLOW);
            } else {
                setFill(Color.GREEN);
            }
        }
    }
}