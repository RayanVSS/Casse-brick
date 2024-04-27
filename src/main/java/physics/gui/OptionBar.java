package physics.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import gui.GraphicsFactory.BallGraphics;
import gui.GraphicsFactory.RacketGraphics;
import physics.entity.Ball;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;


public class OptionBar {
    
    private boolean Bar=false;
    private Ball ball;
    private Pane root;

    private Map<Ball,BallGraphics> map;

    private ArrayList<Circle> circles=new ArrayList<>();
    private ArrayList<Image> list_image=new ArrayList<>(Arrays.asList(
        new Image("/balle/physic/balle1.png"),
        new Image("/balle/physic/balle2.png"),
        new Image("/balle/physic/balle3.png"),
        new Image("/balle/physic/balle4.png"),
        new Image("/balle/physic/balle5.png")
    ));

    private Random random = new Random();
    
    private Label labelrotate;
    private Label labelangle;
    private Label labelspeed;

    public OptionBar(Pane root, Map<Ball,BallGraphics> map,Ball b){
        this.root= root;
        this.map = map;
        this.ball = b;
    }

    public void affiche(){
        Label label = new Label("Informations sur la simulation :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(20);

        root.getChildren().add(label);

        label = new Label("Afficher la trajectoire sans effet :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(10);
        label.setLayoutY(50);
        CheckBox button = new CheckBox();
        button.setOnAction(e -> {
            clearCircles();
            if(button.isSelected()){
                circles=Preview.preview_no_effect(ball, root);
                PhysicEngine.Pause=true;
            }
            else{
                PhysicEngine.Pause=false;
            }
        });

        button.setLayoutX(200);
        button.setLayoutY(52);

        root.getChildren().addAll(button,label);

        labelrotate = new Label("Effet de la rotation :"+ PhysicSetting.CalculateRotation(ball)+" degre");
        labelrotate.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelrotate.setLayoutX(10);
        labelrotate.setLayoutY(100);

        root.getChildren().add(labelrotate);

        labelangle = new Label("Angle du vecteur de la balle : "+PhysicSetting.CalculateAngle(ball)+" degre");
        labelangle.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelangle.setLayoutX(10);
        labelangle.setLayoutY(130);

        root.getChildren().add(labelangle);

        labelspeed =new Label("Vitesse de la balle : "+PhysicSetting.CalculateSpeed(ball.getDirection())+" m/s");
        labelspeed.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelspeed.setLayoutX(10);
        labelspeed.setLayoutY(160);

        root.getChildren().add(labelspeed);

        Button button2 = new Button("Ajouter une nouvelle balle ");
        button2.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button2.setLayoutX(30);
        button2.setLayoutY(190);

        button2.setOnAction(e->{
            if(map.size()<10){
                Ball b2 = PhysicEngine.init_ball();
                Integer nb = random.nextInt(list_image.size());
                BallGraphics ballg = new BallGraphics(list_image.get(nb),b2);
                PhysicEngine.setTakeBall(ballg, b2, this, root);
                map.put(b2,ballg);
                root.getChildren().add(ballg);
            }
            else{
                Label l= new Label("Vous avez atteint le nombre maximal de balles");
                l.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
                l.setLayoutX(10);
                l.setLayoutY(220);
                root.getChildren().add(l);
            }
        });

        root.getChildren().add(button2);

        Button button3 = new Button("Supprimer toutes les balles ajoutées");
        button3.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button3.setLayoutX(30);
        button3.setLayoutY(250);

        button3.setOnAction(e->{
            removeBall();
        });

        root.getChildren().add(button3);

        ComboBox<String> listracket = new ComboBox<String>();
        listracket.getItems().addAll("rectangle","rond","triangle","losange");
        listracket.setValue("rectangle");
        listracket.setLayoutX(30);
        listracket.setLayoutY(280);
        listracket.setOnAction(e -> {
            root.getChildren().remove(PhysicEngine.graphRacket.getShape());
            PhysicEngine.racket = PhysicEngine.init_racket(listracket.getValue());
            PhysicEngine.graphRacket =new RacketGraphics(PhysicEngine.racket, PhysicEngine.racket.getShapeType());
            root.getChildren().add(PhysicEngine.graphRacket.getShape());   
        });

        root.getChildren().add(listracket);
        
    }

    public void reset(){
        clearCircles();
        root.getChildren().removeIf(e -> e instanceof Button || e instanceof CheckBox || e instanceof Label || e instanceof ComboBox);
    }

    public void removeBall(){
        for(Ball b : map.keySet()){
            if(b.equals(ball)){
                continue;
            }
            root.getChildren().remove(map.get(b));
            map.remove(b);
        }
    }

    public void clearCircles(){
        for(Circle p : circles){
            root.getChildren().remove(p);
        }
    }

    public void updateData(){
        labelrotate.setText("Effet de la rotation :"+ PhysicSetting.CalculateRotation(ball)+" degre");
        labelangle.setText("Angle du vecteur de la balle : "+PhysicSetting.CalculateAngle(ball)+" degre");
        labelspeed.setText("Vitesse de la balle : "+PhysicSetting.CalculateSpeed(ball.getDirection())+" m/s");
    }

    public void close(){
        reset();
    }

    public void update(Ball b){
        if(Bar){
            Bar=false;
            close();
        }
        else{
            Bar=true;
            affiche();
        }
    }

    public boolean isBar() {
        return Bar;
    }
}
