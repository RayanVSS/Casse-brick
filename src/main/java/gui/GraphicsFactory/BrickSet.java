package gui.GraphicsFactory;

import entity.EntityColor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import physics.entity.Brick;
import utils.GameConstants;
import utils.ImageLoader;

public class BrickSet extends Group {
    Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");

    public BrickSet(Brick[][] tab) {
        int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;
        BricksGraphics brick = null;
        for (int i = indexFirstColumn; i < indexFirstColumn +
                GameConstants.COLUMNS_OF_BRICKS; i++) {
            for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
                if (tab[i][j] != null) {
                    EntityColor c = tab[i][j].getColor();
                    if (c != null) {
                        brick = new BricksGraphics(tab[i][j], i, j, c);
                    } else {
                        brick = new BricksGraphics(tab[i][j], i, j);
                    }
                    brick.setLayoutX(i * GameConstants.BRICK_WIDTH);
                    brick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
                    brick.setVisible(true);
                    this.getChildren().add(brick);
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.getChildren().size(); i++) {
            BricksGraphics brick = (BricksGraphics) this.getChildren().get(i);
            if (brick != null) {
                // this.getChildren().remove(brick);
                brick.update();
                // brick.setVisible(true);
                // this.getChildren().add(brick);
            }
        }
    }
}

// switch (c) {
// case RED:
// Image image = ImageLoader.loadImage("src/main/ressources/brique.png");
// brick = new BricksGraphics(image, tab[i][j], tab[i][j].getC().getIntX(),
// tab[i][j].getC().getIntY());
// break;
// case GREEN:
// image = ImageLoader.loadImage("src/main/ressources/briquev.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// break;
// case BLUE:
// image = ImageLoader.loadImage("src/main/ressources/briqueb.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// break;
// default:
// image = ImageLoader.loadImage("src/main/ressources/briquee.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// break;
// }

// if (tab[i][j].isUnbreakable()) {
// Image image = ImageLoader.loadImage("src/main/ressources/briquei.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// } else if ((j + i) % 2 == 0) {
// Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// } else {
// Image image = ImageLoader.loadImage("src/main/ressources/brique.png");
// brick = new BricksGraphics(image, tab[i][j], i, j);
// }
// else {
// brick = new BricksGraphics(null, null, j);
// }