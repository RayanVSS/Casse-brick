package entity;

import javafx.scene.shape.Rectangle;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import utils.GameConstants;

public class Boost extends Rectangle {
    private String[] typesList = GameConstants.BONUS_LIST;
    private String type;
    private Coordinates c;


    public Boost(Coordinates c){
        super(c.getX(), c.getY(), GameConstants.WIDTH, GameConstants.HEIGHT);
        type = getRandomType();
        this.c = new Coordinates(c.getX()+(GameConstants.HEIGHT/2), c.getY()+GameConstants.HEIGHT);
        //couleur du boost
        if(type.equals("vitesseP")|| type.equals("largeurP") || type.equals("zhonya")){
            setFill(GameConstants.COLOR_BONUS);
        }else {
            setFill(GameConstants.COLOR_MALUS);
        }
    }



    // Déplacement du boost et activation du boost
    public boolean move(Boolean CollisionRacket, Racket racket) {
        if (CollisionRacket){
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
                default:
                    break;
            }
            return true;
        }
        setY(getY() + GameConstants.BONUS_SPEED);
        this.c.setY(getY());
        return false;
    }

    public static Boost createBoost(Coordinates c) {
        if (Math.random() < GameConstants.BONUS_CHANCE) {
            return new Boost(c);
        }
        return null;


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
