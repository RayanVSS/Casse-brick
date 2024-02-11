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
        setWidth(racket.getLongueur());
        setHeight(racket.getLargeur());
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
