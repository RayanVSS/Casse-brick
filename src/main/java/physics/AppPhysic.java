package physics;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;
import utils.GameConstants;

public class AppPhysic extends Application {
    
    Stage primaryStage;
    Scene scene;
    StackPane root ;
    public static PhysicSetting physics = new PhysicSetting();

    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setTitle("Physic Engine");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
        menu();
    }

    public void menu(){
        Button bouton1 = new Button("Lancer la simulation");
        Button bouton2 = new Button("Options");
        bouton1.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        bouton2.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            new PhysicEngine(primaryStage,this);
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

    public void Speed(){
        Label labelspeed = new Label("Vitesse de la balle : "+physics.getSpeed_Ball()+" km/h");
        Slider speed = new Slider(0, 100, physics.getSpeed_Ball());
        speed.setOrientation(Orientation.HORIZONTAL);
        speed.setMaxWidth(200);
        speed.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setSpeed_Ball(newvalue.intValue());
            labelspeed.setText("Vitesse de la balle : " + newvalue.intValue() + " km/h");
        });
        labelspeed.setStyle("-fx-text-fill: #d5bbb1;");
        labelspeed.setTranslateY(-130);
        labelspeed.setTranslateX(350);
        speed.setTranslateX(labelspeed.getTranslateX());
        speed.setTranslateY(labelspeed.getTranslateY() + 30);
        speed.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        root.getChildren().addAll(labelspeed, speed);
    }

    public void Path(){
        Label labelpath = new Label("Afficher le chemin de la balle");
        ToggleButton buttonpath = new ToggleButton("OFF");
        buttonpath.setSelected(false);
        buttonpath.setOnAction(e -> {
            if (buttonpath.isSelected()) {
                buttonpath.setText("ON");
            } else {
                buttonpath.setText("OFF");
            }
            PhysicEngine.PATH = buttonpath.isSelected();
        });
        labelpath.setStyle("-fx-text-fill: #d5bbb1;");
        labelpath.setTranslateY(0);
        labelpath.setTranslateX(400);
        buttonpath.setTranslateX(labelpath.getTranslateX());
        buttonpath.setTranslateY(labelpath.getTranslateY() + 30);
        buttonpath.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        root.getChildren().addAll(labelpath, buttonpath);
    }

    public void Wind(){
        Label labelspeed = new Label("Vitesse du vent : +"+physics.getSpeed_Wind()+"km/h");
        Slider wind_speed = new Slider(0, 100, physics.getSpeed_Wind());
        wind_speed.setOrientation(Orientation.HORIZONTAL);
        wind_speed.setMaxWidth(200);
        wind_speed.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setSpeed_Wind(newvalue.intValue());
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
                    physics.setDirection_Wind(0);
                    break;
                case "Sud":
                    physics.setDirection_Wind(1);
                    break;
                case "Est":
                    physics.setDirection_Wind(2);
                    break;
                case "Ouest":
                    physics.setDirection_Wind(3);
                    break;
            }
        });
        root.getChildren().addAll(labelspeed, wind_speed, listWind, labelWind);
    }

    public void Gravity(){
        Label labelGravity = new Label("Intensite de la gravite : "+physics.getGravite()*100+" m/s^2");
        labelGravity.setStyle("-fx-text-fill: #d5bbb1;");
        labelGravity.setTranslateY(0);
        labelGravity.setTranslateX(-350);
        Slider gravity = new Slider(-10, 10, physics.getGravite());
        gravity.setOrientation(Orientation.HORIZONTAL);
        gravity.setMaxWidth(200);
        gravity.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setGravite(newvalue.intValue()/100);
            labelGravity.setText("Intensite de la gravite : " + newvalue.intValue() + " m/s^2");
        });
        gravity.setTranslateX(labelGravity.getTranslateX());
        gravity.setTranslateY(labelGravity.getTranslateY() + 30);
        gravity.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        root.getChildren().addAll(labelGravity, gravity);
    }

    public void Mass(){
        Label labelMass = new Label("Masse de la balle : "+physics.getMass()+" g");
        labelMass.setStyle("-fx-text-fill: #d5bbb1;");
        labelMass.setTranslateY(100);
        labelMass.setTranslateX(-350);
        Slider mass = new Slider(1, 1000, physics.getMass());
        mass.setOrientation(Orientation.HORIZONTAL);
        mass.setMaxWidth(200);
        mass.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            physics.setMass(newvalue.intValue()/10);
            labelMass.setText("Masse de la balle : " + newvalue.intValue() + " kg");
        });
        mass.setTranslateX(labelMass.getTranslateX());
        mass.setTranslateY(labelMass.getTranslateY() + 30);
        mass.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        root.getChildren().addAll(labelMass, mass);
    }

    public void Racket(){
        Label labelracket = new Label("Racket");
        Button buttonracket = new Button("OFF");
        buttonracket.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        buttonracket.setOnAction(e -> {
            PhysicEngine.RACKET = !PhysicEngine.RACKET;
            if(PhysicEngine.RACKET){
                buttonracket.setText("ON");
            }
            else{
                buttonracket.setText("OFF");
            }
        });
        labelracket.setStyle("-fx-text-fill: #d5bbb1;");
        labelracket.setTranslateY(-200);
        labelracket.setTranslateX(-350);
        buttonracket.setTranslateX(labelracket.getTranslateX());
        buttonracket.setTranslateY(labelracket.getTranslateY() + 30);
        root.getChildren().addAll(labelracket, buttonracket);
    }

    public void back(){
        Button buttonback = new Button("Retour");
        buttonback.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        buttonback.setOnAction(e -> {
            root.getChildren().clear();
            menu();
        });
        buttonback.setTranslateX(370);
        buttonback.setTranslateY(300);
        root.getChildren().add(buttonback);
    }

    public Scene getScene(){
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
