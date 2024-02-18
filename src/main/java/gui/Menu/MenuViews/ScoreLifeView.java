package gui.Menu.MenuViews;


import config.Game;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ScoreLifeView extends Pane {
    private Text scoreText;
    private Text lifeText;
    private int score;
    private int life;
    private Game game;

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

    public void update() {
        score=game.getScore();
        life=game.getLife();
        scoreText.setText("Score: " + score);
        lifeText.setText("Life: " + life);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLife(int life) {
        this.life = life;
    }
}