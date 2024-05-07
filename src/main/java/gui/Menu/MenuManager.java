package gui.Menu;

import java.util.HashMap;
import java.util.Map;

import gui.ViewPosition;
import gui.GraphicsFactory.ConsoleView;
import gui.Menu.MenuViews.BoutiqueView;
import gui.Menu.MenuViews.ChapterView;
import gui.Menu.MenuViews.GameCustomizerView;
import gui.Menu.MenuViews.GameModeView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StageSelectorView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.TutoView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.GameConstants;

/**
 * Classe de gestion des menus dans l'application.
 */
public class MenuManager {
    private Map<String, Menu> menus = new HashMap<>();

    /**
     * Ajoute un menu à la collection.
     *
     * @param name  le nom de la scène
     * @param menu l'objet implémentant Menu qui contient la scène à ajouter
     */
    public void addMenu(String name, Menu menu) {
        menus.put(name, menu);
    }

    /**
     * Supprime un menu de la collection par son nom.
     *
     * @param name le nom du menu à supprimer
     */
    public void removeMenu(String name) {
        menus.remove(name);
    }

    public Menu getMenu(String name) {
        return menus.get(name);
    }

    public Scene getScene(String name) {
        return getMenu(name).getScene();
    }

    /**
     * Vide la collection de scènes.
     */
    public void clearMenus() {
        menus.clear();
    }

    public Map<String, Menu> getMenus() {
        return menus;
    }

    /**
     * Récupère le nom d'une scène.
     * Utile pour le debug.
     *
     * @param scene la scène dont on veut le nom
     * @return le nom de la scène, ou null si la scène n'est pas dans la collection
     */
    public String getSceneName(Scene scene) {
        for (Map.Entry<String, Menu> entry : menus.entrySet()) {
            if (entry.getValue().getScene().equals(scene)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Ajoute une feuille de style à une scène.
     *
     * @param menu le menu contenant la scène à laquelle ajouter la feuille de style
     */
    public void addStylesheet(Menu menu) {
        menu.getScene().getStylesheets().clear();
        menu.getScene().getStylesheets().add(getClass().getResource(GameConstants.CSS.getPath()).toExternalForm());
    }

    public void updateStylesheets() {
        for (Menu menu : menus.values()) {
            addStylesheet(menu);
        }
    }

    // Charge toutes les scènes du jeu
    public void preCreateAllView(Stage primaryStage) {
        createStartMenuViewScene(primaryStage);
        createOptionsViewScene(primaryStage);
        createSaveViewScene(primaryStage);
        createGameModeViewScene(primaryStage);
        createStageSelectorViewScene(primaryStage);
        createGameCustomizerViewScene(primaryStage);
        createChapterViewScene(primaryStage);
        createBoutiqueView(primaryStage);
        createTutoViewScene(primaryStage);
    }

    // Les méthodes suivantes créent des scènes spécifiques et les ajoutent à la
    // collection
    public void createOptionsViewScene(Stage primaryStage) {
        OptionsView optionsView = new OptionsView(primaryStage);
        addStylesheet(optionsView);
        addMenu("OptionsView", optionsView);
    }

    public void createStartMenuViewScene(Stage primaryStage) {
        StartMenuView startMenuView = new StartMenuView(primaryStage);
        addStylesheet(startMenuView);
        addMenu("StartMenuView", startMenuView);
    }

    public void createSaveViewScene(Stage primaryStage) {
        SaveView saveView = new SaveView(primaryStage);
        addStylesheet(saveView);
        addMenu("SaveView", saveView);
    }

    public void createGameModeViewScene(Stage primaryStage) {
        GameModeView gameModeView = new GameModeView(primaryStage);
        addStylesheet(gameModeView);
        addMenu("GameModeView", gameModeView);
    }

    public void createStageSelectorViewScene(Stage primaryStage) {
        StageSelectorView stageSelectorView = new StageSelectorView(primaryStage);
        addStylesheet(stageSelectorView);
        addMenu("StageSelectorView", stageSelectorView);
    }

    public void createGameCustomizerViewScene(Stage primaryStage) {
        GameCustomizerView gameCustomizerView = new GameCustomizerView(primaryStage);
        addStylesheet(gameCustomizerView);
        addMenu("GameCustomizerView", gameCustomizerView);
    }

    public void createChapterViewScene(Stage primaryStage) {
        ChapterView chapterView = new ChapterView(primaryStage);
        addStylesheet(chapterView);
        addMenu("Chapterview", chapterView);
    }


    public void createBoutiqueView(Stage primaryStage) {
        BoutiqueView boutiqueView = new BoutiqueView(primaryStage);
        addStylesheet(boutiqueView);
        addMenu("BoutiqueView", boutiqueView);
    }
    
    public void createTutoViewScene(Stage primaryStage) {
        TutoView TutoView = new TutoView(primaryStage);
        addStylesheet(TutoView);
        addMenu("TutoView", TutoView);
    }

    /**
     * Change la scène actuelle du primaryStage par une autre scène de la
     * collection.
     * Et actualise la scène avec les nouvelles infos
     *
     * @param primaryStage le primaryStage dont on veut changer la scène
     * @param name         le nom de la nouvelle scène
     */
    public void changeScene(Stage primaryStage, String name) {
        Menu menu = getMenu(name);
        menu.update();
        if (menu instanceof ViewPosition) {
            ((ViewPosition) menu).saveViewPosition();
            ((ViewPosition) menu).moveConsoleView();
            ((ViewPosition) menu).handleDynamicAction();
            ConsoleView.getInstance().unfocusAction();
        }
        primaryStage.setScene(menu.getScene());
    }
}