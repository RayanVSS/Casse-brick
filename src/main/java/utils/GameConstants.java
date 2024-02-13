package utils;

import geometry.Coordinates;
import geometry.Vector;

public final class GameConstants {

    //brick
    public static final int MAP_HEIGHT = 24;
    public static final int MAP_WIDTH = 15;
    public static final int ROWS_OF_BRICKS = 12;
    public static final int COLUMNS_OF_BRICKS = 13;
    public static final int MIN_SPACE_BETWEEN_RACKET_BRICKS = 5;
    public static final int BRICK_WIDTH = 96;
    public static final int BRICK_HEIGHT = 32;

    //ball
    public static final int DEFAULT_BALL_SPEED = 5; 
    public static final int DEFAULT_BALL_RADIUS= 10; 
    public static final Vector DEFAULT_BALL_START_DIRECTION=new Vector( new Coordinates(1, 1));
    public static final Coordinates DEFAULT_BALL_START_COORDINATES = new Coordinates(GameConstants.DEFAULT_WINDOW_WIDTH/2, GameConstants.DEFAULT_WINDOW_HEIGHT/2);

    //fenetre
    public static final double DEFAULT_WINDOW_WIDTH = MAP_WIDTH * BRICK_WIDTH;
    public static final double DEFAULT_WINDOW_HEIGHT = MAP_HEIGHT * BRICK_HEIGHT;
    public static final int DEFAULT_FPS = 120;

    //particle de traînée
    public static final int DEFAULT_trailLength = 70; // taille de la trainée 
    public static final double DEFAULT_PARTICLE_RADIUS = 1.4;
    public static final double DEFAULT_FLUCTUATION = 5;
    public static final int DEFAULT_PARTICLE = 10; // nombre de particules

    //boost
    //temps des boosts
    public static final int BOOST_DURATION_VITESSEP = 5; 
    public static final int BOOST_DURATION_VITESSEM = 5;
    public static final int BOOST_DURATION_LARGEURP = 5;
    public static final int BOOST_DURATION_LARGEURM = 5;
    public static final int BOOST_DURATION_FREEZE = 2;
    public static final int BOOST_DURATION_JUMP = 5;
    //valeur des boosts
    public static final double BOOST_VITESSEP = 2.0;
    public static final int BOOST_VITESSEM = 5;
    public static final int BOOST_LARGEURP = 100;
    public static final int BOOST_LARGEURM = 100;


    
}
