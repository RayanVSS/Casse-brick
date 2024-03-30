package physics.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import gui.GraphicsFactory.BallGraphics;
import utils.GameConstants;
import physics.entity.Ball;
import physics.config.PhysicEngine;
import physics.config.PhysicSetting;


public class OptionBar {
    
    private boolean Bar=false;
    private int size=270;
    private Ball b;
    private Pane root;
    private char position = 'g';

    private Map<Ball,BallGraphics> map;

    private ArrayList<Circle> circles=new ArrayList<>();
    private ArrayList<Integer> list=new ArrayList<>(Arrays.asList(0,1,2,3,4));
    private ArrayList<Image> list_image=new ArrayList<>(Arrays.asList(
        new Image("/balle/physic/balle1.png"),
        new Image("/balle/physic/balle2.png"),
        new Image("/balle/physic/balle3.png"),
        new Image("/balle/physic/balle4.png"),
        new Image("/balle/physic/balle5.png")
    ));

    private Random random = new Random();
    private double start=0;
    
    private Label labelrotate;
    private Label labelangle;
    private Label labelspeed;

    public OptionBar(Pane root, Map<Ball,BallGraphics> map,Ball b){
        this.root= root;
        this.map = map;
        this.b = b;
    }

    public void affiche(){
        start=position=='g'?0:PhysicSetting.DEFAULT_WINDOW_WIDTH-size;
        Label label = new Label("Informations sur la simulation :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(start+10);
        label.setLayoutY(20);

        root.getChildren().add(label);

        label = new Label("Afficher la trajectoire sans effet :");
        label.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        label.setLayoutX(start+10);
        label.setLayoutY(50);

        CheckBox button = new CheckBox();
        button.setOnAction(e -> {
            clearCircles();
            if(button.isSelected()){
                circles=Preview.preview_no_effect(b, root);
                PhysicEngine.Pause=true;
            }
            else{
                PhysicEngine.Pause=false;
            }
        });

        button.setLayoutX(start+200);
        button.setLayoutY(52);

        root.getChildren().addAll(button,label);

        labelrotate = new Label("Effet de la rotation :"+ PhysicSetting.CalculateRotation(b)+" degre");
        labelrotate.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelrotate.setLayoutX(start+10);
        labelrotate.setLayoutY(100);

        root.getChildren().add(labelrotate);

        labelangle = new Label("Angle du vecteur de la balle : "+PhysicSetting.CalculateAngle(b)+" degre");
        labelangle.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelangle.setLayoutX(start+10);
        labelangle.setLayoutY(130);

        root.getChildren().add(labelangle);

        labelspeed =new Label("Vitesse de la balle : "+PhysicSetting.CalculateSpeed(b.getDirection())+" m/s");
        labelspeed.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
        labelspeed.setLayoutX(start+10);
        labelspeed.setLayoutY(160);

        root.getChildren().add(labelspeed);

        Button button2 = new Button("Ajouter une nouvelle balle ");
        button2.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button2.setLayoutX(start+30);
        button2.setLayoutY(190);

        button2.setOnAction(e->{
            if(list.size()>0){
                Ball b2 = PhysicEngine.init_ball();
                Integer nb = list.get(random.nextInt(list.size()));
                BallGraphics ballg = new BallGraphics(list_image.get(nb),b2);
                PhysicEngine.setTakeBall(ballg, b2, this, root);
                list.remove(nb);
                map.put(b2,ballg);
                root.getChildren().add(ballg);
            }
            else{
                Label l= new Label("Vous avez atteint le nombre maximal de balles");
                l.setStyle("-fx-font-size: 13; -fx-text-fill: #1b263b;");
                l.setLayoutX(start+10);
                l.setLayoutY(220);
                root.getChildren().add(l);
            }
        });

        root.getChildren().add(button2);

        Button button3 = new Button("Supprimer toutes les balles ajoutées");
        button3.setStyle("-fx-font-size: 13; -fx-background-color: #1b263b;-fx-text-fill: #d5bbb1;");
        button3.setLayoutX(start+30);
        button3.setLayoutY(250);

        button3.setOnAction(e->{
            removeBall();
        });

        root.getChildren().add(button3);
        
    }

    public void reset(){
        clearCircles();
        root.getChildren().removeIf(e -> e instanceof Button || e instanceof CheckBox || e instanceof Label);
    }

    public void removeBall(){
        for(Ball b : map.keySet()){
            root.getChildren().remove(map.get(b));
            map.remove(b);
        }
        for(int i=1;i<6;i++){
            list.add(i);
        }
    }

    public void clearCircles(){
        for(Circle p : circles){
            root.getChildren().remove(p);
        }
    }

    public void updateData(){
        labelrotate.setText("Effet de la rotation :"+ PhysicSetting.CalculateRotation(b)+" degre");
        labelangle.setText("Angle du vecteur de la balle : "+PhysicSetting.CalculateAngle(b)+" degre");
        labelspeed.setText("Vitesse de la balle : "+PhysicSetting.CalculateSpeed(b.getDirection())+" m/s");
    }

    public void close(){
        reset();
    }

    public void update(Ball b){
        if(Bar){
            Bar=false;
            close();
            if(position=='d'){
                PhysicEngine.f_WIDTH = GameConstants.DEFAULT_WINDOW_WIDTH;
            }
            else{
                PhysicEngine.d_WIDTH = 0;
            }
        }
        else{
            Bar=true;
            if(b.getC().getX()<250){
                position = 'd';
                PhysicEngine.f_WIDTH = GameConstants.DEFAULT_WINDOW_WIDTH-size;
            }
            else{
                position = 'g';
                PhysicEngine.d_WIDTH = size;
            }
            affiche();
        }
    }

    public boolean isBar() {
        return Bar;
    }
}
