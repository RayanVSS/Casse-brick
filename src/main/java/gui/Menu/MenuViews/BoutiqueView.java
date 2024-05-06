package gui.Menu.MenuViews;

import gui.Item;
import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.MenuControllers.BoutiqueController;
import utils.ImageLoader;
import utils.GraphicsToolkit.LabelVBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import save.PlayerData;
import utils.GameConstants;
import gui.Menu.Menu;

public class BoutiqueView implements Menu, ViewPosition {

    private Stage primaryStage;
    private BorderPane root;
    private VBox contentBox;
    private HBox consoleBox;
    private Scene scene;

    private Label title;
    private HBox boutique;
    // Achat de raquette
    private LabelVBox raquette;
    private Item[] raquetteItem = new Item[GameConstants.LABELS_RAQUETTE.length];
    private GridPane raquetteGrid = new GridPane();

    // Achat de balle
    private LabelVBox balle;
    private Item[] balleItem = new Item[GameConstants.LABELS_BALLE.length];
    private GridPane balleGrid = new GridPane();

    private int moneyValue = PlayerData.money;
    private LabelVBox moneyVBox;

    private Button back;
    private final int startPrice = 5;

    private ConsoleView consoleView;

    public BoutiqueView(Stage p) {
        this.primaryStage = p;
        root = new BorderPane();
        contentBox = new VBox();
        consoleBox = new HBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        System.out.println("BoutiqueView created");

        initBoutique();

        title = createLabel("Boutique", 0, 0);
        title.getStyleClass().add("title-style");

        back = createButton("Retour", 0, 0);

        contentBox.getStyleClass().add("root");
        contentBox.getChildren().addAll(title, moneyVBox, boutique, back);

        consoleView = ConsoleView.getInstance();
        consoleBox.getChildren().add(consoleView);

        root.setCenter(contentBox);
        root.setBottom(consoleBox);
        localStyle();

        System.out.println("SKIN BALL: " + GameConstants.SKIN_BALL);
        new BoutiqueController(this);
    }

    private void initArgent() {
        moneyVBox = new LabelVBox("Argent : " + moneyValue, 10);
        moneyVBox.setPrefWidth(10);
    }

    private void initItems() {
        for (int i = 0; i < GameConstants.LABELS_RAQUETTE.length; i++) {
            raquetteItem[i] = new Item(GameConstants.LABELS_RAQUETTE[i], GameConstants.PATHS_RAQUETTE[i],
                    startPrice * i + 5, "raquette");
            if (PlayerData.inventaire.contains(raquetteItem[i].getName())) {
                raquetteItem[i].updateButtonBuy();
            }
            if (raquetteItem[i].getPath().equals(GameConstants.TEXTURE)) {
                System.out.println("raquette: " + GameConstants.TEXTURE);
                System.out.println("raquette path: " + raquetteItem[i].getPath());
                GameConstants.RACKET_PORTE = raquetteItem[i];
                raquetteItem[i].updateChange();
            }
            LabelVBox labelVBox = new LabelVBox(raquetteItem[i].getName(), 20);
            labelVBox.getChildren().addAll(initRectangle(raquetteItem[i].getPath()), raquetteItem[i].getButton());
            raquetteGrid.add(labelVBox, i % 3, i / 3);
        }
        for (int i = 0; i < GameConstants.LABELS_BALLE.length; i++) {
            balleItem[i] = new Item(GameConstants.LABELS_BALLE[i], GameConstants.PATHS_BALLE[i], startPrice * i + 5,
                    "balle");
            if (PlayerData.inventaire.contains(balleItem[i].getName())) {
                balleItem[i].updateButtonBuy();
            }
            if (balleItem[i].getPath().equals(GameConstants.SKIN_BALL)) {
                System.out.println("balle: " + GameConstants.SKIN_BALL);
                System.out.println("balle path: " + balleItem[i].getPath());
                GameConstants.BALL_PORTE = balleItem[i];
                balleItem[i].updateChange();
            }
            LabelVBox labelVBox = new LabelVBox(balleItem[i].getName(), 10);
            labelVBox.getChildren().addAll(initImage(balleItem[i].getPath()), balleItem[i].getButton());
            balleGrid.add(labelVBox, i % 3, i / 3);
        }
    }

    private void initBoutique() {
        initItems();
        raquette = new LabelVBox("Texture Raquette:", 10);
        raquetteGrid.setHgap(10);
        raquetteGrid.setVgap(10);
        raquette.getChildren().add(raquetteGrid);

        balle = new LabelVBox("Skin Balle:", 10);
        balleGrid.setHgap(10);
        balleGrid.setVgap(10);
        balle.getChildren().add(balleGrid);

        initArgent();

        boutique = new HBox(10);
        boutique.setAlignment(javafx.geometry.Pos.CENTER);
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

    // Fonction de correction de style sur les tailles (par-dessus le CSS)
    private void localStyle() {
        // boutique.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size: 16px;"));
        // QUE mettre des changements de tailles etc 
    }

    @Override
    public void moveConsoleView() {
        consoleBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    // GETTERS
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getBtnBack() {
        return back;
    }

    public Item[] getRaquetteItem() {
        return raquetteItem;
    }

    public Item[] getBalleItem() {
        return balleItem;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    }

    public LabelVBox getMoneyVBox() {
        return moneyVBox;
    }

    public Button getButtonFromPath(String path) {
        for (Item item : raquetteItem) {
            if (item.getPath().equals(path)) {
                return item.getButton();
            }
        }
        for (Item item : balleItem) {
            if (item.getPath().equals(path)) {
                return item.getButton();
            }
        }
        return null;
    }

}
