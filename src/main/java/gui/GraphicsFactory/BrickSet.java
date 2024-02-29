package gui.GraphicsFactory;

import entity.brick.Brick;
import gui.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import utils.GameConstants;

public class BrickSet extends Group {
    Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");

    public BrickSet(Brick[][] tab) {
        int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;
        BricksGraphics brick;
        for (int i = indexFirstColumn; i < indexFirstColumn +
                GameConstants.COLUMNS_OF_BRICKS; i++) {
            for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
                if (tab[i][j] != null) {
                    // Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
                    brick = new BricksGraphics(image, tab[i][j]);
                } else {
                    brick = new BricksGraphics(null, null);
                }
                brick.setLayoutX(i * GameConstants.BRICK_WIDTH);
                brick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
                brick.setVisible(true);
                this.getChildren().add(brick);
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.getChildren().size(); i++) {
            BricksGraphics brick = (BricksGraphics) this.getChildren().get(i);
            if (brick != null) {
                brick.update();
            }
        }
    }
}
