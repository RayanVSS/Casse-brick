package gui;

import java.util.Stack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{

    Stage primaryStage;
    Scene scene;
    StackPane root ;
    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        Label label = new Label("Implementation du Menu");
        root = new StackPane();
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(100, 100, 100, 100));
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Casse Brique");
        primaryStage.setScene(scene);
        primaryStage.show();
        Menu();
    }

    public void Menu() {
        // TODO implement here
        Button bouton = new Button("Jouer");

        // Ajouter un gestionnaire d'événements au bouton (dans cet exemple, afficher une alerte)
        bouton.setOnAction(e -> {
            Play();
            // Vous pouvez ajouter ici le code que vous souhaitez exécuter lors du clic sur le bouton.
        });

        root.getChildren().add(bouton);
        root.setMargin(bouton, new javafx.geometry.Insets(0, 100, 100, 0));
    }

    public void Play() {
        // TODO implement here
        Label label = new Label("Implementation du Jeu");
        root.getChildren().clear();
        root.getChildren().add(label);
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
