package gui;

import gui.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import save.PlayerData;
import utils.GameConstants;

public class Item implements Menu{

    private String name;
    private String path;
    private int price;
    private Button button;
    private String type;
    private boolean bought;
    private boolean worn;

    public Item(String name, String path, int price, String type) {
        this.name = name;
        this.path = path;
        this.price = price;
        this.type = type;
        this.bought = PlayerData.inventaire.isBought(this.getName());
        this.worn = GameConstants.BALL_PORTE==this || GameConstants.RACKET_PORTE==this;

        if (worn) {
            this.button = createButton("Porté");
        } else if(bought) {
            this.button = createButton("Acheté");
        }else{
            this.button = createButton("Acheter : "+price);
        }
    }

    public void updateButtonBuy(){
        button.setText("Acheté");
    }
    public void updateChange(){
        button.setText("Porté");
    }
    // GETTERS

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getPrice() {
        return price;
    }

    public Button getButton() {
        return button;
    }

    public String getType() {
        return type;
    }

    public boolean isBought() {
        return bought;
    }

    public boolean isWorn() {
        return worn;
    }

    public void setWorn(boolean worn) {
        this.worn = worn;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }


    @Override
    public Scene getScene() {
        throw new UnsupportedOperationException("Unimplemented method 'getScene'");
    }

}
