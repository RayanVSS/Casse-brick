package gui.Menu.MenuControllers;

import gui.App;
import gui.Menu.MenuViews.BoutiqueView;
import save.PlayerData;
import utils.GameConstants;
import utils.Sound.ClickSound;

public class BoutiqueController {

    private BoutiqueView view;
    private ClickSound click = App.clickSoundPlayer;

    public BoutiqueController(BoutiqueView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> {
            click.play();
            back();
        });
        for (int i = 0; i < view.getRaquetteButton().length; i++) {
            int finalI = i;
            view.getRaquetteButton()[i].setOnAction(e -> {
                click.play();
                acheterRaquette(finalI);
            });
        }

        for (int i = 0; i < view.getBalleButton().length; i++) {
            int finalI = i;
            view.getBalleButton()[i].setOnAction(e -> {
                click.play();
                acheterBalle(finalI);
            });
        }
    }

    private void back() {
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void acheterRaquette(int i) {
        System.out.println("argent :" + PlayerData.money);
        System.out.println("prix raquette :" + ((5 * i) + 5));
        int prix = (5 * i) + 5;
        System.out.println("i: " + i);

        if (PlayerData.inventaire.isBought(view.getLabelsR()[i])) {
            view.getRaquetteButton()[i].setText("Acheté");
            return;
        } else if (PlayerData.money >= prix) {
            PlayerData.money -= prix;
            view.setMoneyValue(PlayerData.money);
            view.getMoneyVBox().getSummaryLabel().setText("Argent : " + PlayerData.money);
            view.getRaquetteButton()[i].setText("Acheté");
            PlayerData.inventaire.addItem(view.getLabelsR()[i]);
            GameConstants.TEXTURE = view.getPathsR()[i];
            view.getRaquetteButton()[i].setDisable(true);
            PlayerData.inventaire.afficheInventaire();
        }
        System.out.println("Pas assez d'argent");
    }

    private void acheterBalle(int i) {
        int prix = (5 * i) + 5;

        if (PlayerData.inventaire.isBought(view.getLabels()[i])) {
            view.getBalleButton()[i].setText("Acheté");
            return;
        } else if (PlayerData.money >= prix) {
            PlayerData.money -= prix;
            view.setMoneyValue(PlayerData.money);
            view.getMoneyVBox().getSummaryLabel().setText("Argent : " + PlayerData.money);
            view.getBalleButton()[i].setText("Acheté");
            if (PlayerData.inventaire != null) {
                PlayerData.inventaire.addItem(view.getLabels()[i]);

                view.getBalleButton()[i].setDisable(true);
                PlayerData.inventaire.afficheInventaire();
            }
        } else {
            System.out.println("Pas assez d'argent");
        }
    }

}
