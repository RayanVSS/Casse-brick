package gui.Menu.MenuViews;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.Menu;
import gui.Menu.MenuManager;
import gui.Menu.MenuControllers.SaveController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import save.Sauvegarde;
import utils.GameConstants;

public class SaveView implements Menu, ViewPosition {
    private Stage primaryStage;
    private BorderPane root = new BorderPane();
    private StackPane centerBox = new StackPane();
    private HBox bottomBox = new HBox();
    private Scene scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH,
            GameConstants.DEFAULT_WINDOW_HEIGHT);
    private MenuManager sceneManager;
    //button
    private Button btnBack;
    private Button btnload;
    private Button btnsave;
    private Button btndelete;
    private Button btnOK;
    private Button resetSave;

    //grid, text, label, textfield, combobox
    private GridPane grid = new GridPane();
    private Label title;
    private Label userName;
    private TextField NameSave;
    private ComboBox<String> listSave;

    private ConsoleView consoleView;

    //c'est pour avoir les fonctions de sauvegarde
    private Sauvegarde sauvegarde = new Sauvegarde();

    public SaveView(Stage p) {
        //super(p, scene, sceneManager);
        this.primaryStage = p;
        centerBox.getStyleClass().add("root");
        //button
        this.btnBack = createButton("Retour", 300, -500);
        this.btnsave = createButton("Sauvegarder", 150, -500);
        this.btndelete = createButton("Supprimer", 200, 590);
        this.btnOK = createButton("OK", 0, -150);
        this.btnload = createButton("Charger", -200, 590);
        this.resetSave = createButton("Réinitialiser", 0, 300);
        //grid
        this.grid.getStyleClass().add("grid-style");
        //text
        this.title = createLabel("Creer une nouvelle sauvegarde", 0, 0);
        this.title.getStyleClass().add("label-style");
        this.grid.add(title, 0, 0, 2, 1);
        //label
        this.userName = createLabel("Nom:", 0, 0);
        this.grid.add(userName, 0, 1);
        //textfield
        this.NameSave = new TextField();
        this.grid.add(NameSave, 1, 1);
        //combobox
        this.listSave = new ComboBox<String>();
        this.listSave.setPromptText("Choisir une sauvegarde existante");
        this.listSave.getItems().addAll(sauvegarde.listerSauvegardes());
        StackPane.setMargin(this.listSave, new javafx.geometry.Insets(-500, 0, 0, 0));
        //add to root
        centerBox.getChildren().addAll(grid, btnBack, btnsave, btndelete, btnOK, btnload, listSave, resetSave);

        consoleView = ConsoleView.getInstance();
        bottomBox.getChildren().add(consoleView);
        root.setCenter(centerBox);
        root.setBottom(bottomBox);
        new SaveController(p, this);
    }

    @Override
    public void moveConsoleView() {
        bottomBox.getChildren().add(consoleView);
    }

    @Override
    public void handleDynamicAction() {
        consoleView.setDynamicFocus(scene);
    }

    //GETTERS
    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnload() {
        return btnload;
    }

    public Button getBtnsave() {
        return btnsave;
    }

    public Button getBtndelete() {
        return btndelete;
    }

    public Button getBtnOK() {
        return btnOK;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public GridPane getGrid() {
        return grid;
    }

    public Label getTitle() {
        return title;
    }

    public Label getUserName() {
        return userName;
    }

    public TextField getNameSave() {
        return NameSave;
    }

    public ComboBox<String> getListSave() {
        return listSave;
    }

    public Button getResetSave() {
        return resetSave;
    }

    public Scene getScene() {
        return scene;
    }

    //afficher une popup avec le texte en parametre
    public void afficherMessage(String message) {
        Stage stage = new Stage();
        stage.initOwner(primaryStage);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label(message);
        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> stage.close());

        gridPane.add(label, 0, 0);
        gridPane.add(closeButton, 0, 1);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setTitle("Message");
        stage.show();
    }

    public MenuManager getSceneManager() {
        return sceneManager;
    }
}