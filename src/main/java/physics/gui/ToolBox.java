package physics.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import utils.GraphicsToolkit.LabelToggleButtonHBox;
import utils.ImageLoader;
import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import gui.GraphicsFactory.RacketGraphics;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.geometry.Coordinates;
import utils.GameConstants;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;

public class ToolBox extends Pane {

    private boolean Bar = false;
    private Ball ball;
    private Pane root;
    private LabelToggleButtonHBox addBrickButton, addBallButton, BrickIncassableButton;
    private LabelToggleButtonHBox pauseButton;
    private boolean testpreset = false;
    private PhysicEngine game;
    private TestPreset test;

    private Map<Ball, BallGraphics> map;
    private Map<Brick, BricksGraphics> map2;

    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Image> list_image = new ArrayList<>(Arrays.asList(
            new Image("src/main/ressources/balle/classic/classic.png"),
            new Image("src/main/ressources/balle/black/classic.png"),
            new Image("src/main/ressources/balle/pink/gravity.png"),
            new Image("src/main/ressources/balle/pink/classic.png"),
            new Image("src/main/ressources/balle/black/gravity.png"),
            new Image("src/main/ressources/balle/classic/gravity.png")));

    private Random random = new Random();

    private Label labelrotate;
    private Label labelangle;
    private Label labelspeed;

    public ToolBox(Pane primarystage, Map<Ball, BallGraphics> map, Map<Brick, BricksGraphics> map2, PhysicEngine game) {
        this.root = primarystage;
        this.map = map;
        this.game = game;
        this.ball = map.keySet().iterator().next();
        this.map2 = map2;
        test = new TestPreset(0, 0, game);
        createUtilsButtons();
        setData();
        setSeparator();
    }

    public void afficherTest() {
        Button button1 = new Button("Activer les test unitaires");
        button1.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button1.setLayoutX(10);
        button1.setLayoutY(400);
        button1.setOnAction(e1 -> {
            testpreset = !testpreset;
            if (testpreset) {
                removeBall();
                removeBrick();
                removeRacket();
                Label label = new Label("Test unitaire activé");
                label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
                label.setLayoutX(10);
                label.setLayoutY(50);
                getChildren().add(label);
                test.setLayoutX(10);
                test.setLayoutY(60);
                button1.setLayoutX(10);
                button1.setLayoutY(200);
                getChildren().add(test);
                getChildren().removeIf(e -> (e instanceof Button && !e.equals(button1)) || e instanceof CheckBox
                        || e instanceof Label || e instanceof ComboBox
                        || (e instanceof LabelToggleButtonHBox && !e.equals(pauseButton)));
                button1.setText("Desactiver les test unitaires");

            } else {
                getChildren().remove(test);
                affiche();
                updateData();
                button1.setText("Activer les test unitaires");
                button1.setLayoutY(400);
            }
        });
        getChildren().addAll(button1, pauseButton);
    }

