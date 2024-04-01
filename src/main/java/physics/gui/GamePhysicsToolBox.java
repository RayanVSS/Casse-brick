package physics.gui;

import java.lang.reflect.Array;
import java.util.ArrayList;

import config.Game;
import entity.ball.ClassicBall;
import entity.brick.BrickClassic;
import gui.GraphicsToolkit.LabelToggleButtonHBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import physics.config.GamePhysics;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class GamePhysicsToolBox extends VBox {

    private GamePhysics game;
    private GamePhysicsView view;

    private LabelToggleButtonHBox addBrickButton, addBallButton;
    private HBox presetsPlusReset;
    private Button clearButton;
    private GridPane presetsButtons;
    private LabelToggleButtonHBox pauseButton;

    public GamePhysicsToolBox(GamePhysics game, GamePhysicsView view) {

        super(15);
        this.game = game;
        this.view = view;

        setAlignment(Pos.CENTER);

        createUtilsButtons();
        createPresetButtons();
        setStyle();

        getChildren().addAll(addBrickButton, addBallButton, presetsPlusReset, pauseButton);
    }

    private void createUtilsButtons() {
        addBrickButton = new LabelToggleButtonHBox("Ajouter une brique", false);
        addBrickButton.getToggleButton().setOnAction(e -> addBrickButton.action());

        addBallButton = new LabelToggleButtonHBox("Ajouter une balle", false);
        addBallButton.getToggleButton().setOnAction(e -> addBallButton.action());

        pauseButton = new LabelToggleButtonHBox("Partie en pause : ", true);
        pauseButton.getToggleButton().setOnAction(e -> pauseButtonAction());

        clearButton = new Button("Clear");
        clearButton.setOnAction(e -> resetGame());
    }

    private void setStyle() {
        setStyle("-fx-border-color: #7a6f6b; " +
                "-fx-border-width: 1px; ");
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
        presetsPlusReset.getChildren().addAll(presetsButtons, clearButton);
    }

    private void setupPreset(int presetIndex) {
        resetGame();
        switch (presetIndex) {
            case 0:
                // game.addBrick(new BrickClassic(new Coordinates(150, 520)));
                game.addBall(new ClassicBall(new Coordinates(150, 520), new Vector(new Coordinates(0, 1))));
                game.addBall(new ClassicBall(new Coordinates(50, 720), new Vector(new Coordinates(1, 0))));
                break;

            default:

                break;
        }
    }

    private void resetGame() {
        game.reset();
        view.reset();
    }

    private void pauseButtonAction() {
        pauseButton.action();
        game.setPaused(pauseButton.getToggleButton().isSelected());
    }

    public LabelToggleButtonHBox getAddBrickButton() {
        return addBrickButton;
    }

    public LabelToggleButtonHBox getAddBallButton() {
        return addBallButton;
    }

}
