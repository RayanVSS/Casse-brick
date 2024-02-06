package entity.ball;

import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;

public class ClassicBall extends Ball {

    public ClassicBall(Coordinates c, Vector direction, int speed, int d) {
        super(c, direction, speed, d);
    }

    public ClassicBall(int d) {
        super(d);
    }

    @Override
    /**
     * Déplace la balle dans un espace défini par la largeur et la hauteur de la fenetre.
     * @return Retourne 'true' si la balle ne touche pas la limite sud de
     *         l'espace, 'false' sinon. (boolean pour géré les condition de défaite
     *         ou de victoire dans le jeu)
     * @author Benmalek Majda
     */
    public boolean movement() {
        boolean aux = true;
        double w= GameConstants.DEFAULT_WINDOW_WIDTH;
        double h= GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();

        if (newX < 0 || newX > w - this.getDiametre()) {
            this.getDirection().setX(-this.getDirection().getX());
            newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
        }
        if (newY < 0) {
            this.getDirection().setY(-this.getDirection().getY());
            newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
        }
        if (newY > h - this.getDiametre()) {
            aux = false;
        }

        this.setC(new Coordinates(newX, newY));
        return aux;
    }

}
