package config;

import java.util.List;
import java.util.ArrayList;

import entity.Boost;
import entity.ball.Ball;
import entity.racket.Racket;
import geometry.Coordinates;
import entity.ball.MagnetBall;

public class Game {


    private Ball ball;
    private Racket racket;
    private Map map;
    private boolean lost = false;
    private int score = 0;
    private int life = 3;
    private static boolean collide;

    private List<Boost> boosts = new ArrayList<>(); 

    public Game(Ball ball, Racket racket, BricksArrangement arrangement) {
        this.ball = ball;
        this.racket = racket;
        this.map = new Map(arrangement);
    }


    public void update(long deltaT) {
        // Vérifie si la balle touche une brique
        map.handleCollisionBricks(ball); // gérer la collision des briques
        int i=map.updateBricksStatus();
        if (i>0) {
            score += 10*i;
            System.out.println("Brick destroyed : "+i);
            //si la briques est cassée, chance d'avoir un boost
            Boost boost = Boost.createBoost(ball.getC());
            if (boost != null) {
                boosts.add(boost);
            }
        }
        // Si la balle touche la raquette
        if (racket.CollisionRacket(ball)) {
            ball.setCollisionR(true);
        }
        // Gere les conditions de perte
        if (!ball.movement()) {
            life--;
            ball.reset();
        }
        if (life == 0) {
            lost = true;
        }
        if(ball instanceof MagnetBall){
            //donne les coordonnées de la raquette a la MagnetBall
            setRa();
            //actualise l'etat de la raquette    
            if(BallFrontRacket()){
                ((MagnetBall) ball).setFront(true);
            }else{
                ((MagnetBall) ball).setFront(false);
            }
        }
    }

    public boolean collisionRacket(Coordinates c) {
        return c.getX() >= racket.getC().getX() && c.getX() <= racket.getC().getX() + racket.getLongueur()
                && c.getY() >= racket.getC().getY() && c.getY() <= racket.getC().getY() + racket.getLargeur();
    }


    // Vérifie si la balle est devant la raquette
    public boolean BallFrontRacket() {
        if(racket.getC().getX()-ball.getC().getX() < 0 && (racket.getC().getX()+racket.getLargeur())-ball.getC().getX() > 0){
            return true;
        }
        return false;
    }


    // Setters/getters
    public Ball getBall() {
        return ball;
    }

    public static Boolean getCollide() {
        return collide;
    }

    public  Racket getRacket() {
        return racket;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public Map getMap() {
        return map;
    }

    public void setRa() {
        MagnetBall.getRa=racket.getC();
    }

    public List<Boost> getBoosts() {
        return boosts;
    }
}
