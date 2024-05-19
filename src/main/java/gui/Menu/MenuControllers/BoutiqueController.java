package gui.Menu.MenuControllers;

import gui.App;
import gui.Console;
import gui.Item;
import gui.Menu.MenuViews.BoutiqueView;
import save.PlayerData;
import utils.GameConstants;
import utils.Sound.ClickSound;

/**
 * Classe contrôleur pour la vue BoutiqueView.
 * @author Benmalek Majda
 */
public class BoutiqueController {

    private BoutiqueView view;
    private ClickSound click = App.clickSoundPlayer;

    /**
     * Constructeur de la classe BoutiqueController.
     * 
     * @param view La vue associée à ce contrôleur.
     */
    public BoutiqueController(BoutiqueView view) {
        this.view = view;
        this.view.getBtnBack().setOnAction(e -> {
            click.play();
            back();
        });

        this.view.getBtnTakeOff().setOnAction(e -> {
            click.play();
            takeOff();
        });

        setButtonAction(view.getRaquetteItem());
        setButtonAction(view.getBalleItem());

    }

    /**
     * Définit l'action pour chaque bouton d'item.
     * 
     * @param Item Le tableau d'items.
     */
    private void setButtonAction(Item[] Item) {
        for (int i = 0; i < Item.length; i++) {
            int finalI = i;
            Item[i].getButton().setOnAction(e -> {
                click.play();
                acheterItem(Item[finalI]);
            });
        }
    }

    /**
     * Change la scène pour revenir au menu de départ.
     */
    private void back() {
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    /**
     * Achète un item si le joueur a assez d'argent.
     * 
     * @param item L'item à acheter.
     */
    private void acheterItem(Item item) {
        if (PlayerData.inventaire.isBought(item.getName())) {
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
            Console.display("Vous n'avez pas assez d'argent pour acheter cet item");
        }
    }

    /**
     * Met à jour un item après l'achat ou le port.
     * 
     * @param item L'item à mettre à jour.
     */
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
        if (aux != null) {
            aux.setWorn(false);
            aux.updateButtonBuy();
        }
        item.updateChange();
        item.setBought(true);
        item.setWorn(true);
    }

    /**
     * Enlève tous les items portés.
     */
    public void takeOff() {
        Item aux;
        if (GameConstants.RACKET_PORTE != null) {
            aux = GameConstants.RACKET_PORTE;
            GameConstants.RACKET_PORTE = null;
            GameConstants.TEXTURE = "Null";
        } else if (GameConstants.BALL_PORTE != null) {
            aux = GameConstants.BALL_PORTE;
            GameConstants.BALL_PORTE = null;
            GameConstants.SKIN_BALL = "Null";
        } else {
            return;
        }
        aux.setWorn(false);
        aux.updateButtonBuy();
    }
}
