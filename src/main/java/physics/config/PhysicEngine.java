package physics.config;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import physics.AppPhysic;
import physics.Preview;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

import gui.GraphicsFactory.RacketGraphics;
import utils.GameConstants;
import utils.Key;

/***************************************************************************
 *                  Explication de classe Simulation                       
 * 
 * @var physics contient les informations de la simulation
 * @var Ball ball : La balle de la simulation 
 * @var Racket racket : La raquette de la simulation
 * 
 * @param primaryStage : la fenetre de la simulation
 * @param physics : les informations de la simulation
 * 
 * @fonction update : permet de mettre à jour la position de la balle et de la raquette
 * 
 * @author Ilias Bencheikh 
 **************************************************************************/

public class PhysicEngine {

    //Initialisation des objets
    public static double DEFAULT_WINDOW_HEIGHT=800;
    public static double DEFAULT_WINDOW_WIDTH=1000;
    public static boolean PATH=true; 
    public static boolean RACKET=true;
    
    Stage primaryStage;
    Pane root;

    PhysicSetting physics;

    Ball ball;
    Circle graphBall;

    Preview preview;

    Racket racket;
    RacketGraphics graphRacket;
    Key key = new Key();

    public static Set<KeyCode> direction = new HashSet<KeyCode>();
    public static boolean BougePColision;
    public static boolean CollisionR_Side = false;

    double mouseX=0;
    double mouseY=0;
    boolean isMouseDraggingBall = false;


