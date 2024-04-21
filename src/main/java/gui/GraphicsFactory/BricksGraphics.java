package gui.GraphicsFactory;

import java.util.HashMap;
import java.util.Map;

import entity.EntityColor;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import physics.entity.Brick;
import physics.entity.Entity;
import utils.*;

public class BricksGraphics extends StackPane implements EntityGraphics {
    public ImageView imageView;
    public Brick brick;
    public int i;
    public int j;
    public boolean isUnbreakable;
    public boolean isTransparent;
    private boolean waitingAdded, waitingRemoved;

    private static final Map<EntityColor, String> colorToImageMap;

    static {
        colorToImageMap = new HashMap<>();
        colorToImageMap.put(EntityColor.RED, "src/main/ressources/brique.png");
        colorToImageMap.put(EntityColor.GREEN, "src/main/ressources/briquev.png");
        colorToImageMap.put(EntityColor.BLUE, "src/main/ressources/briqueb.png");
    }

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
            image = ImageLoader.loadImage(colorToImageMap.get(c));
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
                        image = ImageLoader.loadImage(colorToImageMap.get(brick.getColor()));
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

    public Entity getEntity() {
        return brick;
    }

    public boolean isWaitingAdded() {
        return waitingAdded;
    }

    public void setWaitingAdded(boolean waitingAdded) {
        this.waitingAdded = waitingAdded;
    }

    public boolean isWaitingRemoved() {
        return waitingRemoved;
    }

    public void setWaitingRemoved(boolean waitingRemoved) {
        this.waitingRemoved = waitingRemoved;
    }

}
