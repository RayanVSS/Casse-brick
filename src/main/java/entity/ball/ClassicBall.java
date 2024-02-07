package entity.ball;

import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;



public class ClassicBall extends Ball {

    public ClassicBall(Coordinates c, Vector direction, int vitesse, int d) {
        super(c, direction, vitesse, d);
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
        double h= GameConstants.DEFAULT_WINDOW_WIDTH;
        double w= GameConstants.DEFAULT_WINDOW_HEIGHT;
        double newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        double newY = this.getC().getY() + this.direction.getY() * this.vitesse;

        if (newX < 0 || newX > w - this.getRadius()) {
            this.direction.setX(-this.direction.getX());
            newX = this.getC().getX() + this.direction.getX() * this.vitesse;
        }
        if (newY < 0 || CollisionR ) {
            this.direction.setY(-this.direction.getY());
            newY = this.getC().getY() + this.direction.getY() * this.vitesse;
            CollisionR = false;
        }
        if (newY > h - this.getRadius()) {
            aux = false;
        }

        this.setC(new Coordinates(newX, newY));
        return aux;
    }


}
