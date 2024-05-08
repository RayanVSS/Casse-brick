package gui.Menu.MenuViews;

import gui.Item;
import gui.Menu.MenuControllers.BoutiqueController;
import gui.GraphicsToolkit.LabelVBox;
import gui.ImageLoader;
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
import gui.Menu.Menu;

public class BoutiqueView implements Menu {

    private Stage primaryStage;
    private VBox root;
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

    public BoutiqueView(Stage p) {
        this.primaryStage = p;
        root = new VBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);

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
        moneyVBox.setPrefWidth(10);
    }

    private void initItems() {
        for (int i = 0; i < GameConstants.LABELS_RAQUETTE.length; i++) {
            raquetteItem[i] = new Item(GameConstants.LABELS_RAQUETTE[i], GameConstants.PATHS_RAQUETTE[i],
                    startPrice * i + 5, "raquette");
            // if (PlayerData.inventaire.contains(raquetteItem[i].getName())) {
            // raquetteItem[i].updateButtonBuy();
            // }
            // if (raquetteItem[i].getPath().equals(GameConstants.TEXTURE)) {
            // GameConstants.RACKET_PORTE = raquetteItem[i];
            // raquetteItem[i].updateChange();
            // }

            LabelVBox labelVBox = new LabelVBox(raquetteItem[i].getName(), 20);
            labelVBox.getChildren().addAll(initRectangle(raquetteItem[i].getPath()), raquetteItem[i].getButton());
            raquetteGrid.add(labelVBox, i % 3, i / 3);
        }
        for (int i = 0; i < GameConstants.LABELS_BALLE.length; i++) {
            balleItem[i] = new Item(GameConstants.LABELS_BALLE[i], GameConstants.PATHS_BALLE[i], startPrice * i + 5,
                    "balle");
            // if (PlayerData.inventaire.contains(balleItem[i].getName())) {
            // balleItem[i].updateButtonBuy();
            // }
            // if (balleItem[i].getPath().equals(GameConstants.SKIN_BALL)) {
            // GameConstants.BALL_PORTE = balleItem[i];
            // balleItem[i].updateChange();
            // }
            LabelVBox labelVBox = new LabelVBox(balleItem[i].getName(), 10);
            labelVBox.getChildren().addAll(initImage(balleItem[i].getPath()), balleItem[i].getButton());
            balleGrid.add(labelVBox, i % 3, i / 3);
        }
    }

    private void updateButton(Item i) {
        i.setBought(PlayerData.inventaire.isBought(i.getName()));
        i.setWorn(GameConstants.TEXTURE.equals(i.getPath()) || GameConstants.SKIN_BALL.equals(i.getPath()));
        if (i.isWorn() && i.getType().equals("balle")) {
            GameConstants.BALL_PORTE = i;
            System.out.println("update balle: "+ i.getName());
            i.updateChange();
        } else if (i.isWorn() && i.getType().equals("raquette")) {
            GameConstants.RACKET_PORTE = i;
            System.out.println("update raquette: "+ i.getName());
            i.updateChange();
        } else if (i.isBought()) {
            i.updateButtonBuy();
            System.out.println("update bought: "+ i.getName());
        } else {
            i.getButton().setText("Acheter : " + i.getPrice());
            System.out.println("update not bought: "+ i.getName());
        }

//        PlayerData.inventaire.afficheInventaire();
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

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void update() {
        System.out.println("boutique update 1");
        for (Item i : raquetteItem) {
            updateButton(i);
        }
        for (Item i : balleItem) {
            updateButton(i);
        }
        System.out.println("boutique update 2 ");
    }

    // GETTERS
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public VBox getRoot() {
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

}
