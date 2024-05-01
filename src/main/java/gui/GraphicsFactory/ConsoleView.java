package gui.GraphicsFactory;

import gui.Console;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
* Créé l'objet qui sert d'afficheur pour la console, à chaque création il rétablit toute l'historique en cours.
*/
public class ConsoleView extends VBox {

    private TextArea consoleTextArea;

    public ConsoleView() {
        // Création de la zone de texte pour afficher la console
        consoleTextArea = new TextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setWrapText(true);

        // Ajout de la zone de texte à la vue de la console
        getChildren().add(consoleTextArea);

        // Mise à jour de la vue de la console avec l'historique actuel
        updateConsoleView();
    }

    // Méthode pour mettre à jour la vue de la console avec l'historique actuel
    private void updateConsoleView() {
        StringBuilder history = new StringBuilder();
        for (String message : Console.getHistory()) {
            history.append(message).append("\n");
        }
        consoleTextArea.setText(history.toString());
    }
}
