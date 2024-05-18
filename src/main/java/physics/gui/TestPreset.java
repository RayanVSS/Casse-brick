package physics.gui;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import physics.config.PhysicEngine;
import physics.entity.Ball;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

/**
 * Composant de l'environnement de test des briques physiques.
 */

public class TestPreset extends VBox {

    private HBox presetsPlusReset;
    private GridPane presetsButtons;
    private PhysicEngine game;

    public TestPreset(double zoneWidth, double zoneHeight,PhysicEngine game) {

        super(15);
        this.game = game;

        setAlignment(Pos.CENTER);

        createPresetButtons(zoneWidth, zoneHeight);
        setStyle();

        getChildren().addAll(presetsPlusReset);
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
        presetsPlusReset.getChildren().addAll(presetsButtons);
    }

    private void setStyle() {
        setStyle("-fx-border-color: #7a6f6b; " +
                "-fx-border-width: 1px; ");
    }


    private void setupPreset(int presetIndex, double zoneWidth, double zoneHeight) { // à simplifier
        Random random = new Random();
        Ball temp;
        switch (presetIndex) {
            case 0:
                break;

            case 1:
                temp = PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble())));
                System.out.println(zoneWidth + "+" + zoneHeight);
                game.addBall(temp);
                break;
            case 2:
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                break;
            case 3:
                game.addBall(PhysicEngine.init_ball(new Coordinates(150, 520), new Vector(new Coordinates(0, 1))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(50, 720), new Vector(new Coordinates(1, 0))));
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
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                game.addBall(PhysicEngine.init_ball(new Coordinates(random.nextInt(200), random.nextInt(800)),
                        new Vector(new Coordinates(random.nextDouble(), random.nextDouble()))));
                break;
            default:

                break;
        }
    }
}
