package gui.Menu.MenuViews;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public interface Menu {
    public default Button createButton(String text, double rightMargin, double bottomMargin) {
        Button button = new Button(text);
        // try {
        //     Font font = Font.loadFont(getClass().getResourceAsStream("/ressources/font.otf"), 20);
        //     if(font != null){
        //         button.setFont(font);
        //     }else{
        //         System.out.println("Font not found 1");
        //     }
        // } catch (Exception e) {
        //     System.out.println("Font not found 2");
        // }
        try {
        InputStream is = getClass().getResourceAsStream("/src/main/java/ressources/bla.ttf");
        if (is == null) {
            System.out.println("Font file not found");
        } else {
            Font font = Font.loadFont(is, 14);
            if (font == null) {
                System.out.println("Font not loaded");
            } else {
                button.setFont(font);
            }
        }
    } catch (Exception e) {
        System.out.println("Error loading font: " + e.getMessage());
    }
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
