package gui.GraphicsFactory;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import save.PlayerData;

/**
 * Afficheur des infos du joueur
 */
public class ProfileView extends VBox {

    private Label pseudoLabel;
    private Label expLevelLabel;
    private Label moneyLabel;

    public ProfileView() {
        super(5);
        // Création des composants
        pseudoLabel = new Label("Pseudo : " + PlayerData.pseudo);
        expLevelLabel = new Label("Niveau : " + PlayerData.expLevel);
        moneyLabel = new Label("Argent : " + PlayerData.money);
        setStyle();

        this.getChildren().addAll(pseudoLabel, expLevelLabel, moneyLabel);
    }

    public void update() {
        pseudoLabel.setText("Pseudo : " + PlayerData.pseudo);
        expLevelLabel.setText("Niveau : " + PlayerData.expLevel);
        moneyLabel.setText("Argent : " + PlayerData.money);
    }

    private void setStyle() {
        pseudoLabel.getStyleClass().add("label-style");
        expLevelLabel.getStyleClass().add("label-style");
        moneyLabel.getStyleClass().add("label-style");
        this.getStyleClass().add("label-vbox");
    }
}