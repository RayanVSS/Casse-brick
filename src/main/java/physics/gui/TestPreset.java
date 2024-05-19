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
import utils.GameConstants;

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
        double width = 1200;
        double height = 800;
        double ballRadius = GameConstants.DEFAULT_BALL_RADIUS;
        double brickHeight = GameConstants.BRICK_HEIGHT;
        double brickWidth = GameConstants.BRICK_WIDTH;
        game.clear();
        switch (presetIndex) {
            case 0:
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);

                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2 * (width / 4), (height / 4) - 100),
                        new Vector(new Coordinates(0, 1.5)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3.5 * (width / 4), (height / 4) + brickHeight / 2),
                        new Vector(new Coordinates(-1.5, 0)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(1.5 * (width / 4) - 100, 2 * (height / 4) + brickHeight / 2),
                        new Vector(new Coordinates(1.5, 0)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4), 2.5 * (height / 4)),
                        new Vector(new Coordinates(0, -1.5)));
                game.addBall(tempBall);
                break;

            case 1:
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);

                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2.5 * (width / 4) - 3.5 * brickWidth, (height / 4) - 100),
                        new Vector(new Coordinates(1, 0.9)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3.5 * (width / 4), (height / 4) + -3.5 * brickHeight),
                        new Vector(new Coordinates(-1, 1)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(1.5 * (width / 4) - 100, 2 * (height / 4) + 6 * brickHeight),
                        new Vector(new Coordinates(1, -0.6)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4) + 3 * brickWidth, 2.5 * (height / 4)),
                        new Vector(new Coordinates(-1, -0.5)));
                game.addBall(tempBall);
                break;
            case 2:
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2 * (width / 4), (height / 4) - 100),
                        new Vector(new Coordinates(0, 2.5)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3.5 * (width / 4), 3.5 * (height / 4)),
                        new Vector(new Coordinates(-2, 0)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2 * (width / 4) - 100, 3.5 * (height / 4)),
                        new Vector(new Coordinates(2, 0)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2 * (width / 4), 2.5 * (height / 4)),
                        new Vector(new Coordinates(0, -2.5)));
                game.addBall(tempBall);
                break;
            case 3:
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2.5 * (width / 4) - 3.5 * brickWidth, (height / 4) - 100),
                        new Vector(new Coordinates(1, 0.9)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3.5 * (width / 4), (height / 4) + -3.5 * brickHeight),
                        new Vector(new Coordinates(-1, 1)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(1.5 * (width / 4) - 100, 2 * (height / 4) + 6 * brickHeight),
                        new Vector(new Coordinates(1, -0.6)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4) + 3 * brickWidth, 2.5 * (height / 4)),
                        new Vector(new Coordinates(-1, -0.55)));
                game.addBall(tempBall);
                break;
            case 4:
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(2 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);
                tempBrick = PhysicEngine
                        .init_brick(new Coordinates(3 * (width / 4) - 2 * ballRadius, 2 * (height / 4)));
                game.addBrick(tempBrick);

                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2.5 * (width / 4) - 3.5 * brickWidth, (height / 4) - 100),
                        new Vector(new Coordinates(1, 0.9)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3.5 * (width / 4), (height / 4) + -3.5 * brickHeight),
                        new Vector(new Coordinates(-1, 1)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(1.5 * (width / 4) - 100, 2 * (height / 4) + 6 * brickHeight),
                        new Vector(new Coordinates(1, -0.6)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4) + 3 * brickWidth, 2 * (height / 4)),
                        new Vector(new Coordinates(-1, -0.55)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(2 * (width / 4) - 3 * brickWidth, (height / 4) - 100),
                        new Vector(new Coordinates(1, 0.9)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4), (height / 4) + -3 * brickHeight),
                        new Vector(new Coordinates(-1, 1)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(1 * (width / 4) - 100, 2 * (height / 4) + 6 * brickHeight),
                        new Vector(new Coordinates(1, -0.6)));
                game.addBall(tempBall);
                tempBall = PhysicEngine.init_ball(
                        new Coordinates(3 * (width / 4) + 3 * brickWidth, 2 * (height / 4) + 100),
                        new Vector(new Coordinates(-2, -0.4)));
                game.addBall(tempBall);

                break;
            case 5:
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(3 * (width / 4), 2 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                break;
            case 6:
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(3 * (width / 4), 2 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4), 2 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                break;
            case 7:
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(3 * (width / 4), 2 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4), 2 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4) + i * brickHeight, (height / 4) - 100),
                            new Vector(new Coordinates(-3 + random.nextDouble() * 6, -3 + random.nextDouble() * 6)));
                    game.addBall(tempBall);
                }
                break;
            case 8:
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(3 * (width / 4), 1.5 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-5 + random.nextDouble() * 10, -5 + random.nextDouble() * 10)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4), 1.5 * (height / 4) + i * brickHeight),
                            new Vector(new Coordinates(-5 + random.nextDouble() * 10, -5 + random.nextDouble() * 10)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4) + i * brickHeight, (height / 4) - 100),
                            new Vector(new Coordinates(-5 + random.nextDouble() * 10, -5 + random.nextDouble() * 10)));
                    game.addBall(tempBall);
                }
                for (int i = 0; i < 6; i++) {
                    tempBall = PhysicEngine.init_ball(
                            new Coordinates(2 * (width / 4) + i * brickHeight, 3.5 * (height / 4) - 100),
                            new Vector(new Coordinates(-5 + random.nextDouble() * 10, -5 + random.nextDouble() * 10)));
                    game.addBall(tempBall);
                }
                break;
            default:
                break;
        }
    }
}
