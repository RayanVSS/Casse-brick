package gui.Menu.MenuViews;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import gui.Menu.Menu;

public class TutoView implements Menu {
    private Stage primaryStage;
    private StackPane root;
    private Scene scene;
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();


    public TutoView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new StackPane();
        scene = new Scene(root, 1100, 800); // Set initial width and height
        // Make WebView fill the StackPane
        StackPane.setMargin(webView, new Insets(10)); // Optional: add margin
        // Make WebView fill the AnchorPane
        AnchorPane.setTopAnchor(webView, 0.0);
        AnchorPane.setBottomAnchor(webView, 0.0);
        AnchorPane.setLeftAnchor(webView, 0.0);
        AnchorPane.setRightAnchor(webView, 0.0);
        webEngine.load("https://docs.google.com/document/d/1Zz5YItH7HPHFEvk6UmS5MtY6LmpBHktmTzxJ-UXcSFc/edit?usp=sharing");
        root.getChildren().add(webView);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.close();
            }
        });
    }

    
    // Getters
    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
