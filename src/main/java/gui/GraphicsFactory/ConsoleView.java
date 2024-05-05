package gui.GraphicsFactory;

import java.util.Timer;
import java.util.TimerTask;

import gui.Console;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.GraphicsToolkit;

/**
* Créé l'objet qui sert d'afficheur pour la console, à chaque création il rétablit toute l'historique en cours.
*
* @info
* Objet Singleton, s'auto-update indépendamment
*/
public class ConsoleView extends VBox {

    private static ConsoleView consoleView;

    private Stage stage;
    private Scene scene;

    private ScrollPane scrollPane;
    private VBox consoleTextArea;
    private HBox sendBox;
    private TextField inputField;
    private Button sendButton;
    private Region expandingRegion;
    private boolean focus;

    private Timer updateTimer;

    // Constructeur privé pour empêcher l'instanciation directe
    private ConsoleView() {
        initComponents();
        getChildren().addAll(expandingRegion, scrollPane, sendBox);
        startAutoUpdate();
        setStaticStyle();
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

    public void registerFocusStage(Stage registeredStage) { // EN TEST
        this.stage = registeredStage;
        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.B) {
                inputField.requestFocus();
                System.out.println("Touche Entrée pressée sur le Stage");
            }
        });
    }

    private void initComponents() { // l'ordre compte, influe sur la taille déf
        setPrefSize(360, 200);
        initSendBox();
        initScrollPane();
        expandingRegion = new Region();
        setVgrow(expandingRegion, Priority.ALWAYS);
    }

    private void initScrollPane() {
        scrollPane = new ScrollPane();
        consoleTextArea = new VBox();
        scrollPane.setContent(consoleTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMaxHeight(200);
        scrollPane.setPrefWidth(scrollPane.getWidth());

        // VBar au plus bas aux nouveaux messages
        scrollPane.needsLayoutProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                scrollPane.setVvalue(1.0);
            }
        });
    }

    private void initSendBox() {
        sendBox = new HBox();

        inputField = new TextField();
        inputField.setPromptText("⬆ Entrez un message ou une commande (/) ⬆");
        inputField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
            }
        });
        inputField.setDisable(true);

        sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> {
            sendMessage();
            inputField.requestFocus();
        });
        sendButton.setPrefWidth(60);
        sendButton.setDisable(true);

        HBox.setHgrow(inputField, Priority.ALWAYS);
        sendBox.getChildren().addAll(inputField, sendButton);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            inputField.clear();
            Platform.runLater(() -> {
                Console.process(message);
            });
        }
    }

    public void updateConsoleView() {
        String message = Console.getQueueMessage();
        if (message != null) {
            Label label = new Label(message);
            label.setWrapText(true);
            label.getStyleClass().add("console-scroll-pane-label");
            consoleTextArea.getChildren().add(label);
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

    private void setStaticStyle() {

        this.getStyleClass().add("console");
        scrollPane.getStyleClass().addAll("console-scroll-pane", "console-scroll-pane-unfocus");
        consoleTextArea.getStyleClass().add("console-text-area");
        sendBox.getStyleClass().add("console-send-box");
        inputField.getStyleClass().add("console-input-field");
        sendButton.getStyleClass().add("console-send-button");

        inputField.setOnMouseClicked(event -> {
            focusAction();
        });
        sendButton.setOnMouseClicked(event -> {
            focusAction();
        });
        scrollPane.setOnMouseClicked(event -> {
            focusAction();
        });
        GraphicsToolkit.applyFadeOnDisable(inputField, 1.0, 0.3, 325, 650);
        GraphicsToolkit.applyFadeOnDisable(scrollPane, 1.0, 0.0, 1000, 500);
    }

    public void setDynamicFocus(Scene registeredScene) {
        this.scene = registeredScene;

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            Bounds scrollPaneBounds = scrollPane.localToScene(scrollPane.getBoundsInLocal());
            if (!scrollPaneBounds.contains(event.getSceneX(), event.getSceneY())) {
                if (focus = true) {
                    unfocusAction();
                }
                focus = false;
            } else {
                focusAction();
                focus = true;
            }
        });
    }

    private void focusAction() {

        inputField.requestFocus();

        // Remove au préalable pour éviter le sur-ajout / clear sans clear les autres
        scrollPane.getStyleClass().remove("console-scroll-pane-focus");

        scrollPane.getStyleClass().add("console-scroll-pane-focus");
        scrollPane.getStyleClass().remove("console-scroll-pane-unfocus");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sendButton.setDisable(false);
        inputField.setDisable(false);
        scrollPane.setDisable(false);
        // GraphicsToolkit.playFadeTransition(consoleTextArea, 0.0, 1.0, 2);
    }

    public void unfocusAction() {

        requestFocus();

        // Remove au préalable pour éviter le sur-ajout / clear sans clear les autres
        scrollPane.getStyleClass().remove("console-scroll-pane-unfocus");

        scrollPane.getStyleClass().add("console-scroll-pane-unfocus");
        scrollPane.getStyleClass().remove("console-scroll-pane-focus");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sendButton.setDisable(true);
        inputField.setDisable(true);
        scrollPane.setDisable(true);
        // GraphicsToolkit.playFadeTransition(consoleTextArea, 1.0, 0.0, 2);
    }

}
