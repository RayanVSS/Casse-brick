package physics.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;
import physics.entity.Ball;
import utils.GameConstants;

public class OptionBar {

    private boolean Bar = false;
    private Pane root;
    private Pane bar;
    private ArrayList<Circle> circles = new ArrayList<>();
    private char position = 'g';
    private Map<Ball, Circle> map;
    private ArrayList<Color> list_color = new ArrayList<>(Arrays.asList(Color.RED, Color.BLUE, Color.GREEN,
            Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.BROWN, Color.PINK, Color.CYAN, Color.MAGENTA));
    private ArrayList<Ball> list_ball = new ArrayList<>();

    public OptionBar(Pane root, Map<Ball, Circle> map) {
        this.root = root;
        this.map = map;
        bar = new Pane();
    }

    public void affiche(Ball b) {
        bar.setMaxSize(250, GameConstants.DEFAULT_WINDOW_HEIGHT);
        bar.setMinSize(250, GameConstants.DEFAULT_WINDOW_HEIGHT);
        bar.setStyle("-fx-background-color: #DCDCDC;");
        root.getChildren().add(bar);
        bar.setLayoutX(position == 'g' ? 0 : GameConstants.DEFAULT_WINDOW_WIDTH - 250);

        Label label = new Label("Informations sur la simulation :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(20);

        Button button = new Button("Afficher la trajectoire sans effet");
        button.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button.setLayoutX(30);
        button.setLayoutY(50);
        button.setOnAction(e -> {
            clearCircles();
            circles = Preview.preview_no_effect(b, root);
        });
        bar.getChildren().addAll(button, label);

        label = new Label("Effet de la rotation :"
                + (b.getRotation().getAngle() == 0 ? "" : b.getRotation().getAngle() > 0 ? "Droite" : " Gauche") + " "
                + Math.abs(b.getRotation().getAngle()) + " degre");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(100);

        bar.getChildren().add(label);

        label = new Label("Angle du vecteur de la balle : " + (90 - b.getRotation().getAngle()) + " degre");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(130);

        bar.getChildren().add(label);

        label = new Label("Vitesse de la balle : " + PhysicSetting.CalculateSpeed(b.getDirection()) + " m/s");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(160);

        bar.getChildren().add(label);

        Button button2 = new Button("Ajouter une nouvelle balle ");
        button2.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button2.setLayoutX(30);
        button2.setLayoutY(190);

        button2.setOnAction(e -> {
            if (list_color.size() > 0) {
                Ball b2 = PhysicEngine.init_ball();
                Circle c = new Circle(b2.getC().getX(), b2.getC().getY(), b.getRadius() * 2);
                Color color = list_color.get((int) (Math.random() * list_color.size()));
                c.setFill(color);
                list_color.remove(color);
                list_ball.add(b2);
                map.put(b2, c);
                root.getChildren().add(c);
            } else {
                Label l = new Label("Vous avez atteint le nombre maximal de balles");
                l.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
                l.setLayoutX(10);
                l.setLayoutY(220);
                bar.getChildren().add(l);
            }
        });

        bar.getChildren().add(button2);

        Button button3 = new Button("Supprimer toutes les balles ajoutées");
        button3.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button3.setLayoutX(30);
        button3.setLayoutY(250);

        button3.setOnAction(e -> {
            removeBall();
        });

        bar.getChildren().add(button3);

    }

    public void reset() {
        clearCircles();
        bar.getChildren().removeIf(e -> e instanceof Label);
    }

    public void removeBall() {
        for (Ball b : list_ball) {
            root.getChildren().remove(map.get(b));
            map.remove(b);
        }
        list_ball.clear();
        list_color = new ArrayList<>(Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
                Color.PURPLE, Color.BROWN, Color.PINK, Color.CYAN, Color.MAGENTA));
    }

    public void clearCircles() {
        for (Circle p : circles) {
            root.getChildren().remove(p);
        }
    }

    public void close() {
        reset();
        root.getChildren().remove(bar);
    }

    public void update(Ball b) {
        if (Bar) {
            Bar = false;
            close();
            if (position == 'd') {
                PhysicEngine.f_WIDTH = GameConstants.DEFAULT_WINDOW_WIDTH;
            } else {
                PhysicEngine.d_WIDTH = 0;
            }
        } else {
            Bar = true;
            if (b.getC().getX() < 250) {
                position = 'd';
                PhysicEngine.f_WIDTH = GameConstants.DEFAULT_WINDOW_WIDTH - 250;
            } else {
                position = 'g';
                PhysicEngine.d_WIDTH = 250;
            }
            affiche(b);
        }
    }

    public boolean isBar() {
        return Bar;
    }
}
