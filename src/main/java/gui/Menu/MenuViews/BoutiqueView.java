package gui.Menu.MenuViews;

import gui.ImageLoader;
import gui.GraphicsToolkit.LabelVBox;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.BoutiqueController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import save.PlayerData;
import utils.GameConstants;

public class BoutiqueView implements Menu {

    private Stage primaryStage;
    private VBox root;
    private Scene scene;

    private Label title;
    private HBox boutique;
    // Achat de raquette
    private LabelVBox raquette;
    private String[] labelsR = { "Arc-en-ciel", "Bleu", "Dark Matter", "Or", "Rose", "Rouge", "Violet", "Vert",
            "Etoile", "Noir&Blanc", "Gris","Purple silk"};
    private String[] pathsR = {
            "src/main/ressources/Texture/arc_en_ciel.jpg",
            "src/main/ressources/Texture/bleu.jpg",
            "src/main/ressources/Texture/Dark_Matter.jpg",
            "src/main/ressources/Texture/or.jpg",
            "src/main/ressources/Texture/rose.jpg",
            "src/main/ressources/Texture/rouge.jpg",
            "src/main/ressources/Texture/violet.png",
            "src/main/ressources/Texture/vert.jpg",
            "src/main/ressources/Texture/stars.jpg",
            "src/main/ressources/Texture/black_white.jpg",
            "src/main/ressources/Texture/grey.jpg",
            "src/main/ressources/Texture/purple.jpg"
    };
    private Button[] raquetteButton = new Button[labelsR.length];
    private Rectangle[] raquetteRect = new Rectangle[labelsR.length];
    // Achat de balle
    private LabelVBox balle;
    private String[] labels = { "Classic Classic", "Classic Pink", "Classic Black", "Classic light",
            "Gravity Classic", "Gravity Pink", "Gravity Black", "Gravity light",
            "Hyper Classic", "Hyper Pink", "Hyper Black", "Hyper light",
    };
    private String[] paths = { "src/main/ressources/balle/classic/classic.png",
            "src/main/ressources/balle/pink/classic.png",
            "src/main/ressources/balle/black/classic.png",
            "src/main/ressources/balle/light/classic.png",
            "src/main/ressources/balle/classic/gravity.png",
            "src/main/ressources/balle/pink/gravity.png",
            "src/main/ressources/balle/black/gravity.png",
            "src/main/ressources/balle/light/gravity.png",
            "src/main/ressources/balle/classic/hyper.png",
            "src/main/ressources/balle/pink/hyper.png",
            "src/main/ressources/balle/black/hyper.png",
            "src/main/ressources/balle/light/hyper.png", };
    private GridPane balleGrid;
    private ImageView[] balleImage = new ImageView[labels.length];
    private Button[] balleButton = new Button[labels.length];

    // L'argent
    private int moneyValue = PlayerData.money;
    private LabelVBox moneyVBox;

    private Button back;
    private int price = 5;

    public BoutiqueView(Stage p) {
        this.primaryStage = p;
        root = new VBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        System.out.println("BoutiqueView created");

        initBoutique();

        title = createLabel("Boutique", 0, 0);
        title.getStyleClass().add("title-style");

        back = createButton("retour", 0, 0);

        root.getStyleClass().add("root");
        root.getChildren().add(title);
        root.getChildren().add(moneyVBox);
        root.getChildren().add(boutique);
        root.getChildren().add(back);
        new BoutiqueController(this);
    }

    private void initArgent() {
        moneyVBox = new LabelVBox("Argent : " + moneyValue, 10);
        moneyVBox.getVBox().setPrefWidth(50);
    }

    private void initRaquette() {
        raquette = new LabelVBox("Texture Raquette:", 10);
        GridPane raquetteGrid = new GridPane();
        raquetteGrid.setHgap(10);
        raquetteGrid.setVgap(10);
        for (int i = 0; i < labelsR.length; i++) {
            // raquetteImage[i] = initImage(pathsR[i]);
            raquetteRect[i] = initRectangle(pathsR[i]);
            raquetteButton[i] = createButton("Acheter : " + price, 0, 0);
            LabelVBox raquetteV = new LabelVBox(labelsR[i], 10);
            raquetteV.getChildren().addAll(raquetteRect[i], raquetteButton[i]);
            raquetteGrid.add(raquetteV, i % 3, i / 3); // Ajoutez cette ligne
            price += 5;
        }
        raquette.getChildren().add(raquetteGrid);
    }

    private void initBalle() {
        balle = new LabelVBox("Skin Balle:", 10);
        balleGrid = new GridPane();
        balleGrid.setHgap(10);
        balleGrid.setVgap(10);
        for (int i = 0; i < labels.length; i++) {
            balleImage[i] = initImage(paths[i]);
            balleButton[i] = createButton("Acheter : " + price, 0, 0);
            LabelVBox ballV = new LabelVBox(labels[i], 10);
            ballV.getChildren().addAll(balleImage[i], balleButton[i]);
            balleGrid.add(ballV, i % 3, i / 3); // Ajoutez cette ligne
            price += 5;
        }
        balle.getChildren().add(balleGrid);
    }

    private void initBoutique() {
        boutique = new HBox(10);
        boutique.setAlignment(javafx.geometry.Pos.CENTER);
        initArgent();
        initRaquette();
        initBalle();
        boutique.getChildren().addAll(raquette, balle);
    }

    private ImageView initImage(String path) {
        Image image = ImageLoader.loadImage(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        return imageView;

    }

    private Rectangle initRectangle(String Path) {
        Rectangle rect = new Rectangle(100, 10);
        Image image = ImageLoader.loadImage(Path);
        ImagePattern imagePattern = new ImagePattern(image);
        rect.setFill(imagePattern);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        return rect;
    }

    // GETTERS
    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Button getBtnBack() {
        return back;
    }
}
