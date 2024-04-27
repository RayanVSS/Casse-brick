package gui.GraphicsFactory;

import javafx.scene.control.Label;
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
    private Label fpsText = new Label(); // Texte pour afficher les FPS
    private Label maxfpsText = new Label(); // Texte pour afficher les FPS maximum
    private FPS fpsCalculator = new FPS(); // Calculateur de FPS

    /**
     * Constructeur de la classe FPSGraphics.
     * Initialise les éléments graphiques et les ajoute à la vue.
     */
    public FPSGraphics() {
        fpsText.setLayoutX(10);
        fpsText.setLayoutY(20);
        maxfpsText.setLayoutX(10);
        maxfpsText.setLayoutY(40);
        fpsText.getStyleClass().add("scoreL-style");
        maxfpsText.getStyleClass().add("scoreL-style");
        fpsText.setText("FPS: 0.00");
        maxfpsText.setText("Max FPS: 0.00");
        getChildren().add(fpsText);
        getChildren().add(maxfpsText);
        getStylesheets().add(GameConstants.CSS.getPath());
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