package gui.Menu.MenuViews;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import utils.GameConstants;

public class OptionsView implements Menu {
    private Stage primaryStage;
    private Scene scene;
    private HBox root;
    private Button btnBack;
    private ToggleButton buttonfps;
    private ToggleButton buttonpath;
    private ToggleButton buttonparticles;
    private Slider volumemusic;
    private Slider volumesound;
    private Button buttonleft;
    private Button buttonright;
    private Button buttonpower;

    public OptionsView(Stage p) {
        this.primaryStage = p;
        this.root = new HBox();
        this.scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        btnBack = createButton("Retour", -870, -700);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setScene(scene);

        // Hbox 1: FPS, Chemain de la balle,Particules
        VBox v1 = new VBox();
        // Bouton pour afficher les FPS
        Label labelfps=createLabel("Afficher les FPS", 0, 0);
        buttonfps = new ToggleButton("OFF");
        buttonfps.setSelected(false);
        labelfps.setStyle("-fx-text-fill: #d5bbb1;");
        buttonfps.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        // Bouton pour afficher le chemin de la balle
        Label labelpath = new Label("Afficher le chemin de la balle");
        buttonpath = new ToggleButton("OFF");
        buttonpath.setSelected(false);
        labelpath.setStyle("-fx-text-fill: #d5bbb1;");
        buttonpath.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        // Bouton pour afficher les trainées de particules
        Label labelparticles = new Label("Afficher les particules");
        buttonparticles = new ToggleButton("ON");
        buttonparticles.setSelected(true);

        labelparticles.setStyle("-fx-text-fill: #d5bbb1;");
        buttonparticles.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        v1.getChildren().addAll(labelfps, buttonfps, labelpath, buttonpath, labelparticles, buttonparticles);

        VBox v2 = new VBox();
        // Slider pour le volume de la musique
        Label labelmusic = new Label("Volume de la musique");
        volumemusic = new Slider(0, 100, GameConstants.MUSIC);
        volumemusic.setMaxWidth(200);
        volumemusic.setOrientation(Orientation.HORIZONTAL);
        labelmusic.setStyle("-fx-text-fill: #d5bbb1;");
        volumemusic.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        // Slider pour le volume des sons
        Label labelsound = new Label("Volume des sons");
        volumesound = new Slider(0, 100, GameConstants.SOUND);
        volumesound.setOrientation(Orientation.HORIZONTAL);
        volumesound.setMaxWidth(200);
        labelsound.setStyle("-fx-text-fill: #d5bbb1;");
        volumesound.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        v2.getChildren().addAll(labelmusic, volumemusic, labelsound, volumesound);

        VBox v3 = new VBox();
        // Bouton pour configurer la touche gauche
        Label labelleft = new Label("Touche pour aller a gauche");
        buttonleft = new Button(GameConstants.LEFT.getName());
        labelleft.setStyle("-fx-text-fill: #d5bbb1;");
        buttonleft.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        // Bouton pour configurer la touche droite
        Label labelright = new Label("Touche pour aller a droite");
        buttonright = new Button(GameConstants.RIGHT.getName());

        labelright.setStyle("-fx-text-fill: #d5bbb1;");
        buttonright.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");

        // Bouton pour configure la touche pour activer le pouvoir
        Label labelpower=createLabel("Touche pour activer le pouvoir", 0, 0);
        buttonpower=createButton(GameConstants.SPACE.getName(), 0, 0);

        labelpower.setStyle("-fx-text-fill: #d5bbb1;");
        buttonpower.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        v3.getChildren().addAll(labelleft, buttonleft, labelright, buttonright, labelpower, buttonpower);
        v1.setAlignment(javafx.geometry.Pos.CENTER);
        v2.setAlignment(javafx.geometry.Pos.CENTER);
        v3.setAlignment(javafx.geometry.Pos.CENTER);
        v1.setSpacing(20);
        v2.setSpacing(20);
        v3.setSpacing(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(100);
        root.getChildren().addAll(v1, v2, v3, btnBack);
    }

    // Getters
    public HBox getRoot() {
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

    public ToggleButton getButtonfps() {
        return buttonfps;
    }

    public ToggleButton getButtonpath() {
        return buttonpath;
    }

    public ToggleButton getButtonparticles() {
        return buttonparticles;
    }

    public Slider getVolumemusic() {
        return volumemusic;
    }

    public Slider getVolumesound() {
        return volumesound;
    }

    public Button getButtonleft() {
        return buttonleft;
    }

    public Button getButtonright() {
        return buttonright;
    }

    public Button getButtonpower() {
        return buttonpower;
    }

}
