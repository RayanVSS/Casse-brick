package gui.Menu.MenuViews;

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
        btnBack = createButton("Retour", 0,0);
        root.setStyle("-fx-background-color: #273654;");
        primaryStage.setScene(scene);

        // Hbox 1: FPS, Chemain de la balle,Particules
        VBox v1 = new VBox();
        // Bouton pour afficher les FPS
        Label labelfps=createLabel("Afficher les FPS", 0, 0, 15);
        buttonfps = createToggleButton("OFF", false);

        // Bouton pour afficher le chemin de la balle
        Label labelpath =createLabel("Afficher le chemin de la balle", 0, 0, 15);
        buttonpath = createToggleButton("OFF", false);

        // Bouton pour afficher les trainées de particules
        Label labelparticles = createLabel("Afficher les particules", 0, 0, 15);
        buttonparticles = createToggleButton("ON", false);

        v1.getChildren().addAll(labelfps, buttonfps, labelpath, buttonpath, labelparticles, buttonparticles);

        VBox v2 = new VBox();
        // Slider pour le volume de la musique
        Label labelmusic =createLabel("Volume de la musique", 0, 0, 15);
        volumemusic = createSlider(0, 100, GameConstants.MUSIC, 200);


        // Slider pour le volume des sons
        Label labelsound = createLabel("Volume des sons", 0, 0, 15);
        volumesound = createSlider(0, 100, GameConstants.SOUND,200);

        v2.getChildren().addAll(labelmusic, volumemusic, labelsound, volumesound);

        VBox v3 = new VBox();
        // Bouton pour configurer la touche gauche
        Label labelleft = createLabel("Touche pour aller a gauche", 0, 0, 15);
        buttonleft = createButton(GameConstants.LEFT.getName(), 0, 0);

        // Bouton pour configurer la touche droite
        Label labelright =createLabel("Touche pour aller a droite", 0, 0, 15);
        buttonright = createButton(GameConstants.RIGHT.getName(), 0, 0);

        // Bouton pour configure la touche pour activer le pouvoir
        Label labelpower=createLabel("Touche pour activer le pouvoir", 0, 0,15);
        buttonpower=createButton(GameConstants.SPACE.getName(), 0, 0);

        v3.getChildren().addAll(labelleft, buttonleft, labelright, buttonright, labelpower, buttonpower);

        v1.setAlignment(javafx.geometry.Pos.CENTER);
        v2.setAlignment(javafx.geometry.Pos.CENTER);
        v3.setAlignment(javafx.geometry.Pos.CENTER);
        v1.setSpacing(20);
        v2.setSpacing(20);
        v3.setSpacing(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(50);
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
