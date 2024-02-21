package physics;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import entity.ball.Ball;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import gui.GraphicsFactory.RacketGraphics;
import utils.GameConstants;
import utils.Key;

/***************************************************************************
 *                  Explication de classe Simulation                       
 * 
 * @var Outline contient les informations de la simulation
 * @var Ball ball : La balle de la simulation 
 * @var Racket racket : La raquette de la simulation
 * 
 * @param primaryStage : la fenetre de la simulation
 * @param outline : les informations de la simulation
 * 
 * @fonction update : permet de mettre à jour la position de la balle et de la raquette
 * 
 * @author Ilias Bencheikh 
 **************************************************************************/

public class Simulation {

    //Initialisation des objets

    Stage primaryStage;
    Pane root;
    public static boolean PATH=false; 
    public static boolean RACKET=false;

    Outline outline;

    Ball ball;
    Circle graphBall;
    Preview preview;
    Racket racket;
    RacketGraphics graphRacket;
    Key key = new Key();
    public static Set<KeyCode> direction = new HashSet<KeyCode>();
    public static boolean BougePColision;

    // variables pour la trajectoire

    public Simulation(Stage pStage) {

        //Initialisation de la fenetre

        this.primaryStage = pStage;
        root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT));
        primaryStage.setTitle("Simulation");

        //Initialisation de la simulation

        this.outline = AppPhysic.outline;

        if(RACKET){
            racket = new ClassicRacket();
            graphRacket = new RacketGraphics(racket);
            graphRacket.setFill(Color.BLACK);
            root.getChildren().add(graphRacket);
        }

        ball = new Ball(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
        outline.getDirection(),1, outline.getDiametre()){

            @Override
            public boolean movement(){
                double h = GameConstants.DEFAULT_WINDOW_WIDTH;
                double w = GameConstants.DEFAULT_WINDOW_HEIGHT;
                double newX = this.getC().getX() + this.getDirection().getX() + outline.getWind().getX();
                double newY = this.getC().getY() + this.getDirection().getY() + outline.getWind().getY();
                if (CollisionR) {
                    if (BougePColision) {
                        this.getDirection().setY(-this.getDirection().getY());
                        newY = this.getC().getY() + this.getDirection().getY();
                        CollisionR = false;
                    }
                    if (!BougePColision) {
                        for (KeyCode key : direction) {
                            switch (key) {
                                case RIGHT:
                                case D:
                                    this.getDirection().setX(1);
                                    this.getDirection().setY(-1);
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
        
                                    break;
                                case LEFT:
                                case Q:
                                    this.getDirection().setX(-1);
                                    this.getDirection().setY(-1);
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                if (newX < 0 || newX > h - this.getRadius()) {
                    this.getDirection().setX(-this.getDirection().getX());
                    newX = this.getC().getX() + this.getDirection().getX();
                }
                if (newY < 0 || newY > w - this.getRadius()) {
                    this.getDirection().setY(-this.getDirection().getY());
                    newY = this.getC().getY() + this.getDirection().getY();
                }

                this.setC(new Coordinates(newX, newY));
                outline.checkGravity(ball.getC(), ball.getDirection());
                this.getDirection().add(outline.getWind());
                return true;
            }
        };

        //Initialisation de la partie graphique

        graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getRadius() * 2);
        root.getChildren().add(graphBall);
        primaryStage.show();

        preview = new Preview(ball, outline);
        preview.init_path(ball, root);

        //Initialisation de l'animation

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                if(RACKET){
                    BougePColision = key.isEmpty();
                    racket.handleKeyPress(key.getKeysPressed());
                }
                if (last == 0) {
                    last = now;
                    return;
                }
                var deltaT = now - last;
                if (deltaT > 1000000000 / 120) { 
                    update();
                }
                last = now;
            }
        };
        animationTimer.start();

    }

    public void update() {

        // Mise à jour de la position de la balle et de la trajectoire

        if(ball.getCollisionR()){
            ball.movement();
            preview.init_path(ball, root);
        }
        else{
            ball.movement();
            preview.add_circle(root);
        }


        graphBall.setCenterX(ball.getC().getX());
        graphBall.setCenterY(ball.getC().getY());

        // Mise à jour de la position de la raquette

        if (RACKET) {
            primaryStage.getScene().setOnKeyReleased(eWind -> {
                key.getKeysPressed().remove(eWind.getCode());
            });
            BougePColision = key.isEmpty();
            direction = key.getKeysPressed();
            if (racket.CollisionRacket(ball)) {
                ball.setCollisionR(true);
            }
            if (RACKET) {
                primaryStage.getScene().setOnKeyPressed(eWind -> {
                    key.getKeysPressed().add(eWind.getCode());
                });
            }
            graphRacket.update();
        }
    }

}