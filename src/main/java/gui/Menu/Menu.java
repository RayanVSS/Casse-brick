package gui.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

/**
 * Interface Menu qui définit les méthodes pour créer des éléments
 * d'interface utilisateur.
 * Elle est étendue par les classes qui représentent les vues des menus.
 * 
 * @author Benmalek Majda
 */
public interface Menu {

    public enum Theme {
        PINK("/styles/pink.css"), 
        LIGHT("/styles/light.css"), 
        DARK("/styles/dark.css"),
        BLACK("/styles/black.css"),
        ACHROMATOPSIE("/styles/achromatopsie.css"),
        DEUTERANOPIE("/styles/deuteranopie.css"),
        TRITANOPIE("/styles/tritanopie.css"),
        PROTANOPIE("/styles/protanopie.css")
        ;

        private String path;

        public String getPath() {
            return path;
        }

        Theme(String path) {
            this.path = path;
        }
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
    default Button createButton(String text, double rightMargin, double bottomMargin) {
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
    }

    /**
     * Crée un label avec le texte, la marge droite, la marge inférieure et la
     * taille de la police spécifiés.
     * 
     * @param text         Le texte du label.
     * @param rightMargin  La marge droite du label.
     * @param bottomMargin La marge inférieure du label.
     * @return Le label créé.
     */
    default Label createLabel(String text, double rightMargin, double bottomMargin) {
        Label label = new Label(text);
        label.getStyleClass().add("label-style");
        StackPane.setMargin(label, new Insets(0, rightMargin, bottomMargin, 0));
        return label;
    }

    /**
     * Crée un bouton bascule avec le texte et l'état sélectionné spécifiés.
     * 
     * @param text     Le texte du bouton bascule.
     * @param selected L'état sélectionné du bouton bascule.
     * @return Le bouton bascule créé.
     */
    default ToggleButton createToggleButton(String text, boolean selected) {
        ToggleButton button = new ToggleButton(text);
        if (selected)
            button.getStyleClass().add("button-hover");
        else
            button.getStyleClass().add("button-style");
        button.setSelected(selected);
        button.setOnMouseEntered(e -> {
            button.getStyleClass().remove("button-style");
            button.getStyleClass().add("button-hover");
        });
        button.setOnMouseExited(e -> {
            button.getStyleClass().remove("button-hover");
            button.getStyleClass().add("button-style");
        });
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
    default Slider createSlider(double min, double max, double value, double maxwidth) {
        Slider slider = new Slider(min, max, value);
        slider.getStyleClass().add("slider-style");
        slider.setMaxWidth(maxwidth);
        slider.setOrientation(Orientation.HORIZONTAL);
        return slider;
    }
}