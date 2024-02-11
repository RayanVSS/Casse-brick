package gui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import config.*;
import entite.Brique;
import geometry.Coordonnee;

public class GameView extends App {

    Stage primaryStage;
    Scene scene;
    Pane root;
    Game game;

    public GameView(Stage p, int level) {

        // TODO implement here
        this.primaryStage = p;
        game = new Game(1);

        // TODO A ENLEVER
        Brique[][] tab = new Brique[12][13];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                tab[i][j] = new Brique(new Coordonnee(i, j), j);
            }
        }

        root = new StackPane();
        Ensemble ensemble = new Ensemble(tab);
        root.getChildren().add(ensemble);
        StackPane.setAlignment(ensemble, Pos.TOP_CENTER);
        ensemble.update();
        // TODO A ENLEVER
        int screenWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        int screenHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        scene = new Scene(root, screenWidth, screenHeight);

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
                // System.out.println("deltaT = " + (now - last) / 1000000000.0 + "s");
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
