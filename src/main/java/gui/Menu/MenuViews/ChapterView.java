package gui.Menu.MenuViews;

import gui.Menu.Menu;
import gui.Menu.MenuControllers.ChapterController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import gui.ImageLoader;

public class ChapterView implements Menu {
    private Stage primaryStage;
    private VBox root;
    private Scene scene;

    private Button chapter1;
    private ImageView chapter1Image;

    private Button chapter2;
    private ImageView chapter2Image;

    private Button chapter3;
    private ImageView chapter3Image;

    private Button backButton;

    public ChapterView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new VBox(45);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);// 1100, 800

        // Charger les images
        Image image1 = ImageLoader.loadImage("src/main/ressources/chapitre/chapitre1.png");
        Image image2 = ImageLoader.loadImage("src/main/ressources/chapitre/chapitre2.png");
        Image image3 = ImageLoader.loadImage("src/main/ressources/chapitre/chapitre3.png");

        backButton = createButton("Retour", 0, 0);

        // Initialiser les boutons et les images
        chapter1 = new Button();
        chapter1Image = new ImageView(image1);
        chapter1Image.setFitWidth(900);
        chapter1Image.setFitHeight(150);
        chapter1.setGraphic(chapter1Image);
        chapter1.setStyle("-fx-background-color: transparent;");
        chapter1.setPrefWidth(900);
        chapter1.setPrefHeight(150);

        chapter2 = new Button();
        chapter2Image = new ImageView(image2);
        chapter2Image.setFitWidth(900);
        chapter2Image.setFitHeight(150);
        chapter2.setGraphic(chapter2Image);
        chapter2.setStyle("-fx-background-color: transparent;");
        chapter2.setPrefWidth(900);
        chapter2.setPrefHeight(150);

        chapter3 = new Button();
        chapter3Image = new ImageView(image3);
        chapter3Image.setFitWidth(900);
        chapter3Image.setFitHeight(150);
        chapter3.setGraphic(chapter3Image);
        chapter3.setStyle("-fx-background-color: transparent;");
        chapter3.setPrefWidth(900);
        chapter3.setPrefHeight(150);

        String hoverStyle = "-fx-background-color: #FFFFFF; -fx-opacity: 0.5;";
        chapter1.setOnMouseEntered(e -> chapter1.setStyle(hoverStyle));
        chapter1.setOnMouseExited(e -> chapter1.setStyle("-fx-background-color: transparent;"));
        chapter2.setOnMouseEntered(e -> chapter2.setStyle(hoverStyle));
        chapter2.setOnMouseExited(e -> chapter2.setStyle("-fx-background-color: transparent;"));
        chapter3.setOnMouseEntered(e -> chapter3.setStyle(hoverStyle));
        chapter3.setOnMouseExited(e -> chapter3.setStyle("-fx-background-color: transparent;"));

        // Ajouter les boutons à la racine

        // Ajouter les boutons à la racine
        root.getChildren().addAll(chapter1, chapter2, chapter3, backButton);
        new ChapterController(this);
    }

    // Getters
    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Button getChapter1() {
        return chapter1;
    }

    public Button getChapter2() {
        return chapter2;
    }

    public Button getChapter3() {
        return chapter3;
    }

    public Button getBackButton() {
        return backButton;
    }

}
