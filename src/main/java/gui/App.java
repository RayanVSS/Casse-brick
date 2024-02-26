package gui;

import gui.Menu.MenuViews.StartMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import save.Sauvegarde;
import static utils.GameConstants.LAST_SAVE;

public class App extends Application {

    protected Stage primaryStage;
    private Sauvegarde sauvegarde = new Sauvegarde();

    @Override
    public void start(Stage p) throws Exception {
        // chargement de la derniere sauvegarde
        // System.out.println("Chargement de la derniere sauvegarde");
        // sauvegarde.chargerLastSave();
        // System.out.println(LAST_SAVE + "AAAAAAAAAAAAAAAAAAAAAAAA");
        // sauvegarde.chargerOptionsDuJeu(LAST_SAVE);

        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");

        new StartMenuView(p);

        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
