package physics.gui;

import javafx.stage.Stage;
import physics.config.PhysicEngine;
import utils.GameConstants;

public class OptionBar {

    private Stage primaryStage;
    private boolean Bar=false;

    public OptionBar(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void affiche(){

    }

    public void close(){

    }

    public void update(){
        if(Bar){
            Bar=false;
            close();
            PhysicEngine.START = 0;
        }
        else{
            Bar=true;
            PhysicEngine.START = GameConstants.DEFAULT_WINDOW_WIDTH-GameConstants.DEFAULT_GAME_ROOT_WIDTH;
            affiche();
        }
    }

    public boolean isBar() {
        return Bar;
    }
}
