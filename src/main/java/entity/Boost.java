package entity;

import config.Game;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class Boost extends Bonus {
    private String[] typesList = GameConstants.BONUS_LIST;
    private String type;
    private Coordinates c;
    private Color COLOR_BONUS;
    private Color COLOR_MALUS;

    public Boost(Coordinates c) {
        super(c.getX(), c.getY(), GameConstants.WIDTH, GameConstants.HEIGHT);
        type = getRandomType();
        super.setC(new Coordinates(c.getX() + (GameConstants.HEIGHT / 2), c.getY() + GameConstants.HEIGHT));
        // couleur du boost
        color();
        if (type.equals("vitesseP") || type.equals("largeurP") || type.equals("zhonya")) {
            setFill(COLOR_BONUS);
        } else if (type.equals("infiniteStop")) {
            setFill(GameConstants.COLOR_INFINITE_STOP);
        } else {
            setFill(GameConstants.COLOR_MALUS);
        }
    }

    // Déplacement du boost et activation du boost
    public boolean move(Boolean CollisionRacket, Racket racket, Game game) {
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
                case "infiniteStop":
                    System.out.println("infiniteStop");
                    // game.getRules().infiniteUpdate(game.getMap(), 0);
                    break;
                default:
                    break;
            }
            return true;
        }
        // deplacement du boost
        setY(getY() + GameConstants.BONUS_SPEED);
        super.getC().setY(getY());
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
                COLOR_BONUS = Color.rgb(0, 255, 0);
                COLOR_MALUS = Color.rgb(255, 0, 0);
                break;
            case ACHROMATOPSIE:
                COLOR_BONUS = Color.rgb(150, 150, 150);
                COLOR_MALUS = Color.rgb(10, 10, 10);
                break;
            case DEUTERANOPIE:
                COLOR_BONUS = Color.rgb(255, 211, 143);
                COLOR_MALUS = Color.rgb(162, 122, 0);
                break;
            case PROTANOPIE:
                COLOR_BONUS = Color.rgb(246, 218, 1);
                COLOR_MALUS = Color.rgb(145, 128, 38);
                break;
            case TRITANOPIE:
                COLOR_BONUS = Color.rgb(112, 236, 255);
                COLOR_MALUS = Color.rgb(253, 23, 1);
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

    public int getWIDTH() {
        return GameConstants.WIDTH;
    }

}
