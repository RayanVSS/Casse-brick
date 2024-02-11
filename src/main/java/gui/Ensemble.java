package gui;

import entite.Brique;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Ensemble extends Group {
    public GridPane grid;

    public Ensemble(Brique[][] tab) {
        this.grid = new GridPane();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                Image image = ImageLoader.loadImage("src/main/ressources/briquee.png");
                BricksGraphics brique = new BricksGraphics(image, tab[i][j]);
                brique.setVisible(true);
                this.grid.add(brique, j, i);
            }

        }
        this.getChildren().add(grid);
        this.grid.setVisible(true);
    }

    public void update() {
        for (int i = 0; i < grid.getChildren().size(); i++) {
            BricksGraphics b = (BricksGraphics) grid.getChildren().get(i);
            b.update();
        }
    }
}
