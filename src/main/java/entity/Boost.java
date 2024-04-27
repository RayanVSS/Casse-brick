package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class Boost extends Rectangle {
    private String[] typesList = GameConstants.BONUS_LIST;
    private String type;
    private Coordinates c;
    private Color COLOR_BONUS;
    private Color COLOR_MALUS;

    public Boost(Coordinates c) {
        super(c.getX(), c.getY(), GameConstants.WIDTH, GameConstants.HEIGHT);
        type = getRandomType();
        this.c = new Coordinates(c.getX() + (GameConstants.HEIGHT / 2), c.getY() + GameConstants.HEIGHT);
        // couleur du boost
        color();
        if (type.equals("vitesseP") || type.equals("largeurP") || type.equals("zhonya")) {
            setFill(COLOR_BONUS);
        } else {
            setFill(COLOR_MALUS);
        }
    }

    // Déplacement du boost et activation du boost
    public boolean move(Boolean CollisionRacket, Racket racket) {
        if (CollisionRacket) {
            switch (type) {
                case "vitesseP":
                    System.out.println("vitesseP");
                    racket.setVitesseP(true);
                    break;
                case "vitesseM":
                    System.out.println("vitesseM");
                    racket.setVitesseM(true);
                    break;
                case "largeurP":
                    System.out.println("largeurP");
                    racket.setlargeurP(true);
                    break;
                case "largeurM":
                    System.out.println("largeurM");
                    racket.setLargeurM(true);
                    break;
                case "freeze":
                    System.out.println("freeze");
                    racket.setFreeze(true);
                    break;
                case "zhonya":
                    System.out.println("zhonya");
                    racket.setZhonya(true);
                    break;
                case "intensityBall":
                    System.out.println("intensityBall");
                    racket.setIntensityBall(true);
                    break;
                default:
                    break;
            }
            return true;
        }
        // deplacement du boost
        setY(getY() + GameConstants.BONUS_SPEED);
        this.c.setY(getY());
        return false;
    }

    // Création d'un boost
    public static Boost createBoost(Coordinates c) {
        if (Math.random() < GameConstants.BONUS_CHANCE) {
            return new Boost(c);
        }
        return null;

    }

    // Couleur du boost
    private void color() {
        switch (GameConstants.CSS) {
            case CLASSIC:
            case PINK:
            case LIGHT:
            case BLACK:
                COLOR_BONUS = Color.rgb(57, 255, 20);
                COLOR_MALUS = Color.rgb(255, 87, 51);
                break;
            case ACHROMATOPSIE:
                COLOR_BONUS = Color.GREY;
                COLOR_MALUS = Color.BLACK;
                break;
            case DEUTERANOPIE:
                COLOR_BONUS = Color.rgb(254,1,154);
                COLOR_MALUS = Color.rgb(31, 81, 255);
                break;
            case PROTANOPIE:
                //TODO: eviter le rouge et le vert
                break;
            case TRITANOPIE:
                //TODO: eviter le vert et le violet 
            default:
                break;
        }
    }

    private String getRandomType() {
        return typesList[(int) (Math.random() * typesList.length)];
    }

    public String getType() {
        return type;
    }

    public Coordinates getC() {
        return c;
    }

    public int getWIDTH() {
        return GameConstants.WIDTH;
    }

}
