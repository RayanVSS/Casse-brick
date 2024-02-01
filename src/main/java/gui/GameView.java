package gui;

import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import config.*;
import entite.Balle;

public class GameView extends App {

    private Stage primaryStage;
    private int i;
    private Scene scene;
    private Pane root;
    private Game game;
    private Circle ball;

    public GameView(Stage p, int level) {
        // TODO implement here
        this.primaryStage = p;
        game = new Game(1);
        root = new Pane();

        Balle balle = game.getBalle();
        ball = new Circle(balle.getC().getX(), balle.getC().getY(), balle.getDiametre() * 2);
        // ball =new
        // Circle(game.getBalle().getC().getX(),game.getBalle().getC().getY(),game.getBalle().getDiametre());
        ball.setFill(Color.RED);
        root.getChildren().add(ball);

        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");
                var deltaT = now - last;

                game.update(deltaT,root.getWidth(),root.getHeight());
                System.out.println("largeur : "+root.getWidth()+" hauteur : "+root.getHeight());
                update();

                last = now;
            }
        };
        animationTimer.start();

    }

    public void update() {
        // TODO implement here
        Balle balle = game.getBalle();
        ball.setCenterX(balle.getC().getX());
        ball.setCenterY(balle.getC().getY());

        // System.out.println("Position dans update : (" + game.getBalle().getC().getX() + ", "
        //         + game.getBalle().getC().getY() + ")");
        // System.out.println("parent Balle:" + ball.getParent());
        // System.out.println("Position balle dans update : (" + ball.getCenterX() + ", " + ball.getCenterY() + ")");
    }

}
