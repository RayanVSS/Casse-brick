package gui.Menu.MenuViews;

import gui.Item;
import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.BaseView;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.BoutiqueController;
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
import utils.GraphicsToolkit.LabelVBox;
import utils.ImageLoader;

/**
 * Classe représentant la vue de la boutique dans le jeu.
 * Cette classe est responsable de l'affichage des éléments de la boutique et de
 * leur mise à jour.
 * Implemente l'interface Menu et ViewPosition.
 * @author Benmalek Majda
 */
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
    private Button takeOff;
    private final int startPrice = 5;

    private ConsoleView consoleView;
    private BaseView baseView;

    /**
     * Constructeur de la classe BoutiqueView.
     * Initialise la vue de la boutique avec les éléments nécessaires.
     *
     * @param p Le stage principal sur lequel la vue de la boutique sera affichée.
     */
    public BoutiqueView(Stage p) {
        this.primaryStage = p;
        root = new BorderPane();
        contentBox = new VBox();
        consoleBox = new HBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

        initBoutique();

        title = createLabel("Boutique", 0, 0);
        title.getStyleClass().add("title-style");

        HBox h = new HBox();
        back = createButton("Retour", 0, 0);
        takeOff = createButton("Tout enlever", 0, 0);
        h.getChildren().addAll(back, takeOff);
        h.setAlignment(javafx.geometry.Pos.CENTER);
        h.setSpacing(10);
        StackPane.setMargin(h, new javafx.geometry.Insets(10, 10, 10, 10));
        contentBox.getStyleClass().add("root");
        contentBox.getChildren().addAll(title, moneyVBox, boutique, h);
        contentBox.setAlignment(javafx.geometry.Pos.CENTER);

        consoleView = ConsoleView.getInstance();
        consoleBox.getChildren().add(consoleView);

        root.setCenter(contentBox);
        root.setBottom(consoleBox);
        localStyle();

        baseView = new BaseView(root, contentBox, consoleBox);
        new BoutiqueController(this);
    }

    /**
     * Initialise la VBox contenant le montant d'argent du joueur.
     */
    private void initArgent() {
        moneyVBox = new LabelVBox("Argent : " + moneyValue, 10);
        moneyVBox.setPrefWidth(10);
    }

    /**
     * Initialise les items disponibles dans la boutique.
     */
    private void initItems() {
        for (int i = 0; i < GameConstants.LABELS_RAQUETTE.length; i++) {
            raquetteItem[i] = new Item(GameConstants.LABELS_RAQUETTE[i], GameConstants.PATHS_RAQUETTE[i],
                    startPrice * i + 5, "raquette");
            LabelVBox labelVBox = new LabelVBox(raquetteItem[i].getName(), 20);
            labelVBox.getChildren().addAll(initRectangle(raquetteItem[i].getPath()), raquetteItem[i].getButton());
            raquetteGrid.add(labelVBox, i % 4, i / 4);
        }

        for (int i = 0; i < GameConstants.LABELS_BALLE.length; i++) {
            balleItem[i] = new Item(GameConstants.LABELS_BALLE[i], GameConstants.PATHS_BALLE[i], startPrice * i + 5,
                    "balle");
            LabelVBox labelVBox = new LabelVBox(balleItem[i].getName(), 10);
            labelVBox.getChildren().addAll(initImage(balleItem[i].getPath()), balleItem[i].getButton());
            balleGrid.add(labelVBox, i % 4, i / 4);
        }
    }

    /**
     * Met à jour l'état du bouton d'un item.
     *
     * @param i L'item dont le bouton doit être mis à jour.
     */
    private void updateButton(Item i) {
        i.setBought(PlayerData.inventaire.isBought(i.getName()));
        i.setWorn(GameConstants.TEXTURE.equals(i.getPath()) || GameConstants.SKIN_BALL.equals(i.getPath()));
        if (i.isWorn() && i.getType().equals("balle")) {
            GameConstants.BALL_PORTE = i;
            i.updateChange();
        } else if (i.isWorn() && i.getType().equals("raquette")) {
            GameConstants.RACKET_PORTE = i;
            i.updateChange();
        } else if (i.isBought()) {
            i.updateButtonBuy();
        } else {
            i.getButton().setText("Prix : " + i.getPrice());
        }
    }

    /**
     * Initialise la boutique avec les items et l'argent du joueur.
     */
    private void initBoutique() {
        initItems();
        raquette = new LabelVBox("Texture Raquette:", 5);
        raquetteGrid.setHgap(5);
        raquetteGrid.setVgap(5);
        raquette.getChildren().add(raquetteGrid);

        balle = new LabelVBox("Skin Balle:", 5);
        balleGrid.setHgap(5);
        balleGrid.setVgap(5);
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
        // boutique.lookupAll("*").forEach(node -> node.setStyle("-fx-font-size:
        // 16px;"));
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

    public Scene getScenes() {
        return scene;
    }

    /**
     * Met à jour la vue de la boutique.
     * Cette méthode est appelée pour rafraîchir l'affichage de la boutique.
     */
    @Override
    public void update() {
        for (Item i : raquetteItem) {
            updateButton(i);
        }
        for (Item i : balleItem) {
            updateButton(i);
        }
        baseView.update();
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

    public Button getBtnTakeOff() {
        return takeOff;
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

    @Override
    public Scene getScene() {
        return scene;
    }

}
