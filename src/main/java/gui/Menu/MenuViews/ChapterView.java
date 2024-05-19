package gui.Menu.MenuViews;

import gui.Menu.BaseView;
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
import javafx.scene.layout.StackPane;
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
    private Button chapter2;

    private Button chapter3;
    private ImageView chapter3Image;

    private Button chapter4;
    private ImageView chapter4Image;

    private Button backButton;

    private ConsoleView consoleView;
    private BaseView baseView;

    public ChapterView(Stage primaryStage) {

        this.primaryStage = primaryStage;
        root = new BorderPane();
        centerBox = new VBox(30);
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);// 1100, 800

        // Charger les images
        Image image1 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre1.png");
        Image image2 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre2.png");
        Image image3 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre3.png");
        Image image4 = ImageLoader.loadImage("src/main/ressources/chapitre/Chapitre4.png");

        // Créer les boutons
        chapter1 = createChapterButton(image1, null, -10);
        chapter2 = createChapterButton(image2,  null, 0);
        chapter3 = createChapterButton(image3, null, 10);
        chapter4 = createChapterButton(image4, null, 20);


        backButton = createButton("Retour", 0, 0);

        // Ajouter les boutons à la box centrale
        centerBox.getChildren().addAll(chapter1, chapter2, chapter3, chapter4, backButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(50, 0, 0, 0));

        bottomBox = new HBox();
        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().add(consoleView);

        

        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        baseView = new BaseView(root, centerBox, bottomBox);
        new ChapterController(this);
    }

    private Button createChapterButton(Image chapterImage, Image lockImage, int lockYTranslate) {
        ImageView chapterImageView = new ImageView(chapterImage);
        chapterImageView.setFitWidth(800);
        chapterImageView.setFitHeight(120);

        ImageView lockView = new ImageView(lockImage);
        lockView.setFitWidth(40);
        lockView.setFitHeight(40);
        lockView.setTranslateX(-100);
        lockView.setTranslateY(lockYTranslate);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(chapterImageView, lockView);

        Button chapterButton = new Button();
        chapterButton.setGraphic(stackPane);
        chapterButton.setStyle("-fx-background-color: transparent;");
        chapterButton.setPrefWidth(800);
        chapterButton.setPrefHeight(120);

        String hoverStyle = "-fx-background-color: #FFFFFF; -fx-opacity: 0.5;";
        chapterButton.setOnMouseEntered(e -> chapterButton.setStyle(hoverStyle));
        chapterButton.setOnMouseExited(e -> chapterButton.setStyle("-fx-background-color: transparent;"));

        return chapterButton;
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
    }

    @Override
    public void update() {
        baseView.update();
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

    public Button getChapter4() {
        return chapter4;
    }

    public Button getBackButton() {
        return backButton;
    }

}
