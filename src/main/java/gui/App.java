package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class App extends Application{

    protected Stage primaryStage;
    Scene scene;
    protected StackPane root =new StackPane();
    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;

        primaryStage.setTitle("Casse Brique");
        StartMenu startMenu = new StartMenu(primaryStage);
        primaryStage.setScene(startMenu.menu());

        primaryStage.show();
        primaryStage.getOnCloseRequest();

        

        Label label = new Label("Implementation du Menu");
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        

    }



    public void Options() {
        // TODO implement here
        Label label = new Label("Implementation des Options");
        root.getChildren().clear();
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));
        
        Button bouton1 = new Button("Retour");
        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            StartMenu startMenu = new StartMenu(primaryStage);
        });
        
        
        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, -870, -700, 0));
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
