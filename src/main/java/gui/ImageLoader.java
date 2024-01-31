package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLoader { //à adapter pour prendre en compte la hauteur et largeur

    private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage loadImage(String imagePath) {
        if (imageCache.containsKey(imagePath)) {

            return imageCache.get(imagePath);

        } else {

            try {

                BufferedImage image = ImageIO.read(new File(imagePath));

                imageCache.put(imagePath, image);
                return image;

            } catch (IOException e) {

                e.printStackTrace();
                return null;
            }
        }
    }

    public static BufferedImage loadImage(String imagePath, int resizeWidth, int resizeHeight) {
        if (imageCache.containsKey(imagePath)) {

            return imageCache.get(imagePath);

        } else {

            try {

                BufferedImage image = ImageIO.read(new File(imagePath));
                image = GraphicsToolkit.resizeImage(image, resizeWidth, resizeHeight);
                imageCache.put(imagePath, image);
                return image;

            } catch (IOException e) {

                e.printStackTrace();
                return null;
            }
        }
    }

    public static BufferedImage loadImage(String imagePath, double ratioWidth, double ratioHeight) {
        if (imageCache.containsKey(imagePath)) {

            return imageCache.get(imagePath);

        } else {
            try {

                BufferedImage image = ImageIO.read(new File(imagePath));
                image = GraphicsToolkit.resizeImage(image,
                        (int) (image.getWidth() * ratioWidth),
                        (int) (image.getHeight() * ratioHeight));

                imageCache.put(imagePath, image);
                return image;

            } catch (IOException e) {
                System.out.println(imagePath);
                return null;
            }
        }
    }

    public static Map<String, BufferedImage> getImageCache() {
        return imageCache;
    }

}
