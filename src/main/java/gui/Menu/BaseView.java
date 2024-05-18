package gui.Menu;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import utils.GameConstants;
import javafx.scene.layout.Region;

public class BaseView  {

    private LinearGradient linearGradient = null;
    private Pane pane;
    private Node[] n;

    public BaseView( Pane pane,Node... n) {
        this.pane = pane;
        this.n = n;
        switch (GameConstants.CSS) {
            case ACHROMATOPSIE:
                break;
            case BLACK:
                break;
            case CLASSIC:
                break;
            case DEUTERANOPIE:
                break;
            case LIGHT:
                break;
            case PINK:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#FEE5F2")),
                        new Stop(1, Color.web("#FFB6C1"))
                });
                break;
            case PROTANOPIE:
                break;
            case TRITANOPIE:
                break;
            default:
                break;
        }

        // Appliquer le LinearGradient à la scène
        pane.setBackground(new Background(new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Node node : n) {
            System.out.println("Node : " + node);
            ((Region) node).setBackground(
                    new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        System.out.println("BaseView created");
    }

    public void update() {
        switch (GameConstants.CSS) {
            case ACHROMATOPSIE:
                break;
            case BLACK:
                break;
            case CLASSIC:
                break;
            case DEUTERANOPIE:
                break;
            case LIGHT:
                break;
            case PINK:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#FEE5F2")),
                        new Stop(1, Color.web("#FFB6C1"))
                });
                break;
            case PROTANOPIE:
                break;
            case TRITANOPIE:
                break;
            default:
                break;
        }
        pane.setBackground(new Background(new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Node node : n) {
            System.out.println("Node : " + node);
            ((Region) node).setBackground(
                    new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        System.out.println("BaseView created");
    }



}
