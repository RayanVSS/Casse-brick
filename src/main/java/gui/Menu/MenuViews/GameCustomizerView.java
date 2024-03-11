package gui.Menu.MenuViews;

import gui.GraphicsToolkit.LabelToggleButtonVBox;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameCustomizerController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class GameCustomizerView implements Menu {

    private Stage primaryStage;
    private VBox root;
    private Scene scene;
    private HBox configOptionsBox;
    private VBox optionsBoxLeft, optionsBoxRight;

    private Slider life, mapWidht, mapHeight, ballSize, ballSpeed, timeLimit, bouncesLimit;
    private ComboBox<String> ball, racket;
    private LabelToggleButtonVBox ruleLimitedTime, ruleLimitedBounces, ruleRandomSwitchBricks, ruleColorRestricted,
            ruleTransparent, ruleUnbreakable;

    private HBox actionButtons;
    private Button createGame, backButton;

    public GameCustomizerView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new VBox(35);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        initOptionsBox();
        initActionButtons();

        root.getChildren().addAll(configOptionsBox, actionButtons);

        new GameCustomizerController(this);
    }

    private void initOptionsBox() {
        configOptionsBox = new HBox(55);
        configOptionsBox.setAlignment(Pos.CENTER);
        initOptionsBoxLeft();
        initOptionsBoxRight();
        configOptionsBox.getChildren().addAll(optionsBoxLeft, optionsBoxRight);
    }

    private void initOptionsBoxLeft() {
        optionsBoxLeft = new VBox(25);
        optionsBoxLeft.getChildren().addAll();
    }

    private void initOptionsBoxRight() {

        optionsBoxRight = new VBox(25);

        ruleLimitedTime = new LabelToggleButtonVBox("Temps limité", false);
        ruleLimitedBounces = new LabelToggleButtonVBox("Rebonds limités", false);
        ruleRandomSwitchBricks = new LabelToggleButtonVBox("Swicth Aléatoire", false);
        ruleColorRestricted = new LabelToggleButtonVBox("Restriction de Couleur", false);
        ruleTransparent = new LabelToggleButtonVBox("Briques Fantôme", false);
        ruleUnbreakable = new LabelToggleButtonVBox("Briques Incassables", false);

        optionsBoxRight.getChildren().addAll(ruleLimitedTime, ruleLimitedBounces, ruleRandomSwitchBricks,
                ruleColorRestricted, ruleTransparent, ruleUnbreakable);
    }

    private void initActionButtons() {
        actionButtons = new HBox(35);
        actionButtons.setAlignment(Pos.CENTER);
        backButton = createButton("Retour", 0, 0);
        createGame = createButton("Créer", 0, 0);
        actionButtons.getChildren().addAll(createGame, backButton);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Slider getLife() {
        return life;
    }

    public Slider getMapWidht() {
        return mapWidht;
    }

    public Slider getMapHeight() {
        return mapHeight;
    }

    public Slider getBallSize() {
        return ballSize;
    }

    public Slider getBallSpeed() {
        return ballSpeed;
    }

    public Slider getTimeLimit() {
        return timeLimit;
    }

    public Slider getBouncesLimit() {
        return bouncesLimit;
    }

    public ComboBox<String> getBall() {
        return ball;
    }

    public ComboBox<String> getRacket() {
        return racket;
    }

    public Button getCreateGame() {
        return createGame;
    }

    public LabelToggleButtonVBox getRuleLimitedTime() {
        return ruleLimitedTime;
    }

    public LabelToggleButtonVBox getRuleLimitedBounces() {
        return ruleLimitedBounces;
    }

    public LabelToggleButtonVBox getRuleRandomSwitchBricks() {
        return ruleRandomSwitchBricks;
    }

    public LabelToggleButtonVBox getRuleColorRestricted() {
        return ruleColorRestricted;
    }

    public LabelToggleButtonVBox getRuleTransparent() {
        return ruleTransparent;
    }

    public LabelToggleButtonVBox getRuleUnbreakable() {
        return ruleUnbreakable;
    }

}
