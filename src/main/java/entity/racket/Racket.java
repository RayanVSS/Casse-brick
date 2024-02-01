package entity.racket;

import entity.Entity;
import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.MouseEvent;

public class Racket extends Entity {

    Vector direction;

    public Racket() {
        super(new Coordinates(0, 0));
        this.direction = new Vector(new Coordinates(0, 0));
    }

    public Racket(Coordinates c, Vector direction) {
        super(c);
        this.direction = direction;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void mMouseMove(MouseEvent e) {
        this.mOnMouseMoved(e);
    }

    private void mOnMouseMoved(MouseEvent e) {
        this.mX((int) e.getX());
    }

    public void mX(int pX) {
        this.getC().setX(pX);
    }

    public void mY(int pY) {
        this.getC().setY(pY);
    }

    public int mX() {
        return this.getC().getX();
    }

}
