package physics.gui;

import java.util.Iterator;
import java.util.Map.Entry;

import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import gui.GraphicsFactory.EntityGraphics;
import javafx.scene.layout.Pane;
import physics.config.GamePhysics;
import physics.entity.Entity;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysicsView {

    private Pane entitiesArea;
    private GamePhysics game;

    public GamePhysicsView(Pane gameArea, GamePhysics game) {
        entitiesArea = new Pane();
        gameArea.getChildren().addAll(entitiesArea);
        this.game = game;
    }

    public void update() {
        updateEntitiesGraphics();
    }

    private void updateEntitiesGraphics() {
        Iterator<Entry<Entity, EntityGraphics>> iterator = game.getEntities().entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Entity, EntityGraphics> entry = iterator.next();
            EntityGraphics eg = entry.getValue();
            if (eg.isWaitingAdded()) {
                if (eg instanceof BricksGraphics) {
                    entitiesArea.getChildren().add((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    entitiesArea.getChildren().add((BallGraphics) eg);
                }
                eg.setWaitingAdded(false);
            }
            if (eg.isWaitingRemoved()) {
                if (eg instanceof BricksGraphics) {
                    entitiesArea.getChildren().remove((BricksGraphics) eg);
                } else if (eg instanceof BallGraphics) {
                    entitiesArea.getChildren().remove((BallGraphics) eg);
                }
                iterator.remove(); // à vérifier fonctionnement
            }
            eg.update();
        }
    }

    public void reset() {
        entitiesArea.getChildren().clear();
    }
}
