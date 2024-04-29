package gui;

import config.Game;
import config.StageLevel;
import gui.GraphicsFactory.FPSGraphics;
import gui.GraphicsFactory.ScoreLifeGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.Key;

public class GameView {
    private Stage primaryStage;
    private Pane root = new Pane();
    private VBox SLFPS = new VBox();
    private BorderPane pane = new BorderPane();
    private GameRoot gameRoot;
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    private StageLevel stageLevel;
    // lire les touches
    Key key = new Key();
    // fps & lifeScore
    private FPSGraphics fpsGraphics = new FPSGraphics();
    private ScoreLifeGraphics scoreLifeView;
    // animation
    private AnimationTimer animationTimer;
    private ImageView separator;

    public GameView(Stage p, StageLevel stageLevel) {
        this.primaryStage = p;
        this.stageLevel = stageLevel;
        Game game = stageLevel.getGame();
        gameRoot = new GameRoot(stageLevel, this, scene, p);
        scoreLifeView = new ScoreLifeGraphics(game, stageLevel);
        root.getStyleClass().add("game-backgorund");
        if (GameConstants.FPS)
            SLFPS.getChildren().add(fpsGraphics);
        scoreLifeView.setPrefWidth(200.0);
        SLFPS.getChildren().add(scoreLifeView);
        SLFPS.setPrefWidth(200.0);
        this.pane.setRight((gameRoot.getRoot()));
        gameRoot.getRoot().setPrefWidth(GameConstants.DEFAULT_GAME_ROOT_WIDTH);
        this.pane.setCenter(setSeparator());
        this.pane.setLeft(SLFPS);
        this.root.getChildren().add(pane);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        scene.getStylesheets().add(GameConstants.CSS.getPath());
        this.animation();
        // Affichage de la fenêtre
        primaryStage.setScene(scene);
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
                    if (GameConstants.FPS)
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

    private ImageView setSeparator() {
        Image image = null;
        switch (GameConstants.CSS) {
            case PINK:
                image = ImageLoader.loadImage("src/main/ressources/lifeScore/pinkSep.png");
                break;
            case BLACK:
                image = ImageLoader.loadImage("src/main/ressources/lifeScore/whiteSep.png");
                break;
            case LIGHT:
            case CLASSIC:
            case ACHROMATOPSIE:
            case DEUTERANOPIE:
            case TRITANOPIE:
            case PROTANOPIE:
                image = ImageLoader.loadImage("src/main/ressources/lifeScore/blackSep.png");
                break;
        }
        separator = new ImageView(image);
        double windowHeight = scene.getHeight();
        separator.setFitHeight(windowHeight);
        separator.setFitWidth(20);
        separator.setSmooth(true);
        separator.setPreserveRatio(false);
        return separator;
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
