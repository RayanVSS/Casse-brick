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
    private Image physicBall=new Image("/balle/ballephysic.png");

    public BallGraphics(Ball ball) {
        this.ball = ball;
        updateImageAndProperties();
        setX(ball.getC().getX() - ball.getRadius());
        setY(ball.getC().getY() - ball.getRadius());
    }

    public BallGraphics(String id,Ball ball) {
        this.ball = ball;
        updateImageAndProperties();
        setX(ball.getC().getX());
        setY(ball.getC().getY());
        setScaleX(ball.getRadius());
        setScaleY(ball.getRadius());
        switch (id) {
            case "classicBall":
                setImage(classicBall);
                break;
            case "hyperBall":
                setImage(hyperBall);
                break;
            case "gravityBall":
                setImage(gravityBall);
                break;
            case "positifBall":
                setImage(positifBall);
                break;
            case "negatifBall":
                setImage(negatifBall);
                break;
            case "redBall":
                setImage(redBall);
                break;
            case "greenBall":
                setImage(greenBall);
                break;
            case "blueBall":
                setImage(blueBall);
                break;
            default:
                setImage(physicBall);
                break;
        }
        setFitWidth(5);
        setFitHeight(5);
        setPreserveRatio(true);
        setCache(true);
    }

    public void update() {
        setX(ball.getC().getX());
        setY(ball.getC().getY());
        //updateImageAndProperties();
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
        setFitWidth(ball.getRadius() * 3);
        setFitHeight(ball.getRadius() * 3);
        setPreserveRatio(true);
        setCache(true);
    }
}