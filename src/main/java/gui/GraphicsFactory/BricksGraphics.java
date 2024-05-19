package gui.GraphicsFactory;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import physics.entity.Brick;
import physics.entity.Entity;
import physics.entity.Entity.EntityColor;
import utils.GameConstants;
import utils.ImageLoader;


/**
 * Classe BricksGraphics qui étend ImageView et implémente EntityGraphics.
 * Cette classe est utilisée pour représenter graphiquement une brique dans le jeu.
 */

public class BricksGraphics extends ImageView implements EntityGraphics {
    public Image currentImage;
    public Brick brick;
    public boolean isUnbreakable, isTransparent;
    private boolean waitingAdded, waitingRemoved;

    //Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX, mouseY = 0;

    public static final Map<EntityColor, String> colorToImageMap;

    //permet d'associer à chaque couleur une image de brique
    static {
        colorToImageMap = new HashMap<>();
        colorToImageMap.put(EntityColor.RED, "src/main/ressources/brique/briqueRougeContours.png");
        colorToImageMap.put(EntityColor.GREEN, "src/main/ressources/brique/briqueVertContours.png");
        colorToImageMap.put(EntityColor.BLUE, "src/main/ressources/brique/briqueBleuContours.png");
    }

    public BricksGraphics(Brick brick) {
        this.brick = brick;
        this.waitingAdded = true;
        this.waitingRemoved = false;
        setFitWidth(GameConstants.BRICK_WIDTH);
        setFitHeight(GameConstants.BRICK_HEIGHT);
        this.setX(brick.getC().getX());
        this.setY(brick.getC().getY());
        update();
    }

    public BricksGraphics(Brick brick, EntityColor c) {
        this(brick);
    }

    public void setImageSize(Image image) {
        setImage(image);
        setFitWidth(GameConstants.BRICK_WIDTH);
        setFitHeight(GameConstants.BRICK_HEIGHT);
    }

    public void update() {
        setWaitingRemoved(brick.isDestroyed());
        setOpacity(brick.isTransparent() ? 0.4 : 1.0);
        if (brick.getColor() != null) {
            setImage(ImageLoader.loadImage(colorToImageMap.get(brick.getColor())));
        } else {
            setImage(ImageLoader.loadImage("src/main/ressources/brique/briqueOrangeContours.png"));
        }
        if (brick.isUnbreakable()) {
            setImage(ImageLoader.loadImage("src/main/ressources/brique/briqueGrisContours.png"));
        }
        this.setX(brick.getC().getX());
        this.setY(brick.getC().getY());
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

    public Brick getBrick() {
        return brick;
    }

}
