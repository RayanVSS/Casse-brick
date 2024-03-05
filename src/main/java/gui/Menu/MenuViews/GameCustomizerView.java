package gui.Menu.MenuViews;

import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameCustomizerController;
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
    private ToggleButton ruleLimitedTime, ruleLimitedBounces, ruleRandomSwitchBricks, ruleColorRestricted,
            ruleTransparent, ruleUnbreakable;

    private HBox actionButtons;
    private Button createGame, backButton;

    public GameCustomizerView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new VBox(35);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        initOptionsBox();
        initSliders();
        initComboBox();
        initToggleButtons();
        initActionButtons();
        initActionButtons();
        root.getChildren().addAll(configOptionsBox, actionButtons);

        new GameCustomizerController(this);
    }

    private void initOptionsBox() {
        configOptionsBox = new HBox(55);
        optionsBoxLeft = new VBox(25);
        optionsBoxRight = new VBox(25);
        configOptionsBox.getChildren().addAll(optionsBoxLeft, optionsBoxRight);
    }

    private void initSliders() {
        life = new Slider();
        // life.setMin(0);
        // life.setMax(100);
        // life.setValue(50);
        // // life.setShowTickLabels(true);
        // // life.setShowTickMarks(true);
        // // life.setMajorTickUnit(20);
        // // life.setMinorTickCount(5);
        // life.setBlockIncrement(10);
        // optionsBoxLeft.getChildren().addAll(life);

        mapWidht = new Slider();

        mapHeight = new Slider();

        ballSize = new Slider();

        ballSpeed = new Slider();

        timeLimit = new Slider();

        bouncesLimit = new Slider();
    }

    private void initComboBox() {
        ball = new ComboBox<>();
        racket = new ComboBox<>();

    }

    private void initToggleButtons() {
        ruleLimitedTime = new ToggleButton("Limited Time");

        ruleLimitedBounces = new ToggleButton("Limited Bounces");

        ruleRandomSwitchBricks = new ToggleButton("Random Switch Bricks");

        ruleColorRestricted = new ToggleButton("Color Restricted");

        ruleTransparent = new ToggleButton("Transparent");

        ruleUnbreakable = new ToggleButton("Unbreakable");

    }

    private void initActionButtons() {
        actionButtons = new HBox(35);
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

    public ToggleButton getRuleLimitedTime() {
        return ruleLimitedTime;
    }

    public ToggleButton getRuleLimitedBounces() {
        return ruleLimitedBounces;
    }

    public ToggleButton getRuleRandomSwitchBricks() {
        return ruleRandomSwitchBricks;
    }

    public ToggleButton getRuleColorRestricted() {
        return ruleColorRestricted;
    }

    public ToggleButton getRuleTransparent() {
        return ruleTransparent;
    }

    public ToggleButton getRuleUnbreakable() {
        return ruleUnbreakable;
    }

    public Button getCreateGame() {
        return createGame;
    }

}
