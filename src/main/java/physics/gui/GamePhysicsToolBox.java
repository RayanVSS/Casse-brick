package physics.gui;

import gui.GraphicsToolkit.LabelToggleButtonHBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysicsToolBox extends VBox {

    private LabelToggleButtonHBox addBrickButton, addBallButton;

    public GamePhysicsToolBox() {

        super(15);
        setAlignment(Pos.CENTER);

        createButtons();
        setStyle();

        getChildren().addAll(addBrickButton, addBallButton);
    }

    private void createButtons() {
        addBrickButton = new LabelToggleButtonHBox("Ajouter une brique", false);
        addBrickButton.getToggleButton().setOnAction(e -> addBrickButton.action());

        addBallButton = new LabelToggleButtonHBox("Ajouter une balle", false);
        addBallButton.getToggleButton().setOnAction(e -> addBallButton.action());
    }

    private void setStyle() {
        setStyle("-fx-border-color: #7a6f6b; " +
                "-fx-border-width: 2px; ");
    }

    public LabelToggleButtonHBox getAddBrickButton() {
        return addBrickButton;
    }

    public LabelToggleButtonHBox getAddBallButton() {
        return addBallButton;
    }

}
