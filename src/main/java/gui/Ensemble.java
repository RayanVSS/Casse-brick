package gui;

import entity.brick.Brick;
import javafx.scene.Group;
import javafx.scene.image.Image;
import utils.GameConstants;

public class Ensemble extends Group {

    public Ensemble(Brick[][] tab) {
        int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;
        BricksGraphics brique;
        for (int i = indexFirstColumn; i < indexFirstColumn +
                GameConstants.COLUMNS_OF_BRICKS; i++) {
            for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
                if (tab[i][j] != null) {
                    Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
                    brique = new BricksGraphics(image, tab[i][j]);
                } else {
                    brique = new BricksGraphics(null, null);
                }
                brique.setLayoutX(i * GameConstants.BRICK_WIDTH);
                brique.setLayoutY(j * GameConstants.BRICK_HEIGHT);
                brique.setVisible(true);
                this.getChildren().add(brique);
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.getChildren().size(); i++) {
            BricksGraphics b = (BricksGraphics) this.getChildren().get(i);
            if (b != null) {
                b.update();
            }
        }
    }
}
