package gui.Menu.MenuViews;

import gui.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoutiqueView implements Menu{
    
    private Stage primaryStage;
    private Scene scene;
    private VBox root;

    public BoutiqueView(Stage p) {
        this.primaryStage = p;
    }

}
