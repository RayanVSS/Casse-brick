package gui.Menu.MenuViews;

import gui.Menu.Menu;
import gui.Menu.MenuControllers.ChapterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;
import utils.ImageLoader;
import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;

public class ChapterView implements Menu, ViewPosition {
    private Stage primaryStage;
    private BorderPane root;
    private VBox centerBox;
    private HBox bottomBox;
    private Scene scene;

    private Button chapter1;
    private ImageView chapter1Image;

    private Button chapter2;
    private ImageView chapter2Image;

    private Button chapter3;
    private ImageView chapter3Image;

    private Button backButton;

    private ConsoleView consoleView;

    public ChapterView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new BorderPane();
        centerBox = new VBox(30);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);// 1100, 800

        // Charger les images
        Image image1 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre1.png");
        Image image2 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre2.png");
        Image image3 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre3.png");

        backButton = createButton("Retour", 0, 0);

        // Initialiser les boutons et les images
        chapter1 = new Button();
        chapter1Image = new ImageView(image1);
        chapter1Image.setFitWidth(800);
        chapter1Image.setFitHeight(120);
        chapter1.setGraphic(chapter1Image);
        chapter1.setStyle("-fx-background-color: transparent;");
        chapter1.setPrefWidth(800);
        chapter1.setPrefHeight(120);

        chapter2 = new Button();
        chapter2Image = new ImageView(image2);
        chapter2Image.setFitWidth(800);
        chapter2Image.setFitHeight(120);
        chapter2.setGraphic(chapter2Image);
        chapter2.setStyle("-fx-background-color: transparent;");
        chapter2.setPrefWidth(800);
        chapter2.setPrefHeight(120);

        chapter3 = new Button();
        chapter3Image = new ImageView(image3);
        chapter3Image.setFitWidth(800);
        chapter3Image.setFitHeight(120);
        chapter3.setGraphic(chapter3Image);
        chapter3.setStyle("-fx-background-color: transparent;");
        chapter3.setPrefWidth(800);
        chapter3.setPrefHeight(120);

        String hoverStyle = "-fx-background-color: #FFFFFF; -fx-opacity: 0.5;";
        chapter1.setOnMouseEntered(e -> chapter1.setStyle(hoverStyle));
        chapter1.setOnMouseExited(e -> chapter1.setStyle("-fx-background-color: transparent;"));
        chapter2.setOnMouseEntered(e -> chapter2.setStyle(hoverStyle));
        chapter2.setOnMouseExited(e -> chapter2.setStyle("-fx-background-color: transparent;"));
        chapter3.setOnMouseEntered(e -> chapter3.setStyle(hoverStyle));
        chapter3.setOnMouseExited(e -> chapter3.setStyle("-fx-background-color: transparent;"));

        // Ajouter les boutons à la box centrale
        centerBox.getChildren().addAll(chapter1, chapter2, chapter3, backButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(50, 0, 0, 0));

        bottomBox = new HBox();
        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().add(consoleView);

        root.setCenter(centerBox);
        root.setBottom(bottomBox);
        new ChapterController(this);
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
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
