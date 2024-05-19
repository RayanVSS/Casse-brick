package gui.GraphicsFactory;

import utils.GameConstants;
import utils.ImageLoader;
import entity.ball.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
// import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import physics.entity.Ball;
import physics.entity.Entity;

/**
 * Classe pour la représentation graphique d'une balle.
 * Étends la classe Circle de JavaFX.
 * Implémente l'interface EntityGraphics.
 * @author Benmalek Majda |
 */
public class BallGraphics extends Circle implements EntityGraphics {
    private Color color;
    private Color MAGNET_POS;
    private Color MAGNET_NEG;
    private Color GRAVITY;
    private Color HYPER;

    private Ball ball;

    private boolean waitingAdded = true, waitingRemoved;
    // Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX, mouseY = 0;
    private Image image;
    private ImagePattern ballImage;

    /**
     * Constructeur de la classe BallGraphics.
     * 
     * @param ball la balle à représenter graphiquement.
     */
    public BallGraphics(Ball ball) {
        super(ball.getRadius());
        this.ball = ball;
        setBall();
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
    }

    /**
     * Constructeur de la classe BallGraphics avec une image.
     * 
     * @param i    l'image de la balle.
     * @param ball la balle à représenter graphiquement.
     */
    public BallGraphics(Image i, Ball ball) {
        super(ball.getRadius());
        this.ball = ball;
        if (i != null) {
            ballImage = new ImagePattern(i);
            setFill(ballImage);
        } else {
            this.setFill(Color.BLACK);
        }
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
    }

    /**
     * Met à jour la représentation graphique de la balle.
     */
    public void update() {
        updateBall();
        setWaitingRemoved(ball.isDestroyed());
        setCenterX(ball.getC().getX());
        setCenterY(ball.getC().getY());
        setRotate(getRotate() + ball.getRotation().getAngle() / 2);
    }

    private void setBall() {
        if (GameConstants.SKIN_BALL.equals("Null")) {
            color();
            this.setFill(color);
            if (ball instanceof GravityBall) {
                this.setStrokeWidth(3.5);
                this.setStroke(GRAVITY);
            } else if (ball instanceof HyperBall) {
                this.setStrokeWidth(3.5);
                this.setStroke(HYPER);
            } 
        }else if ( (ball instanceof MagnetBall)){
            color();
            this.setFill(color);
        } else {
            image = ImageLoader.loadImage(GameConstants.SKIN_BALL);
            ballImage = new ImagePattern(image);
            setFill(ballImage);
        }
    }

    private void color() {
        switch (GameConstants.CSS) {
            case PINK:
                color = Color.rgb(199, 21, 133);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case CLASSIC:
                color = Color.rgb(39, 54, 84);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case LIGHT:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case BLACK:
                color = Color.rgb(245, 245, 245);
                MAGNET_POS = Color.rgb(255, 255, 0);
                MAGNET_NEG = Color.rgb(0, 128, 0);
                break;
            case ACHROMATOPSIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(130, 130, 130);
                MAGNET_NEG = Color.rgb(40, 40, 40);
                break;
            case DEUTERANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 246, 233);
                MAGNET_NEG = Color.rgb(137, 104, 29);
                break;
            case PROTANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 247, 216);
                MAGNET_NEG = Color.rgb(123, 110, 0);
                break;
            case TRITANOPIE:
                color = Color.rgb(101, 119, 134);
                MAGNET_POS = Color.rgb(255, 244, 249);
                MAGNET_NEG = Color.rgb(54, 118, 129);
                break;
            default:
                break;
        }
        GRAVITY = Color.rgb(255, 16, 240);
        HYPER = Color.rgb(0, 0, 255);
    }

    private void updateBall() {
        if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif")) {
                this.setFill(MAGNET_POS);
            } else {
                this.setFill(MAGNET_NEG);
            }
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    this.setFill(Color.RED);
                    break;
                case GREEN:
                    this.setFill(Color.GREEN);
                    break;
                case BLUE:
                    this.setFill(Color.BLUE);
                    break;
                default:
                    break;
            }
        }
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

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public void setMouseXY(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public Entity getEntity() {
        return ball;
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