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
    public static double DEFAULT_WINDOW_HEIGHT=800;
    public static double DEFAULT_WINDOW_WIDTH=1000;
    public static boolean PATH=true; 
    public static boolean RACKET=true;
    
    Stage primaryStage;
    Pane root;

    Outline outline;

    Ball ball;
    Circle graphBall;

    Preview preview;

    Racket racket;
    RacketGraphics graphRacket;
    Key key = new Key();
    public static Set<KeyCode> direction = new HashSet<KeyCode>();
    public static boolean BougePColision;

    double mouseX=0;
    double mouseY=0;
    boolean isMouseDraggingBall = false;


    public Simulation(Stage pStage, AppPhysic appPhysic) {

        //Initialisation de la fenetre

        this.primaryStage = pStage;
        root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
        primaryStage.setTitle("Simulation");

        //Initialisation de la simulation

        this.outline = AppPhysic.outline;

        if(RACKET){
            racket = new Racket(200, 20, 8, false, true){
                @Override
                public void handleKeyPress(Set<KeyCode> keysPressed) {
                    for (KeyCode key : keysPressed) {
                        if(key==GameConstants.LEFT){
                            if (this.mX() > -largeur / 2)
                                this.mX(this.mX() - speed);
                        }
                        if(key==GameConstants.RIGHT){
                            if (this.mX() < DEFAULT_WINDOW_WIDTH - longueur - 70)
                                this.mX(this.mX() + speed);
                        }
                        if(key==GameConstants.SPACE){
                            setlargeurP(true);
                            setVitesseP(true);
                        }
                    }
                }
                
                @Override
                public void handleKeyRelease(KeyCode event) {
                    switch (event) {
                    }
                }
            };

            graphRacket = new RacketGraphics(racket);
            graphRacket.setFill(Color.BLACK);
            root.getChildren().add(graphRacket);
        }

        ball = new Ball(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
        outline.getDirection(),1, outline.getRadius()){

            @Override
            public boolean movement(){
                double h = DEFAULT_WINDOW_WIDTH;
                double w = DEFAULT_WINDOW_HEIGHT;
                double newX = this.getC().getX() + this.getDirection().getX() + outline.getWind().getX() + outline.getFrictionRacket().getX();
                double newY = this.getC().getY() + this.getDirection().getY() + outline.getWind().getY() ;
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
                                    this.getDirection().setX(this.getDirection().getX());
                                    this.getDirection().setY(-this.getDirection().getY());
                                    outline.getFrictionRacket().setX(outline.getFrictionRacket().getX() + 0.5);
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed()+ outline.getFrictionRacket().getX();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    //this.getDirection().setX(this.getDirection().getX()+0.5);
        
                                    break;
                                case LEFT:
                                case Q:
                                    this.getDirection().setX(this.getDirection().getX());
                                    this.getDirection().setY(-this.getDirection().getY());
                                    outline.getFrictionRacket().setX(outline.getFrictionRacket().getX() - 0.5);
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed() + outline.getFrictionRacket().getX();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    //this.getDirection().setX(this.getDirection().getX()-0.5);
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
                    this.getDirection().setX(this.getDirection().getX()*outline.getRetention());
                }
                if (newY < 0 || newY > w - this.getRadius()) {
                    this.getDirection().setY(-this.getDirection().getY());
                    newY = this.getC().getY() + this.getDirection().getY();
                    this.getDirection().setY(this.getDirection().getY()*outline.getRetention());
                } 
                this.setC(new Coordinates(newX, newY));
                this.getDirection().add(outline.getWind());
                outline.checkGravity(ball.getC(), ball.getDirection());
                //outline.checkFrictionRacket();
                return true;
            }
        };

        //Initialisation de la partie graphique

        graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getRadius() * 2);
        root.getChildren().add(graphBall);
        primaryStage.show();

        preview = new Preview(ball, outline);
        preview.init_path(ball, root);

        //Initialisation des evenements de la souris

        graphBall.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            double ballX = ball.getC().getX();
            double ballY = ball.getC().getY();
            double distance = Math.sqrt(Math.pow(mouseX - ballX, 2) + Math.pow(mouseY - ballY, 2));
            if (distance <= ball.getRadius()+7) {
                isMouseDraggingBall = true;
                preview.clear_path(root);
            }
        });

        graphBall.setOnMouseDragged(event -> {
            if (isMouseDraggingBall) {
                ball.getC().setX(event.getSceneX());
                ball.getC().setY(event.getSceneY());
            }
        });

        graphBall.setOnMouseReleased(event -> {
            if (isMouseDraggingBall) {
                isMouseDraggingBall = false;
                double dx = (event.getSceneX() - mouseX)/40;
                double dy = (event.getSceneY() - mouseY)/40;
                Vector newVelocity = new Vector(new Coordinates(dx,dy));
                ball.setDirection(newVelocity);
                preview.init_path(ball, root);
            }
        });

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

        primaryStage.getScene().setOnKeyPressed(eWind -> {
            switch(eWind.getCode()){
                case ESCAPE:
                    animationTimer.stop();
                    primaryStage.setScene(appPhysic.getScene());
                    appPhysic.menu();
            }
        });

    }

    

    public void update() {
        // Mise à jour de la position de la balle et de la trajectoire
        if(!isMouseDraggingBall){
            if(ball.getCollisionR()){
                ball.movement();
                preview.init_path(ball, root);
            }
            else{
                ball.movement();
                preview.add_circle(root);
            }
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