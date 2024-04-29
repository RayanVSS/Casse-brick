package gui.Menu.MenuViews;

import gui.GraphicsToolkit.LabelVBox;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.BoutiqueController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GameConstants;

public class BoutiqueView implements Menu {

    private Stage primaryStage;
    private VBox root;
    private Scene scene ;

    private Label title;
    private HBox boutique;
    // Achat de raquette
    private VBox raquette;
    private LabelVBox raquette1, raquette2, raquette3;


    // Achat de balle
    private VBox balle;
    private LabelVBox balle1, balle2, balle3;

    // L'argent
    private int argentValue;
    private LabelVBox argent;

    private Button back;
    public BoutiqueView(Stage p) {
        this.primaryStage = p;
        root = new VBox();
        scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
        System.out.println("BoutiqueView created");

        initBoutique();

        title=createLabel("Boutique", 0, 0);
        title.getStyleClass().add("title-style");
        
        back=createButton("retour", 0, 0);

        root.getStyleClass().add("root");
        root.getChildren().add(title);
        root.getChildren().add(argent);
        root.getChildren().add(boutique);
        root.getChildren().add(back);
        new BoutiqueController(this);
    }

    private void initArgent() {
        argent = new LabelVBox("Argent : " + 0, 10);
        argent.getVBox().setPrefWidth(50);
    }

    private void initRaquette() {
        raquette= new VBox(10);
        raquette1 = new LabelVBox("Raquette 1", 10);
        raquette2 = new LabelVBox("Raquette 2", 10);
        raquette3 = new LabelVBox("Raquette 3", 10);
        raquette.getChildren().addAll(raquette1, raquette2, raquette3);
    }

    private void initBalle() {
        balle = new VBox(10);
        balle1 = new LabelVBox("Balle 1", 10);
        balle2 = new LabelVBox("Balle 2", 10);
        balle3 = new LabelVBox("Balle 3", 10);
        balle.getChildren().addAll(balle1, balle2, balle3);
    }

    private void initBoutique() {
        boutique = new HBox(10);
        boutique.setAlignment(javafx.geometry.Pos.CENTER);
        initArgent();
        initRaquette();
        initBalle();
        boutique.getChildren().addAll( raquette, balle);
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
