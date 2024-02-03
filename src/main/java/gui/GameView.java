package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import config.*;
import entity.ball.Ball;
import geometry.Cordinates;

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
        this.root = new Pane();

        game = new Game(1, new Cordinates(800 / 3, 600 / 3));

        Ball ball = game.getBalle();
        this.ball = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getDiametre() * 2);

        root.getChildren().add(this.ball);
        this.scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            double delay=0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }

                var deltaT = now - last;
                
                System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");
                
                // laisser un delai de 5 seconde avant de deplacer la balle
                if (delay < 4.0) {
                    delay+=deltaT/1000000000.0;
                }else{
                    game.update(deltaT, root.getWidth(), root.getHeight());
                    update();
                }
                last = now;
            }
        };
        animationTimer.start();
    }

    public void update() {
        // TODO implement here
        Ball ball = game.getBalle();
        this.ball.setCenterX(ball.getC().getX());
        this.ball.setCenterY(ball.getC().getY());
    }

}
