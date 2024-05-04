package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.BoutiqueView;
import javafx.scene.control.Button;
import save.PlayerData;
import utils.GameConstants;
import utils.Sound.ClickSound;

public class BoutiqueController {

    private BoutiqueView view;
    private Button porteR;
    private Button porteB;
    private ClickSound click = App.clickSoundPlayer;

    public BoutiqueController(BoutiqueView view) {
        this.view = view;
        this.porteR = view.getButtonFromPath(GameConstants.TEXTURE);
        this.porteB = view.getButtonFromPath(GameConstants.SKIN_BALL);
        this.view.getBtnBack().setOnAction(e -> {
            click.play();
            back();
        });
        setButtonAction(view.getRaquetteButton(), "raquette");
        setButtonAction(view.getBalleButton(), "balle");
    }

    private void setButtonAction(Button[] buttons, String type) {
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnAction(e -> {
                click.play();
                
                acheterItem(finalI, type);
            });
        }
    }

    private void back() {
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void acheterItem(int i, String type) {
        int prix = (5 * i) + 5;
        String label = type.equals("raquette") ? view.getLabelsR()[i] : view.getLabels()[i];
        Button b = type.equals("raquette") ? view.getRaquetteButton()[i] : view.getBalleButton()[i];
        String path = type.equals("raquette") ? view.getPathsR()[i] : view.getPaths()[i];

        if (PlayerData.inventaire.isBought(label)) {
            porteR(path, b, type);
            return;
        } else if (PlayerData.money >= prix) {
            PlayerData.money -= prix;
            view.setMoneyValue(PlayerData.money);
            view.getMoneyVBox().getSummaryLabel().setText("Argent : " + PlayerData.money);
            view.buttonModif(b, path);
            PlayerData.inventaire.addItem(label);
            if (type.equals("raquette")) {
                GameConstants.TEXTURE = path;
                porteR = b;
            }else{
                GameConstants.SKIN_BALL = path;
                porteB = b;
            }
            PlayerData.inventaire.afficheInventaire();
        } else {
            System.out.println("Pas assez d'argent");
        }
    }

    private void porteR(String path, Button b, String type) {
        if (type.equals("raquette")) {
            b.setOnAction(e -> {
                porteR.setText("Acheté");
                b.setText("Porté");
                System.out.println("Porté");
                GameConstants.TEXTURE = path;
                porteR = b;
            });
        } else {
            b.setOnAction(e -> {
                porteB.setText("Acheté");
                b.setText("Porté");
                System.out.println("Porté");
                GameConstants.SKIN_BALL = path;
                porteB = b;
            });
        }
    }
}
