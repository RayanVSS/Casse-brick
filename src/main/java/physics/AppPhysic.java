package physics;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;

public class AppPhysic extends Application {

    Stage primaryStage;
    Scene scene;
    StackPane root;
    public static AppPhysic app;
    public static PhysicSetting physics = new PhysicSetting();

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        app = this;
        root = new StackPane();
        scene = new Scene(root, PhysicSetting.DEFAULT_WINDOW_WIDTH, PhysicSetting.DEFAULT_WINDOW_HEIGHT);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setTitle("Physic Engine");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
        primaryStage.setOnCloseRequest(e->{
            primaryStage.close();
            System.exit(0);
        });
        menu();
    }

    public void menu() {
        Button bouton1 = new Button("Lancer la simulation");
        Button bouton2 = new Button("Options");
        bouton1.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        bouton2.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            root.getChildren().add((new PhysicEngine()));;
        });

        bouton2.setOnAction(e -> {
            root.getChildren().clear();
            back();
            Speed();
            Path();
            Wind();
            Gravity();
            Mass();
            Racket();
        });

        root.getChildren().add(bouton1);
        StackPane.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        StackPane.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 0));
    }

    public void Speed() {
        Label labelspeed = new Label("Vitesse de la balle : " + physics.getSpeed_Ball() + " km/h");
        Slider speed = new Slider(0, 100, physics.getSpeed_Ball());
        speed.setOrientation(Orientation.HORIZONTAL);
        speed.setMaxWidth(200);
        speed.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setSpeed_Ball(newvalue.intValue());
            labelspeed.setText("Vitesse de la balle : " + newvalue.intValue() + " km/h");
        });
        labelspeed.setStyle("-fx-text-fill: #FFFFFF;");
        labelspeed.setTranslateY(-130);
        labelspeed.setTranslateX(350);
        speed.setTranslateX(labelspeed.getTranslateX());
        speed.setTranslateY(labelspeed.getTranslateY() + 30);
        speed.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        root.getChildren().addAll(labelspeed, speed);
    }

    public void Path() {
        Label labelpath = new Label("Afficher le chemin de la balle");
        ToggleButton buttonpath = new ToggleButton((PhysicEngine.PATH) ? "ON" : "OFF");
        buttonpath.setSelected(PhysicEngine.PATH);
        buttonpath.setOnAction(e -> {
            if (buttonpath.isSelected()) {
                buttonpath.setText("ON");
            } else {
                buttonpath.setText("OFF");
            }
            PhysicEngine.PATH = buttonpath.isSelected();
        });
        labelpath.setStyle("-fx-text-fill: #FFFFFF;");
        labelpath.setTranslateY(0);
        labelpath.setTranslateX(400);
        buttonpath.setTranslateX(labelpath.getTranslateX());
        buttonpath.setTranslateY(labelpath.getTranslateY() + 30);
        buttonpath.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        root.getChildren().addAll(labelpath, buttonpath);
    }

    public void Wind() {
        Label labelspeed = new Label("Vitesse du vent : +" + physics.getSpeed_Wind() + "m/h");
        Slider wind_speed = new Slider(0, 100, physics.getSpeed_Wind());
        wind_speed.setOrientation(Orientation.HORIZONTAL);
        wind_speed.setMaxWidth(200);
        wind_speed.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setSpeed_Wind(newvalue.intValue());
            labelspeed.setText("Vitesse du vent : " + newvalue.intValue() + " km/h");
        });
        labelspeed.setStyle("-fx-text-fill: #FFFFFF;");
        labelspeed.setTranslateY(-130);
        labelspeed.setTranslateX(0);
        wind_speed.setTranslateX(labelspeed.getTranslateX());
        wind_speed.setTranslateY(labelspeed.getTranslateY() + 30);
        wind_speed.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");

        Label labelWind = new Label("Direction du vent");
        labelWind.setStyle("-fx-text-fill: #FFFFFF;");
        labelWind.setTranslateY(0);
        labelWind.setTranslateX(0);
        ComboBox<String> listWind = new ComboBox<String>();
        listWind.getItems().addAll("Est", "Ouest");
        listWind.setTranslateX(0);
        listWind.setTranslateY(30);
        listWind.setMinWidth(250);
        listWind.setMaxWidth(250);
        listWind.setMinHeight(40);
        listWind.setMaxHeight(40);
        listWind.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        listWind.setOnAction(e -> {
            switch (listWind.getValue()) {
                case "Est":
                    physics.setDirection_Wind(0);
                    break;
                case "Ouest":
                    physics.setDirection_Wind(1);
                    break;
            }
        });
        root.getChildren().addAll(labelspeed, wind_speed, listWind, labelWind);
    }

    public void Gravity() {
        Label labelGravity = new Label("Gravite terrestre");
        labelGravity.setStyle("-fx-text-fill: #FFFFFF;");
        labelGravity.setTranslateY(0);
        labelGravity.setTranslateX(-350);
        Button buttonGravity = new Button((physics.getGravity()) ? "ON" : "OFF");
        buttonGravity.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        buttonGravity.setOnAction(e -> {
            physics.changeGravity();
            if (physics.getGravity()) {
                buttonGravity.setText("ON");
            } else {
                buttonGravity.setText("OFF");
            }
        });
        buttonGravity.setTranslateX(labelGravity.getTranslateX());
        buttonGravity.setTranslateY(labelGravity.getTranslateY() + 30);
        root.getChildren().addAll(labelGravity, buttonGravity);
    }

    public void Mass() {
        Label labelMass = new Label("Masse de la balle : " + physics.getMass() + " g");
        labelMass.setStyle("-fx-text-fill: #FFFFFF;");
        labelMass.setTranslateY(100);
        labelMass.setTranslateX(-350);
        Slider mass = new Slider(1, 10, physics.getMass());
        mass.setOrientation(Orientation.HORIZONTAL);
        mass.setMaxWidth(200);
        mass.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setMass(newvalue.intValue());
            labelMass.setText("Masse de la balle : " + newvalue.intValue() + " g");
        });
        mass.setTranslateX(labelMass.getTranslateX());
        mass.setTranslateY(labelMass.getTranslateY() + 30);
        mass.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        root.getChildren().addAll(labelMass, mass);
    }

    public void Racket() {
        Label labelracket = new Label("Racket");
        Button buttonracket = new Button((PhysicEngine.RACKET)?"ON":"OFF");
        buttonracket.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        buttonracket.setOnAction(e -> {
            PhysicEngine.RACKET = !PhysicEngine.RACKET;
            if (PhysicEngine.RACKET) {
                buttonracket.setText("ON");
            } else {
                buttonracket.setText("OFF");
            }
        });
        labelracket.setStyle("-fx-text-fill: #FFFFFF;");
        labelracket.setTranslateY(-200);
        labelracket.setTranslateX(-350);
        buttonracket.setTranslateX(labelracket.getTranslateX());
        buttonracket.setTranslateY(labelracket.getTranslateY() + 30);
        root.getChildren().addAll(labelracket, buttonracket);
    }

    public void back() {
        Button buttonback = new Button("Retour");
        buttonback.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #FFFFFF;");
        buttonback.setOnAction(e -> {
            root.getChildren().clear();
            menu();
        });
        buttonback.setTranslateX(370);
        buttonback.setTranslateY(300);
        root.getChildren().add(buttonback);
    }

    public void returnMenu(){
        root.getChildren().clear();
        menu();
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
