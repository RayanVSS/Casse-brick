package gui.GraphicsFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import physics.entity.*;
import entity.racket.*;
import physics.entity.Racket;

/**
 * Classe RacketGraphics qui encapsule un objet Shape pour représenter graphiquement une raquette.
 * 
 * @author Benmalek Majda | belhassen rayan
 */
public class RacketGraphics {
    private Shape shape;
    private Racket racket;
    private String shapeType;

    public RacketGraphics(Racket racket, String shapeType) {
        this.racket = racket;
        this.shapeType = shapeType;
        setShape();
    }

    private void setShape() {
        switch (shapeType.toLowerCase()) {
            case "rectangle":
                shape = new Rectangle();
                ((Rectangle) shape).setX(racket.getC().getX());
                ((Rectangle) shape).setY(racket.getC().getY());
                ((Rectangle) shape).setWidth(racket.getLargeur());
                ((Rectangle) shape).setHeight(racket.getLongueur());
                ((Rectangle) shape).setArcWidth(20);
                ((Rectangle) shape).setArcHeight(20);
                break;
            case "losange":
                shape = new Polygon();
                ((Polygon) shape).getPoints().addAll(new Double[] {
                        racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
                        racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY(),
                        racket.getC().getX(), racket.getC().getY() + racket.getLongueur() / 2,
                        racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY()
                });
                break;
            case "rond":
                shape = new Ellipse();
                ((Ellipse) shape).setCenterX(racket.getC().getX());
                ((Ellipse) shape).setCenterY(racket.getC().getY());
                ((Ellipse) shape).setRadiusX(racket.getLargeur() / 2);
                ((Ellipse) shape).setRadiusY(racket.getLongueur() / 2);
                break;
            case "triangle":
                shape = new Polygon();
                ((Polygon) shape).getPoints().addAll(new Double[] {
                        racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
                        racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY() + racket.getLongueur() / 2,
                        racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY() + racket.getLongueur() / 2
                });
                break;
            default:
                throw new IllegalArgumentException("Forme non reconnue: " + shapeType);
        }

        if (racket instanceof ClassicRacket)
            shape.getStyleClass().add("racket");
        else if (racket instanceof YNotFixeRacket)
            shape.getStyleClass().add("ynotfixeracket");
        else if (racket instanceof MagnetRacket)
            shape.getStyleClass().add("magnetracket");
    }

    

    public Shape getShape() {
        return shape;
    }

    public void update() {
        setShape();
        if (racket instanceof MagnetRacket) {
            if (MagnetRacket.getEtat().equals("positif")) {
                shape.setFill(Color.YELLOW);
            } else {
                shape.setFill(Color.GREEN);
            }
        }
    }
}