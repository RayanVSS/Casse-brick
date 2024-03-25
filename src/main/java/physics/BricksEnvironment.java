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
import javafx.scene.layout.Pane;
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
    private BorderPane root;
    private StackPane uiPane;
    private StackPane gameArea;
    private AnimationTimer animTimer;

    // Composant de l'environnement de test des briques physiques.
    private GamePhysics game;
    private GamePhysicsView gameView;
    private GamePhysicsToolBox toolBox;

    @Override
    public void start(Stage p) throws Exception {

        primaryStage = p;
        root = new BorderPane();

        uiPane = new StackPane();
        gameArea = new StackPane();
        root.setLeft(uiPane);
        root.setCenter(gameArea);

        // Création du jeu
        game = new GamePhysics();
        gameView = new GamePhysicsView(gameArea, game);

        // Création de la boîte à outils
        toolBox = new GamePhysicsToolBox();
        uiPane.getChildren().addAll(toolBox);

        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
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
        gameArea.setOnMouseClicked(event -> {
            // Vérifier si la souris est dans la zone de jeu
            double mouseX = event.getX();
            double mouseY = event.getY();
            Bounds gameAreaBounds = gameArea.getBoundsInLocal(); // Obtenir les limites locales du StackPane

            if (gameAreaBounds.contains(mouseX, mouseY)) {
                if (toolBox.getAddBrickButton().getToggleButton().isSelected()) {
                    game.addBrick(new BrickClassic(new Coordinates(mouseX, mouseY), new Vector(new Coordinates(0, 0))));
                    toolBox.getAddBrickButton().getToggleButton().setSelected(false);
                    toolBox.getAddBrickButton().action();
                } else if (toolBox.getAddBallButton().getToggleButton().isSelected()) {
                    game.addBall(new ClassicBall(new Coordinates(mouseX, mouseY), new Vector(new Coordinates(1, 1))));
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
