package gui.Menu.MenuViews;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public interface Menu {
    public default Button createButton(String text, double rightMargin, double bottomMargin) {
        Button button = new Button(text);
        StackPane.setMargin(button, new Insets(100, rightMargin, bottomMargin, 10));
        button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #d5bbb1;-fx-text-fill: #1b263b;");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        });
        return button;
    };

    public default Label createLabel(String text, double rightMargin, double bottomMargin) {
        Label label = new Label(text);
        StackPane.setMargin(label, new Insets(0, rightMargin, bottomMargin, 0));
        label.setStyle("-fx-font-size: 50; -fx-text-fill: black;");
        return label;
    };

}
