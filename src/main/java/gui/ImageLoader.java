package gui;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static final Map<String, Image> imageCache = new HashMap<>();

    public static Image loadImage(String imagePath) {
        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        } else {
            Image image = new Image(new File(imagePath).toURI().toString());
            imageCache.put(imagePath, image);
            return image;
        }
    }

    public static Image loadImage(String imagePath, int resizeWidth, int resizeHeight) {
        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        } else {
            Image originalImage = new Image(new File(imagePath).toURI().toString());
            Image resizedImage = GraphicsToolkit.resizeImage(originalImage, resizeWidth, resizeHeight);
            imageCache.put(imagePath, resizedImage);
            return resizedImage;
        }
    }

    public static Image loadImage(String imagePath, double ratioWidth, double ratioHeight) {
        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        } else {
            Image originalImage = new Image(new File(imagePath).toURI().toString());
            Image resizedImage = GraphicsToolkit.resizeImage(originalImage,
                    originalImage.getWidth() * ratioWidth,
                    originalImage.getHeight() * ratioHeight);

            imageCache.put(imagePath, resizedImage);
            return resizedImage;
        }
    }

    public static Map<String, Image> getImageCache() {
        return imageCache;
    }
}
