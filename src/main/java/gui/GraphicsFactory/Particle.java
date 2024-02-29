package gui.GraphicsFactory;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import utils.GameConstants;


/***************************************************************************
 *                  Explication de classe Particle                         *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 *      Base: 
 * @var double angle : Angle de la particule
 * @var double speed : Vitesse de la particule
 * 
 *     creation de la particule:
 * @param x : Coordonnée x de la particule
 * @param y : Coordonnée y de la particule
 * 
 *     mise a jour de la particule:
 * @return : rien
 * permet de suivre la ball
 * 
 *     appliquer une fluctuation aléatoire:
 * @return : rien
 * permet de faire bouger les particules de maniere aléatoire
 * 
 *     appliquer une couleur aléatoire:
 * @return : une couleur aléatoire
 * permet de donner une couleur aléatoire a la particule
 * 
 * 
 * 
 * @author Rayan Belhasse
*****************************************************************************/


public class Particle extends Circle {

    //base
    private double angle;
    private double speed;

    //creation de la particule
    public Particle(double x, double y) {
        super(x, y, GameConstants.DEFAULT_PARTICLE_RADIUS, applyRandomColor()); // ajustez le rayon et la couleur
        this.angle = Math.random() * 360;
        this.speed = Math.random() * 0.5 + 0.1;
    }

    //mise a jour de la particule
    public void update() {
        angle += speed;
        double newX = getCenterX() + Math.cos(Math.toRadians(angle)) * 2;
        double newY = getCenterY() + Math.sin(Math.toRadians(angle)) * 2;
        setCenterX(newX);
        setCenterY(newY);
    }

    //appliquer une fluctuation aléatoire
    public void applyRandomFluctuation() {
        double fluctuation = GameConstants.DEFAULT_FLUCTUATION; // Ajustez l'amplitude de la fluctuation selon vos préférences
        setCenterX(getCenterX() + (Math.random() * fluctuation - fluctuation / 2));
        setCenterY(getCenterY() + (Math.random() * fluctuation - fluctuation / 2));
    }

    //appliquer une couleur aléatoire
    public static Paint applyRandomColor() {
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.LIGHTGRAY;
            case 2:
                return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }



}
