package gui.Menu.MenuViews;

import gui.App;
import gui.ImageLoader;
import gui.ViewPosition;
import gui.GraphicsToolkit.LabelButton;
import config.StageLevel;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.PauseController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class PauseView extends Pane implements Menu, ViewPosition {
    private Stage primaryStage;
    private Button btnReplay, btnMenu, btnQuit, btnResume;
    private AnimationTimer animationTimer;
    private Pane root, gameRoot;
    private Label pause;
    private StageLevel stageLevel;
    private LabelButton btnMuteSound, btnMuteMusic;
    private VBox vBox = new VBox();
    private Image muteImage = ImageLoader.loadImage("src/main/ressources/mute.png");
    private ImageView muteImageView = new ImageView(muteImage);
    private Image unmuteImage = ImageLoader.loadImage("src/main/ressources/unmute.png");
    private ImageView unmuteImageView = new ImageView(unmuteImage);

    private Image muteImage2 = ImageLoader.loadImage("src/main/ressources/mute.png");
    private ImageView muteImageView2 = new ImageView(muteImage2);
    private Image unmuteImage2 = ImageLoader.loadImage("src/main/ressources/unmute.png");
    private ImageView unmuteImageView2 = new ImageView(unmuteImage2);

    public PauseView(Stage p, Pane game, Pane gameRoot, AnimationTimer animationTimer, StageLevel stageLevel) {
        this.primaryStage = p;
        this.animationTimer = animationTimer;
        this.gameRoot = gameRoot;
        this.root = game;
        this.stageLevel = stageLevel;
        this.getStylesheets().add(GameConstants.CSS.getPath());
        this.getStyleClass().add("pause-view");
        pause = createLabel("Pause", 0, 0);
        pause.getStyleClass().add("title-game-over-style");
        initializeButtons();
        configureVBox();
        this.getChildren().addAll(vBox);
        this.getChildren().add(btnMuteMusic);
        this.getChildren().add(btnMuteSound);

        this.setLayoutX(game.getLayoutX());
        this.setLayoutY(game.getLayoutY());
        this.setPrefHeight(game.getHeight());
        this.setPrefWidth(game.getWidth());
        // Pour centrer la VBox
        vBox.setLayoutX((this.getPrefWidth() - vBox.getPrefWidth()) / 2);
        vBox.setLayoutY((this.getPrefHeight() - vBox.getPrefHeight()) / 2.5);
        saveViewPosition();
        new PauseController(p, this);
    }

    private void initializeButtons() {
        this.btnMenu = createButton("Menu", 0, 0);
        this.btnReplay = createButton("Rejouer", 0, 0);
        this.btnResume = createButton("Reprendre", 0, 0);
        this.btnQuit = createButton("Quitter", 0, 0);
        MusicSound();
    }

    private void configureVBox() {
        vBox.getChildren().addAll(pause, btnResume, btnReplay, btnMenu, btnQuit);
        vBox.setSpacing(20);
        vBox.setPrefWidth(200);
        vBox.setPrefHeight(200);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
    }

    private void MusicSound() {
        configureImageView(muteImageView);
        configureImageView(unmuteImageView);
        configureImageView(muteImageView2);
        configureImageView(unmuteImageView2);
        this.btnMuteMusic = new LabelButton("Musique:", null);
        if (App.music.isMute()) {
            btnMuteMusic.getButton().setGraphic(muteImageView);
        } else {
            btnMuteMusic.getButton().setGraphic(unmuteImageView);
        }
        this.btnMuteSound = new LabelButton("Son:", null);
        if (GameConstants.SOUND == 0) {
            btnMuteSound.getButton().setGraphic(muteImageView2);
        } else {
            btnMuteSound.getButton().setGraphic(unmuteImageView2);
        }
        btnMuteMusic.setLayoutX(850);
        btnMuteMusic.setLayoutY(10);
        btnMuteSound.setLayoutX(850);
        btnMuteSound.setLayoutY(100);
    }

    private void configureImageView(ImageView imageView) {
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }

    // Getters

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Button getBtnReplay() {
        return btnReplay;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnMenu() {
        return btnMenu;
    }

    public Button getBtnResume() {
        return btnResume;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public Pane getRoot() {
        return root;
    }

    public Pane getGameRoot() {
        return gameRoot;
    }

    public StageLevel getStageLevel() {
        return stageLevel;
    }

    public Button getBtnMuteMusic() {
        return btnMuteMusic.getButton();
    }

    public Button getBtnMuteSound() {
        return btnMuteSound.getButton();
    }

    public ImageView getMuteImageView() {
        return muteImageView;
    }

    public ImageView getUnmuteImageView() {
        return unmuteImageView;
    }

    public ImageView getMuteImageView2() {
        return muteImageView2;
    }

    public ImageView getUnmuteImageView2() {
        return unmuteImageView2;
    }
}
