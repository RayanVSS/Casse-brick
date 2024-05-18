package gui;

import gui.Menu.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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
        this.worn = GameConstants.TEXTURE.equals(this.getPath()) || GameConstants.SKIN_BALL.equals(this.getPath());
        // GameConstants.BALL_PORTE==this || GameConstants.RACKET_PORTE==this;

        button = new Button("");
        button.getStyleClass().add("button-boutique");
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("button-boutique");
            button.getStyleClass().add("button-hover");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("button-hover");
            button.getStyleClass().add("button-boutique");
        });
    }

    public void updateButtonBuy(){
        button.setText("Acheté");
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(javafx.scene.paint.Color.BLUE);
        glow.setRadius(10);
        button.setEffect(glow);

    }
    public void updateChange(){
        button.setText("Porté");
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(javafx.scene.paint.Color.CRIMSON);
        glow.setRadius(10);
        button.setEffect(glow);
    }

    public void updateNone(){
        this.getButton().setText("Acheter : " + this.getPrice());
        this.getButton().setEffect(null);

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
