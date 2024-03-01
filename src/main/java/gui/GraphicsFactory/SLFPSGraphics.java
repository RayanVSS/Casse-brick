package gui.GraphicsFactory;

import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
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
        // setLayoutX(GameConstants.DEFAULT_WINDOW_WIDTH );
        // setLayoutY(0);
        StackPane.setAlignment(this,Pos.TOP_RIGHT);
    }

    public void update() {
        scoreLifeView.update();
        if (GameConstants.FPS) {
            fpsGraphics.update();
        }
    }

}
