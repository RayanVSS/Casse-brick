// package gui;

// import java.util.HashMap;
// import entite.Brique;
// // import javafx.scene.Group;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.StackPane;

// //group

// public class BricksGraphics extends StackPane {
//     public HashMap<Image, Brique> brique;

//     public BricksGraphics(Image image, Brique brique) {
//         this.brique = new HashMap<>();
//         this.brique.put(image, brique);
//         ImageView imageView = new ImageView(image);
//         imageView.setPreserveRatio(true);
//         imageView.setFitWidth(50);
//         getChildren().add(imageView);
//         // System.out.println("BRICKS GRAPHICS");
//     }

// }

package gui;

// import entite.Brique;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BricksGraphics extends StackPane {
    private ImageView imageView;

    public BricksGraphics(Image image) {
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(60);
        getChildren().add(imageView);
        System.out.println("BricksGraphics ajoute avec succes !");
    }
}

// public BricksGraphics(HashMap<Image, Brique> brique) {
// this.brique = brique;
// }
// public class BricksGraphics extends StackPane {
// private Brique brique;

// public BricksGraphics(Image image, Brique brique) {
// this.brique = brique;

// ImageView imageView = new ImageView(image);
// getChildren().add(imageView);
// }

// public Brique getBrique() {
// return brique;
// }
// }
