package physics.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import config.Game;
import entity.brick.BrickClassic;
import entity.racket.CircleRacket;
import entity.racket.ClassicRacket;
import entity.racket.DegradeRacket;
import entity.racket.DiamondRacket;
import entity.racket.YNotFixeRacket;
import gui.GraphicsFactory.RacketGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import physics.AppPhysic;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import javafx.scene.paint.Color;

import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import physics.gui.ToolBox;
import physics.gui.Preview;
import physics.gui.ToolBox;
import utils.GameConstants;
import utils.Key;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/***************************************************************************
 * Explication de classe PhysicEngine :
 * 
 * @var physics contient les informations de la simulation
 * @var Ball ball : La balle de la simulation
 * @var Racket racket : La raquette de la simulation
 * 
 * @param primaryStage : la fenetre de la simulation
 * @param physics      : les informations de la simulation
 * 
 * @fonction update : permet de mettre à jour la position de la balle et de la
 *           raquette et de briques
 * 
 * @author Ilias Bencheikh , Olivier Guan
 **************************************************************************/

public class PhysicEngine {

    //Initialisation des objets
    public static boolean PATH=true; 
    public static boolean RACKET=true;
    public static double start_border=0;
    
    private Stage primaryStage;
    private Pane root;
    private ToolBox toolBox;

    PhysicSetting physics;

    Map<Ball,BallGraphics> listball=new HashMap<>();
    Map<Brick,BricksGraphics> listbrick=new HashMap<>();
    BallGraphics graphBall;

    public static Racket racket;
    public static RacketGraphics graphRacket;
    Key key = new Key();

    public static Set<KeyCode> direction = new HashSet<KeyCode>();
    public static boolean BougePColision;

    public static boolean Pause = false;

    public PhysicEngine(Stage pStage, AppPhysic appPhysic) {
        clear();

        // Initialisation de la fenetre

        this.primaryStage = pStage;
        root = new Pane();
        primaryStage.setScene(new javafx.scene.Scene(root, PhysicSetting.DEFAULT_WINDOW_WIDTH, PhysicSetting.DEFAULT_WINDOW_HEIGHT));

        // Initialisation de la simulation

        this.physics = AppPhysic.physics;

        Ball ball = init_ball(null,null);
        graphBall = new BallGraphics("src/main/ressources/balle/classic/classic.png",ball);
        root.getChildren().add(graphBall);
        listball.put(ball, graphBall);

        listbrick = new HashMap<>();

        // Initialisation de la barre d'option
        
        toolBox = new ToolBox(root,listball,listbrick,this);

        if(RACKET){
            racket=init_racket("rectangle");
            graphRacket = new RacketGraphics(racket, racket.getShapeType());
            graphRacket.getShape().setFill(Color.BLACK);
            root.getChildren().add(graphRacket.getShape());
        }
        primaryStage.show();

        // Initialisation de la trajectoire

        ball.setPreview(new Preview(ball, physics, root));
        ball.getPreview().init_path();

        //Initialisation des evenements de la souris

        setTakeBall(graphBall,ball,toolBox,root);

        // Initialisation de l'animation

        AnimationTimer animationTimer = new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                KeyPressed();
                if (racket != null) {
                    racket.handleKeyPress(key.getKeysPressed());
                }
                if (last == 0) {
                    last = now;
                    return;
                }
                var deltaT = (now - last)/5000000;
                if(deltaT>1){
                    update(deltaT);
                }
                last = now;
            }

