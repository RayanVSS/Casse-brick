package gui.GraphicsFactory;

import javafx.scene.shape.Rectangle;
import entity.racket.*;
import javafx.scene.paint.Color;

public class RacketGraphics extends Rectangle {
    private Racket racket;

    public RacketGraphics(Racket racket) {
        this.racket = racket;
        setX(racket.getC().getX());
        setY(racket.getC().getY());
        System.out.println("Longeur racket: " + racket.getLongueur() + " " + "Largeur: " + racket.getLargeur());
        setWidth(racket.getLongueur());
        setHeight(racket.getLargeur());
        System.out.println("Longeur rectangle: "+this.getHeight() + " " + "Largeur: " + this.getWidth());
        setArcWidth(20);
        setArcHeight(20);

        if (racket instanceof ClassicRacket)
            setFill(Color.RED);
        else if (racket instanceof YNotFixeRacket)
            setFill(Color.BLUE);
        else
            setFill(Color.BLACK);
    }

    public void update() {
        setX(racket.getC().getX());
        setY(racket.getC().getY());
    }
}
