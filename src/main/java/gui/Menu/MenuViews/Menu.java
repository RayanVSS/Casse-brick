package gui.Menu.MenuViews;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
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

    public default Label createLabel(String text, double rightMargin, double bottomMargin, double fontSize) {
        Label label = new Label(text);
        StackPane.setMargin(label, new Insets(0, rightMargin, bottomMargin, 0));
        label.setStyle("-fx-font-size: " + fontSize + "; -fx-text-fill: #d5bbb1;");
        return label;
    };

    public default ToggleButton createToggleButton(String text, boolean selected) {
        ToggleButton button = new ToggleButton(text);
        button.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button.setSelected(selected);
        return button;
    }

    public default Slider createSlider(double min, double max, double value,double maxwidth) {
        Slider slider = new Slider(min, max, value);
        slider.setMaxWidth(maxwidth);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setStyle("-fx-font-size: 20; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        return slider;
    }

}
