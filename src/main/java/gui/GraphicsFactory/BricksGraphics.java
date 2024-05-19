package gui.GraphicsFactory;

import java.util.HashMap;
import java.util.Map;
import entity.EntityColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// import javafx.scene.layout.StackPane;
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

    public static final Map<EntityColor, String> colorToImageMap;

    static {
        colorToImageMap = new HashMap<>();
        colorToImageMap.put(EntityColor.RED, "src/main/ressources/brique/briqueRouge.png");
        colorToImageMap.put(EntityColor.GREEN, "src/main/ressources/brique/briqueVert.png");
        colorToImageMap.put(EntityColor.BLUE, "src/main/ressources/brique/briqueBleu.png");
    }

    public BricksGraphics(Brick brick) {
        this.brick = brick;
        this.waitingAdded = true;
        this.waitingRemoved = false;
        setFitWidth(GameConstants.BRICK_WIDTH);
        setFitHeight(GameConstants.BRICK_HEIGHT);
        update();
    }

    public BricksGraphics(Brick brick, EntityColor c) {
        this(brick);
        update();
    }

    public void setImageSize(Image image) {
        setImage(image);
        setFitWidth(GameConstants.BRICK_WIDTH);
        setFitHeight(GameConstants.BRICK_HEIGHT);
    }

    public void update() {
        setWaitingRemoved(brick.isDestroyed());
        setOpacity(brick.isTransparent() ? 0.5 : 1.0);
        if (brick.getColor() != null) {
            setImage(ImageLoader.loadImage(colorToImageMap.get(brick.getColor()), GameConstants.BRICK_WIDTH,
                    GameConstants.BRICK_HEIGHT));
        } else {
            setImage(ImageLoader.loadImage("src/main/ressources/brique/briqueOrange.png", GameConstants.BRICK_WIDTH,
                    GameConstants.BRICK_HEIGHT));
        }
        if (brick.isUnbreakable()) {
            setImage(ImageLoader.loadImage("src/main/ressources/brique/briqueGris.png", GameConstants.BRICK_WIDTH,
                    GameConstants.BRICK_HEIGHT));
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
}
