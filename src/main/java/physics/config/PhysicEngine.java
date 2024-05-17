package physics.config;

import java.util.Map;
import java.util.Iterator;

import entity.brick.BrickClassic;
import entity.racket.CircleRacket;
import entity.racket.ClassicRacket;
import entity.racket.DegradeRacket;
import entity.racket.DiamondRacket;
import entity.racket.YNotFixeRacket;
import gui.GraphicsFactory.RacketGraphics;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import physics.AppPhysic;
import physics.entity.Ball;
import physics.entity.Brick;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Segment;
import physics.geometry.Vector;
import javafx.scene.paint.Color;

import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.BricksGraphics;
import physics.gui.ToolBox;
import physics.gui.Preview;
import utils.GameConstants;
import utils.Key;
import java.util.ArrayList;
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

    public static ArrayList<Segment> LIMIT_SIMULATION = new ArrayList<Segment>() {
        {
            add(new Segment(new Coordinates(0,0),new Coordinates(PhysicSetting.DEFAULT_WINDOW_WIDTH,0)));
            add(new Segment(new Coordinates(0,0),new Coordinates(0,PhysicSetting.DEFAULT_WINDOW_HEIGHT)));
            add(new Segment(new Coordinates(PhysicSetting.DEFAULT_WINDOW_WIDTH,0),new Coordinates(PhysicSetting.DEFAULT_WINDOW_WIDTH,PhysicSetting.DEFAULT_WINDOW_HEIGHT)));
            add(new Segment(new Coordinates(0,PhysicSetting.DEFAULT_WINDOW_HEIGHT),new Coordinates(PhysicSetting.DEFAULT_WINDOW_WIDTH,PhysicSetting.DEFAULT_WINDOW_HEIGHT)));
        }
};
    
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
        graphBall = new BallGraphics(new Image("src/main/ressources/balle/classic/classic.png"),ball);
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
                else if(key.getKeysPressed().contains(KeyCode.P)){
                    Pause = !Pause;
                    key.getKeysPressed().remove(KeyCode.P);
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
            Ball ball = listball.keySet().iterator().next();
            if(ball.getPreview()==null){
                ball.setPreview(new Preview(ball, physics, root));
                ball.getPreview().init_path();
            }
        }

        for(Ball b : listball.keySet()){
            if(!listball.get(b).IsMouseDraggingBall()){
                b.checkCollisionOtherBall(listball.keySet());
                if(b.getPreview()!=null){
                    b.getPreview().checkCollisionOtherBall(b);
                }
            }
        }

        // Mise à jour de la position de la balle et de la trajectoire
        for(Ball b : listball.keySet()){
            if(!listball.get(b).IsMouseDraggingBall()){
                b.checkCollision(listbrick.keySet());
                b.movement(deltaT);
                if(b.getPreview()!=null){
                    b.updatePreview(listbrick.keySet());
                }
            }
        }
        

        for(Ball b : listball.keySet()){
            listball.get(b).update();
        }

        // Mise à jour de la position des briques
        Iterator<Brick> it = listbrick.keySet().iterator();
        while(it.hasNext()){
            Brick b = (Brick) it.next();
            listbrick.get(b).update();
            if(b.isDestroyed()){
                it.remove();
            }
        }

        if(RACKET){
            update_racket();
        }

        // Mise à jour de la position de la raquette
        
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
                for(Segment s : LIMIT_SIMULATION){
                    if(this.checkCollision(s)){
                        break;
                    }
                }
                double newX = this.getX() + this.getDirection().getX() * this.getSpeed() ;
                double newY = this.getY() + this.getDirection().getY() * this.getSpeed() ;
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
        Racket r;
        if(type.equals("YnotFixe")){
            r=new YNotFixeRacket();
        }
        else if(type.equals("losange")){
            r=new DiamondRacket();
        }
        else if(type.equals("rond")){
            r= new CircleRacket();
        }
        else if(type.equals("triangle")){
            r= new DegradeRacket();
        }
        else{
            r= new ClassicRacket();
        }
        r.setWindowWidth(PhysicSetting.DEFAULT_WINDOW_WIDTH);
        return r;
    }

    public void update_racket() {
        if(racket==null || Pause){
            return;
        }
        primaryStage.getScene().setOnKeyReleased(eWind -> {
            key.getKeysPressed().remove(eWind.getCode());
        });
        Racket.d = key.getKeysPressed();
        for (Ball ball : listball.keySet()) {
            ball.checkCollision(racket);
            if (ball.getPreview() != null) {
                ball.getPreview().update(racket);
            }
        }
        racket.getDirection().setX(0);
        primaryStage.getScene().setOnKeyPressed(eWind -> {
            key.getKeysPressed().add(eWind.getCode());
        });
        graphRacket.updatePos();
    }

    public void setTakeBall(BallGraphics g,Ball b,ToolBox toolBox,Pane root){
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
                //Condition
                boolean nochevauchement = true;
                for(Brick brick : listbrick.keySet()){
                    if(brick.contains(event.getSceneX(),event.getSceneY())){
                        nochevauchement = false;
                        break;
                    }
                }
                if(nochevauchement &&event.getSceneX() > start_border+b.getRadius() && event.getSceneX() < PhysicSetting.DEFAULT_WINDOW_WIDTH-b.getRadius() && event.getSceneY() > b.getRadius() && event.getSceneY() < PhysicSetting.DEFAULT_WINDOW_HEIGHT-b.getRadius()){
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

    public void setTakeBrick(BricksGraphics g,Brick b,ToolBox toolBox,Pane root){
        
        g.setOnMousePressed(event -> {
            if(!toolBox.isBar() || Pause){
                g.setMouseXY(event.getSceneX(),event.getSceneY());
                double brickX = b.getC().getX();
                double brickY = b.getC().getY();
                double distance = Math.sqrt(Math.pow(g.getMouseX() - brickX, 2) + Math.pow(g.getMouseY() - brickY, 2));
                if (distance <= GameConstants.BRICK_WIDTH+10) {
                    g.setMouseDraggingBall(true);
                    b.setTransparent(true);
                }
            }
        });

        g.setOnMouseDragged(event -> {
            if (g.IsMouseDraggingBall() && (!toolBox.isBar()|| Pause)) {
                boolean nochevauchement = true;
                for(Brick brick : listbrick.keySet()){
                    if((brick.contains(event.getSceneX(),event.getSceneY()) || brick.contains(event.getSceneX()+GameConstants.BRICK_WIDTH,event.getSceneY()) || brick.contains(event.getSceneX(),event.getSceneY()+GameConstants.BRICK_HEIGHT) || brick.contains(event.getSceneX()+GameConstants.BRICK_WIDTH,event.getSceneY()+GameConstants.BRICK_HEIGHT))){
                        nochevauchement = false;
                        break;
                    }
                }
                if(nochevauchement && event.getSceneX() > start_border+GameConstants.BRICK_WIDTH && event.getSceneX() < PhysicSetting.DEFAULT_WINDOW_WIDTH-GameConstants.BRICK_WIDTH && event.getSceneY() > GameConstants.BRICK_HEIGHT && event.getSceneY() < PhysicSetting.DEFAULT_WINDOW_HEIGHT-GameConstants.BRICK_HEIGHT){
                    b.getC().setX(event.getSceneX());
                    b.getC().setY(event.getSceneY());
                }

            }
        });

        g.setOnMouseReleased(event -> {
            if (g.IsMouseDraggingBall()&& (!toolBox.isBar()|| Pause)) {
                g.setMouseDraggingBall(false);
                b.createsegments();
                b.setTransparent(false);
            }
        });
    }

    public void addBall(Ball ball){
        BallGraphics graphBall = new BallGraphics(ball);
        root.getChildren().add(graphBall);
        listball.put(ball, graphBall);
    }

    public void clear(){
        PhysicEngine.start_border=0;
        Racket.d.clear();
        Pause = false;
    }
}