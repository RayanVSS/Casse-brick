package gui.Menu.MenuViews;

import gui.ImageLoader;
import gui.GraphicsToolkit.LabelVBox;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.BoutiqueController;
import javafx.scene.Node;
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
    private String[] labelsR = { "Arc-en-ciel", "Bleu", "DarkMatter", "Or", "Rose", "Rouge", "Violet", "Vert",
            "Etoile", "Noir&Blanc", "Gris", "PurpleSilk" };
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
    private GridPane raquetteGrid;
    // Achat de balle
    private LabelVBox balle;
    private String[] labels = { "ClassicClassic", "ClassicPink", "ClassicBlack", "ClassicLight",
            "GravityClassic", "GravityPink", "GravityBlack", "GravityLight",
            "HyperClassic", "HyperPink", "HyperBlack", "Hyperlight",
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
    private Button[] balleButton = new Button[labels.length];

    // L'argent
    private int moneyValue = PlayerData.money;
    private LabelVBox moneyVBox;

    private Button back;
    private int priceBall = 5;
    private int priceRaquette = 5;

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

    private void initItem(String[] labels, String[] paths, Button[] buttons, GridPane grid, int price, String type) {
        for (int i = 0; i < labels.length; i++) {
            Node item;
            LabelVBox itemV;
            if (type.equals("raquette")) {
                item = initRectangle(paths[i]);
                itemV = new LabelVBox(labels[i], 20);
            } else {
                item = initImage(paths[i]);
                itemV = new LabelVBox(labels[i], 10);
            }
            buttons[i] = createButton("Acheter : " + price);
            itemV.getChildren().addAll(item, buttons[i]);
            grid.add(itemV, i % 3, i / 3);
            price += 5;
            if (PlayerData.inventaire.isBought(labels[i])) {
                buttonModif(buttons[i], paths[i]);
            }
        }
    }

    public void buttonModif(Button button, String path) {
        if (GameConstants.TEXTURE.equals(path) || GameConstants.SKIN_BALL.equals(path)) {
            button.setText("Porté");
        } else {
            button.setText("Acheté");
        }
        button.getStyleClass().clear();
        button.getStyleClass().add("bought-style");
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("bought-style");
            button.getStyleClass().add("bought-hover");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("bought-hover");
            button.getStyleClass().add("bought-style");
        });

    }

    private void initRaquette() {
        raquette = new LabelVBox("Texture Raquette:", 10);
        raquetteGrid = new GridPane();
        raquetteGrid.setHgap(10);
        raquetteGrid.setVgap(10);
        initItem(labelsR, pathsR, raquetteButton, raquetteGrid, priceRaquette, "raquette");
        raquette.getChildren().add(raquetteGrid);
    }

    private void initBalle() {
        balle = new LabelVBox("Skin Balle:", 10);
        balleGrid = new GridPane();
        balleGrid.setHgap(10);
        balleGrid.setVgap(10);
        initItem(labels, paths, balleButton, balleGrid, priceBall, "balle");
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

    public Button[] getBalleButton() {
        return balleButton;
    }

    public Button[] getRaquetteButton() {
        return raquetteButton;
    }

    public LabelVBox getMoneyVBox() {
        return moneyVBox;
    }

    public int getPriceRaquette() {
        return priceRaquette;
    }

    public void setMoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public String[] getLabelsR() {
        return labelsR;
    }

    public String[] getPathsR() {
        return pathsR;
    }

    public String[] getLabels() {
        return labels;
    }

    public String[] getPaths() {
        return paths;
    }

    public Button getButtonFromPath(String path) {
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].equals(path)) {
                return balleButton[i];
            }
        }
        for (int i = 0; i < pathsR.length; i++) {
            if (pathsR[i].equals(path)) {
                return raquetteButton[i];
            }
        }
        return null;
    }

}
