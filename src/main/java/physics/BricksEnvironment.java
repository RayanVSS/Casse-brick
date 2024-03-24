package physics;

import entity.ball.ClassicBall;
import entity.brick.BrickClassic;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import physics.config.GamePhysics;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import physics.gui.GamePhysicsToolBox;
import physics.gui.GamePhysicsView;
import utils.GameConstants;

/**
 * Environnement de test des briques physiques.
 * @usage ./gradlew bricks ou ./gradlew bricksEnv
 */

public class BricksEnvironment extends Application {

    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private BorderPane mainPane;
    private StackPane gameArea; //tmp
    private AnimationTimer animTimer;

    // Composant de l'environnement de test des briques physiques.
    private GamePhysics game;
    private GamePhysicsView gameView;
    private GamePhysicsToolBox toolBox;

    @Override
    public void start(Stage p) throws Exception {

        primaryStage = p;
        root = new StackPane();
        mainPane = new BorderPane();

        // Création de la zone de jeu
        gameArea = new StackPane();
        mainPane.setCenter(gameArea);

        // Création du jeu
        game = new GamePhysics();
        gameView = new GamePhysicsView(primaryStage, game);

        // Création de la boîte à outils
        toolBox = new GamePhysicsToolBox();
        mainPane.setLeft(toolBox);

        scene = new Scene(mainPane, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource(GameConstants.CSS.getPath()).toExternalForm());

        addControls();

        primaryStage.setTitle("Environnement de développement des briques physiques");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);

        animate();
    }

    private void animate() {
        animTimer = new AnimationTimer() {
            private long lastUpdateTime = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= 60) {
                    // Mise à jour de l'affichage
                    // label.setText("FPS: " + (1_000_000_000.0 / (now - lastUpdateTime)));

                    // Réinitialiser le temps de la dernière mise à jour
                    lastUpdateTime = now;
                    game.update();
                    gameView.update();
                }
            }
        };
        animTimer.start();
    }

    private void addControls() {
        mainPane.setOnMouseClicked(event -> {
            // Vérifier si la souris est dans la zone de jeu
            double mouseX = event.getX();
            double mouseY = event.getY();
            Bounds gameAreaBounds = mainPane.getCenter().getBoundsInParent();

            if (gameAreaBounds.contains(mouseX, mouseY)) {
                if (toolBox.getAddBrickButton().getToggleButton().isSelected()) {
                    game.addBrick(new BrickClassic(new Coordinates(mouseX, mouseY), new Vector(new Coordinates(0, 0))));
                    toolBox.getAddBrickButton().getToggleButton().setSelected(false);
                    toolBox.getAddBrickButton().action();
                } else if (toolBox.getAddBallButton().getToggleButton().isSelected()) {
                    game.addBall(new ClassicBall(new Coordinates(mouseX, mouseY), new Vector(new Coordinates(0, 0))));
                    toolBox.getAddBallButton().getToggleButton().setSelected(false);
                    toolBox.getAddBallButton().action();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
