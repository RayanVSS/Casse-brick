package physics;

import geometry.Coordinates;
import geometry.Vector;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.GameConstants;

import java.util.Map;

public class AppPhysic extends Application {
    
    Stage primaryStage;
    Scene scene;
    StackPane root ;
    Outline outline = new Outline();


    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, 1000, 800);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setTitle("Physique");
        primaryStage.setScene(scene);
        primaryStage.show();
        menu();
    }

    public void menu(){
        Button bouton1 = new Button("Lancer la simulation");
        Button bouton2 = new Button("Options");
        bouton1.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        bouton2.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            new Simulation(outline,primaryStage);
        });

        bouton2.setOnAction(e -> {
            root.getChildren().clear();
            options();
        });

        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 0));
    }


    public void options() {

        Label labelpath = new Label("Afficher le chemin de la balle");
        ToggleButton buttonpath = new ToggleButton("OFF");
        buttonpath.setSelected(false);
        buttonpath.setOnAction(e -> {
            if (buttonpath.isSelected()) {
                buttonpath.setText("ON");
            } else {
                buttonpath.setText("OFF");
            }
            Simulation.PATH = buttonpath.isSelected();
        });
        labelpath.setStyle("-fx-text-fill: #d5bbb1;");
        labelpath.setTranslateY(0);
        labelpath.setTranslateX(400);
        buttonpath.setTranslateX(labelpath.getTranslateX());
        buttonpath.setTranslateY(labelpath.getTranslateY() + 30);
        buttonpath.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        Label labelspeed = new Label("Vitesse du vent : 0 km/h");
        Slider wind_speed = new Slider(0, 100, Outline.Vitesse_Vent);
        wind_speed.setOrientation(Orientation.HORIZONTAL);
        wind_speed.setMaxWidth(200);
        wind_speed.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            Outline.Vitesse_Vent = newvalue.intValue();
            labelspeed.setText("Vitesse du vent : " + newvalue.intValue() + " km/h");
        });
        labelspeed.setStyle("-fx-text-fill: #d5bbb1;");
        labelspeed.setTranslateY(-130);
        labelspeed.setTranslateX(0);
        wind_speed.setTranslateX(labelspeed.getTranslateX());
        wind_speed.setTranslateY(labelspeed.getTranslateY() + 30);
        wind_speed.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");


        Label labelWind = new Label("Direction du vent");
        labelWind.setStyle("-fx-text-fill: #d5bbb1;");
        labelWind.setTranslateY(0);
        labelWind.setTranslateX(0);
        ComboBox<String> listWind = new ComboBox<String>();
        listWind.getItems().addAll("Nord", "Sud", "Est", "Ouest");
        listWind.setTranslateX(0);
        listWind.setTranslateY(30);
        listWind.setMinWidth(250);
        listWind.setMaxWidth(250);
        listWind.setMinHeight(40);
        listWind.setMaxHeight(40);
        listWind.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        listWind.setOnAction(e -> {
            switch (listWind.getValue()) {
                case "Nord":
                    Outline.Direction_Vent = 0;
                    break;
                case "Sud":
                    Outline.Direction_Vent = 1;
                    break;
                case "Est":
                    Outline.Direction_Vent = 2;
                    break;
                case "Ouest":
                    Outline.Direction_Vent = 3;
                    break;
            }
        });

        Label labelGravity = new Label("Intensite de la gravite : 0 m/s^2");
        labelGravity.setStyle("-fx-text-fill: #d5bbb1;");
        labelGravity.setTranslateY(0);
        labelGravity.setTranslateX(-350);
        Slider gravity = new Slider(-50, 50, Outline.Gravite);
        gravity.setOrientation(Orientation.HORIZONTAL);
        gravity.setMaxWidth(200);
        gravity.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            Outline.Gravite = newvalue.intValue();
            labelGravity.setText("Intensite de la gravite : " + newvalue.intValue() + " m/s^2");
        });
        gravity.setTranslateX(labelGravity.getTranslateX());
        gravity.setTranslateY(labelGravity.getTranslateY() + 30);
        gravity.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");


        Button buttonback = new Button("Retour");
        buttonback.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        buttonback.setOnAction(e -> {
            root.getChildren().clear();
            menu();
        });
        buttonback.setTranslateX(370);
        buttonback.setTranslateY(300);


        root.getChildren().addAll(labelpath, buttonpath, labelspeed, wind_speed, listWind, labelWind, labelGravity, gravity, buttonback);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
