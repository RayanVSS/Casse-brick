package entity.ball;

import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;


public class HyperBall extends Ball{

    public HyperBall(int d) {
        super(d);
    }

    public HyperBall(Coordinates c, Vector direction, int vitesse, int d) {
        super(c, direction, vitesse, d);
    }

    public boolean movement() {
        boolean aux = true;
        double h= GameConstants.DEFAULT_WINDOW_WIDTH;
        double w= GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        double newY = this.getC().getY() + this.direction.getY() * this.vitesse;

        if (newX < 0 || newX > h - this.getRadius()) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
            setBoost();
        }
        if (newY < 0 || CollisionR ) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
            CollisionR = false;
            setBoost();
        }
        if (newY > w - this.getRadius()) {
            aux = false;
        }

        this.setC(new Coordinates(newX, newY));
        return aux;
    }

    public void setBoost() {
        this.vitesse +=0.2;
        System.out.println(this.vitesse);
    }
    
}
