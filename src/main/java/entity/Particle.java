package entity;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Particle extends Circle {

    private double angle;
    private double speed;

    public Particle(double x, double y) {
        super(x, y, 1, applyRandomColor()); // ajustez le rayon et la couleur selon vos besoins
        this.angle = Math.random() * 360;
        this.speed = Math.random() * 0.5 + 0.1;
    }

    public void update() {
        angle += speed;
        double newX = getCenterX() + Math.cos(Math.toRadians(angle)) * 2;
        double newY = getCenterY() + Math.sin(Math.toRadians(angle)) * 2;
        setCenterX(newX);
        setCenterY(newY);
    }

    public void applyRandomFluctuation() {
        double fluctuation = 3.5; // Ajustez l'amplitude de la fluctuation selon vos préférences
        setCenterX(getCenterX() + (Math.random() * fluctuation - fluctuation / 2));
        setCenterY(getCenterY() + (Math.random() * fluctuation - fluctuation / 2));
    }

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
