package gui.GraphicsFactory;

import javafx.scene.layout.VBox;
import utils.GameConstants;

public class SLFPSGraphics extends VBox {
    private ScoreLifeGraphics scoreLifeView;
    private FPSGraphics fpsGraphics;

    public SLFPSGraphics(ScoreLifeGraphics scoreLifeView, FPSGraphics fpsGraphics) {
        this.scoreLifeView = scoreLifeView;
        this.fpsGraphics = fpsGraphics;
        getChildren().add(scoreLifeView);
        if (GameConstants.FPS) {
            getChildren().add(fpsGraphics);
        }
        //setWidth(100.0);
    }

    public void update() {
        scoreLifeView.update();
        if (GameConstants.FPS) {
            fpsGraphics.update();
        }
    }

}
