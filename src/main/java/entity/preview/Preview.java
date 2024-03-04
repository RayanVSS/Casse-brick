package entity.preview;

import config.Game;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import utils.GameConstants;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.*;
import entity.ball.*;

public class Preview extends Circle {
    private List<Circle> trajectoryPoints; // Liste des points de trajectoire
    private Circle point;
    private Ball invisibleBall; // Balle invisible
    private Boolean isPreview = false;

    public Preview(Ball invisibleBall) {
        if (invisibleBall instanceof ClassicBall) {
            this.invisibleBall = new ClassicBall();
        } else if (invisibleBall instanceof HyperBall) {
            this.invisibleBall = new HyperBall();
        } else if (invisibleBall instanceof GravityBall) {
            this.invisibleBall = new GravityBall();
        } else {
            System.err.println("Error: erreur ball pour la preview");
        }
        this.invisibleBall.setSpeed(10);
        //changer la couleur de la balle en invisible
        // this.invisibleBall.setRadius(10);
        // Initialisation de la liste des points de trajectoire
        trajectoryPoints = new ArrayList<>();
    }

    //mouvemnt de la balle invisible
    public void movementBis(Pane root) {
        // if (Game.getBall().getCollisionR() || Game.getCollide()){
        //     System.out.println("collision racketaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAa");
        //     invisibleBall.setC(Game.getBall().getC());
        //     clearTrajectory(root);
        // } else {
        //     mouvementa();
        // }
    }

    public void mouvementa() {
        // if(Game.getBall().getCollisionR()){
        //     Game.getBall().setCollisionR(false);
        // }
        //     double h = GameConstants.DEFAULT_WINDOW_HEIGHT;
        //     double w = GameConstants.DEFAULT_WINDOW_WIDTH;
        //     double newX = invisibleBall.getC().getX() + invisibleBall.getDirection().getX() * invisibleBall.getSpeed();
        //     double newY = invisibleBall.getC().getY() + invisibleBall.getDirection().getY() * invisibleBall.getSpeed();
        //     if (newX < 0 || newX > w - invisibleBall.getRadius()) {
        //         invisibleBall.getDirection().setX(-invisibleBall.getDirection().getX());
        //         newX = invisibleBall.getC().getX() + invisibleBall.getDirection().getX() * invisibleBall.getSpeed();
        //     }
        //     if (newY < 0) {
        //         invisibleBall.getDirection().setY(-invisibleBall.getDirection().getY());
        //         newY = invisibleBall.getC().getY() + invisibleBall.getDirection().getY() * invisibleBall.getSpeed();            
        //     }
        //     invisibleBall.setC(new Coordinates(newX, newY));
    }

    //méthode pour set le point
    public void setDot(Pane root) {
        if (!isPreview) {
            isPreview = true;
            dot(root);
        }
    }

    //timer pour updateTrajectory
    public void dot(Pane root) {
        Timer Timer = new Timer();
        Timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateTrajectory(invisibleBall.getC(), root);
                    isPreview = false;
                    Timer.cancel();
                });
            }
        }, 100);
        if (point != null) {
            if (!root.getChildren().contains(point)) {
                root.getChildren().add(point);
            }
        }
    }

    // Méthode pour mettre à jour la trajectoire et les points de trajectoire
    public void updateTrajectory(Coordinates currentPosition, Pane root) {
        // Ajouter le point actuel à la trajectoire
        Platform.runLater(() -> {
            point = new Circle(currentPosition.getX(), currentPosition.getY(), 2); // Créer un point de 2 pixels
            point.setFill(Color.BLUE); // Couleur du point
            trajectoryPoints.add(point); // Ajouter le point à la liste des points de trajectoire
        });
    }

    // Méthode pour effacer la trajectoire
    public void clearTrajectory(Pane root) {
        for (Circle point : trajectoryPoints) {
            if (point != null)
                root.getChildren().remove(point);
        }
    }

    public Ball getInvisibleBall() {
        return invisibleBall;
    }

    public Circle getPoint() {
        return point;
    }
}
