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
	// pour la couleur
	private Color color;
	private Color BOOST;
	private Color MALUS;
	private Color MAGNET_POS;
	private Color MAGNET_NEG;

	public RacketGraphics(Racket racket, String shapeType) {
		this.racket = racket;
		this.shapeType = shapeType;
		switch (shapeType.toLowerCase()) {
			case "rectangle":
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
		getStrokeColor();
		color();
		if (!GameConstants.TEXTURE.equals("Null")) {
			image = ImageLoader.loadImage("src/main/ressources/Texture/" + GameConstants.TEXTURE);
			texture = new ImagePattern(image);
			addTexture();
		} else {
			shape.setFill(color);
			shape.setStroke(color);
		}
	}

	public void update() {
		updatePos();
		magnetColor();
		strokeColor();
	}

	// public void updatePos() {
	// switch (shapeType.toLowerCase()) {
	// case "rectangle":
	// setRectangleShape();
	// break;
	// case "losange":
	// setLosangeShape();
	// break;
	// case "rond":
	// setRondShape();
	// break;
	// case "triangle":
	// setTriangleShape();
	// break;
	// default:
	// throw new IllegalArgumentException("Forme non reconnue: " + shapeType);
	// }
	// if (!GameConstants.TEXTURE.equals("Null")) {
	// addTexture();
	// } else {
	// shape.setFill(color);
	// shape.setStroke(color);
	// }
	// }

	// private void setRectangleShape() {
	// ((Rectangle) shape).setX(racket.getC().getX());
	// ((Rectangle) shape).setY(racket.getC().getY());
	// ((Rectangle) shape).setWidth(racket.getLargeur());
	// ((Rectangle) shape).setHeight(racket.getLongueur());
	// ((Rectangle) shape).setArcWidth(20);
	// ((Rectangle) shape).setArcHeight(20);
	// }

	// private void setLosangeShape() {
	// ((Polygon) shape).getPoints().addAll(new Double[] {
	// racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
	// racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY(),
	// racket.getC().getX(), racket.getC().getY() + racket.getLongueur() / 2,
	// racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY()
	// });
	// }

	// private void setRondShape() {
	// ((Ellipse) shape).setCenterX(racket.getC().getX());
	// ((Ellipse) shape).setCenterY(racket.getC().getY());
	// ((Ellipse) shape).setRadiusX(racket.getLargeur() / 2);
	// ((Ellipse) shape).setRadiusY(racket.getLongueur() / 2);
	// }

	// private void setTriangleShape() {
	// ((Polygon) shape).getPoints().addAll(new Double[] {
	// racket.getC().getX(), racket.getC().getY() - racket.getLongueur() / 2,
	// racket.getC().getX() + racket.getLargeur() / 2, racket.getC().getY() +
	// racket.getLongueur() / 2,
	// racket.getC().getX() - racket.getLargeur() / 2, racket.getC().getY() +
	// racket.getLongueur() / 2
	// });

	// }
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

		// if (GameConstants.TEXTURE.equals("Null")) {
		// 	shape.setFill(color);
		// } else {
		// 	addTexture();
		// }
	}

	public void updatePos() {
		if (shape instanceof Rectangle) {
			((Rectangle) shape).setX(racket.getC().getX());
			((Rectangle) shape).setY(racket.getC().getY());
		} else if (shape instanceof Polygon) {
			((Polygon) shape).getPoints().set(0, racket.getC().getX());
			((Polygon) shape).getPoints().set(1, racket.getC().getY() - racket.getLongueur() / 2);
			((Polygon) shape).getPoints().set(2, racket.getC().getX() + racket.getLargeur() / 2);
			((Polygon) shape).getPoints().set(3, racket.getC().getY());
			((Polygon) shape).getPoints().set(4, racket.getC().getX());
			((Polygon) shape).getPoints().set(5, racket.getC().getY() + racket.getLongueur() / 2);
			((Polygon) shape).getPoints().set(6, racket.getC().getX() - racket.getLargeur() / 2);
			((Polygon) shape).getPoints().set(7, racket.getC().getY());
		} else if (shape instanceof Ellipse) {
			((Ellipse) shape).setCenterX(racket.getC().getX());
			((Ellipse) shape).setCenterY(racket.getC().getY());
		}
	}

	private void magnetColor() {
		if (racket instanceof MagnetRacket) {
			if (MagnetRacket.getEtat().equals("positif")) {
				shape.setFill(MAGNET_POS);
			} else {
				shape.setFill(MAGNET_NEG);
			}
		}
	}

	private void strokeColor() {
		shape.setStrokeWidth(3.5);
		if (racket.getFreeze() || racket.getLargeurM() || racket.getVitesseM() || racket.getIntensityBall()) {
			shape.setStroke(MALUS);
		} else if (racket.getLargeurP() || racket.getVitesseP() || racket.getZhonya()) {
			shape.setStroke(BOOST);
		} else {
			if (!GameConstants.TEXTURE.equals("Null")) {
				addTexture();
			} else {
				shape.setStroke(color);
				shape.setFill(color);
			}
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
				BOOST = Color.rgb(0, 255, 0);
				MALUS = Color.rgb(255, 0, 0);
				MAGNET_POS = Color.rgb(255, 255, 0);
				MAGNET_NEG = Color.rgb(0, 128, 0);
				break;
			case ACHROMATOPSIE:
				BOOST = Color.rgb(150, 150, 150);
				MALUS = Color.rgb(10, 10, 10);
				MAGNET_POS = Color.rgb(130, 130, 130);
				MAGNET_NEG = Color.rgb(40, 40, 40);
				break;
			case DEUTERANOPIE:
				BOOST = Color.rgb(255, 211, 143);
				MALUS = Color.rgb(162, 122, 0);
				MAGNET_POS = Color.rgb(255, 246, 233);
				MAGNET_NEG = Color.rgb(137, 104, 29);
				break;
			case PROTANOPIE:
				BOOST = Color.rgb(246, 218, 1);
				MALUS = Color.rgb(145, 128, 38);
				MAGNET_POS = Color.rgb(255, 247, 216);
				MAGNET_NEG = Color.rgb(123, 110, 0);
				break;
			case TRITANOPIE:
				BOOST = Color.rgb(112, 236, 255);
				MALUS = Color.rgb(253, 23, 1);
				MAGNET_POS = Color.rgb(255, 244, 249);
				MAGNET_NEG = Color.rgb(54, 118, 129);
				break;
			default:
				break;
		}
	}

	private void addTexture() {
		shape.setFill(texture);
		shape.setStroke(texture);
	}

	public Shape getShape() {
		return shape;
	}
}