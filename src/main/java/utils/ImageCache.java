package utils;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe ImageCache pour optimiser le chargement des images.
 * Cette classe utilise une technique de mise en cache pour stocker les images déjà chargées.
 * Lorsqu'une image est demandée, elle est d'abord recherchée dans le cache.
 * Si elle n'est pas trouvée, elle est chargée, ajoutée au cache, puis retournée.
 * Si elle est trouvée dans le cache, elle est simplement retournée.
 */
public class ImageCache {
    /**
     * Le cache d'images. Les clés sont les chemins des images et les valeurs sont les images elles-mêmes.
     */
    private static Map<String, Image> cache = new HashMap<>();

    /**
     * Obtient une image à partir du cache. Si l'image n'est pas dans le cache, elle est chargée, ajoutée au cache, puis retournée.
     * @param path Le chemin de l'image à charger.
     * @return L'image chargée.
     */
    public static Image getImage(String path) {
        Image image = cache.get(path);
        if (image == null) {
            image = new Image(path);
            cache.put(path, image);
        }
        return image;
    }
}