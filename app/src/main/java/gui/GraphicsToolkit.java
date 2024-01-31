package gui;

import javafx.geometry.Dimension2D;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
