package gui.Menu.MenuViews;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

/**
 * Interface Menu qui définit les méthodes par défaut pour créer des éléments
 * d'interface utilisateur.
 * Elle est implémentée par les classes qui représentent les vues des menus.
 * @author Benmalek Majda
 */
public interface Menu {

    /**
     * Crée un bouton avec le texte, la marge droite et la marge inférieure
     * spécifiés.
     * 
     * @param text         Le texte du bouton.
     * @param rightMargin  La marge droite du bouton.
     * @param bottomMargin La marge inférieure du bouton.
     * @return Le bouton créé.
     */
    public default Button createButton(String text, double rightMargin, double bottomMargin) {
        Button button = new Button(text);
        StackPane.setMargin(button, new Insets(0, rightMargin, bottomMargin, 0));
        button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #d5bbb1;-fx-text-fill: #1b263b;");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
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
    public default Label createLabel(String text, double rightMargin, double bottomMargin, double fontSize) {
        Label label = new Label(text);
        StackPane.setMargin(label, new Insets(0, rightMargin, bottomMargin, 0));
        label.setStyle("-fx-font-size: " + fontSize + "; -fx-text-fill: #d5bbb1;");
        return label;
    };

    /**
     * Crée un bouton bascule avec le texte et l'état sélectionné spécifiés.
     * 
     * @param text     Le texte du bouton bascule.
     * @param selected L'état sélectionné du bouton bascule.
     * @return Le bouton bascule créé.
     */
    public default ToggleButton createToggleButton(String text, boolean selected) {
        ToggleButton button = new ToggleButton(text);
        button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
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
    public default Slider createSlider(double min, double max, double value, double maxwidth) {
        Slider slider = new Slider(min, max, value);
        slider.setMaxWidth(maxwidth);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        return slider;
    }
}