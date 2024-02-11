package gui;

import entity.brick.Brick;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BricksGraphics extends StackPane {
    public ImageView imageView;
    public Brick brique;

    public BricksGraphics(Image image, Brick brique) {
        this.brique = brique;
        this.imageView = new ImageView(image);
        this.imageView.setPreserveRatio(true);
        this.imageView.setFitWidth(60);
        getChildren().add(this.imageView);
    }

    public void update() {
        if (brique.getDurability() == 0) {
            imageView = null;
        } else if (brique.getDurability() < 70 && brique.getDurability() > 50) {
            Image im = ImageLoader.loadImage("src/main/ressources/briquec.png");
            imageView = new ImageView(im);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(60);
            getChildren().add(imageView);
        } else if (brique.getDurability() > 0 && brique.getDurability() < 50) {
            Image im = ImageLoader.loadImage("src/main/ressources/briquecc.png");
            imageView = new ImageView(im);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(60);
        }
    }
}
