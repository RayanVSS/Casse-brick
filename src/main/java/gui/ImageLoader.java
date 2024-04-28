package gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.scene.image.Image;

public class ImageLoader {

    private static final Map<String, Image> imageCache = new HashMap<>();

   private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static Image loadImage(String imagePath) {
        return imageCache.computeIfAbsent(imagePath, key -> {
            Future<Image> future = executorService.submit(() -> new Image(new File(key).toURI().toString()));
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        });
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