    public PhysicEngine(Stage pStage, AppPhysic appPhysic) {

        //Initialisation de la fenetre

        this.primaryStage = pStage;
        root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));

        //Initialisation de la simulation

        this.physics = AppPhysic.physics;

        ball = init_ball();

        //Initialisation de la partie graphique

        if(RACKET){
            racket=init_racket();
            graphRacket = new RacketGraphics(racket);
            graphRacket.setFill(Color.BLACK);
            root.getChildren().add(graphRacket);
        }

        graphBall = new Circle(ball.getC().getX(), ball.getC().getY(), ball.getRadius() * 2);
        root.getChildren().add(graphBall);
        primaryStage.show();

        //Initialisation de la trajectoire

        preview = new Preview(ball, physics,root);
        preview.init_path();

        //Initialisation des evenements de la souris

        graphBall.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            double ballX = ball.getC().getX();
            double ballY = ball.getC().getY();
            double distance = Math.sqrt(Math.pow(mouseX - ballX, 2) + Math.pow(mouseY - ballY, 2));
            if (distance <= ball.getRadius()+10) {
                isMouseDraggingBall = true;
                preview.clear_path(root);
                physics.restore();
            }
        });

        graphBall.setOnMouseDragged(event -> {
            if (isMouseDraggingBall) {
                if(ball.getC().getX() > ball.getRadius() && ball.getC().getX() < DEFAULT_WINDOW_WIDTH -ball.getRadius() && ball.getC().getY() > ball.getRadius() && ball.getC().getY() < DEFAULT_WINDOW_HEIGHT-ball.getRadius()){
                    ball.getC().setX(event.getSceneX());
                    ball.getC().setY(event.getSceneY());
                }
            }
        });

        graphBall.setOnMouseReleased(event -> {
            if (isMouseDraggingBall) {
                isMouseDraggingBall = false;
                double dx = (event.getSceneX() - mouseX)/40;
                double dy = (event.getSceneY() - mouseY)/40;
                Vector newVelocity = new Vector(new Coordinates(dx,dy));
                ball.setDirection(newVelocity);
                preview.init_path();
            }
        });

        //Initialisation de l'animation

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                KeyPressed();
                if(RACKET){
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

            public void KeyPressed() {
                if(key.getKeysPressed().contains(KeyCode.ESCAPE)){
                    stop();
                    primaryStage.setScene(appPhysic.getScene());
                    appPhysic.menu();
                }
            }
        };

        animationTimer.start();
    }

    

    public void update() {
        // Mise à jour de la position de la balle et de la trajectoire
        if(!isMouseDraggingBall){
            ball.movement();
            preview.add_circle();
            //preview.check();
            //check_ball();
        }

        graphBall.setCenterX(ball.getC().getX());
        graphBall.setCenterY(ball.getC().getY());

        // Mise à jour de la position de la raquette
        if(RACKET){
            update_racket();
        }
    }

    public Ball init_ball(){
        return new Ball(new Coordinates(primaryStage.getWidth() / 2, primaryStage.getHeight() / 2),
        physics.randomDirection(),1, physics.getRadius()){

            @Override
            public boolean movement(){
                double h = DEFAULT_WINDOW_WIDTH;
                double w = DEFAULT_WINDOW_HEIGHT;
                double newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed() ;
                double newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed() ;
                if (CollisionR) {
                    if (BougePColision || CollisionR_Side) {
                        this.getDirection().setY(-this.getDirection().getY());
                        this.getDirection().add(physics.getFrictionRacket());
                        newY = this.getC().getY() + this.getDirection().getY();
                        CollisionR = false;
                        CollisionR_Side = false;
                        physics.UpdateFrictionRacket();
                        preview.init_path();
                    }
                    else {
                        for (KeyCode key : direction) {
                            switch (key) {
                                case RIGHT:
                                case D:
                                    this.getDirection().setY(-this.getDirection().getY());
                                    physics.getFrictionRacket().setX(physics.getFrictionRacket().getX() + 1);
                                    this.getDirection().add(physics.getFrictionRacket());
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    preview.init_path();
                                    break;
                                case LEFT:
                                case Q:
                                    this.getDirection().setY(-this.getDirection().getY());
                                    physics.getFrictionRacket().setX(physics.getFrictionRacket().getX()-1);
                                    this.getDirection().add(physics.getFrictionRacket());
                                    newX = this.getC().getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getC().getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    preview.init_path();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }   
                if (newX < 0 || newX > h - this.getRadius()) {
                    this.getDirection().setX(-this.getDirection().getX());
                    this.getDirection().add(physics.getFrictionRacket());
                    physics.UpdateFrictionRacket();
                    newX = this.getC().getX() + this.getDirection().getX()*this.getSpeed();
                    this.getDirection().setX(this.getDirection().getX()*physics.getRetention());
                }
                if (newY < 0 || newY > w - this.getRadius()) {
                    this.getDirection().add(physics.getFrictionRacket());
                    this.getDirection().setY(-this.getDirection().getY());
                    physics.UpdateFrictionRacket();
                    newY = this.getC().getY() + this.getDirection().getY()* this.getSpeed();
                    this.getDirection().setY(this.getDirection().getY()*physics.getRetention());
                } 
                this.setC(new Coordinates(newX, newY));
                this.getDirection().add(physics.getWind());
                physics.checkGravity(ball.getC(), ball.getDirection());
                return true;
            }
        };
    }

    public Racket init_racket(){
        return new Racket(200, 20, 8, false, true){
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
                // Fonction non utilisée
            }
        };
    }

    public void update_racket(){
        primaryStage.getScene().setOnKeyReleased(eWind -> {
            key.getKeysPressed().remove(eWind.getCode());
        });
        BougePColision = key.isEmpty();
        direction = key.getKeysPressed();
        if (physics.checkCollisionRacket(racket, ball.getC(), ball.getDirection())){
            ball.setCollisionR(true);
        }
        preview.update(racket);
        primaryStage.getScene().setOnKeyPressed(eWind -> {
            key.getKeysPressed().add(eWind.getCode());
        });
        graphRacket.update();
    }

    public void check_ball(){
       if(ball.getC().getX()+ball.getRadius() > DEFAULT_WINDOW_WIDTH || ball.getC().getX()+ball.getRadius() < 0 || ball.getC().getY()+ball.getRadius() > DEFAULT_WINDOW_HEIGHT || ball.getC().getY()+ball.getRadius() < 0){
            ball.setC(new Coordinates(DEFAULT_WINDOW_WIDTH/2,DEFAULT_WINDOW_HEIGHT/2));
       }
    }
}