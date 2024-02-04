package gui;

import entite.Brique;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
// import java.util.HashMap;

public class Ensemble extends Group {
    public GridPane grid;

    public Ensemble(Brique[][] tab) {
        this.grid = new GridPane();
        // int count = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                // HashMap<Image, Brique> hash = new HashMap<>();
                // Image image = ImageLoader.loadImage(
                // "file:C:/Users/Amenah
                // Mushtaq/Documents/L2INFO/2023-ad1-c/src/main/ressources/brique.png");
                // Image image = new Image(
                // "file:C:/Users/Amenah
                // Mushtaq/Documents/L2INFO/2023-ad1-c/src/main/ressources/brique.png");
                Image image = null;
                try {
                    // TODO changer le chemin
                    image = new Image(
                            "file:C:/Users/Amenah Mushtaq/Documents/L2INFO/2023-ad1-c/src/main/ressources/briquee.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // hash.put(image, tab[i][j]);
                // BricksGraphics brique = new BricksGraphics(image, tab[i][j]);
                BricksGraphics brique = new BricksGraphics(image);
                brique.setVisible(true);
                this.grid.add(brique, j, i);
                // count++;
            }
        }
        this.getChildren().add(grid);
        // System.out.println("Nombre de briques total :" + count);
        this.grid.setVisible(true);
    }

}

// public Ensemble(Brique[][] tab) {
// this.grid = new GridPane();
// for (int i = 0; i < tab.length; i++) {
// for (int j = 0; j < tab[0].length; j++) {
// Image image = new Image(
// "file:C:/Users/Amenah
// Mushtaq/Documents/L2INFO/2023-ad1-c/src/main/ressources/brique.png");
// Brique brique = tab[i][j];

// if (image.isError()) {
// System.out.println("Erreur lors du chargement de l'image : " +
// image.getUrl());
// } else {
// // System.out.println("L'image a EtE chargEe avec succEs : " +
// image.getUrl());
// BricksGraphics bricksGraphics = new BricksGraphics(image, brique);
// bricksGraphics.setVisible(true);
// this.grid.add(bricksGraphics, j, i);
// }
// }
// this.grid.setVisible(true);
// }
// }