            public void KeyPressed() {
                if (key.getKeysPressed().contains(KeyCode.ESCAPE)) {
                    stop();
                    primaryStage.setScene(appPhysic.getScene());
                    appPhysic.menu();
                } else if (key.getKeysPressed().contains(KeyCode.M)) {
                    toolBox.update();
                    key.getKeysPressed().remove(KeyCode.M);
                }
            }
        };

        animationTimer.start();
    }

    public void update(Long deltaT) {
        if(Pause){
            return;
        }
        if(!listball.isEmpty()){
            if(listball.keySet().iterator().next().getPreview()==null){
                Ball ball = listball.keySet().iterator().next();
                ball.setPreview(new Preview(ball, physics, root));
                ball.getPreview().init_path();
            }
        }
        // Mise à jour de la position de la balle et de la trajectoire
        for(Ball b : listball.keySet()){
            if(!listball.get(b).IsMouseDraggingBall()){
                b.checkCollisionOtherBall(listball.keySet());
                b.checkCollision(listbrick.keySet());
                b.movement(deltaT);
                if(b.getPreview()!=null){
                    b.updatePreview(listball.keySet());
                }
            }
        }
        

        for(Ball b : listball.keySet()){
            listball.get(b).update();
            b.CollisionB=false;
        }

        // Mise à jour de la position des briques
        for(Brick b : listbrick.keySet()){
            listbrick.get(b).update();
        }

        // Mise à jour de la position de la raquette
        if(RACKET){
            update_racket();
        }
        
        if(toolBox.isBar()){
            toolBox.updateData();
        }
    }

    public static Brick init_brick(Coordinates c) {
        return new BrickClassic(c);
    }

    public static Ball init_ball(Coordinates c,Vector v) {
        if(c==null){
            c=PhysicSetting.DEFAULT_BALL_START_COORDINATES.clone();
        }
        if(v==null){
            v=PhysicSetting.NEW_BALL_DIRECTION();
        }
        PhysicSetting physics = AppPhysic.physics;
        return new Ball(c,
        v,1, physics.getRadius()){
            @Override
            public void movement(long deltaT) {
                double h = PhysicSetting.DEFAULT_WINDOW_HEIGHT;
                double w = PhysicSetting.DEFAULT_WINDOW_WIDTH;
                double newX = this.getX() + this.getDirection().getX() * this.getSpeed() ;
                double newY = this.getY() + this.getDirection().getY() * this.getSpeed() ;
                if (CollisionR) {
                    if (BougePColision || CollisionR_Side) {
                        this.getDirection().setY(-this.getDirection().getY()
                                + (this.getRotation().getAngle()) / 90 * this.getDirection().getY());
                        this.getRotation().Collision();
                        newY = this.getY() + this.getDirection().getY() * this.getSpeed();
                        CollisionR = false;
                        CollisionR_Side = false;
                    } else {
                        for (KeyCode key : direction) {
                            switch (key) {
                                case RIGHT:
                                case D:
                                    this.getDirection().setY(-this.getDirection().getY()
                                            + (this.getRotation().getAngle()) / 90 * this.getDirection().getY());
                                    this.getRotation().addEffect('d');
                                    newX = this.getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    break;
                                case LEFT:
                                case Q:
                                    this.getDirection().setY(-this.getDirection().getY()
                                            + (this.getRotation().getAngle()) / 90 * this.getDirection().getY());
                                    this.getRotation().addEffect('g');
                                    newX = this.getX() + this.getDirection().getX() * this.getSpeed();
                                    newY = this.getY() + this.getDirection().getY() * this.getSpeed();
                                    CollisionR = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }   
                else if (newX < start_border || newX > w - this.getRadius()) {
                    this.getDirection().setX(-this.getDirection().getX());
                    this.getDirection().setY(this.getDirection().getY()+(this.getRotation().getAngle())/90*this.getDirection().getY());
                    this.getRotation().Collision();
                    newX = this.getX() + this.getDirection().getX() * this.getSpeed();
                    this.getDirection().setX(this.getDirection().getX());
                }
                else if (newY < 0 || newY > h - this.getRadius()) {
                    this.getDirection().setY(-this.getDirection().getY()
                            + (this.getRotation().getAngle()) / 90 * this.getDirection().getY());
                    this.getRotation().Collision();
                    newY = this.getY() + this.getDirection().getY() * this.getSpeed();
                    this.getDirection().setY(this.getDirection().getY());
                }
                
                this.setC(new Coordinates(newX, newY));
                this.getDirection().add(physics.getWind());
                physics.checkGravity(this.getC(), this.getDirection());
            }
        };
    }

    public static Racket init_racket(String type) {
        /* 
        int longueur=0;
        int largeur=0;
        if(type.equals("rectangle")){
            longueur=200;
            largeur=20;
        }
        else if(type.equals("losange")){
            longueur=200;
            largeur=40;
        }
        else if(type.equals("rond")){
            longueur=200;
            largeur=200;
        }
        else if(type.equals("triangle")){
            longueur=200;
            largeur=200;
        }
        return new Racket(longueur, largeur, type, 8, false, true) {
            @Override
            public void handleKeyPress(Set<KeyCode> keysPressed) {
                for (KeyCode key : keysPressed) {
                    if(key==KeyCode.LEFT){
                        if (this.mX() >start_border +longueur / 2 )
                            this.mX(this.mX() - speed);
                    }
                    if(key==KeyCode.RIGHT){
                        if (this.mX() < PhysicSetting.DEFAULT_WINDOW_WIDTH - longueur - 70)
                            this.mX(this.mX() + speed);
                    }
                }
            }

            @Override
            public void handleKeyRelease(KeyCode event) {
                // Fonction non utilisée
            }
        };
        */
        if(type.equals("YnotFixe")){
            return new YNotFixeRacket();
        }
        else if(type.equals("losange")){
            return new DiamondRacket();
        }
        else if(type.equals("rond")){
            return new CircleRacket();
        }
        else if(type.equals("triangle")){
            return new DegradeRacket();
        }
        else{
            return new ClassicRacket();
        }
    }

    public void update_racket() {
        if(racket==null || Pause){
            return;
        }
        primaryStage.getScene().setOnKeyReleased(eWind -> {
            key.getKeysPressed().remove(eWind.getCode());
        });
        BougePColision = key.isEmpty();
        direction = key.getKeysPressed();
        for (Ball ball : listball.keySet()) {
            if (physics.checkCollisionRacket(racket, ball)) {
                ball.setCollisionR(true);
            }
            if (ball.getPreview() != null) {
                ball.getPreview().update(racket);
            }
        }
        primaryStage.getScene().setOnKeyPressed(eWind -> {
            key.getKeysPressed().add(eWind.getCode());
        });
        graphRacket.updatePos();
    }

    public static void setTakeBall(BallGraphics g,Ball b,ToolBox toolBox,Pane root){
        g.setOnMousePressed(event -> {
            if(!toolBox.isBar() || Pause){
                g.setMouseXY(event.getSceneX(),event.getSceneY());
                double ballX = b.getC().getX();
                double ballY = b.getC().getY();
                double distance = Math.sqrt(Math.pow(g.getMouseX() - ballX, 2) + Math.pow(g.getMouseY() - ballY, 2));
                if (distance <= b.getRadius()+10) {
                    g.setMouseDraggingBall(true);
                    if(b.getPreview()!=null){
                        b.getPreview().clear_path(root);
                    }
                }
            }
        });

        g.setOnMouseDragged(event -> {
            if (g.IsMouseDraggingBall() && (!toolBox.isBar()|| Pause)) {
                if(event.getSceneX() > start_border+b.getRadius() && event.getSceneX() < PhysicSetting.DEFAULT_WINDOW_WIDTH-b.getRadius() && event.getSceneY() > b.getRadius() && event.getSceneY() < PhysicSetting.DEFAULT_WINDOW_HEIGHT-b.getRadius()){
                    b.getC().setX(event.getSceneX());
                    b.getC().setY(event.getSceneY());
                }
            }
        });

        g.setOnMouseReleased(event -> {
            if (g.IsMouseDraggingBall()&& !toolBox.isBar()) {
                g.setMouseDraggingBall(false);
                double dx = (event.getSceneX() - g.getMouseX()) / 40;
                double dy = (event.getSceneY() - g.getMouseY()) / 40;
                if (dx > 7) {
                    dx = 7;
                }
                if (dy > 7) {
                    dy = 7;
                }
                b.getRotation().stopRotation();
                b.setDirection(new Vector(new Coordinates(dx, dy)));
                if(b.getPreview()!=null){
                    b.getPreview().init_path();
                }
            }
        });
    }

    public static void setTakeBrick(BricksGraphics g,Brick b,ToolBox toolBox,Pane root){
        g.setOnMousePressed(event -> {
            if(!toolBox.isBar() || Pause){
                g.setMouseXY(event.getSceneX(),event.getSceneY());
                double brickX = b.getC().getX();
                double brickY = b.getC().getY();
                double distance = Math.sqrt(Math.pow(g.getMouseX() - brickX, 2) + Math.pow(g.getMouseY() - brickY, 2));
                if (distance <= GameConstants.BRICK_WIDTH+10) {
                    g.setMouseDraggingBall(true);
                }
            }
        });

        g.setOnMouseDragged(event -> {
            if (g.IsMouseDraggingBall() && (!toolBox.isBar()|| Pause)) {
                if(event.getSceneX() > start_border+GameConstants.BRICK_WIDTH && event.getSceneX() < PhysicSetting.DEFAULT_WINDOW_WIDTH-GameConstants.BRICK_WIDTH && event.getSceneY() > GameConstants.BRICK_HEIGHT && event.getSceneY() < PhysicSetting.DEFAULT_WINDOW_HEIGHT-GameConstants.BRICK_HEIGHT){
                    b.getC().setX(event.getSceneX());
                    b.getC().setY(event.getSceneY());
                }
            }
        });

        g.setOnMouseReleased(event -> {
            if (g.IsMouseDraggingBall()&& (!toolBox.isBar()|| Pause)) {
                g.setMouseDraggingBall(false);
            }
        });
    }

    public void addBall(Ball ball){
        BallGraphics graphBall = new BallGraphics(ball);
        root.getChildren().add(graphBall);
        listball.put(ball, graphBall);
    }

    public void clear(){
        direction.clear();
        Pause = false;
        BougePColision = false;
    }
}