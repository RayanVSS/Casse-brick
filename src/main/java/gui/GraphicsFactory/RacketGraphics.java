package gui.GraphicsFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import entity.racket.*;
import physics.entity.Racket;
import utils.GameConstants;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Classe RacketGraphics qui encapsule un objet Shape pour représenter
 * graphiquement une raquette.
 * 
 * @author Benmalek Majda | belhassen rayan
 */
public class RacketGraphics {
    private Shape shape;
    private Racket racket;
    private String shapeType;
    //pour la texture
    private Image image;
    private ImagePattern texture;
    private Color color;

    public RacketGraphics(Racket racket, String shapeType) {
        this.racket = racket;
        this.shapeType = shapeType;
        setShape();
        // Ajout de la texture
        if(!GameConstants.TEXTURE.equals("Null")){
            image = ImageLoader.loadImage("src/main/ressources/Texture/" + GameConstants.TEXTURE);
            texture = new ImagePattern(image);
            shape.setFill(texture);
            shape.setStroke(texture);
        }
        color();
        shape.setFill(color);
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

        if (GameConstants.TEXTURE.equals("Null")){
            if (racket instanceof ClassicRacket){
                shape.getStyleClass().add("racket");
            }   
            else if (racket instanceof YNotFixeRacket){
                shape.getStyleClass().add("ynotfixeracket");
            }
            else if (racket instanceof MagnetRacket){
                shape.getStyleClass().add("magnetracket");
            } 
        } else {
            addTexture();
        }
    }

    private void addTexture() {
        shape.setFill(texture);
        shape.setStroke(texture);
    }


    public Shape getShape() {
        return shape;
    }

    public void update() {
        setShape();
        if (racket instanceof MagnetRacket) {
            if (MagnetRacket.getEtat().equals("positif")) {
                magnetPosColor();
            } else {
                magnetNegColor();
            }
        }   
        shape.setStrokeWidth(5);
        //TODO: changer les couleurs
        if (racket.getFreeze()) {
            shape.setFill(color);
            shape.setStroke(Color.rgb(165, 197, 217));
        } else if (racket.getLargeurM()) {
            shape.setFill(color);
            shape.setStroke(Color.rgb(255, 0, 0));
        } else if (racket.getLargeurP()) {
            shape.setFill(color);
            shape.setStroke(Color.BLUE);
        } else if (racket.getVitesseP()) {
            shape.setFill(color);
            shape.setStroke(Color.BLUE);
        } else if (racket.getVitesseM()) {
            shape.setFill(color);
            shape.setStroke(Color.rgb(255, 0, 0));
        } else if (racket.getIntensityBall()) {
            shape.setFill(color);
            shape.setStroke(Color.BLUE);
        } else if (racket.getZhonya()) {
            shape.setFill(color);
            shape.setStroke(Color.BLUE);
        } else {
            shape.setFill(color);
        }
    }

    public Color color() {
        switch (GameConstants.CSS) {
            case PINK:
                color = Color.rgb(199, 21, 133);
                break;
            case CLASSIC:
                color = Color.rgb(39, 54, 84);
                break;
            case LIGHT:
                color = Color.rgb(101, 119, 134);
                break;
            case BLACK:
                color = Color.rgb(245, 245, 245);
                break;
            case ACHROMATOPSIE:
            case DEUTERANOPIE:
            case PROTANOPIE:
            case TRITANOPIE:
                color = Color.rgb(101, 119, 134);
                break;
            default:
                break;
        }
        return color;
    }

    public void magnetPosColor() {
        shape.setFill(Color.YELLOW);
    }

    public void magnetNegColor() {
        shape.setFill(Color.GREEN);
    }
}