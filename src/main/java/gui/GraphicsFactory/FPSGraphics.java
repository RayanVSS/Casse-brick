package gui.GraphicsFactory;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.FPS;

public class FPSGraphics extends Pane {
    private Text fpsText = new Text();
    private Text maxfpsText = new Text();
    private FPS fpsCalculator = new FPS();

    public FPSGraphics() {
        fpsText.setX(10);
        fpsText.setY(20);
        maxfpsText.setX(10);
        maxfpsText.setY(40);
        fpsText.setStyle("-fx-font-size: 20; -fx-fill: #d5bbb1;");
        maxfpsText.setStyle("-fx-font-size: 20; -fx-fill: #d5bbb1;");
        getChildren().add(fpsText);
        getChildren().add(maxfpsText);
        setLayoutX(100);
        setLayoutY(10);
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