package gui;

import gui.Menu.MenuViews.StartMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import save.Sauvegarde;

public class App extends Application {

    protected Stage primaryStage;
    private Sauvegarde sauvegarde = new Sauvegarde();

    @Override
    public void start(Stage p) throws Exception {
        //chargement de la derniere sauvegarde
        sauvegarde.SetupLastSave();

        this.primaryStage = p;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Casse Brique");

        new StartMenuView(p);

        primaryStage.show();
        primaryStage.getOnCloseRequest();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
