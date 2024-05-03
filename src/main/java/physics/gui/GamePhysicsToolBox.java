package physics.gui;

import java.util.Random;

import entity.ball.ClassicBall;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import physics.config.GamePhysics;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import utils.GraphicsToolkit.LabelToggleButtonHBox;

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

    public GamePhysicsToolBox(GamePhysics game, GamePhysicsView view, double zoneWidth, double zoneHeight) {

        super(15);
        this.game = game;
        this.view = view;

        setAlignment(Pos.CENTER);

        createUtilsButtons();
        createPresetButtons(zoneWidth, zoneHeight);
        setStyle();

        getChildren().addAll(addBrickButton, addBallButton, presetsPlusReset, pauseButton);
    }

    private void createUtilsButtons() {
        addBrickButton = new LabelToggleButtonHBox("Ajouter une brique", false);
        addBrickButton.getToggleButton().setOnAction(e -> addBrickButton.updateText());

        addBallButton = new LabelToggleButtonHBox("Ajouter une balle", false);
        addBallButton.getToggleButton().setOnAction(e -> addBallButton.updateText());

        pauseButton = new LabelToggleButtonHBox("Partie en pause : ", true);
        pauseButton.getToggleButton().setOnAction(e -> pauseButtonAction());

        clearButton = new Button("Clear");
        clearButton.setOnAction(e -> resetGame());
    }

    private void setStyle() {
        setStyle("-fx-border-color: #7a6f6b; " +
                "-fx-border-width: 1px; ");
    }

    private void createPresetButtons(double zoneWidth, double zoneHeight) {
        presetsButtons = new GridPane();
        for (int i = 0; i < 9; i++) {
            Button temp = new Button("" + (i + 1));
            final int j = i;
            temp.setOnAction(event -> {
                setupPreset(j, zoneWidth, zoneHeight);
            });
            presetsButtons.add(temp, i % 3, i / 3);
        }
        presetsPlusReset = new HBox(30);
        presetsPlusReset.setAlignment(Pos.CENTER);
        presetsPlusReset.getChildren().addAll(presetsButtons, clearButton);
    }

    private void setupPreset(int presetIndex, double zoneWidth, double zoneHeight) { // à simplifier
        resetGame();
        Random random = new Random();
        Ball temp;
        switch (presetIndex) {
            case 0:
                break;

            case 1:
                temp = new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble())));
                // temp.setZoneWidth(700);
                // temp.setZoneHeight(700);
                System.out.println(zoneWidth + "+" + zoneHeight);
                game.addBall(temp);
                break;
            case 2:
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                break;
            case 3:
                game.addBall(new ClassicBall(new Coordinates(150, 520), new Vector(new Coordinates(0, 1))));
                game.addBall(new ClassicBall(new Coordinates(50, 720), new Vector(new Coordinates(1, 0))));
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
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(new ClassicBall(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                break;
            default:

                break;
        }
    }

    private void resetGame() {
        game.reset();
        view.reset();
        if (!pauseButton.getToggleButton().isSelected()) {
            pauseButton.getToggleButton().setSelected(true);
            pauseButton.updateText();
        }
    }

    private void pauseButtonAction() {
        pauseButton.updateText();
        game.setPaused(pauseButton.getToggleButton().isSelected());
    }

    public LabelToggleButtonHBox getAddBrickButton() {
        return addBrickButton;
    }

    public LabelToggleButtonHBox getAddBallButton() {
        return addBallButton;
    }

}
