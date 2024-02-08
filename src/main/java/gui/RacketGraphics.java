package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import entity.racket.*;

public class RacketGraphics extends Rectangle {
    private Racket racket;

    public RacketGraphics(Racket racket) {
        this.racket = racket;
        setX(racket.getC().getX());
        setY(racket.getC().getY());
        setWidth(racket.getwidth());
        setHeight(racket.getheight());
        setArcWidth(20);
        setArcHeight(20);
        if (racket instanceof ClasssicRacket)
            setFill(Color.BLACK);
        else if (racket instanceof YNotFixeRacket)
            setFill(Color.BLUE);
        else
            setFill(Color.RED);
    }

    public void update() {
        setX(racket.getC().getX());
        setY(racket.getC().getY());
    }
}
