package physics.gui;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class TestPreset extends VBox {

    private HBox presetsPlusReset;
    private GridPane presetsButtons;
    private PhysicEngine game;

    public TestPreset(PhysicEngine game) {

        super(15);
        this.game = game;

        setAlignment(Pos.CENTER);

        createPresetButtons();
        setStyle();

        getChildren().addAll(presetsPlusReset);
    }

    private void createPresetButtons() {
        presetsButtons = new GridPane();
        for (int i = 0; i < 9; i++) {
            Button temp = new Button("" + (i + 1));
            final int j = i;
            temp.setOnAction(event -> {
                setupPreset(j);
            });
            presetsButtons.add(temp, i % 3, i / 3);
        }
        presetsPlusReset = new HBox(30);
        presetsPlusReset.setAlignment(Pos.CENTER);
        presetsPlusReset.getChildren().addAll(presetsButtons);
    }

    private void setStyle() {
        setStyle("-fx-border-color: #7a6f6b; " +
                "-fx-border-width: 1px; ");
    }

    private void setupPreset(int presetIndex) { // à simplifier
        Random random = new Random();
        Ball tempBall;
        Brick tempBrick;
        double width = PhysicSetting.DEFAULT_WINDOW_WIDTH;
        double height = PhysicSetting.DEFAULT_WINDOW_HEIGHT;
        game.clear();
        switch (presetIndex) {
            case 0:
                tempBrick = PhysicEngine.init_brick(new Coordinates(width / 4, height / 4));
                game.addBrick(tempBrick);

                break;

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                break;
        }
    }
}
