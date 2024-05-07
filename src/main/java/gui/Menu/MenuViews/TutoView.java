package gui.Menu.MenuViews;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import gui.ViewPosition;
import gui.Menu.Menu;
import gui.Menu.MenuControllers.TutoController;

public class TutoView implements Menu, ViewPosition {
    private Stage primaryStage;
    private StackPane root;
    private Scene scene;
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    public TutoView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new StackPane();
        scene = new Scene(root, 1100, 800);
        StackPane.setMargin(webView, new Insets(15));
        root.getChildren().add(webView);
        new TutoController(primaryStage, this, scene);
    }

    // Getters
    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public WebView getWebView() {
        return webView;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

}
