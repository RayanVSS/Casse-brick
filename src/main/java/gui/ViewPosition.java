package gui;

import utils.GameConstants;

/**
 * Utilité principale : Connaître la vue sur laquelle on se trouve à l'instant actuel.
 * Méthode : étiquettage des classes relatives à la vue actuelle (connaître la position).
 */
public interface ViewPosition {

    // Pour connaître sur quelle vue l'on se trouve, utiliser : "instanceof" sur GameConstants.ACTUAL_VIEW
    default void saveViewPosition() {
        GameConstants.ACTUAL_VIEW = this;
    }
}
