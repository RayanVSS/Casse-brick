package entite;

import geometry.Coordonnee;
import geometry.Vector;
import javafx.scene.input.MouseEvent;


public class Racket extends Entite{

    Coordonnee c;
    Vector direction;
    int type;


    public Racket(Coordonnee c, int type, Vector direction) {
        this.c = c;
        this.direction = direction;
    }

    public Racket(int type) {
        this.c = new Coordonnee(0,0);
        this.type = type ;
        this.direction = new Vector(c);
    }

    public Coordonnee getC() {
        return c;
    }

    public void setC(Coordonnee c) {
        this.c = c;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void mMouseMove(MouseEvent e){
    	this.mOnMouseMoved(e);
    }

    private void mOnMouseMoved(MouseEvent e){
    	this.mX((int) e.getX());		
    }
        
    public void mX(int pX){
        this.c.setX(pX);
    }

    public void mY(int pY){
        this.c.setY(pY);
    }

    public double mX(){
        return this.c.getX();
    }

    
}
