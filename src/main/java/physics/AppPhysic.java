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


    public void options(){
        boolean[] bool = {false,false,false,false,false};
        int[] valeur = {0,0,0,0};

        Label label = new Label("Veuillez saisir la valeur de la vitesse initiale :");

        label.setLayoutX(100);
        label.setLayoutY(100);

        root.getChildren().clear();
        root.getChildren().add(label);
        root.setMargin(label, new javafx.geometry.Insets(0, 100, 50, 0));

        TextField textField = new TextField();
        Button button1 = new Button("Soumettre");

        // Action à effectuer lorsqu'on clique sur le bouton
        button1.setOnAction(event -> {
            String userInput = textField.getText(); // Récupère la valeur saisie
            if(userInput.equals("")){
                label.setText("Aucune valeur saisie");
            }
            else if(userInput.matches(".*[a-z].*") || userInput.matches(".*[A-Z].*")){
                label.setText("Valeur saisie incorrecte");
            }
            else{
                bool[0] = true;
                valeur[0]=Integer.parseInt(userInput);
                label.setText("Valeur saisie : " + userInput);
            }
        });

        
        root.getChildren().add(textField);
        root.setMargin(textField, new javafx.geometry.Insets(0, 100, 100, 0));
        root.getChildren().add(button1);
        root.setMargin(button1, new javafx.geometry.Insets(0, 0, 10, 10));
        scene.setRoot(root);
        primaryStage.show();

        boolean verif = false;
        for(int i = 0; i < bool.length; i++){
            verif = verif && bool[i];
        }
        if(verif){
            outline=new Outline(valeur[0],valeur[1],new Vector(new Coordinates(0, valeur[2]/2)),new Vector(new Coordinates(0, 0)));
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