    public void affiche() {
        Label label = new Label("Informations sur la simulation :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(10);

        getChildren().add(label);
        label = new Label("Afficher la trajectoire sans effet :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(50);
        CheckBox button = new CheckBox();
        button.setOnAction(e -> {
            clearCircles();
            if (button.isSelected()) {
                if (map.size() > 0) {
                    circles = Preview.preview_no_effect(ball, root);
                    PhysicEngine.Pause = true;
                } else {
                    button.setSelected(false);
                }
            } else {
                PhysicEngine.Pause = false;
            }
        });
        button.setLayoutX(200);
        button.setLayoutY(50);

        getChildren().addAll(labelspeed, labelangle, labelrotate, button, label);

        getChildren().addAll(addBallButton, addBrickButton);

        Button button2 = new Button("Supprimer toutes les briques ajoutées");
        button2.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button2.setLayoutX(30);
        button2.setLayoutY(250);
        button2.setOnAction(e -> {
            removeBrick();
        });
        getChildren().add(button2);

        Button button3 = new Button("Supprimer toutes les balles ajoutées");
        button3.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button3.setLayoutX(30);
        button3.setLayoutY(270);

        button3.setOnAction(e -> {
            removeBall();
        });

        getChildren().add(button3);

        label = new Label("Changer la forme de la raquette :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(310);
        ComboBox<String> listracket = new ComboBox<String>();
        listracket.getItems().addAll("rectangle", "rond", "triangle", "losange", "YnotFixe");
        listracket.setValue(null);
        listracket.setLayoutX(40);
        listracket.setLayoutY(330);
        listracket.setOnAction(e -> {
            root.getChildren().remove(PhysicEngine.graphRacket.getShape());
            PhysicEngine.racket = PhysicEngine.init_racket(listracket.getValue());
            PhysicEngine.graphRacket = new RacketGraphics(PhysicEngine.racket, PhysicEngine.racket.getShapeType());
            root.getChildren().add(PhysicEngine.graphRacket.getShape());
        });

        getChildren().addAll(label, listracket);

    }

    public void reset() {
        clearCircles();
        getChildren().removeIf(e -> e instanceof Button || e instanceof CheckBox || e instanceof Label
                || e instanceof ComboBox || e instanceof LabelToggleButtonHBox);
    }

    public void removeBall() {
        for (Ball b : map.keySet()) {
            root.getChildren().remove(map.get(b));
            if (b.getPreview() != null) {
                b.getPreview().clear_path(root);
            }
        }
        map.clear();
        ball = null;
    }

    public void removeRacket() {
        root.getChildren().remove(PhysicEngine.graphRacket.getShape());
        PhysicEngine.racket = null;
    }

    public void removeBrick() {
        for (Brick b : map2.keySet()) {
            root.getChildren().remove(map2.get(b));
        }
        map2.clear();
    }

    public void clearCircles() {
        for (Circle p : circles) {
            root.getChildren().remove(p);
        }
    }

    public void updateData() {
        if (map.size() > 0) {
            ball = map.keySet().iterator().next();
            labelrotate.setText("Effet de la rotation :" + PhysicSetting.CalculateRotation(ball) + " degre");
            labelangle.setText("Angle du vecteur de la balle : " + PhysicSetting.CalculateAngle(ball) + " degre");
            labelspeed.setText("Vitesse de la balle : " + PhysicSetting.CalculateSpeed(ball.getDirection()) + " m/s");
        } else {
            labelrotate.setText("Effet de la rotation : -");
            labelangle.setText("Angle du vecteur de la balle : -");
            labelspeed.setText("Vitesse de la balle : -");
        }
        if (map2.size() == 0) {
            getChildren().remove(BrickIncassableButton);
        }
        pauseButton.getToggleButton().setSelected(PhysicEngine.Pause);
        pauseButton.updateText();
    }

    public void setData() {
        labelrotate = new Label("Effet de la rotation :" + PhysicSetting.CalculateRotation(ball) + " degre");
        labelrotate.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelrotate.setLayoutX(10);
        labelrotate.setLayoutY(80);

        labelangle = new Label("Angle du vecteur de la balle : " + PhysicSetting.CalculateAngle(ball) + " degre");
        labelangle.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelangle.setLayoutX(10);
        labelangle.setLayoutY(110);

        labelspeed = new Label("Vitesse de la balle : " + PhysicSetting.CalculateSpeed(ball.getDirection()) + " m/s");
        labelspeed.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelspeed.setLayoutX(10);
        labelspeed.setLayoutY(140);

    }

    public void close() {
        reset();
    }

    public void update() {
        if (Bar) {
            Bar = false;
            root.setOnMouseClicked(null);
            PhysicEngine.start_border = 0;
            PhysicEngine.LIMIT_SIMULATION.get(1).addX(-300);

            close();
            root.getChildren().remove(this);
        } else {
            Bar = true;
            addControls();
            PhysicEngine.start_border = 300;
            PhysicEngine.LIMIT_SIMULATION.get(1).addX(300);
            root.getChildren().add(this);
            affiche();
            afficherTest();
        }
    }

    public boolean isBar() {
        return Bar;
    }

    public void setSeparator() {
        ImageView separator = new ImageView(ImageLoader.loadImage("src/main/ressources/lifeScore/blackSep.png"));
        double windowHeight = getHeight();
        separator.setFitHeight(windowHeight);
        separator.setFitWidth(20);
        separator.setLayoutX(280);
        separator.setLayoutY(-100);
        separator.setSmooth(true);
        separator.setPreserveRatio(false);
        getChildren().add(separator);
    }

    //GamePhysicsToolBox

    private void createUtilsButtons() {
        addBrickButton = new LabelToggleButtonHBox("Ajouter une brique", false);
        addBrickButton.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        addBrickButton.getToggleButton().setOnAction(e -> {
            if (addBrickButton.getToggleButton().isSelected()) {
                if (!getChildren().contains(BrickIncassableButton)) {
                    getChildren().add(BrickIncassableButton);
                }
            }
            addBrickButton.updateText();
        });

        BrickIncassableButton = new LabelToggleButtonHBox("Incassable", false);
        BrickIncassableButton.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        BrickIncassableButton.getToggleButton().setOnAction(e -> {
            for (Brick brick : map2.keySet()) {
                if (BrickIncassableButton.getToggleButton().isSelected()) {
                    brick.setUnbreakable(true);
                } else {
                    brick.setUnbreakable(false);
                }
            }
            BrickIncassableButton.updateText();
        });

        addBallButton = new LabelToggleButtonHBox("Ajouter une balle", false);
        addBallButton.getToggleButton().setOnAction(e -> addBallButton.updateText());
        addBallButton.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        pauseButton = new LabelToggleButtonHBox("Partie en pause : ", PhysicEngine.Pause);
        pauseButton.getToggleButton().setOnAction(e -> {
            PhysicEngine.Pause = !PhysicEngine.Pause;
            pauseButton.updateText();
        });
        pauseButton.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");

        BrickIncassableButton.setLayoutX(10);
        BrickIncassableButton.setLayoutY(200);

        addBrickButton.setLayoutX(10);
        addBrickButton.setLayoutY(170);

        addBallButton.setLayoutX(10);
        addBallButton.setLayoutY(230);

        pauseButton.setLayoutX(10);
        pauseButton.setLayoutY(360);

    }

    private void addControls() {
        root.setOnMouseClicked(event -> {
            // Vérifier si la souris est dans la zone de jeu
            double mouseX = event.getX();
            double mouseY = event.getY();
            boolean nochevauchement = true;

            if (mouseX > PhysicEngine.start_border + GameConstants.BRICK_WIDTH
                    && mouseX < PhysicSetting.DEFAULT_WINDOW_WIDTH - GameConstants.BRICK_WIDTH
                    && mouseY > GameConstants.BRICK_HEIGHT
                    && mouseY < PhysicSetting.DEFAULT_WINDOW_HEIGHT - GameConstants.BRICK_HEIGHT) {
                if (addBrickButton.getToggleButton().isSelected()) {
                    for (Brick brick : map2.keySet()) {
                        if ((brick.contains(mouseX, mouseY)
                                || brick.contains(mouseX + GameConstants.BRICK_WIDTH, mouseY)
                                || brick.contains(mouseX, mouseY + GameConstants.BRICK_HEIGHT) || brick.contains(
                                        mouseX + GameConstants.BRICK_WIDTH, mouseY + GameConstants.BRICK_HEIGHT))) {
                            nochevauchement = false;
                            break;
                        }
                    }
                    if (nochevauchement) {
                        Brick b = PhysicEngine.init_brick(new Coordinates(mouseX, mouseY));
                        if (BrickIncassableButton.getToggleButton().isSelected()) {
                            b.setUnbreakable(true);
                        }
                        BricksGraphics brickg = new BricksGraphics(b);
                        game.setTakeBrick(brickg, b, this, root);
                        map2.put(b, brickg);
                        root.getChildren().add(brickg);
                        addBrickButton.getToggleButton().setSelected(false);
                        addBrickButton.updateText();
                    }
                } else if (addBallButton.getToggleButton().isSelected()) {
                    Ball b2 = PhysicEngine.init_ball(new Coordinates(mouseX, mouseY), null);
                    Integer nb = random.nextInt(list_image.size());
                    BallGraphics ballg = new BallGraphics(list_image.get(nb), b2);
                    game.setTakeBall(ballg, b2, this, root);
                    map.put(b2, ballg);
                    root.getChildren().add(ballg);
                    addBallButton.getToggleButton().setSelected(false);
                    addBallButton.updateText();
                }
            }
        });
    }

}
