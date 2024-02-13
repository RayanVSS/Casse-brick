// package gui;

// import entity.brick.Brick;
// import javafx.scene.Group;
// import javafx.scene.image.Image;
// import javafx.scene.layout.GridPane;
// import utils.GameConstants;

// public class Ensemble extends Group {
//     public GridPane grid;

//     // public Ensemble(Brick[][] tab) {
//     // this.grid = new GridPane();
//     // for (int i = 0; i < GameConstants.ROWS_OF_BRICKS; i++) {
//     // for (int j = 0; j < GameConstants.COLUMNS_OF_BRICKS; j++) {
//     // Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
//     // BricksGraphics brique = new BricksGraphics(image, tab[i][j]);
//     // brique.setVisible(true);
//     // this.grid.add(brique, j, i);
//     // }

//     // }
//     // this.getChildren().add(grid);
//     // this.grid.setVisible(true);
//     // }
//     public Ensemble(Brick[][] tab) {
//         this.grid = new GridPane();
//         for (int i = 0; i < tab.length; i++) {
//             for (int j = 0; j < tab[0].length; j++) {
//                 if (tab[i][j] != null) {
//                     Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
//                     BricksGraphics brique = new BricksGraphics(image, tab[i][j]);
//                     brique.setVisible(true);
//                     brique.setLayoutX(i * GameConstants.BRICK_WIDTH);
//                     brique.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//                     // this.grid.add(brique, i, j);
//                 } else {
//                     BricksGraphics brique = new BricksGraphics(null, null);
//                     brique.setLayoutX(i * GameConstants.BRICK_WIDTH);
//                     brique.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//                     brique.setVisible(true);
//                     // this.grid.add(brique, i, j);
//                 }
//             }

//         }
//         this.grid.setVisible(true);
//         this.getChildren().add(this.grid);
//     }

//     public void update() {
//         for (int i = 0; i < grid.getChildren().size(); i++) {
//             BricksGraphics b = (BricksGraphics) grid.getChildren().get(i);
//             if (b != null) {
//                 b.update();
//             }
//         }
//     }
// }

// BROUILLON

// package gui;

// import entity.brick.Brick;
// import javafx.scene.Group;
// import javafx.scene.image.Image;
// import utils.GameConstants;

// public class Ensemble extends Group {

// public Ensemble(Brick[][] tab) {
// BricksGraphics brique = null;
// for (int i = 0; i < tab.length; i++) {
// for (int j = 1; j < tab[0].length; j++) {
// if (tab[i][j] != null) {
// Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
// brique = new BricksGraphics(image, tab[i][j]);
// brique.setVisible(true);
// this.add(brique, i, j);
// }
// }
// }
// }

// private void add(BricksGraphics brique, int i, int j) {
// this.getChildren().add(brique);
// this.getChildren().get(i).setLayoutX(i * GameConstants.BRICK_WIDTH);
// this.getChildren().get(i).setLayoutY(j * GameConstants.BRICK_HEIGHT);
// }

// public void update() {
// for (int i = 0; i < this.getChildren().size(); i++) {
// BricksGraphics b = (BricksGraphics) this.getChildren().get(i);
// if (b.brique != null) {
// b.update();
// }
// }
// }
// }

package gui;

import entity.brick.Brick;
import javafx.scene.Group;
import javafx.scene.image.Image;
import utils.GameConstants;

public class Ensemble extends Group {

    public Ensemble(Brick[][] tab) {
        int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;
        for (int i = indexFirstColumn; i < indexFirstColumn +
                GameConstants.COLUMNS_OF_BRICKS; i++) {
            for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
                if (tab[i][j] != null) {
                    Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
                    BricksGraphics brique = new BricksGraphics(image, tab[i][j]);
                    brique.setVisible(true);
                    brique.setLayoutX(i * GameConstants.BRICK_WIDTH);
                    brique.setLayoutY(j * GameConstants.BRICK_HEIGHT);
                    this.getChildren().add(brique);
                } else {
                    BricksGraphics brique = new BricksGraphics(null, null);
                    brique.setLayoutX(i * GameConstants.BRICK_WIDTH);
                    brique.setLayoutY(j * GameConstants.BRICK_HEIGHT);
                    brique.setVisible(true);
                    this.getChildren().add(brique);
                }
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
