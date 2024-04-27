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
	private Color FREEZE;
	private Color LARGEURP;
	private Color LARGEURM;
	private Color VITESSEP;
	private Color VITESSEM;
	private Color INTENSITYBALL;
	private Color ZHONYA;

	public RacketGraphics(Racket racket, String shapeType) {
		this.racket = racket;
		this.shapeType = shapeType;
		setShape();
		getStrokeColor();
		// Ajout de la texture
		if (!GameConstants.TEXTURE.equals("Null")) {
			image = ImageLoader.loadImage("src/main/ressources/Texture/" + GameConstants.TEXTURE);
			texture = new ImagePattern(image);
			shape.setFill(texture);
			// shape.setStroke(texture);
		} else {
			color();
			shape.setFill(color);
		}
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
		if (racket.getFreeze()) {
			shape.setStroke(FREEZE);
		} else if (racket.getLargeurM()) {
			shape.setStroke(LARGEURM);
		} else if (racket.getLargeurP()) {
			shape.setStroke(LARGEURP);
		} else if (racket.getVitesseP()) {
			shape.setStroke(VITESSEP);
		} else if (racket.getVitesseM()) {
			shape.setStroke(VITESSEM);
		} else if (racket.getIntensityBall()) {
			shape.setStroke(INTENSITYBALL);
		} else if (racket.getZhonya()) {
			shape.setStroke(ZHONYA);
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

	private void getStrokeColor() {
		switch (GameConstants.CSS) {
			case PINK:
			case CLASSIC:
			case LIGHT:
			case BLACK:
				FREEZE = Color.rgb(18, 184, 255);
				LARGEURM = Color.rgb(255, 58, 6);
				LARGEURP = Color.rgb(127, 255, 212); 
				VITESSEP = Color.rgb(141, 255, 10);
				VITESSEM = Color.rgb(252, 94, 49);
				INTENSITYBALL = Color.rgb(255, 239, 6);
				ZHONYA = Color.rgb(1, 227, 46);
				break;
			case ACHROMATOPSIE:
				//TODO: NUANCE DE GRIS 
				FREEZE = Color.rgb(165,165,165);
				LARGEURM = Color.rgb(108,108,108);
				LARGEURP = Color.rgb(165,165,165);
				VITESSEP = Color.rgb(221,221,221);
				VITESSEM = Color.rgb(129,129,129);
				INTENSITYBALL = Color.rgb(231,231,231);
				ZHONYA = Color.rgb(187, 187, 187);
			case DEUTERANOPIE:	
			case PROTANOPIE:
				//TODO: TOUT SAUF Nuance de : ROUGE ET VERT
				//TODO: TOUT SAUF Nuance de : ROUGE et VERT
			case TRITANOPIE:
				//TODO: TOUT SAUF Nuance de : VERT ET VIOLET
			default:
		}
	}

	public void magnetPosColor() {
		shape.setFill(Color.YELLOW);
	}

	public void magnetNegColor() {
		shape.setFill(Color.GREEN);
	}
}