package gui.Menu.MenuControllers;


import gui.App;
import gui.Menu.MenuViews.TutoView;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;


public class TutoController {
        private TutoView view;
        private boolean isFullScreen = false;
        private static BooleanProperty loaded = new SimpleBooleanProperty(false);


        public TutoController(Stage p, TutoView v, Scene scene) {
            this.view = v;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                view.getPrimaryStage().resizableProperty().set(false);
                App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");                
            }
            if (event.getCode() == KeyCode.F11) {
                if (!isFullScreen) {
                    view.getPrimaryStage().setFullScreen(true);
                    isFullScreen = true;
                    StackPane.setMargin(view.getWebView(), new Insets(0));
                } else {
                    view.getPrimaryStage().setFullScreen(false);
                    isFullScreen = false;
                    StackPane.setMargin(view.getWebView(), new Insets(15));

                }
            }
        });

        TutoController.loadedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    view.getWebEngine().load("https://docs.google.com/document/d/1Zz5YItH7HPHFEvk6UmS5MtY6LmpBHktmTzxJ-UXcSFc/edit?usp=sharing");
                }
            }
        });
        
        }

        public static void setLoaded(boolean loaded) {
            TutoController.loaded.set(loaded);
        }

        public static BooleanProperty loadedProperty() {
            return loaded;
        }

}
