package gui.Menu.MenuViews;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.GameCustomizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.GraphicsToolkit.LabelComboBoxHBox;
import utils.GraphicsToolkit.LabelSliderHBox;
import utils.GraphicsToolkit.LabelToggleButtonHBox;
import utils.GraphicsToolkit.LabelVBox;

public class GameCustomizerView implements Menu, ViewPosition {

    private Stage primaryStage;
    private BorderPane root;
    private VBox centerBox;
    private HBox bottomBox;
    private Scene scene;
    private HBox configOptionsBox;
    private VBox optionsBoxLeft, optionsBoxCenter, optionsBoxRight;

    private LabelSliderHBox life, mapWidht, mapHeight, ballSize, ballSpeed, timeLimit, bouncesLimit;
    private LabelComboBoxHBox ball, racket;
    private LabelToggleButtonHBox ruleLimitedTime, ruleLimitedBounces, ruleRandomSwitchBricks, ruleColorRestricted,
            ruleTransparent, ruleUnbreakable,ruleInfinite;

    private HBox actionButtons;
    private Button createGame, backButton;

    private ConsoleView consoleView;

    public GameCustomizerView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new BorderPane();
        centerBox = new VBox(40);
        bottomBox = new HBox();
        consoleView = ConsoleView.getInstance();

        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        initOptionsBox();
        initActionButtons();
        addComponents();
        localStyle();

        new GameCustomizerController(this);
    }

    private void addComponents() {
        centerBox.getChildren().addAll(configOptionsBox, actionButtons);
        bottomBox.getChildren().addAll(consoleView);
        root.setCenter(centerBox);
        root.setBottom(bottomBox);
        configOptionsBox.getChildren().addAll(optionsBoxLeft, optionsBoxCenter, optionsBoxRight);
    }

    private void initOptionsBox() {
        configOptionsBox = new HBox(30);
        configOptionsBox.setAlignment(Pos.CENTER);
        initOptionsBoxLeft();
        initOptionsBoxCenter();
        initOptionsBoxRight();
    }

    private void initOptionsBoxLeft() {

        optionsBoxLeft = new VBox(15);
        optionsBoxLeft.setAlignment(Pos.CENTER);

        LabelVBox timeVBox = new LabelVBox("Time", 3);
        ruleLimitedTime = new LabelToggleButtonHBox("Temps limité", false);
        timeLimit = new LabelSliderHBox("Temps (secondes)", 1, 1800, 300, true, 1); //en secondes
        timeVBox.getChildren().addAll(ruleLimitedTime, timeLimit);

        LabelVBox bouncesVBox = new LabelVBox("Bounces", 3);
        ruleLimitedBounces = new LabelToggleButtonHBox("Rebonds limités", false);
        bouncesLimit = new LabelSliderHBox("Rebonds", 1, 150, 50, true, 1);
        bouncesVBox.getChildren().addAll(ruleLimitedBounces, bouncesLimit);

        optionsBoxLeft.getChildren().addAll(timeVBox, bouncesVBox);
    }

    private void initOptionsBoxCenter() {

        optionsBoxCenter = new VBox(25);
        optionsBoxCenter.setAlignment(Pos.CENTER);

        life = new LabelSliderHBox("Nombre de vies", 1, 9, 3, false, 1);

        LabelVBox mapVBox = new LabelVBox("Map", 5);
        mapHeight = new LabelSliderHBox("Lignes de briques", 1, 12, 5, false, 1);
        mapWidht = new LabelSliderHBox("Colonnes de briques", 1, 13, 13, false, 1);
        ruleInfinite = new LabelToggleButtonHBox("Mode infini", false);
        mapVBox.getChildren().addAll(mapHeight, mapWidht,ruleInfinite);

        LabelVBox ballVBox = new LabelVBox("Ball", 5);
        ball = new LabelComboBoxHBox("Type de balle",
                new String[] { "Classic", "Gravity", "Hyper", "Magnet" }, "Classic");
        ballSize = new LabelSliderHBox("Taille de la balle", 1, 20, GameConstants.DEFAULT_BALL_RADIUS, false, 1);
        ballSpeed = new LabelSliderHBox("Vitesse de la balle", 1, 20, 5, false, 1);
        ballVBox.getChildren().addAll(ball, ballSize, ballSpeed);

        racket = new LabelComboBoxHBox("Type de raquette", new String[] { "Classic", "Magnet", "YNotFixe" }, "Classic");

        optionsBoxCenter.getChildren().addAll(life, mapVBox, ballVBox, racket);
    }

    private void initOptionsBoxRight() {

        optionsBoxRight = new VBox(15);
        optionsBoxRight.setAlignment(Pos.CENTER);

        LabelVBox eventVBox = new LabelVBox("Event", 3);
        ruleRandomSwitchBricks = new LabelToggleButtonHBox("Swicth Aléatoire", false);
        ruleColorRestricted = new LabelToggleButtonHBox("Restriction de Couleur", false);
        ruleTransparent = new LabelToggleButtonHBox("Briques Fantôme", false);
        ruleUnbreakable = new LabelToggleButtonHBox("Briques Incassables", false);
        eventVBox.getChildren().addAll(ruleRandomSwitchBricks, ruleColorRestricted, ruleTransparent, ruleUnbreakable);

        optionsBoxRight.getChildren().addAll(eventVBox);
    }

    private void initActionButtons() {
        actionButtons = new HBox(35);
        actionButtons.setAlignment(Pos.CENTER);
        backButton = createButton("Retour", 0, 0);
        createGame = createButton("Créer", 0, 0);
        actionButtons.getChildren().addAll(createGame, backButton);
    }

    // Fonction de correction de style sur les tailles (par-dessus le CSS)
    private void localStyle() {
        optionsBoxLeft.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size: 16px;"));
        optionsBoxCenter.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size: 15px;"));
        optionsBoxRight.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size: 16px;"));
        actionButtons.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size: 20px;"));
        centerBox.setPadding(new Insets(36, 0, 0, 0));
        actionButtons.setPadding(new Insets(0, 0, 0, 120));
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
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

    public LabelSliderHBox getLife() {
        return life;
    }

    public LabelSliderHBox getMapWidht() {
        return mapWidht;
    }

    public LabelSliderHBox getMapHeight() {
        return mapHeight;
    }

    public LabelSliderHBox getBallSize() {
        return ballSize;
    }

    public LabelSliderHBox getBallSpeed() {
        return ballSpeed;
    }

    public LabelSliderHBox getTimeLimit() {
        return timeLimit;
    }

    public LabelSliderHBox getBouncesLimit() {
        return bouncesLimit;
    }

    public LabelComboBoxHBox getBall() {
        return ball;
    }

    public LabelComboBoxHBox getRacket() {
        return racket;
    }

    public Button getCreateGame() {
        return createGame;
    }

    public LabelToggleButtonHBox getRuleLimitedTime() {
        return ruleLimitedTime;
    }

    public LabelToggleButtonHBox getRuleLimitedBounces() {
        return ruleLimitedBounces;
    }

    public LabelToggleButtonHBox getRuleRandomSwitchBricks() {
        return ruleRandomSwitchBricks;
    }

    public LabelToggleButtonHBox getRuleColorRestricted() {
        return ruleColorRestricted;
    }

    public LabelToggleButtonHBox getRuleTransparent() {
        return ruleTransparent;
    }

    public LabelToggleButtonHBox getRuleUnbreakable() {
        return ruleUnbreakable;
    }

    public LabelToggleButtonHBox getRuleInfinite() {
        return ruleInfinite;
    }

}
