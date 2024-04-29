package gui.GraphicsFactory;

import config.Game;
import config.GameRules;
import config.StageLevel;
import gui.ImageLoader;
import gui.Menu.Menu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import utils.GameConstants;

/**
 * Classe ScoreLifeView qui étend Pane pour afficher le score et la vie dans le
 * jeu.
 * Elle est mise à jour à chaque frame pour afficher le score et la vie actuels.
 * 
 * @author Benmalek Majda
 */
public class ScoreLifeGraphics extends VBox implements Menu {
    private int score, life;
    private GridPane lifeGrid;
    private Image lifeOK, lifeKO;
    private ImageView[] lifeImages;
    private Game game;
    private Label maxScore, time, bounces, niveau, scoreLabel;
    private GameRules rules;

    /**
     * Constructeur de ScoreLifeView.
     * 
     * @param game L'instance de Game à partir de laquelle obtenir le score et la
     *             vie.
     */
    public ScoreLifeGraphics(Game game, StageLevel stage) {
        this.game = game;
        score = game.getScore();
        life = game.getLife();
        rules = game.getRules();
        lifeGrid = new GridPane();
        lifeGrid.setHgap(10);
        lifeGrid.setVgap(10);
        lifeImages = new ImageView[life];

        lifeOK = ImageLoader.loadImage(GameConstants.BALL_PATH);
        lifeKO = ImageLoader.loadImage(getLifeKoImagePath());

        initializeLifeImages();
        scoreLabel = createLabel("Score: " + score);

        maxScore = createLabel("Meilleur score: " + stage.getMaxScore());
        if (!stage.isCustomGame()) {
            niveau = createLabel("Niveau: " + (stage.getDifficulty() + 1));
            niveau.setVisible(true);
        }

        if (rules.isLimitedTime()) {
            time = createLabel("Temps restant: " + rules.getRemainingTime());
            time.setVisible(true);
        }

        if (rules.isLimitedBounces()) {
            bounces = createLabel("Rebonds restants: " + rules.getRemainingBounces());
            bounces.setVisible(true);
        }

        setMargin(lifeGrid, new javafx.geometry.Insets(10, 10, 10, 10));

        setLayoutX(10);
        setLayoutY(10);
        getChildren().add(lifeGrid);
        getChildren().add(scoreLabel);
        getChildren().add(maxScore);
        if (!stage.isCustomGame()) {
            getChildren().add(niveau);
        }
        if (rules.isLimitedTime()) {
            getChildren().add(time);
        }
        if (rules.isLimitedBounces()) {
            getChildren().add(bounces);
        }

        getStylesheets().add(GameConstants.CSS.getPath());

    }

    private String getLifeKoImagePath() {
        switch (GameConstants.CSS) {
            case PINK:
                return "src/main/ressources/lifeScore/lifeKOp.png";
            case BLACK:
                return "src/main/ressources/lifeScore/lifeKOw.png";
            case CLASSIC:
            case LIGHT:
            case ACHROMATOPSIE:
            case DEUTERANOPIE:
            case TRITANOPIE:
            case PROTANOPIE:
                return "src/main/ressources/lifeScore/lifeKOb.png";
            default:
                return null;
        }
    }

    private void initializeLifeImages() {
        for (int i = 0; i < lifeImages.length; i++) {
            lifeImages[i] = initializeLifeImageView();
            lifeGrid.add(lifeImages[i], i % 3, i / 3);
        }
    }

    private ImageView initializeLifeImageView() {
        ImageView imageView = new ImageView(lifeOK);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        imageView.smoothProperty().set(true);
        return imageView;
    }

    /**
     * Méthode pour mettre à jour le score et la vie affichés.
     */
    public void update() {
        score = game.getScore();
        life = game.getLife();
        scoreLabel.setText("Score: " + score);
        if (rules.isLimitedTime()) {
            time.setText("Temps restant: " + rules.getRemainingTime());
        }
        if (rules.isLimitedBounces()) {
            bounces.setText("Rebonds restants: " + rules.getRemainingBounces());
        }
        for (int i = 0; i < lifeImages.length; i++) {
            Image newImage = i < life ? lifeOK : lifeKO;
            if (lifeImages[i].getImage() != newImage) {
                lifeImages[i].setImage(newImage);
            }
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("scoreL-style");
        setMargin(label, new javafx.geometry.Insets(10, 10, 10, 10));
        label.setLayoutX(20);
        return label;
    }
}