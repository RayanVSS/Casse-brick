package gui.GraphicsFactory;

import entity.EntityColor;
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
    public boolean isUnbreakable;
    public boolean isTransparent;

    public BricksGraphics(Brick brick, int i, int j) {
        Image image;
        if (brick.isUnbreakable()) {
            image = ImageLoader.loadImage("src/main/ressources/briqueii.png");
        } else {
            image = ImageLoader.loadImage("src/main/ressources/briquee.png");
        }
        this.brick = brick;
        this.i = i;
        this.j = j;
        this.setImageView(image);
        this.isUnbreakable = brick.isUnbreakable();
        this.isTransparent = brick.isTransparent();
    }

    public BricksGraphics(Brick brick, int i, int j, EntityColor c) {
        Image image = null;
        if (brick.isUnbreakable()) {
            image = ImageLoader.loadImage("src/main/ressources/briqueii.png");
        } else {
            switch (c) {
                case RED:
                    image = ImageLoader.loadImage("src/main/ressources/brique.png");
                    break;
                case GREEN:
                    image = ImageLoader.loadImage("src/main/ressources/briquev.png");
                    break;
                case BLUE:
                    image = ImageLoader.loadImage("src/main/ressources/briqueb.png");
                    break;
                default:
                    break;
            }
        }
        this.brick = brick;
        this.i = i;
        this.j = j;
        this.setImageView(image);
        this.isUnbreakable = brick.isUnbreakable();
        this.isTransparent = brick.isTransparent();
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
            } else {
                if (brick.getC().getIntX() != i || brick.getC().getIntY() != j) {
                    i = brick.getC().getIntX();
                    j = brick.getC().getIntY();
                    setLayoutX(i);
                    setLayoutY(j);
                }
                if (brick.isUnbreakable() && !this.isUnbreakable) {
                    getChildren().remove(imageView);
                    Image im = ImageLoader.loadImage("src/main/ressources/briqueii.png");
                    this.setImageView(im);
                    this.isUnbreakable = true;
                }
                if (!brick.isUnbreakable() && this.isUnbreakable) {
                    this.isUnbreakable = false;
                    getChildren().remove(imageView);
                    Image image = null;
                    if (brick.getColor() != null) {
                        switch (brick.getColor()) {
                            case RED:
                                image = ImageLoader.loadImage("src/main/ressources/brique.png");
                                break;
                            case GREEN:
                                image = ImageLoader.loadImage("src/main/ressources/briquev.png");
                                break;
                            case BLUE:
                                image = ImageLoader.loadImage("src/main/ressources/briqueb.png");
                                break;
                            default:
                                break;
                        }
                    } else {
                        image = ImageLoader.loadImage("src/main/ressources/briquee.png");
                    }
                    this.setImageView(image);
                }
                if (brick.isTransparent() && !isTransparent) {
                    imageView.setOpacity(0.5);
                    this.isTransparent = true;
                } else if (!brick.isTransparent() && isTransparent) {
                    isTransparent = false;
                    imageView.setOpacity(1);
                }
            }
        }
    }
}
