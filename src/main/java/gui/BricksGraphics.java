package gui;

import entite.Brique;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BricksGraphics extends StackPane {
    public ImageView imageView;
    public Brique brique;

    public BricksGraphics(Image image, Brique brique) {
        this.brique = brique;
        this.imageView = new ImageView(image);
        this.imageView.setPreserveRatio(true);
        this.imageView.setFitWidth(60);
        getChildren().add(this.imageView);
    }

    public void update() {
        if (brique.getDurabilite() == 0) {
            imageView = null;
        } else if (brique.getDurabilite() < 70 && brique.getDurabilite() > 50) {
            Image im = ImageLoader.loadImage("src/main/ressources/briquec.png");
            imageView = new ImageView(im);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(60);
            getChildren().add(imageView);
        } else if (brique.getDurabilite() > 0 && brique.getDurabilite() < 50) {
            Image im = ImageLoader.loadImage("src/main/ressources/briquecc.png");
            imageView = new ImageView(im);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(60);
        }
    }
}
