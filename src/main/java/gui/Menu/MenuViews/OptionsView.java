package gui.Menu.MenuViews;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import utils.GameConstants;

public class OptionsView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private StackPane root;
    private Button btnBack;


    public OptionsView(Stage p) {
        this.primaryStage = p;
        this.root = new StackPane();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        btnBack = createButton("Retour", -870, -700);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setScene(scene);
        
        //Bouton pour afficher les FPS
        Label labelfps = new Label("Afficher les FPS");
        ToggleButton buttonfps = new ToggleButton("OFF");
        buttonfps.setSelected(false);
        buttonfps.setOnAction(e -> {
            if (buttonfps.isSelected()) {
                buttonfps.setText("ON");
            } else {
                buttonfps.setText("OFF");
            }
            GameConstants.FPS = buttonfps.isSelected();
        });
        labelfps.setStyle("-fx-text-fill: #d5bbb1;");
        labelfps.setTranslateY(-80);
        labelfps.setTranslateX(-600);
        buttonfps.setTranslateX(labelfps.getTranslateX());
        buttonfps.setTranslateY(labelfps.getTranslateY() + 30);
        buttonfps.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");


        //Bouton pour afficher le chemin de la balle
        Label labelpath = new Label("Afficher le chemin de la balle");
        ToggleButton buttonpath = new ToggleButton("OFF");
        buttonpath.setSelected(false);
        buttonpath.setOnAction(e -> {
            if (buttonpath.isSelected()) {
                buttonpath.setText("ON");
            } else {
                buttonpath.setText("OFF");
            }
            GameConstants.PATH = buttonpath.isSelected();
        });
        labelpath.setStyle("-fx-text-fill: #d5bbb1;");
        labelpath.setTranslateY(0);
        labelpath.setTranslateX(-600);
        buttonpath.setTranslateX(labelpath.getTranslateX());
        buttonpath.setTranslateY(labelpath.getTranslateY() + 30);
        buttonpath.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Bouton pour afficher les trainées de particules
        Label labelparticles = new Label("Afficher les particules");
        ToggleButton buttonparticles = new ToggleButton("ON");
        buttonparticles.setSelected(true);
        buttonparticles.setOnAction(e -> {
            if (buttonparticles.isSelected()) {
                buttonparticles.setText("ON");
            } else {
                buttonparticles.setText("OFF");
            }
            GameConstants.PARTICLES = buttonparticles.isSelected();
        });
        labelparticles.setStyle("-fx-text-fill: #d5bbb1;");
        labelparticles.setTranslateY(80);
        labelparticles.setTranslateX(-600);
        buttonparticles.setTranslateX(labelparticles.getTranslateX());
        buttonparticles.setTranslateY(labelparticles.getTranslateY() + 30);
        buttonparticles.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Slider pour le volume de la musique
        Label labelmusic = new Label("Volume de la musique");
        Slider volumemusic = new Slider(0, 100, GameConstants.MUSIC);
        volumemusic.setMaxWidth(200);
        volumemusic.setOrientation(Orientation.HORIZONTAL);
        volumemusic.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            GameConstants.MUSIC = newvalue.intValue();
        });
        labelmusic.setStyle("-fx-text-fill: #d5bbb1;");
        labelmusic.setTranslateY(-80);
        labelmusic.setTranslateX(0);
        volumemusic.setTranslateX(labelmusic.getTranslateX());
        volumemusic.setTranslateY(labelmusic.getTranslateY() + 30);
        volumemusic.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Slider pour le volume des sons
        Label labelsound = new Label("Volume des sons");
        Slider volumesound = new Slider(0, 100, GameConstants.SOUND);
        volumesound.setOrientation(Orientation.HORIZONTAL);
        volumesound.setMaxWidth(200);
        volumesound.valueProperty().addListener((observable, oldvalue, newvalue) -> {
            GameConstants.SOUND = newvalue.intValue();
        });
        labelsound.setStyle("-fx-text-fill: #d5bbb1;");
        labelsound.setTranslateY(0);
        labelsound.setTranslateX(0);
        volumesound.setTranslateX(labelsound.getTranslateX());
        volumesound.setTranslateY(labelsound.getTranslateY() + 30);
        volumesound.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Bouton pour configurer la touche gauche
        Label labelleft = new Label("Touche pour aller a gauche");
        Button buttonleft = new Button(GameConstants.LEFT.getName());
        buttonleft.setOnAction(e -> {
            buttonleft.setText("Appuyez sur une touche...");
            buttonleft.setOnKeyPressed(event -> {
                GameConstants.LEFT = event.getCode();
                buttonleft.setText(GameConstants.LEFT.getName());
                buttonleft.setOnKeyPressed(null); // Désactive la liaison après la configuration
            });
        });
        labelleft.setStyle("-fx-text-fill: #d5bbb1;");
        labelleft.setTranslateY(-80);
        labelleft.setTranslateX(600);
        buttonleft.setTranslateX(labelleft.getTranslateX());
        buttonleft.setTranslateY(labelleft.getTranslateY() + 30);
        buttonleft.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Bouton pour configurer la touche droite
        Label labelright = new Label("Touche pour aller a droite");
        Button buttonright = new Button(GameConstants.RIGHT.getName());
        buttonright.setOnAction(e -> {
            buttonright.setText("Appuyez sur une touche...");
            buttonright.setOnKeyPressed(event -> {
                GameConstants.RIGHT = event.getCode();
                buttonright.setText(GameConstants.RIGHT.getName());
                buttonright.setOnKeyPressed(null); // Désactive la liaison après la configuration
            });
        });
        labelright.setStyle("-fx-text-fill: #d5bbb1;");
        labelright.setTranslateY(0);
        labelright.setTranslateX(600);
        buttonright.setTranslateX(labelright.getTranslateX());
        buttonright.setTranslateY(labelright.getTranslateY() + 30);
        buttonright.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        //Bouton pour configure la touche pour activer le pouvoir
        Label labelpower = new Label("Touche pour activer le pouvoir");
        Button buttonpower = new Button(GameConstants.SPACE.getName());
        buttonpower.setOnAction(e -> {
            buttonpower.setText("Appuyez sur une touche...");
            buttonpower.setOnKeyPressed(event -> {
                GameConstants.SPACE = event.getCode();
                buttonpower.setText(GameConstants.SPACE.getName());
                buttonpower.setOnKeyPressed(null); // Désactive la liaison après la configuration
            });
        });
        labelpower.setStyle("-fx-text-fill: #d5bbb1;");
        labelpower.setTranslateY(80);
        labelpower.setTranslateX(600);
        buttonpower.setTranslateX(labelpower.getTranslateX());
        buttonpower.setTranslateY(labelpower.getTranslateY() + 30);
        buttonpower.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        
        root.getChildren().addAll(btnBack, buttonfps, buttonpath, labelfps, labelpath, 
        labelparticles, buttonparticles, labelmusic, volumemusic, labelsound, volumesound, 
        labelleft, buttonleft, labelright, buttonright, labelpower, buttonpower);
    }
        

    // Getters
    public StackPane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBtnBack() {
        return btnBack;
    }

}
