package gui.GraphicsFactory;

import java.util.Timer;
import java.util.TimerTask;

import gui.Console;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
* Créé l'objet qui sert d'afficheur pour la console, à chaque création il rétablit toute l'historique en cours.

* @category
* Objet Singleton, s'auto-update indépendamment
*/
public class ConsoleView extends VBox {

    private static ConsoleView consoleView;

    private ScrollPane scrollPane;
    private VBox consoleTextArea;
    private HBox sendBox;
    private TextField inputField;
    private Button sendButton;

    private Timer updateTimer;

    // Constructeur privé pour empêcher l'instanciation directe
    private ConsoleView() {
        initComponents();
        getChildren().addAll(scrollPane, sendBox);
        startAutoUpdate();
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
        consoleTextArea = new VBox();
        scrollPane.setContent(consoleTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefHeight(200);
    }

    private void initSendBox() {
        sendBox = new HBox();

        inputField = new TextField();
        inputField.setPromptText("Entrez un message ou une commande (/) ...");
        inputField.setPrefWidth(300);

        sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> sendMessage());

        sendBox.getChildren().addAll(inputField, sendButton);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            inputField.clear();
            Platform.runLater(() -> Console.process(message));
        }
    }

    public void updateConsoleView() {
        String message = Console.getQueueMessage();
        if (message != null) {
            consoleTextArea.getChildren().add(new Label(message));
        }
    }

    private void startAutoUpdate() { // Vitesse de 10 messages / sec
        if (updateTimer != null) {
            updateTimer.cancel();
        }
        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> updateConsoleView());
            }
        }, 0, 100);
    }
}
