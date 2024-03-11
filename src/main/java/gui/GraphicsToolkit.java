package gui;

import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
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
     * Classe englobant un Label et un ToggleButton dans une VBox
     */
    public static class LabelToggleButtonVBox extends HBox {

        private Label label;
        private ToggleButton toggleButton;

        public LabelToggleButtonVBox(String text, boolean selected) {
            super(20);
            setAlignment(Pos.CENTER);
            setPadding(new Insets(5));

            label = new Label(text);

            toggleButton = new ToggleButton(selected ? "On" : "Off");
            toggleButton.setSelected(selected);

            setStyle();
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            getChildren().addAll(label, spacer, toggleButton);
        }

        private void setStyle() {
            getStyleClass().add("label-toggle-button-vbox");

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
            toggleButton.setSelected(toggleButton.isSelected());
            toggleButton.setText(toggleButton.isSelected() ? "On" : "Off");
        }

        public Label getLabel() {
            return label;
        }

        public ToggleButton getToggleButton() {
            return toggleButton;
        }

    }

    public static class LabelSliderVBox {

        private Label label;
        private Slider slider;

    }

}
