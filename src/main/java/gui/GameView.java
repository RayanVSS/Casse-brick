package gui;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import config.*;
import gui.GraphicsFactory.*;
import utils.*;


public class GameView {
    private Stage primaryStage;
    private Pane root=new Pane();
    private VBox SLFPS=new VBox();
    private BorderPane pane=new BorderPane();
    private GameRoot gameRoot;
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private StageLevel stageLevel;
    // lire les touches
    Key key = new Key();
    // fps & lifeScore
    private FPSGraphics fpsGraphics=new FPSGraphics();
    private ScoreLifeGraphics scoreLifeView;
    // animation
    private AnimationTimer animationTimer;

    public GameView(Stage p, StageLevel stageLevel) {
        this.primaryStage = p;
        this.stageLevel = stageLevel;
        Game game = stageLevel.getGame();
        gameRoot = new GameRoot(stageLevel, this, scene, p);
        scoreLifeView = new ScoreLifeGraphics(game);
        root.getStyleClass().add("game-backgorund");
        if(GameConstants.FPS)
            SLFPS.getChildren().add(fpsGraphics);
        SLFPS.getChildren().add(scoreLifeView);
        SLFPS.setPrefWidth(150.0);
        this.pane.setCenter(gameRoot.getRoot());
        this.pane.setLeft(SLFPS);
        this.root.getChildren().add(pane);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        scene.getStylesheets().add(GameConstants.CSS.getPath());
        this.animation();
        // Affichage de la fenêtre
        primaryStage.setScene(scene);
        //game.start();
    }

    public void animation() {
        animationTimer = new AnimationTimer() {
            long last = 0;
            double delay = 0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                var deltaT = now - last;
                // laisser un delai de 2 seconde avant de deplacer la balle
                if (delay < 2.0) {
                    delay += deltaT / 1000000000.0;
                } else if (now - last > 1000000000 / 120) {
                    if(GameConstants.FPS)
                        fpsGraphics.update();
                    gameRoot.update(deltaT);
                    scoreLifeView.update();
                    scene.getStylesheets().add(GameConstants.CSS.getPath());
                }
                last = now;
            }
        };
        animationTimer.start();
    }
    public void animationStart() {
        System.out.println("GameView.animationStart()");
        animationTimer.start();
    }

    public void animationStop() {
        animationTimer.stop();
    }

    public Pane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public Scene getScene() {
        return scene;
    }

    public StageLevel getStageLevel() {
        return stageLevel;
    }

    public ScoreLifeGraphics getScoreLifeView() {
        return scoreLifeView;
    }

}
