package gui.Menu.MenuViews;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import save.Sauvegarde;
import utils.GameConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;



public class SaveView extends Menu{
    private Stage primaryStage;
    private static StackPane root=new StackPane();
    private static Scene scene=new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
    
    
    //button
    private Button btnBack;
    private Button btnload;
    private Button btnsave;
    private Button btndelete;
    private Button btnOK;
    
    //grid, text, label, textfield, combobox
    private GridPane grid = new GridPane();
    private Label title;
    private Label userName;
    private TextField NameSave;
    private ComboBox<String> listSave;

    //c'est pour avoir les fonctions de sauvegarde
    private Sauvegarde sauvegarde = new Sauvegarde();
    
    public SaveView(Stage p) {
        super(p, scene);
        this.primaryStage = p;
        scene.getStylesheets().add("/styles/blue.css");
        root.getStyleClass().add("root");
        //button
        this.btnBack = createButton("Retour", -870, -700);
        this.btnsave = createButton("Sauvegarder", 0, -500);
        this.btndelete = createButton("Supprimer", 200, 590);
        this.btnOK = createButton("OK", 0, -150);
        this.btnload = createButton("Charger", -200, 590);
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
        root.getChildren().addAll(grid, btnBack, btnsave, btndelete, btnOK, btnload,listSave);
        this.primaryStage.setScene(scene);
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
    public StackPane getRoot() {
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

}