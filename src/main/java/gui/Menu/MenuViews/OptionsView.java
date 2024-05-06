package gui.Menu.MenuViews;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.OptionsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.GraphicsToolkit.LabelButton;
import utils.GraphicsToolkit.LabelComboBoxHBox;
import utils.GraphicsToolkit.LabelSliderHBox;
import utils.GraphicsToolkit.LabelToggleButtonHBox;
import utils.GraphicsToolkit.LabelVBox;

/**
 * Classe OptionsView qui implémente l'interface Menu pour représenter la vue
 * des options.
 * 
 * @author Benmalek Majda
 * @author Bencheikh Ilias
 */
public class OptionsView implements Menu, ViewPosition {
    private Stage primaryStage;

    private BorderPane root;
    private VBox centerBox;
    private HBox bottomBox;
    private Scene scene;

    private HBox options;
    private VBox optionsLeft, optionsCentre, optionsRight;

    private LabelSliderHBox volumeMusic, volumeSound;
    private LabelComboBoxHBox theme;
    private LabelToggleButtonHBox fps, path, particles;
    private LabelButton buttonleft, buttonpower, buttonright;

    // private List<String> textureNames = loadTextureNames();
    // private LabelComboBoxHBox texture;
    // private ImageView textureImageView;

    // public final String TEXTURE_FOLDER = "src/main/ressources/Texture";

    private HBox actionButtons;
    private Button backButton;

    private ConsoleView consoleView;

    public OptionsView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new BorderPane();
        centerBox = new VBox(50);
        bottomBox = new HBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        initOptions();
        initActionButtons();

        centerBox.getChildren().addAll(options, actionButtons);
        centerBox.setPadding(new Insets(90, 0, 0, 0));

        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().add(consoleView);

        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        new OptionsController(primaryStage, this);
    }

    private void initOptions() {
        options = new HBox(50);
        options.setAlignment(Pos.CENTER);
        initOptionsLeft();
        initOptionsCentre();
        initOptionsRight();
        options.getChildren().addAll(optionsLeft, optionsCentre, optionsRight);
    }

    private void initOptionsLeft() {

        optionsLeft = new VBox(25);
        optionsLeft.setAlignment(Pos.CENTER);

        LabelVBox labelVBox = new LabelVBox("Musique & Themes", 12);
        volumeMusic = new LabelSliderHBox("Musique", 0, 100, 50, false, 1);
        volumeSound = new LabelSliderHBox("Sons", 0, 100, 50, false, 1);
        String[] themes = { "Classic", "Black", "Light", "Pink", "Achromatopsie", "Deuteranopie", "Protanopie",
                "Tritanopie" };
        String name = GameConstants.CSS.name();
        String capitalizedPath = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        theme = new LabelComboBoxHBox("Theme", themes, capitalizedPath);

        labelVBox.getChildren().addAll(volumeMusic, volumeSound, theme);
        optionsLeft.getChildren().addAll(labelVBox);
    }

    private void initOptionsCentre() {
        optionsCentre = new VBox(25);
        optionsCentre.setAlignment(Pos.CENTER);
        LabelVBox labelVBox = new LabelVBox("Afficage", 12);
        fps = new LabelToggleButtonHBox("FPS", GameConstants.FPS);
        path = new LabelToggleButtonHBox("Chemin de la balle", GameConstants.PATH);
        particles = new LabelToggleButtonHBox("Particules", GameConstants.PARTICLES);

        labelVBox.getChildren().addAll(fps, path, particles);
        optionsCentre.getChildren().addAll(labelVBox);
    }

    private void initOptionsRight() {
        optionsRight = new VBox(25);
        optionsRight.setAlignment(Pos.CENTER);
        LabelVBox labelVBox = new LabelVBox("Controles", 12);
        buttonleft = new LabelButton("Controle Gauche", GameConstants.LEFT.getName());
        buttonright = new LabelButton("Controle Droite", GameConstants.RIGHT.getName());
        buttonpower = new LabelButton("Controle Magnet", GameConstants.SPACE.getName());

        labelVBox.getChildren().addAll(buttonleft, buttonright, buttonpower);
        optionsRight.getChildren().addAll(labelVBox);
    }

    private void initActionButtons() {
        actionButtons = new HBox(50);
        actionButtons.setAlignment(Pos.CENTER);

        backButton = createButton("Retour", 0, 0);

        actionButtons.getChildren().add(backButton);
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBackButton() {
        return backButton;
    }

    public LabelSliderHBox getVolumeMusic() {
        return volumeMusic;
    }

    public LabelSliderHBox getVolumeSound() {
        return volumeSound;
    }

    public LabelComboBoxHBox getTheme() {
        return theme;
    }

    public LabelToggleButtonHBox getFps() {
        return fps;
    }

    public LabelToggleButtonHBox getPath() {
        return path;
    }

    public LabelToggleButtonHBox getParticles() {
        return particles;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public HBox getOptions() {
        return options;
    }

    public VBox getOptionsLeft() {
        return optionsLeft;
    }

    public VBox getOptionsCentre() {
        return optionsCentre;
    }

    public VBox getOptionsRight() {
        return optionsRight;
    }

    public HBox getActionButtons() {
        return actionButtons;
    }

    public LabelButton getButtonleft() {
        return buttonleft;
    }

    public Button getButtonright() {
        return buttonright.getButton();
    }

    public Button getButtonpower() {
        return buttonpower.getButton();
    }

    public ComboBox<String> getListTheme() {
        return theme.getComboBox();
    }

}
