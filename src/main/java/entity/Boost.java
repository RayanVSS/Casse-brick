package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.GameConstants;


public class Boost extends Rectangle {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final Color COLOR = Color.GREEN;
    private String[] typesList = {"vitesseP", "vitesseM", "largeurP", "largeurM","freeze"};
    private String type;

    public Boost(double x, double y,String type) {
        super(x, y, WIDTH, HEIGHT);
        this.type = RandomType();
        this.setFill(COLOR);
    }

    public void update() {
        setY(getY() + 1);
        if(getY() > GameConstants.DEFAULT_WINDOW_WIDTH){
            //TODO
        }
    }

    public String RandomType(){
        int random = (int) (Math.random() * typesList.length-1);
        return typesList[random];
    }



 
}
