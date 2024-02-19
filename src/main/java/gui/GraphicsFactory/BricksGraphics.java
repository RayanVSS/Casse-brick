package gui.GraphicsFactory;

import entity.brick.Brick;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.*;

public class BricksGraphics extends StackPane {
    public ImageView imageView;
    public Brick brick;

    public BricksGraphics(Image image, Brick brick) {
        this.brick = brick;
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
        if (brick != null) {
            if (brick.isDestroyed()) {
                getChildren().remove(imageView);
                imageView = new ImageView();
                getChildren().add(imageView);
            } else if (brick.getDurability() < 70 && brick.getDurability() > 50) {
                getChildren().remove(imageView);
                Image im = ImageLoader.loadImage("src/main/ressources/briquec.png");
                this.setImageView(im);
            } else if (brick.getDurability() > 0 && brick.getDurability() < 50) {
                getChildren().remove(imageView);
                Image im = ImageLoader.loadImage("src/main/ressources/briquec.png");
                this.setImageView(im);
            }
        }
    }
}
