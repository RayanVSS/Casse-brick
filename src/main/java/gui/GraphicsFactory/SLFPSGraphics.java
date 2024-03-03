package gui.GraphicsFactory;

import javafx.scene.layout.VBox;
import utils.GameConstants;

/**
 * Classe SLFPSGraphics qui étend VBox.
 * Cette classe est utilisée pour afficher les éléments graphiques de score, de vie et de FPS (Frames Per Second).
 * 
 * @author Benmalek Majda
 */
public class SLFPSGraphics extends VBox {
    private ScoreLifeGraphics scoreLifeView; // Vue graphique pour le score et la vie
    private FPSGraphics fpsGraphics; // Vue graphique pour le FPS

    /**
     * Constructeur de la classe SLFPSGraphics.
     * 
     * @param scoreLifeView Vue graphique pour le score et la vie
     * @param fpsGraphics Vue graphique pour les FPS
     */
    public SLFPSGraphics(ScoreLifeGraphics scoreLifeView, FPSGraphics fpsGraphics) {
        this.scoreLifeView = scoreLifeView;
        this.fpsGraphics = fpsGraphics;
        this.getChildren().add(scoreLifeView);
        if (GameConstants.FPS) {
            this.getChildren().add(fpsGraphics);
        }
        setPrefWidth(150.0);
    }

    /**
     * Méthode pour mettre à jour les vues graphiques.
     */
    public void update() {
        scoreLifeView.update();
        if (GameConstants.FPS) {
            fpsGraphics.update();
        }
    }
}
