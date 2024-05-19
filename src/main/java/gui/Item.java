package gui;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import save.PlayerData;
import utils.GameConstants;

/**
 * Classe représentant un item dans le jeu.
 * Cette classe est responsable de la gestion des informations relatives à un
 * item, comme son nom, son prix, son état (acheté ou porté), etc.
 * 
 * @author Benmalek Majda
 */
public class Item {

    private String name;
    private String path;
    private int price;
    private Button button;
    private String type;
    private boolean bought;
    private boolean worn;

    /**
     * Constructeur de la classe Item.
     * Initialise un nouvel item avec les informations fournies.
     *
     * @param name  Le nom de l'item.
     * @param path  Le chemin d'accès à l'image de l'item.
     * @param price Le prix de l'item.
     * @param type  Le type de l'item.
     */
    public Item(String name, String path, int price, String type) {
        this.name = name;
        this.path = path;
        this.price = price;
        this.type = type;
        this.bought = PlayerData.inventaire.isBought(this.getName());
        this.worn = GameConstants.TEXTURE.equals(this.getPath()) || GameConstants.SKIN_BALL.equals(this.getPath());

        button = new Button("");
        button.getStyleClass().add("button-boutique");
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("button-boutique");
            button.getStyleClass().add("button-hover-boutique");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("button-hover-boutique");
            button.getStyleClass().add("button-boutique");
        });
    }

    /**
     * Met à jour l'état du bouton de l'item pour indiquer qu'il a été acheté.
     */
    public void updateButtonBuy() {
        button.setText("Acheté");
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(javafx.scene.paint.Color.BLUE);
        glow.setRadius(10);
        button.setEffect(glow);

    }

    /**
     * Met à jour l'état du bouton de l'item pour indiquer qu'il est actuellement
     * porté.
     */
    public void updateChange() {
        button.setText("Porté");
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(javafx.scene.paint.Color.CRIMSON);
        glow.setRadius(10);
        button.setEffect(glow);
    }

    /**
     * Réinitialise l'état du bouton de l'item pour indiquer qu'il n'est ni acheté
     * ni porté.
     */
    public void updateNone() {
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

}
