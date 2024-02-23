package gui.Menu.MenuViews;

import gui.Menu.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Classe abstraite Menu qui définit les méthodes pour créer des éléments
 * d'interface utilisateur.
 * Elle est étendue par les classes qui représentent les vues des menus.
 * 
 * @author Benmalek Majda
 */
public abstract class Menu {

    private Scene scene;
    private SceneManager sceneManager;
    /**
     * Constructeur de la classe Menu.
     * 
     * @param p Le stage principal de l'application.
     * @param s La scène de l'application.
     */
    public Menu(Stage p, Scene s, SceneManager sceneManager) {
        this.scene = s;
        this.scene.getStylesheets().add("/styles/blue.css");
        this.sceneManager = sceneManager;
    }

    /**
     * Crée un bouton avec le texte, la marge droite et la marge inférieure
     * spécifiés.
     * 
     * @param text         Le texte du bouton.
     * @param rightMargin  La marge droite du bouton.
     * @param bottomMargin La marge inférieure du bouton.
     * @return Le bouton créé.
     */
    public Button createButton(String text, double rightMargin, double bottomMargin) {
        Button button = new Button(text);

        StackPane.setMargin(button, new Insets(0, rightMargin, bottomMargin, 0));
        button.getStyleClass().add("button-style");
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("button-style");
            button.getStyleClass().add("button-hover");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("button-hover");
            button.getStyleClass().add("button-style");
        });
        return button;
    };

    /**
     * Crée un label avec le texte, la marge droite, la marge inférieure et la
     * taille de la police spécifiés.
     * 
     * @param text         Le texte du label.
     * @param rightMargin  La marge droite du label.
     * @param bottomMargin La marge inférieure du label.
     * @param fontSize     La taille de la police du label.
     * @return Le label créé.
     */
    public Label createLabel(String text, double rightMargin, double bottomMargin) {
        Label label = new Label(text);
        label.getStyleClass().add("label-style");
        StackPane.setMargin(label, new Insets(0, rightMargin, bottomMargin, 0));
        return label;
    };

    /**
     * Crée un bouton bascule avec le texte et l'état sélectionné spécifiés.
     * 
     * @param text     Le texte du bouton bascule.
     * @param selected L'état sélectionné du bouton bascule.
     * @return Le bouton bascule créé.
     */
    public ToggleButton createToggleButton(String text, boolean selected) {
        ToggleButton button = new ToggleButton(text);
        button.getStyleClass().add("toggle-button-style");
        button.setSelected(selected);
        return button;
    }

    /**
     * Crée un slider avec le minimum, le maximum, la valeur et la largeur maximale
     * spécifiés.
     * 
     * @param min      La valeur minimale du slider.
     * @param max      La valeur maximale du slider.
     * @param value    La valeur du slider.
     * @param maxwidth La largeur maximale du slider.
     * @return Le slider créé.
     */
    public Slider createSlider(double min, double max, double value, double maxwidth) {
        Slider slider = new Slider(min, max, value);
        slider.setMaxWidth(maxwidth);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        return slider;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}