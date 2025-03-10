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

/**
 * Cette classe sert à gérer le background de tout les panes du jeu pour avoir
 * le dégradé de couleur.
 * 
 * @author Benmalek Majda
 */
public class BaseView {

    private LinearGradient linearGradient = null;
    private Pane pane;
    private Node[] n;

    /**
     * Constructeur de la classe BaseView.
     * Initialise la vue de base avec les éléments nécessaires.
     *
     * @param pane Le pane sur lequel la vue de base sera affichée.
     * @param n Les noeuds qui seront affichés sur le pane.
     */
    public BaseView(Pane pane, Node... n) {
        this.pane = pane;
        this.n = n;
        update();
    }

    /**
     * Met a jour pour géré le changement de themes.
     * Cette méthode est appelée pour rafraîchir l'affichage.
     */
    public void update() {
        setLinear();
        pane.setBackground(new Background(new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        for (Node node : n) {
            ((Region) node).setBackground(
                    new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }


    /**
     * Définit le gradient linéaire.
     * Cette méthode est appelée pour définir le gradient linéaire en fonction du CSS actuel.
     */
    public void setLinear() {
        switch (GameConstants.CSS) {
            case ACHROMATOPSIE:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#FFFFFF")),
                        new Stop(1, Color.web("#808080"))
                });
                break;
            case BLACK:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#6A6A6A")),
                        new Stop(1, Color.web("#282C35"))
                });
                break;
            case CLASSIC:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#B9D9EB")),
                        new Stop(1, Color.web("#273654"))
                });
                break;
            case DEUTERANOPIE:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#6A6A6A")),
                        new Stop(1, Color.web("#282C35"))
                });
                break;
            case LIGHT:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#E1E8ED")),
                        new Stop(1, Color.web("#1DA1F2"))
                });
                break;
            case PINK:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#FEE5F2")),
                        new Stop(1, Color.web("#FFB6C1"))
                });
                break;
            case PROTANOPIE:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#C0C0C0")),
                        new Stop(1, Color.web("#282C35"))
                });
                break;
            case TRITANOPIE:
                linearGradient = new LinearGradient(1, 1, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#B3B3B3")),
                        new Stop(1, Color.web("#333333"))
                });
                break;
            default:
                break;
        }
    }
}