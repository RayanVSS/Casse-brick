package gui.Menu;

import gui.App;
import gui.GameView;
import javafx.scene.control.Button;

public class StartMenu extends App {
    public StartMenu() {
        super();
            // TODO implement here
            Button bouton1 = new Button("Jouer");


            bouton1.setOnAction(e -> {
                root.getChildren().clear();
                new GameView(primaryStage,1);
            });

            Button bouton2 = new Button("Options");

            bouton2.setOnAction(e -> {
                Options();
            });


            root.getChildren().add(bouton1);
            root.setMargin(bouton1, new javafx.geometry.Insets(0, 100, 100, 0));
            root.getChildren().add(bouton2);
            root.setMargin(bouton2, new javafx.geometry.Insets(0, 100, 200, 000));


    }

}
