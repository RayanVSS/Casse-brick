package gui.GraphicsFactory;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.FPS;
import utils.GameConstants;

public class FPSGraphics extends Pane {
    private Text fpsText = new Text();
    private Text maxfpsText = new Text();
    private FPS fpsCalculator = new FPS();

    public FPSGraphics() {
        fpsText.setX(10);
        fpsText.setY(20);
        maxfpsText.setX(10);
        maxfpsText.setY(40);
        fpsText.getStyleClass().add("scoreL-style");
        maxfpsText.getStyleClass().add("scoreL-style");
        getChildren().add(fpsText);
        getChildren().add(maxfpsText);
        // setLayoutX(150);
        // setLayoutY(10);
        getStylesheets().add(GameConstants.CSS);
    }

    public void update() {
        // Calcul des FPS
        double fps = fpsCalculator.calculateFPS();
        double maxfps = fpsCalculator.getMaxFps();

        // // Mise à jour du texte FPS
        fpsText.setText("FPS: " + String.format("%.2f", fps));
        maxfpsText.setText("Max FPS: " + String.format("%.2f", maxfps));
    }
}