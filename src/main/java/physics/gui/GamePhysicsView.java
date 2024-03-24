package physics.gui;

import java.util.Map.Entry;

import config.Game;
import gui.GameView;
import gui.GraphicsFactory.EntityGraphics;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import physics.config.GamePhysics;
import physics.entity.Entity;
import utils.GameConstants;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysicsView {

    private Stage primaryStage;
    private Pane root;
    private Scene scene;

    private GamePhysics game;

    public GamePhysicsView(Stage p, GamePhysics game) {
        this.primaryStage = p;
        this.root = new Pane();
        this.scene = new Scene(root);

        this.game = game;
    }

    public void update() {
        updateEntitiesGraphics();
    }

    private void updateEntitiesGraphics() {
        for (Entry<Entity, EntityGraphics> entry : game.getEntities().entrySet()) {
            entry.getValue().update();
        }
    }
}
