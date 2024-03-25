package gui.GraphicsFactory;

import config.Game;
import config.StageLevel;
import gui.Menu.Menu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameConstants;
import utils.ImageCache;

/**
 * Classe ScoreLifeView qui étend Pane pour afficher le score et la vie dans le
 * jeu.
 * Elle est mise à jour à chaque frame pour afficher le score et la vie actuels.
 * 
 * @author Benmalek Majda
 */
public class ScoreLifeGraphics extends VBox implements Menu {
    private Text scoreText;
    private int score;
    private int life;
    private GridPane lifeGrid;
    private Image lifeOK ;
    private Image lifeKO ;
    private ImageView[] lifeImages;
    private Label niveau;
    private Game game;
    private Label maxScore;
    private Label time;
    private Label bounces;

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
        lifeGrid = new GridPane();
        lifeGrid.setHgap(10);
        lifeGrid.setVgap(10);   
        lifeImages = new ImageView[life];
        System.out.println("life: "+life);
    
        lifeOK = ImageCache.getImage(getLifeOkImagePath());
        lifeKO = ImageCache.getImage(getLifeKoImagePath());
        initializeLifeImages();
        //lifeBox.getChildren().addAll(lifeImages);
        scoreText = new Text("Score: " + score);
        scoreText.setX(20);
        scoreText.setY(80);
        scoreText.getStyleClass().add("scoreL-style");

        niveau=createLabel("Niveau: "+(stage.getDifficulty()+1), 0, 0);
        niveau.getStyleClass().add("scoreL-style");
        niveau.setLayoutX(20);
        niveau.setLayoutY(100);

        maxScore=createLabel("Meilleur score: "+stage.getMaxScore(), 0, 0);
        maxScore.getStyleClass().add("scoreL-style");
        maxScore.setLayoutX(20);
        maxScore.setLayoutY(120);

        if(game.getRules().isLimitedTime()){
            time=createLabel("Temps restant: "+game.getRules().getRemainingTime(), 0, 0);
            time.setVisible(true);
            time.getStyleClass().add("scoreL-style");
            time.setLayoutX(20);
            time.setLayoutY(140);
            setMargin(time, new javafx.geometry.Insets(10, 10, 10, 10));
            getChildren().add(time);
        }

        if (game.getRules().isLimitedBounces()){
            //TODO: changer la taille de la police dans tout les fichiers css
            bounces=createLabel("Rebonds restants: "+game.getRules().getRemainingBounces(), 0, 0);
            bounces.setVisible(true);
            bounces.getStyleClass().add("scoreL-style");
            bounces.setLayoutX(20);
            bounces.setLayoutY(160);
            setMargin(bounces, new javafx.geometry.Insets(10, 10, 10, 10));
            getChildren().add(bounces);
        }

        setMargin(lifeGrid, new javafx.geometry.Insets(10, 10, 10, 10));
        setMargin(scoreText, new javafx.geometry.Insets(10, 10, 10, 10));
        setMargin(niveau, new javafx.geometry.Insets(10, 10, 10, 10));
        setMargin(maxScore, new javafx.geometry.Insets(10, 10, 10, 10));
        
        setLayoutX(10);
        setLayoutY(10);
        getChildren().add(lifeGrid);
        getChildren().add(scoreText);
        getChildren().add(niveau);
        getChildren().add(maxScore);
        getStylesheets().add(GameConstants.CSS.getPath());

    }

    private String getLifeOkImagePath() {
        switch (GameConstants.CSS) {
            case PINK:
                return "/lifeScore/lifeOKp.png";
            case DARK:
            case BLACK:
            case LIGHT:
                return "/lifeScore/lifeBlack.png";
            case ACHROMATOPSIE:
                return "/lifeScore/lifeAchromatopsie+.png";
            case DEUTERANOPIE:
                return "/lifeScore/lifeDeuteranopie+.png";
            case TRITANOPIE:
                return "/lifeScore/lifeTritanopie+.png";
            case PROTANOPIE:
                return "/lifeScore/lifeProtanopie+.png";
            default:
                return null;
        }
    }

    private String getLifeKoImagePath() {
        switch (GameConstants.CSS) {
            case PINK:
                return "/lifeScore/lifeKOp.png";
            case DARK:
            case BLACK:
            case LIGHT:
                return "/lifeScore/lifeKO.png";
            case ACHROMATOPSIE:
                return "/lifeScore/lifeAchromatopsie-.png";
            case DEUTERANOPIE:
                return "/lifeScore/lifeDeuteranopie-.png";
            case TRITANOPIE:
                return "/lifeScore/lifeTritanopie-.png";
            case PROTANOPIE:
                return "/lifeScore/lifeProtanopie-.png";
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
        scoreText.setText("Score: " + score);
        if (game.getRules().isLimitedTime()) {
            time.setText("Temps restant: "+game.getRules().getRemainingTime());
        }
        if (game.getRules().isLimitedBounces()) {
            bounces.setText("Rebonds restants: "+game.getRules().getRemainingBounces());
        }
        for (int i = 0; i < lifeImages.length; i++) {
            Image newImage = i < life ? lifeOK : lifeKO;
            if (lifeImages[i].getImage() != newImage) {
                lifeImages[i].setImage(newImage);
            }
        }
    }
}