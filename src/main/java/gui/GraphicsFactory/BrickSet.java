// package gui.GraphicsFactory;

// import config.Map;
// import entity.EntityColor;
// import gui.ImageLoader;
// import javafx.scene.Group;
// import javafx.scene.image.Image;
// import physics.entity.Brick;
// import utils.GameConstants;



// //TODO : SERT PLUS A RIEN
// public class BrickSet extends Group {
//     // Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");

//     // public BrickSet(Brick[][] tab) {
//     // int indexFirstColumn = GameConstants.MAP_WIDTH /
//     // GameConstants.COLUMNS_OF_BRICKS;
//     // BricksGraphics brick = null;
//     // for (int i = indexFirstColumn; i < indexFirstColumn +
//     // GameConstants.COLUMNS_OF_BRICKS; i++) {
//     // for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
//     // EntityColor c = tab[i][j].getColor();
//     // if (c != null) {
//     // brick = new BricksGraphics(tab[i][j], i, j, c);
//     // } else {
//     // brick = new BricksGraphics(tab[i][j], i, j);
//     // }
//     // brick.setLayoutX(i * GameConstants.BRICK_WIDTH);
//     // brick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//     // brick.setVisible(true);
//     // this.getChildren().add(brick);
//     // }
//     // }
//     // }

//     // public BrickSet(Brick[][] tab) {
//     //     BricksGraphics brick = null;
//     //     for (int i = 0; i < tab.length; i++) {
//     //         for (int j = 0; j < tab[0].length; j++) {
//     //             if (tab[i][j] != null) {
//     //                 EntityColor c = tab[i][j].getColor();
//     //                 if (c != null) {
//     //                     brick = new BricksGraphics(tab[i][j], i, j, c);
//     //                 } else {
//     //                     brick = new BricksGraphics(tab[i][j], i, j);
//     //                 }
//     //                 brick.setLayoutX(i * GameConstants.BRICK_WIDTH);
//     //                 brick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//     //                 brick.setVisible(true);
//     //                 this.getChildren().add(brick);
//     //             }
//     //         }
//     //     }
//     // }


//     //POUR LES TESTS
//     public BrickSet(Brick[][] tab) {
//         BricksGraphics brick = null;
//         int indexFirstColumn = GameConstants.MAP_WIDTH / GameConstants.COLUMNS_OF_BRICKS;
//         for (int i = indexFirstColumn; i < indexFirstColumn +
//                 GameConstants.COLUMNS_OF_BRICKS; i++) {
//             for (int j = 1; j < GameConstants.ROWS_OF_BRICKS + 1; j++) {
//                 if (tab[i][j] != null) {
//                     EntityColor c = tab[i][j].getColor();
//                     if (c != null) {
//                         brick = new BricksGraphics(tab[i][j], i, j, c);
//                     } else {
//                         brick = new BricksGraphics(tab[i][j], i, j);
//                     }
//                     brick.setLayoutX(i * GameConstants.BRICK_WIDTH);
//                     brick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//                     brick.setVisible(true);
//                     this.getChildren().add(brick);
//                 }
//             }
//         }
//     }

//     public void update() {
//         for (int i = 0; i < this.getChildren().size(); i++) {
//             BricksGraphics brick = (BricksGraphics) this.getChildren().get(i);
//             if (brick != null) {
//                 // this.getChildren().remove(brick);
//                 brick.update();
//                 // brick.setVisible(true);
//                 // this.getChildren().add(brick);
//             }
//         }
//     }

//     public boolean isCreated(int i, int j) {
//         for (int k = 0; k < this.getChildren().size(); k++) {
//             BricksGraphics brick = (BricksGraphics) this.getChildren().get(k);
//             if (brick.getI() == i && brick.getJ() == j) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     // public void infiniteUpdate(Map map) {
//     //     for (int i = 0; i < map.getBricks().length; i++) {
//     //         for (int j = 0; j < map.getBricks()[0].length; j++) {
//     //             if (map.getBricks()[i][j] != null) {
//     //                 if (isCreated(i, j)) {
//     //                     BricksGraphics brick = (BricksGraphics) this.getChildren()
//     //                             .get(i * GameConstants.ROWS_OF_BRICKS + j);
//     //                     brick.update();
//     //                 } else {
//     //                     BricksGraphics newBrick = new BricksGraphics(map.getBricks()[i][j], i, j);
//     //                     newBrick.setLayoutX(i * GameConstants.BRICK_WIDTH);
//     //                     newBrick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//     //                     newBrick.setVisible(true);
//     //                     this.getChildren().add(newBrick);
//     //                 }
//     //             }
//     //         }
//     //     }
//     // }

//     //POUR LES TESTS
//     public void infiniteUpdate(Map map) {
//         BricksGraphics newBrick;
//         for (int i = 0; i < map.getBricks().length; i++) {
//             for (int j = 0; j < map.getBricks()[0].length; j++) {
//                 if (map.getBricks()[i][j] != null) {
//                     if (isCreated(i, j)) {
//                         BricksGraphics brick = (BricksGraphics) this.getChildren()
//                                 .get(i * GameConstants.ROWS_OF_BRICKS + j);
//                         brick.update();
//                     } else {
//                         if ((i+j)%2==0){
//                              newBrick = new BricksGraphics(map.getBricks()[i][j], i, j,EntityColor.BLUE);
//                         }else{
//                             newBrick = new BricksGraphics(map.getBricks()[i][j], i, j,EntityColor.RED);
//                         }
//                         newBrick.setLayoutX(i * GameConstants.BRICK_WIDTH);
//                         newBrick.setLayoutY(j * GameConstants.BRICK_HEIGHT);
//                         newBrick.setVisible(true);
//                         this.getChildren().add(newBrick);
//                     }
//                 }
//             }
//         }
//     }
// }

// // switch (c) {
// // case RED:
// // Image image = ImageLoader.loadImage("src/main/ressources/brique.png");
// // brick = new BricksGraphics(image, tab[i][j], tab[i][j].getC().getIntX(),
// // tab[i][j].getC().getIntY());
// // break;
// // case GREEN:
// // image = ImageLoader.loadImage("src/main/ressources/briquev.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // break;
// // case BLUE:
// // image = ImageLoader.loadImage("src/main/ressources/briqueb.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // break;
// // default:
// // image = ImageLoader.loadImage("src/main/ressources/briquee.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // break;
// // }

// // if (tab[i][j].isUnbreakable()) {
// // Image image = ImageLoader.loadImage("src/main/ressources/briquei.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // } else if ((j + i) % 2 == 0) {
// // Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // } else {
// // Image image = ImageLoader.loadImage("src/main/ressources/brique.png");
// // brick = new BricksGraphics(image, tab[i][j], i, j);
// // }
// // else {
// // brick = new BricksGraphics(null, null, j);
// // }