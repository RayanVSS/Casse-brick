package physics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Environnement de test des briques physiques.
 */
public class BricksEnvTest extends Application {

    Stage primaryStage;
    Scene scene;
    StackPane root;
    BorderPane mainPane;

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        root = new StackPane();
        mainPane = new BorderPane();

        // Création de la boîte à outils
        VBox toolbox = createToolbox();
        mainPane.setLeft(toolbox);

        // Création de la zone de jeu
        StackPane gameArea = new StackPane();
        gameArea.setStyle("-fx-background-color: #273654;");
        mainPane.setCenter(gameArea);

        scene = new Scene(mainPane, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        primaryStage.setTitle("Bricks Env");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
    }

    private VBox createToolbox() {
        VBox toolbox = new VBox();
        toolbox.setAlignment(Pos.CENTER);
        toolbox.setSpacing(10);
        toolbox.setPadding(new Insets(10));

        Button addBrickButton = new Button("Ajouter une brique");
        addBrickButton.setOnAction(e -> addBrick());

        Button addBallButton = new Button("Ajouter une balle");
        addBallButton.setOnAction(e -> addBall());

        toolbox.getChildren().addAll(addBrickButton, addBallButton);

        return toolbox;
    }

    private void addBrick() {
        // Ajouter la logique pour ajouter une brique à la zone de jeu
        // Vous pouvez utiliser des formes JavaFX pour représenter la brique
    }

    private void addBall() {
        // Ajouter la logique pour ajouter une balle à la zone de jeu
        // Vous pouvez utiliser des cercles JavaFX pour représenter la balle
    }

    public static void main(String[] args) {
        launch(args);
    }
}
