package gui.Menu.MenuViews;

import gui.Menu.SceneManager;
import gui.Menu.MenuControllers.OptionsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe OptionsView qui implémente l'interface Menu pour représenter la vue
 * des options.
 * 
 * @author Benmalek Majda
 * @author Bencheikh Ilias
 */
public class OptionsView implements Menu {
    private Stage primaryStage;
    private static HBox root = new HBox();
    private static Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH,
            GameConstants.DEFAULT_WINDOW_HEIGHT);
    private SceneManager sceneManager;
    private Button btnBack;
    private ToggleButton buttonfps;
    private ToggleButton buttonpath;
    private ToggleButton buttonparticles;
    private Slider volumemusic;
    private Slider volumesound;
    private Button buttonleft;
    private Button buttonright;
    private Button buttonpower;
    private ComboBox<String> listTheme;

    /**
     * Constructeur de OptionsView.
     * 
     * @param p Le stage principal sur lequel la vue des options est affichée.
     */
    public OptionsView(Stage p, SceneManager sceneManager) {
        //super(p, scene, sceneManager);
        System.out.println("OptionsView CHECKED");
        this.primaryStage = p;
        this.sceneManager = sceneManager;
        btnBack = createButton("Retour", 0, 0);
        //root.getStyleClass().add("root");

        primaryStage.setScene(scene);

        // VBox 1: FPS, Chemain de la balle,Particules
        VBox v1 = new VBox();
        // Bouton pour afficher les FPS
        Label labelfps = createLabel("Afficher les FPS", 0, 0);
        buttonfps = createToggleButton("OFF", false);

        // Bouton pour afficher le chemin de la balle
        Label labelpath = createLabel("Afficher le chemin de la balle", 0, 0);
        buttonpath = createToggleButton("OFF", false);

        // Bouton pour afficher les trainées de particules
        Label labelparticles = createLabel("Afficher les particules", 0, 0);
        buttonparticles = createToggleButton("ON", false);

        v1.getChildren().addAll(labelfps, buttonfps, labelpath, buttonpath, labelparticles, buttonparticles);

        // VBox 2: Volume de la musique, Volume des sons
        VBox v2 = new VBox();
        // Slider pour le volume de la musique
        Label labelmusic = createLabel("Volume de la musique", 0, 0);
        volumemusic = createSlider(0, 100, GameConstants.MUSIC, 200);

        // Slider pour le volume des sons
        Label labelsound = createLabel("Volume des sons", 0, 0);
        volumesound = createSlider(0, 100, GameConstants.SOUND, 200);

        Label labeltheme = createLabel("Themes: ", 0, 0);
        listTheme = new ComboBox<String>();
        listTheme.setPromptText("Choisir un theme");
        listTheme.getItems().addAll("blue", "pink", "light");

        v2.getChildren().addAll(labelmusic, volumemusic, labelsound, volumesound, labeltheme, listTheme);

        // VBox 3: Modif des : Touche gauche, Touche droite, Touche pour activer le
        // pouvoir
        VBox v3 = new VBox();
        // Bouton pour configurer la touche gauche
        Label labelleft = createLabel("Touche pour aller a gauche", 0, 0);
        buttonleft = createButton(GameConstants.LEFT.getName(), 0, 0);

        // Bouton pour configurer la touche droite
        Label labelright = createLabel("Touche pour aller a droite", 0, 0);
        buttonright = createButton(GameConstants.RIGHT.getName(), 0, 0);

        // Bouton pour configure la touche pour activer le pouvoir
        Label labelpower = createLabel("Touche pour activer le pouvoir", 0, 0);
        buttonpower = createButton(GameConstants.SPACE.getName(), 0, 0);

        v3.getChildren().addAll(labelleft, buttonleft, labelright, buttonright, labelpower, buttonpower);

        // Ajout des VBox à la racine et mise en forme
        v1.setAlignment(javafx.geometry.Pos.CENTER);
        v2.setAlignment(javafx.geometry.Pos.CENTER);
        v3.setAlignment(javafx.geometry.Pos.CENTER);
        v1.setSpacing(20);
        v2.setSpacing(20);
        v3.setSpacing(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(50);
        root.getChildren().addAll(v1, v2, v3, btnBack);
        new OptionsController(p, this);
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

    public ComboBox<String> getListTheme() {
        return listTheme;
    }
    public SceneManager getSceneManager() {
        return sceneManager;
    }
}