package gui.GraphicsFactory;

import config.Game;
import config.StageLevel;
import gui.Menu.Menu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.GameConstants;

/**
 * Classe ScoreLifeView qui étend Pane pour afficher le score et la vie dans le
 * jeu.
 * Elle est mise à jour à chaque frame pour afficher le score et la vie actuels.
 * 
 * @author Benmalek Majda
 */
public class ScoreLifeGraphics extends Pane implements Menu {
    private Text scoreText;
    private int score;
    private int life;
    private StageLevel stage;
    private HBox lifeBox;
    private Image lifeOK ;
    private Image lifeKO ;
    private ImageView lifeImage1  ;
    private ImageView lifeImage2;
    private ImageView lifeImage3 ;
    private Label niveau;
    private Game game;
    private Label hiScore;

    /**
     * Constructeur de ScoreLifeView.
     * 
     * @param game L'instance de Game à partir de laquelle obtenir le score et la
     *             vie.
     */
    public ScoreLifeGraphics(StageLevel stage) {
        this.stage = stage;
        this.game = stage.getGame();
        score = game.getScore();
        life = game.getLife();
        lifeBox = new HBox();
    
        lifeOK = initializeLifeImage();
        lifeKO = new Image("/lifeScore/lifeKO.png");
    
        lifeImage1 = initializeLifeImageView();
        lifeImage2 = initializeLifeImageView();
        lifeImage3 = initializeLifeImageView();
    
        lifeBox.getChildren().addAll(lifeImage1, lifeImage2, lifeImage3);
    
        scoreText = new Text("Score: " + score);
        scoreText.setX(20);
        scoreText.setY(80);
        scoreText.getStyleClass().add("scoreL-style");

        niveau=createLabel("Niveau: "+(stage.getDifficulty()+1), 0, 0);
        niveau.getStyleClass().add("scoreL-style");
        niveau.setLayoutX(20);
        niveau.setLayoutY(100);

        // hiScore = createLabel("High Score :", 0, 0);
        // hiScore.getStyleClass().add("scoreL-style");
        // hiScore.setLayoutX(20);
        // hiScore.setLayoutY(160);
        
        setLayoutX(10);
        setLayoutY(10);
        getChildren().add(lifeBox);
        getChildren().add(scoreText);
        getChildren().add(niveau);
        //getChildren().add(hiScore);
        getStylesheets().add(GameConstants.CSS.getPath());

        //ajout de la séparation
    }

    private Image initializeLifeImage() {
        switch (GameConstants.CSS) {
            case PINK:
                return new Image("/lifeScore/lifePink.png");
            case DARK:
                return new Image("/lifeScore/lifeBlack.png");
            case BLACK:
                return new Image("/lifeScore/lifeBlack.png");
            case LIGHT:
                return new Image("/lifeScore/lifeBlack.png");
            case ACHROMATOPSIE:
                return new Image("/lifeScore/lifeAchromatopsie+.png");
            case DEUTERANOPIE:
                return new Image("/lifeScore/lifeDeuteranopie+.png");
            case TRITANOPIE:
                return new Image("/lifeScore/lifeTritanopie+.png");
            case PROTANOPIE:
                return new Image("/lifeScore/lifeProtanopie+.png");
            default:
                return null;
        }
    }

    private ImageView initializeLifeImageView() {
        ImageView imageView = new ImageView(lifeOK);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setSmooth(true);
        imageView.setCache(true);
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
        switch (life) {
            case 3:
                lifeImage1.setImage(lifeOK);
                lifeImage2.setImage(lifeOK);
                lifeImage3.setImage(lifeOK);
                break;
            case 2:
                lifeImage3.setImage(lifeKO);
                break;
            case 1:
                lifeImage2.setImage(lifeKO);
                break;
            case 0:
                lifeImage3.setImage(lifeKO);
                break;
        }
    }
}