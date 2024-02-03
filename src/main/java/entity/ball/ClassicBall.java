package entity.ball;

import geometry.Coordinates;
import geometry.Vector;

public class ClassicBall extends Ball{

    public ClassicBall(Coordinates c, Vector direction, int vitesse, int d) {
        super(c, direction, vitesse, d);
    }

    public ClassicBall(int d) {
        super(d);
    }

    public boolean mouvement(double l, double h) {
        boolean res=true;

        double newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        double newY = this.getC().getY() + this.direction.getY() * this.vitesse;

        if (newX < 0 || newX > l - this.diametre) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        }
        if (newY < 0) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
        }
        if (newY > h - this.diametre) {
            res = false;
        }

        this.setC(new Coordinates(newX, newY));
        return res;
    }
    
}
