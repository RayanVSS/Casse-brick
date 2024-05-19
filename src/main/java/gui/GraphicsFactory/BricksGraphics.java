package gui.GraphicsFactory;

import java.util.HashMap;
import java.util.Map;
import entity.EntityColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import physics.entity.Brick;
import physics.entity.Entity;
import utils.GameConstants;
import utils.ImageLoader;


public class BricksGraphics extends ImageView implements EntityGraphics {
    public Image currentImage;
    public Brick brick;
    public boolean isUnbreakable, isTransparent;
    private boolean waitingAdded, waitingRemoved;

    //Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX, mouseY = 0;

    private static final Map<EntityColor, String> colorToImageMap;

    static {
        colorToImageMap = new HashMap<>();
        colorToImageMap.put(EntityColor.RED, "src/main/ressources/brique.png");
        colorToImageMap.put(EntityColor.GREEN, "src/main/ressources/briquev.png");
        colorToImageMap.put(EntityColor.BLUE, "src/main/ressources/briqueb.png");
    }

    public BricksGraphics(Brick brick) {
        if (brick.isUnbreakable()) {
            this.currentImage = ImageLoader.loadImage("src/main/ressources/briqueii.png");
        } else {
            this.currentImage = ImageLoader.loadImage("src/main/ressources/briquee.png");
        }
        this.brick = brick;
        this.setImageSize(this.currentImage);
        this.setX(brick.getC().getX());
        this.setY(brick.getC().getY());
        this.isUnbreakable = brick.isUnbreakable();
        this.isTransparent = brick.isTransparent();
        this.waitingAdded = true;
        this.waitingRemoved = false;
    }

    public BricksGraphics(Brick brick, EntityColor c) {
        if (brick.isUnbreakable()) {
            this.currentImage = ImageLoader.loadImage("src/main/ressources/briqueii.png");
        } else {
            this.currentImage = ImageLoader.loadImage(colorToImageMap.get(c));
        }
        this.brick = brick;
        this.setImageSize(this.currentImage);
        this.setX(brick.getC().getX());
        this.setY(brick.getC().getY());
        this.isUnbreakable = brick.isUnbreakable();
        this.isTransparent = brick.isTransparent();
        this.waitingAdded = true;
        this.waitingRemoved = false;
    }

    public void setImageSize(Image image) {
        setImage(image);
        setFitWidth(GameConstants.BRICK_WIDTH);
        setFitHeight(GameConstants.BRICK_HEIGHT);
    }

    public void update() {
        setWaitingRemoved(brick.isDestroyed());
        if (brick != null) {
            if (brick.isDestroyed()) {
                this.waitingRemoved = true;
                if (this.getImage() != null) {
                    this.setImage(null);
                }
            } else {
                if (brick.getC().getX() != this.getX() || brick.getC().getY() != this.getY()) {
                    this.setX(brick.getC().getX());
                    this.setY(brick.getC().getY());
                }
                if (brick.isUnbreakable() && !this.isUnbreakable) {
                    this.currentImage = ImageLoader.loadImage("src/main/ressources/briqueii.png");
                    this.setImageSize(this.currentImage);
                    this.isUnbreakable = true;
                }
                if (!brick.isUnbreakable() && this.isUnbreakable) {
                    this.isUnbreakable = false;

                    if (brick.getColor() != null) {
                        this.currentImage = ImageLoader.loadImage(colorToImageMap.get(brick.getColor()));
                    } else {
                        this.currentImage = ImageLoader.loadImage("src/main/ressources/briquee.png");
                    }
                    this.setImage(this.currentImage);
                }
                if (brick.isTransparent() && !isTransparent) {
                    setOpacity(0.5);
                    this.isTransparent = true;
                } else if (!brick.isTransparent() && isTransparent) {
                    isTransparent = false;
                    setOpacity(1);
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

    public boolean IsMouseDraggingBall() {
        return isMouseDraggingBall;
    }

    public void setMouseDraggingBall(boolean mouseDraggingBall) {
        isMouseDraggingBall = mouseDraggingBall;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseXY(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

}