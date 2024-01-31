package gui;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import config.*;


public class GameView extends App{
    
    Stage primaryStage;
    Scene scene;
    StackPane root ;
    Game game;

    public GameView(Stage p,int level) {
        // TODO implement here
        this.primaryStage = p;
        game = new Game(1);

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
                game.update(deltaT);
                last = now;
            }
        };
        animationTimer.start();


    }

    public void update() {
        // TODO implement here
    }
}
