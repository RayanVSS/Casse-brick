package gui.Menu.MenuViews;

import config.Game;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Classe ScoreLifeView qui étend Pane pour afficher le score et la vie dans le
 * jeu.
 * Elle est mise à jour à chaque frame pour afficher le score et la vie actuels.
 * 
 * @author Benmalek Majda
 */
public class ScoreLifeView extends Pane {
    private Text scoreText;
    private Text lifeText;
    private int score;
    private int life;
    private Game game;

    /**
     * Constructeur de ScoreLifeView.
     * 
     * @param game L'instance de Game à partir de laquelle obtenir le score et la
     *             vie.
     */
    public ScoreLifeView(Game game) {
        this.game = game;
        score = game.getScore();
        life = game.getLife();
        scoreText = new Text("Score: " + score);
        lifeText = new Text("Life: " + life);
        scoreText.setX(10);
        scoreText.setY(20);
        lifeText.setX(10);
        lifeText.setY(40);
        scoreText.setStyle("-fx-font-size: 20; -fx-fill: #d5bbb1;");
        lifeText.setStyle("-fx-font-size: 20; -fx-fill: #d5bbb1;");
        setLayoutX(10);
        setLayoutY(10);
        getChildren().add(scoreText);
        getChildren().add(lifeText);
    }

    /**
     * Méthode pour mettre à jour le score et la vie affichés.
     */
    public void update() {
        score = game.getScore();
        life = game.getLife();
        scoreText.setText("Score: " + score);
        lifeText.setText("Life: " + life);
    }

    /**
     * Méthode pour définir le score.
     * 
     * @param score Le nouveau score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Méthode pour définir la vie.
     * 
     * @param life La nouvelle vie.
     */
    public void setLife(int life) {
        this.life = life;
    }
}