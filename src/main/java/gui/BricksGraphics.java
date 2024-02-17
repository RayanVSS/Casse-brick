package gui;

import entity.brick.Brick;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.*;

public class BricksGraphics extends StackPane {
    public ImageView imageView;
    public Brick brique;

    public BricksGraphics(Image image, Brick brique) {
        this.brique = brique;
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(GameConstants.BRICK_WIDTH);
        this.imageView.setFitHeight(GameConstants.BRICK_HEIGHT);
        getChildren().add(this.imageView);
    }

    public void setImageView(Image image) {
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(GameConstants.BRICK_WIDTH);
        this.imageView.setFitHeight(GameConstants.BRICK_HEIGHT);
        getChildren().add(this.imageView);
    }

    public void update() {
        if (brique != null) {
            if (brique.isDestroyed()) {
                getChildren().remove(imageView);
                imageView = new ImageView();
                getChildren().add(imageView);
            } else if (brique.getDurability() < 70 && brique.getDurability() > 50) {
                getChildren().remove(imageView);
                Image im = ImageLoader.loadImage("src/main/ressources/briquec.png");
                this.setImageView(im);
            } else if (brique.getDurability() > 0 && brique.getDurability() < 50) {
                getChildren().remove(imageView);
                Image im = ImageLoader.loadImage("src/main/ressources/briquecc.png");
                this.setImageView(im);
            }
        }
    }
}
