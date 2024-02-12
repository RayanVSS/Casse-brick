package physics;

import geometry.Coordinates;
import geometry.Vector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Map;

public class AppPhysic extends Application {
    
    Stage primaryStage;
    Scene scene;
    StackPane root ;
    Outline outline = new Outline();


    @Override
    public void start(Stage p) throws Exception {
        this.primaryStage = p;
        root = new StackPane();
        scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Physique");
        primaryStage.setScene(scene);
        primaryStage.show();
        Button bouton1 = new Button("Lancer la simulation");
        Button bouton2 = new Button("Options");

        bouton1.setOnAction(e -> {
            root.getChildren().clear();
            new Simulation(outline,primaryStage);
        });

        bouton2.setOnAction(e -> {
            root.getChildren().clear();
            options();
        });

        root.getChildren().add(bouton1);
        root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(bouton2);
        root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 000));

    }


    public void options() {
        boolean[] bool = { false, false, false, false, false };
        int[] valeur = { 0, 0, 0, 0 };
    
        // Configurations des labels et des champs de saisie pour chaque paramètre
        Map<Label,TextField> labels = Map.of(
            new Label("Veuillez saisir la valeur de la vitesse initiale :"),new TextField(),
            new Label("Veuillez saisir la valeur de la gravité :"),new TextField(),
            new Label("Veuillez saisir la valeur du paramètre 3 :"),new TextField(),
            new Label("Veuillez saisir la valeur du paramètre 4 :"),new TextField()
        );
        for (Label label : labels.keySet()) {
            int i = 0;
            label.setLayoutX(100 + 100 * i);
            label.setLayoutY(100); // Ajustez la position verticale de chaque label
    
            root.getChildren().add(label);
            root.setMargin(label, new javafx.geometry.Insets(0, 100+100*i, 50, 0));
            root.getChildren().add(labels.get(label));
            root.setMargin(labels.get(label), new javafx.geometry.Insets(0, 100, 100, 0 + 200)); // Ajustez la position horizontale du champ de texte
            i++;
            
        }

        Button button = new Button("Soumettre");
        button.setOnAction(event -> {
            int m = 0;
            for (Label label : labels.keySet()) {
                String userInput = labels.get(label).getText(); // Récupère la valeur saisie
                if (userInput.equals("")) {
                    label.setText("Aucune valeur saisie");
                } else if (userInput.matches(".*[a-z].*") || userInput.matches(".*[A-Z].*")) {
                    label.setText("Valeur saisie incorrecte");
                } else {
                    bool[m] = true;
                    valeur[m] = Integer.parseInt(userInput);
                    label.setText("Valeur saisie : " + userInput);
                }
                m++;
            }
        });
        root.getChildren().add(button);
        root.setMargin(button, new javafx.geometry.Insets(0, 0, 10, 10));
    
        scene.setRoot(root);
        primaryStage.show();
    
        boolean verif = true; // Changer l'opération logique pour 'true' au lieu de 'false'
        for (int i = 0; i < bool.length; i++) {
            verif = verif && bool[i];
        }
        if (verif) {
            outline = new Outline(valeur[0], valeur[1], new Vector(new Coordinates(0, valeur[2] / 2)), new Vector(new Coordinates(0, 0)));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
