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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private VBox raquette;
    private LabelVBox raquette1, raquette2, raquette3;

    // Achat de balle
    private LabelVBox balle;
    private String[] labels = { "Classic Classic", "Classic Pink", "Classic Black", "Classic light",
            "Gravity Classic", "Gravity Pink", "Gravity Black", "Gravity light",
            "Hyper Classic", "Hyper Pink", "Hyper Black", "Hyper light",
    };
    private String[] paths = { "src/main/ressources/balle/classic/classic.png",
            "src/main/ressources/balle/pink/classic.png", "src/main/ressources/balle/black/classic.png",
            "src/main/ressources/balle/light/classic.png", "src/main/ressources/balle/classic/gravity.png",
            "src/main/ressources/balle/pink/gravity.png", "src/main/ressources/balle/black/gravity.png",
            "src/main/ressources/balle/light/gravity.png", "src/main/ressources/balle/classic/hyper.png",
            "src/main/ressources/balle/pink/hyper.png", "src/main/ressources/balle/black/hyper.png",
            "src/main/ressources/balle/light/hyper.png" };
    private LabelVBox[] balleVBox = new LabelVBox[labels.length];
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
        raquette = new VBox(10);
        raquette1 = new LabelVBox("Raquette 1", 10);
        raquette2 = new LabelVBox("Raquette 2", 10);
        raquette3 = new LabelVBox("Raquette 3", 10);
        raquette.getChildren().addAll(raquette1, raquette2, raquette3);
    }

    private void initBalle() {
        balle = new LabelVBox("Skin Balle:", 10);
        //TODO: faire quelque chose comme pour ScoreLifeGraphics les vies
        for (int i = 0; i < labels.length; i++) {
            balleVBox[i] = new LabelVBox(labels[i]);
            balleImage[i] = initImage(paths[i]);
            balleButton[i] = createButton("Acheter : " + price, 0, 0);
            balleVBox[i].getChildren().addAll(balleImage[i], balleButton[i]);
            balle.getChildren().add(balleVBox[i]);
            price += 5;
        }
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
