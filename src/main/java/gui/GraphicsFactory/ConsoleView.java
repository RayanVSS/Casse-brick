package gui.GraphicsFactory;

import gui.Console;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
* Créé l'objet qui sert d'afficheur pour la console, à chaque création il rétablit toute l'historique en cours.
* Objet Singleton
*/
public class ConsoleView extends VBox {

    private static ConsoleView consoleView;

    private ScrollPane scrollPane;

    private HBox sendBox;
    private TextField inputField;
    private Button sendButton;

    // Constructeur privé pour empêcher l'instanciation directe
    private ConsoleView() {
        initComponents();
        getChildren().addAll(scrollPane, sendBox);
        updateConsoleView();
    }

    /**
     * @return l'instance unique de ConsoleView
     */
    public static ConsoleView getInstance() {
        if (consoleView == null) {
            consoleView = new ConsoleView();
        }
        return consoleView;
    }

    private void initComponents() {
        initScrollPane();
        initSendBox();
    }

    private void initScrollPane() {
        scrollPane = new ScrollPane();
        scrollPane.setContent(sendBox); // Ici on définit le contenu du ScrollPane
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private void initSendBox() {
        sendBox = new HBox();
        sendBox.setPrefSize(500, 50);

        inputField = new TextField();
        inputField.setPromptText("Entrez votre message (une ligne)");

        sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> sendMessage());

        sendBox.getChildren().addAll(inputField, sendButton);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            Console.display("Moi: " + message);
            updateConsoleView();
            inputField.clear();
        }
    }

    public void updateConsoleView() {
        sendBox.getChildren().clear();
        for (String message : Console.getHistory()) {
            sendBox.getChildren().add(new Label(message));
        }
    }
}
