package gui.Menu.MenuControllers;

import gui.App;
import gui.Item;
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
        setButtonAction(view.getRaquetteItem());
        setButtonAction(view.getBalleItem());
    }

    private void setButtonAction(Item[] Item) {
        for (int i = 0; i < Item.length; i++) {
            int finalI = i;
            Item[i].getButton().setOnAction(e -> {
                click.play();
                acheterItem(Item[finalI]);
            });
        }
    }

    private void back() {
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void acheterItem(Item item) {
        if (PlayerData.inventaire.isBought(item.getName())) {
            System.out.println("Deja achete");
            updateItem(item);
            return;
        } else if (PlayerData.money >= item.getPrice()) {
            PlayerData.money -= item.getPrice();
            view.setMoneyValue(PlayerData.money);
            view.getMoneyVBox().getSummaryLabel().setText("Argent : " + PlayerData.money);
            updateItem(item);
            PlayerData.inventaire.addItem(item.getName());
            PlayerData.inventaire.afficheInventaire();
        } else {
            System.out.println("Pas assez d'argent");
        }
    }

    private void updateItem(Item item) {
        Item aux;
        if (item.getType().equals("raquette")) {
            aux = GameConstants.RACKET_PORTE;
            GameConstants.RACKET_PORTE = item;
            GameConstants.TEXTURE = item.getPath();
        } else if (item.getType().equals("balle")) {
            aux = GameConstants.BALL_PORTE;
            GameConstants.BALL_PORTE = item;
            GameConstants.SKIN_BALL = item.getPath();
        } else {
            return;
        }
        aux.updateButtonBuy();
        item.updateChange();
        item.setBought(true);
        item.setWorn(true);
    }
}
