package gui;

import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class GraphicsToolkit {

    private static Font originalGameFont = initGameFont();
    private static Font boldDerivedFont = Font.font(originalGameFont.getFamily(), FontWeight.BOLD, 14);

    // Fonction qui garde les proportions de l'image
    public static Image resizeImage(Image originalImage, double targetWidth, double targetHeight) {
        double aspectRatio = originalImage.getWidth() / originalImage.getHeight();
        double newWidth = targetWidth;
        double newHeight = newWidth / aspectRatio;

        // Si la nouvelle hauteur calculée est supérieure à la hauteur cible,
        // redimensionner en fonction de la hauteur cible plutôt que de la largeur
        // cible
        if (newHeight > targetHeight) {
            newHeight = targetHeight;
            newWidth = newHeight * aspectRatio;
        }

        ImageView imageView = new ImageView(originalImage);
        imageView.setFitWidth(newWidth);
        imageView.setFitHeight(newHeight);

        return imageView.snapshot(null, null);
    }

    public static double getCenterPlacement(double totalHeight, double imageHeight) {
        return (totalHeight - imageHeight) / 2;
    }

    private static Font initGameFont() {
        try (InputStream inputStream = new FileInputStream("src/main/ressources/Font.ttf")) {
            return Font.loadFont(inputStream, 12); // Utilisez la taille de police souhaitée
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Dimension2D getScreenDimension() {
        return new Dimension2D(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
    }

    public static Font getGameFont() {
        return originalGameFont;
    }

    public static Font getBoldDerivedGameFont() {
        return boldDerivedFont;
    }

    /**
     * Classe englobant un Label et un ToggleButton dans une HBox
     */
    public static class LabelToggleButtonHBox extends HBox {

        private Label label;
        private ToggleButton toggleButton;

        /**
        * Constructeur de la classe LabelToggleButtonHBox.
        *
        * @param text     Le texte à afficher à côté du ToggleButton.
        * @param selected Indique si le ToggleButton est sélectionné par défaut.
        */
        public LabelToggleButtonHBox(String text, boolean selected) {

            super(20);
            setAlignment(Pos.CENTER);

            initComponents(text, selected);

            setStyle();
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            getChildren().addAll(label, spacer, toggleButton);
        }

        private void initComponents(String text, boolean selected) {

            label = new Label(text);
            toggleButton = new ToggleButton(selected ? "On" : "Off");
            toggleButton.setSelected(selected);
            toggleButton.setMinWidth(55);
        }

        private void setStyle() {
            getStyleClass().add("label-toggle-button-hbox");

            label.getStyleClass().add("label-style");

            if (toggleButton.isSelected())
                toggleButton.getStyleClass().add("button-hover");
            else
                toggleButton.getStyleClass().add("button-style");

            toggleButton.setOnMouseEntered(e -> {
                toggleButton.getStyleClass().remove("button-style");
                toggleButton.getStyleClass().add("button-hover");
            });

            toggleButton.setOnMouseExited(e -> {
                toggleButton.getStyleClass().remove("button-hover");
                toggleButton.getStyleClass().add("button-style");
            });
        }

        public void action() {
            toggleButton.setText(toggleButton.isSelected() ? "On" : "Off");
        }

        public ToggleButton getToggleButton() {
            return toggleButton;
        }

    }

    /**
    * Classe englobant un Label et un Slider dans une HBox
    */
    public static class LabelSliderHBox extends HBox {

        private Label label;
        private Slider slider;
        private Label valueLabel;

        /**
        * Constructeur de la classe LabelSliderHBox.
        *
        * @param text       Le texte à afficher à côté du Slider.
        * @param min        La valeur minimale du Slider.
        * @param max        La valeur maximale du Slider.
        * @param defaultVal La valeur par défaut du Slider.
        * @param disabled   Indique si le Slider est désactivé par défaut.
        * @param increment  L'incrémentation du Slider.
        */
        public LabelSliderHBox(String text, int min, int max, int defaultVal, boolean disabled, int increment) {
            super(20);
            setAlignment(Pos.CENTER);

            initComponents(text, min, max, defaultVal, disabled, increment);

            setStyle();
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            getChildren().addAll(label, spacer, slider, valueLabel);
        }

        private void initComponents(String text, int min, int max, int defaultVal, boolean disabled, int increment) {

            label = new Label(text);

            slider = new Slider(min, max, defaultVal);
            slider.setBlockIncrement(increment);
            slider.setDisable(disabled);

            valueLabel = new Label(Integer.toString(defaultVal));
            valueLabel.setMinWidth(50);

            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                int intValue = newValue.intValue();
                valueLabel.setText(Integer.toString(intValue));
            });
        }

        private void setStyle() {

            getStyleClass().add("label-slider-hbox");

            label.getStyleClass().add("label-style");
            valueLabel.getStyleClass().add("label-style");
        }

        public Slider getSlider() {
            return slider;
        }

        public Label getValueLabel() {
            return valueLabel;
        }

    }

    /**
    * Classe englobant un Label et une ComboBox dans une HBox.
    */
    public static class LabelComboBoxHBox extends HBox {

        private Label label;
        private ComboBox<String> comboBox;

        /**
         * Constructeur de la classe LabelComboBoxHBox.
         *
         * @param text     Le texte à afficher à côté de la ComboBox.
         * @param options  Les options de la ComboBox.
         * @param defaultOption L'option par défaut de la ComboBox.
         */
        public LabelComboBoxHBox(String text, String[] options, String defaultOption) {
            super(20);
            setAlignment(Pos.CENTER);

            initComponents(text, options, defaultOption);

            setStyle();
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            getChildren().addAll(label, spacer, comboBox);
        }

        private void initComponents(String text, String[] options, String defaultOption) {
            label = new Label(text);

            comboBox = new ComboBox<>();
            comboBox.getItems().addAll(options);
            comboBox.setValue(defaultOption);
        }

        private void setStyle() {
            getStyleClass().add("label-combobox-hbox");

            label.getStyleClass().add("label-style");
        }

        public ComboBox<String> getComboBox() {
            return comboBox;
        }
    }

    /**
    * Une classe représentant une VBox avec une étiquette de résumé en italique.
    */
    public static class LabelVBox extends VBox {

        private Label summaryLabel;

        /**
         * Crée une nouvelle instance de LabelVBox avec un résumé donné.
         *
         * @param summary Le résumé à afficher en italique.
         * @param spacing Espacement entre les composants
         */
        public LabelVBox(String summary, int spacing) {
            super(spacing);

            initComponents(summary);

            setStyle();

            getChildren().addAll(summaryLabel);
        }

        private void initComponents(String summary) {
            summaryLabel = new Label(summary);
        }

        private void setStyle() {
            getStyleClass().add("label-vbox");

            summaryLabel.getStyleClass().add("label-vbox-description");
        }
    }

}
