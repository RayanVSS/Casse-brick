package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public final class GraphicsToolkit { // Biblio de generation des composants graphiques

    private static Font originalGameFont = initGameFont();
    private static Font boldDerivedFont = originalGameFont.deriveFont(Font.BOLD, 14);

    public static JButton getQuitButton(JFrame frame) {

        JButton button = new JButton("Quitter");

        button.addActionListener(e -> {
            frame.dispose();
        });

        return button;
    }

    // Fonction qui garde les proportions de l'image
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        double aspectRatio = (double) originalImage.getWidth() / originalImage.getHeight();
        int newWidth = targetWidth;
        int newHeight = (int) (newWidth / aspectRatio);

        // Si la nouvelle hauteur calculée est supérieure à la hauteur cible,
        // redimensionner en fonction de la hauteur cible plutôt que de la largeur
        // cible
        if (newHeight > targetHeight) {
            newHeight = targetHeight;
            newWidth = (int) (newHeight * aspectRatio);
        }

        Image resultingImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // Dessiner l'image redimensionnée sur le BufferedImage
        resizedImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return resizedImage;
    }

    public static int getCenterPlacement(int totalHeight, int imageHeight) {
        return (totalHeight - imageHeight) / 2;
    }

    private static Font initGameFont() {

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream("src/main/ressources/Font.ttf"));

            return Font.createFont(Font.TRUETYPE_FONT, inputStream);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Font getGameFont() {
        return originalGameFont;
    }

    public static Font getBoldDerivedGameFont() {
        return boldDerivedFont;
    }

}
