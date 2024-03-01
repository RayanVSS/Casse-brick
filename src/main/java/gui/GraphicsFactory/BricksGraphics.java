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
    public int i;
    public int j;

    public BricksGraphics(Image image, Brick brick, int i, int j) {
        this.brick = brick;
        this.i = i;
        this.j = j;
        this.setImageView(image);
        // this.imageView = new ImageView(image);
        // this.imageView.setFitWidth(GameConstants.BRICK_WIDTH);
        // this.imageView.setFitHeight(GameConstants.BRICK_HEIGHT);
        // getChildren().add(this.imageView);
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
            } else if (brick.getC().getIntX() != i || brick.getC().getIntY() != j) {
                i = brick.getC().getIntX();
                j = brick.getC().getIntY();
                setLayoutX(i);
                setLayoutY(j);
            }

            // else if (brick.isUnbreakable()) {
            // // &&
            // !imageView.getImage().getUrl().equals("src/main/ressources/briqueii.png"))
            // // {
            // getChildren().remove(imageView);
            // Image im = ImageLoader.loadImage("src/main/ressources/briqueii.png");
            // this.setImageView(im);
            // }
            // if (brick.isTransparent()) {
            // imageView.setOpacity(0.5);
            // }
        }
    }
}
