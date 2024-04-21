package gui.GraphicsFactory;

import physics.entity.Ball;
import entity.ball.ClassicBall;
import entity.ball.GravityBall;
import entity.ball.HyperBall;
import entity.ball.MagnetBall;
import gui.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BallGraphics extends ImageView {

    private Ball ball;
    private Image classicBall = new Image("/balle/balle1.png");
    private Image hyperBall = new Image("/balle/balleHyper.png");
    private Image gravityBall = new Image("/balle/balleGravity.png");
    private Image positifBall = new Image("/balle/positif.png");
    private Image negatifBall = new Image("/balle/negatif.png");
    private Image redBall = new Image("/balle/balleRouge.png");
    private Image greenBall = new Image("/balle/balleVert.png");
    private Image blueBall = new Image("/balle/balleBleu.png");
    private Image physicBall = new Image("/balle/ballePhysic.png");

    //Partie pour le drag and drop
    private boolean isMouseDraggingBall = false;
    private double mouseX , mouseY = 0;

    public BallGraphics(Ball ball) {
        this.ball = ball;
        updateImageAndProperties();
        setX(ball.getC().getX());
        setY(ball.getC().getY());
        setScaleX(ball.getRadius());
        setScaleY(ball.getRadius());
    }

    public BallGraphics(Image i,Ball ball) {
        this.ball = ball;
        setImage(i);
        if(i==null){
            setImage(physicBall);
        }
        setFitWidth(ball.getRadius());
        setFitHeight(ball.getRadius());
        setPreserveRatio(true);
        setCache(true);
        setX(ball.getC().getX());
        setY(ball.getC().getY());
        setScaleX(ball.getRadius());
        setScaleY(ball.getRadius());
    }

    public void update() {
        setX(ball.getC().getX());
        setY(ball.getC().getY());
        setRotate(getRotate()+ball.getRotation().getAngle()/2);
    }

    private void updateImageAndProperties() {
        if (ball instanceof ClassicBall)
            setImage(classicBall);
        else if (ball instanceof HyperBall)
            setImage(hyperBall);
        else if (ball instanceof GravityBall)
            setImage(gravityBall);
        else if (ball instanceof MagnetBall) {
            if (((MagnetBall) ball).getEtat().equals("positif"))
                setImage(positifBall);
            else
                setImage(negatifBall);
        }
        if (ball.getColor() != null) {
            switch (ball.getColor()) {
                case RED:
                    setImage(redBall);
                    break;
                case GREEN:
                    setImage(greenBall);
                    break;
                case BLUE:
                    setImage(blueBall);
                    break;
            }
        }
        setFitWidth(3);
        setFitHeight(3);
        setPreserveRatio(true);
        setCache(true);
    }

    public boolean IsMouseDraggingBall() {
        return isMouseDraggingBall;
    }

    public void setMouseDraggingBall(boolean isMouseDraggingBall) {
        this.isMouseDraggingBall = isMouseDraggingBall;
    }

    public void setMouseXY(double x, double y) {
        mouseX = x;
        mouseY = y;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public Ball getBall() {
        return ball;
    }
}