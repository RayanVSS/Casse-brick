package gui.GraphicsFactory;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.FPS;
import utils.GameConstants;

/**
 * Classe FPSGraphics qui étend Pane.
 * Cette classe est utilisée pour afficher les informations de FPS (Frames Per Second) et de FPS maximum.
 * 
 * @author Benmalek Majda
 * @author Belhassen rayan
 */
public class FPSGraphics extends Pane {
    private Text fpsText = new Text(); // Texte pour afficher les FPS
    private Text maxfpsText = new Text(); // Texte pour afficher les FPS maximum
    private FPS fpsCalculator = new FPS(); // Calculateur de FPS

    /**
     * Constructeur de la classe FPSGraphics.
     * Initialise les éléments graphiques et les ajoute à la vue.
     */
    public FPSGraphics() {
        fpsText.setX(10);
        fpsText.setY(20);
        maxfpsText.setX(10);
        maxfpsText.setY(40);
        fpsText.getStyleClass().add("scoreL-style");
        maxfpsText.getStyleClass().add("scoreL-style");
        getChildren().add(fpsText);
        getChildren().add(maxfpsText);
        getStylesheets().add(GameConstants.CSS);
    }

    /**
     * Méthode pour mettre à jour les informations de FPS et de FPS maximum.
     * Calcule les nouvelles valeurs de FPS et de FPS maximum, puis met à jour le texte affiché.
     */
    public void update() {
        // Calcul des FPS
        double fps = fpsCalculator.calculateFPS();
        double maxfps = fpsCalculator.getMaxFps();

        // Mise à jour du texte FPS
        fpsText.setText("FPS: " + String.format("%.2f", fps));
        maxfpsText.setText("Max FPS: " + String.format("%.2f", maxfps));
    }
}