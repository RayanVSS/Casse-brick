package physics.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import config.Game;
import entity.EntityColor;
import entity.ball.ClassicBall;
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
import physics.gui.TestPreset;
import utils.GameConstants;
import utils.ImageLoader;
import utils.Key;

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

public class PhysicEngine extends Pane{

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
    
    private ToolBox toolBox;
    private Ball firstBall;

    PhysicSetting physics;

    Map<Ball,BallGraphics> listball=new HashMap<>();
    Map<Brick,BricksGraphics> listbrick=new HashMap<>();

    public AppPhysic app=AppPhysic.app;
    public static Racket racket;
    public static RacketGraphics graphRacket;
    Key key = new Key();

    public static boolean Pause = false;

    public PhysicEngine() {
        clear();
        // Initialisation de la simulation

        this.physics = AppPhysic.physics;
        physics.setWind();
        this.setStyle("-fx-background-color: #FFFFFF;");

        firstBall = init_ball(null,null);
        addBall(firstBall);

        listbrick = new HashMap<>();

        // Initialisation de la barre d'option
        
        toolBox = new ToolBox(listball,listbrick,this);

        if(RACKET){
            racket=init_racket("rectangle");
            graphRacket = new RacketGraphics(racket, racket.getShapeType());
            graphRacket.getShape().setFill(Color.BLACK);
            this.getChildren().add(graphRacket.getShape());
        }
        // Initialisation de la trajectoire

        firstBall.setPreview(new Preview(firstBall, physics, this));
        firstBall.getPreview().init_path();

        //Initialisation des evenements de la souris

        setTakeBall(listball.get(firstBall),firstBall,toolBox);

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
                    app.returnMenu();
                } else if (key.getKeysPressed().contains(KeyCode.M)&& !ToolBox.testpreset) {
                    toolBox.update();
                    key.getKeysPressed().remove(KeyCode.M);
                }
                else if(key.getKeysPressed().contains(KeyCode.P)){
                    Pause = !Pause;
                    toolBox.updateData();
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
        if(firstBall==null && listball.size()>0){
            firstBall = listball.keySet().iterator().next();
            firstBall.setPreview(new Preview(firstBall, physics, this));
            firstBall.getPreview().init_path();
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
            b.CollisionB=false;
        }

        // Mise à jour de la position des briques
        Iterator<Brick> it = listbrick.keySet().iterator();
        while(it.hasNext()){
            Brick b = (Brick) it.next();
            listbrick.get(b).update2();
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
                if((newX<start_border+this.getRadius())){
                    newX=start_border+this.getRadius();
                }
                this.getC().setXY(newX,newY);
                getC().setX(getX()+physics.getMass()/physics.getWind().getX());
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
        app.getScene().setOnKeyReleased(eWind -> {
            key.getKeysPressed().remove(eWind.getCode());
        });
        Racket.d = key.getKeysPressed();
        for (Ball ball : listball.keySet()) {
            if(ball.checkCollision(racket)){
                if (ball.getC().getX() > racket.getC().getX()
                            && ball.getC().getX() < racket.getC().getX() + racket.getLargeur() - 2
                            && ball.getC().getY() > racket.getC().getY()) {
                        System.out.println(ball.getC().getX() + "  " + racket.getC().getX());
                        if (ball.getC().getX() < racket.getC().getX() + racket.getLargeur()/2) {
                            ball.setC(
                                    new Coordinates(ball.getC().getX() - ball.getRadius() - 15, racket.getC().getY()));
                            ball.setDirection(new Vector(-ball.getDirection().getX(), ball.getDirection().getY()));
                            ball.getRotation().stopRotation();
                            System.out.println("ball dans la raquette a gauche");
                        } else if (ball.getC().getX() < racket.getC().getX() + racket.getLargeur()
                                && ball.getC().getX() > racket.getC().getX() + racket.getLargeur()/2) {
                            ball.setC(
                                    new Coordinates(ball.getC().getX() + ball.getRadius() + 15, racket.getC().getY()));
                            ball.setDirection(new Vector(-ball.getDirection().getX(), ball.getDirection().getY()));
                            ball.getRotation().stopRotation();
                            System.out.println("ball dans la raquette a droite");
                        } else {
                            ball.setC(new Coordinates(ball.getC().getX() + racket.getLargeur() + ball.getRadius() + 30,
                                    racket.getC().getY()));
                            ball.setDirection(new Vector(ball.getDirection().getX(), -ball.getDirection().getY()));
                            ball.getRotation().stopRotation();
                            System.out.println("ball dans la raquette au milieu");
                        }
                    }
            }
            if (ball.getPreview() != null) {
                ball.getPreview().update(racket);
            }
        }
        racket.getDirection().setX(0);
        racket.getDirection().setY(0);
        app.getScene().setOnKeyPressed(eWind -> {
            key.getKeysPressed().add(eWind.getCode());
        });
        graphRacket.updatePos();
    }

    public void setTakeBall(BallGraphics g,Ball b,ToolBox toolBox){
        g.setOnMousePressed(event -> {
            if(!toolBox.isBar() && !Pause){
                g.setMouseXY(event.getSceneX(),event.getSceneY());
                double ballX = b.getC().getX();
                double ballY = b.getC().getY();
                double distance = Math.sqrt(Math.pow(g.getMouseX() - ballX, 2) + Math.pow(g.getMouseY() - ballY, 2));
                if (distance <= b.getRadius()+10) {
                    g.setMouseDraggingBall(true);
                    if(b.getPreview()!=null){
                        b.getPreview().clear_path(this);
                    }
                }
            }
        });

        g.setOnMouseDragged(event -> {
            if (g.IsMouseDraggingBall() && (!toolBox.isBar()&& !Pause)) {
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
            if (g.IsMouseDraggingBall()&& !toolBox.isBar() && !Pause) {
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

    public void setTakeBrick(BricksGraphics g,Brick b,ToolBox toolBox){
        
        g.setOnMousePressed(event -> {
            if(!toolBox.isBar() && !Pause){
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
            if (g.IsMouseDraggingBall() && (!toolBox.isBar()&& !Pause)) {
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
            if (g.IsMouseDraggingBall()&& (!toolBox.isBar() && !Pause)) {
                g.setMouseDraggingBall(false);
                b.createsegments();
                b.setTransparent(false);
            }
        });
    }

    public void addBall(Ball ball){
        BallGraphics graphBall = new BallGraphics(ToolBox.list_image.get((int)(Math.random()*listball.size())),ball);
        this.getChildren().add(graphBall);
        listball.put(ball, graphBall);
    }

    public void addBrick(Brick brick){
        BricksGraphics graphBrick = new BricksGraphics(brick,EntityColor.BLUE);
        this.getChildren().add(graphBrick);
        listbrick.put(brick, graphBrick);
    }

    public void clear(){
        PhysicEngine.LIMIT_SIMULATION.get(1).addX(-PhysicEngine.start_border);
        PhysicEngine.start_border=0;
        Racket.d.clear();
        removeBall();
        removeBrick();
        removeRacket();
        Pause = false;
    }

    public void removeBall(){
        for(Ball b : listball.keySet()){
            this.getChildren().remove(listball.get(b));
            if(b.getPreview()!=null){
                b.getPreview().clear_path(this);
            }
        }
        firstBall=null;
        listball.clear();
    }

    public void removeRacket(){
        if(PhysicEngine.racket==null){
            return;
        }
        this.getChildren().remove(PhysicEngine.graphRacket.getShape());
        PhysicEngine.racket=null;
    }

    public void removeBrick(){
        for(Brick b : listbrick.keySet()){
            this.getChildren().remove(listbrick.get(b));
        }
        listbrick.clear();
    }

    public Ball getFirstBall() {
        return firstBall;
    }
}