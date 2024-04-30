package gui.GraphicsFactory;

import entity.racket.MagnetRacket;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import physics.entity.Racket;
import utils.GameConstants;

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
	// pour la texture
	private Image image;
	private ImagePattern texture;
	private Color color;

	public RacketGraphics(Racket racket, String shapeType) {
		this.racket = racket;
		this.shapeType = shapeType;
		switch (shapeType.toLowerCase()) {
			case "rectangle":
			System.err.println("aaaaaaaaaaaaaaaaaaaaaa");
				shape = new Rectangle();
				break;
			case "losange":
				shape = new Polygon();
				break;
			case "rond":
				shape = new Ellipse();
				break;
			case "triangle":
				shape = new Polygon();
				break;
		}
		setShape();
		// Ajout de la texture
		if (!GameConstants.TEXTURE.equals("Null")) {
			image = ImageLoader.loadImage("src/main/ressources/Texture/" + GameConstants.TEXTURE);
			texture = new ImagePattern(image);
			shape.setFill(texture);
			//shape.setStroke(texture);
		} else {
			color();
			shape.setFill(color);
		}

	}

	private void setShape() {
		switch (shapeType.toLowerCase()) {
			case "rectangle":
				((Rectangle) shape).setX(racket.getC().getX());
				((Rectangle) shape).setY(racket.getC().getY());
				((Rectangle) shape).setWidth(racket.getLargeur());
				((Rectangle) shape).setHeight(racket.getLongueur());
				((Rectangle) shape).setArcWidth(20);
				((Rectangle) shape).setArcHeight(20);
				break;
			case "losange":
				((Polygon) shape).getPoints().addAll(new Double[] {
						racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
						racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY(),
						racket.getC().getX(), racket.getC().getY() + racket.getLongueur() / 2,
						racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY()
				});
				break;
			case "rond":
				((Ellipse) shape).setCenterX(racket.getC().getX());
				((Ellipse) shape).setCenterY(racket.getC().getY());
				((Ellipse) shape).setRadiusX(racket.getLargeur() / 2);
				((Ellipse) shape).setRadiusY(racket.getLongueur() / 2);
				break;
			case "triangle":
				((Polygon) shape).getPoints().addAll(new Double[] {
						racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
						racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY() + racket.getLongueur() / 2,
						racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY() + racket.getLongueur() / 2
				});
				break;
			default:
				throw new IllegalArgumentException("Forme non reconnue: " + shapeType);
		}

		if (GameConstants.TEXTURE.equals("Null")) {
			shape.setFill(color);
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
		// TODO: changer les couleurs
		if (racket.getFreeze()) {
			shape.setStroke(Color.rgb(165, 197, 217));
		} else if (racket.getLargeurM()) {
			shape.setStroke(Color.rgb(255, 0, 0));
		} else if (racket.getLargeurP()) {
			shape.setStroke(Color.rgb(76, 187, 23));
		} else if (racket.getVitesseP()) {
			shape.setStroke(Color.rgb(64, 130, 109));
		} else if (racket.getVitesseM()) {
			shape.setStroke(Color.rgb(255, 0, 0));
		} else if (racket.getIntensityBall()) {
			shape.setStroke(Color.rgb(102, 66, 77));
		} else if (racket.getZhonya()) {
			shape.setStroke(Color.rgb(144, 238, 144));
		}
		// if(color != null){
		// 	shape.setFill(color);
		// }
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