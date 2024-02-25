package utils;

import config.GameRules;
import config.Map;
import config.GameRules.BricksArrangement;
import entity.ball.Ball;
import entity.ball.ClassicBall;
import entity.racket.ClassicRacket;
import entity.racket.Racket;
import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;

import javafx.stage.Screen;

public final class GameConstants {

        // brick
        public static final int MAP_HEIGHT = 24;
        public static final int MAP_WIDTH = 15;
        public static final int ROWS_OF_BRICKS = 5;
        public static final int COLUMNS_OF_BRICKS = 13;
        public static final int MIN_SPACE_BETWEEN_RACKET_BRICKS = 5;
        public static final int BRICK_WIDTH = 60;
        public static final int BRICK_HEIGHT = 32;

        //ball
        public static final int DEFAULT_BALL_SPEED = 5;
        public static final int DEFAULT_BALL_RADIUS = 10;
        public static final Vector DEFAULT_BALL_START_DIRECTION = new Vector(new Coordinates(1, 1));
        public static final Coordinates DEFAULT_BALL_START_COORDINATES = new Coordinates(
                        GameConstants.DEFAULT_WINDOW_WIDTH / 2, GameConstants.DEFAULT_WINDOW_HEIGHT / 2);

        // fenetre
        // public static final double DEFAULT_WINDOW_WIDTH = MAP_WIDTH * BRICK_WIDTH;
        // public static final double DEFAULT_WINDOW_HEIGHT = MAP_HEIGHT * BRICK_HEIGHT;
        public static final double DEFAULT_WINDOW_WIDTH = 1000.0;
        public static final double DEFAULT_WINDOW_HEIGHT = 800.0;
        public static final int DEFAULT_FPS = 120;

        // particle de traînée
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

        //Options du jeu
        public static boolean FPS = false;
        public static boolean PATH = false;
        public static boolean PARTICLES = true;
        public static int SOUND = 50;
        public static int MUSIC = 50;
        public static KeyCode LEFT = KeyCode.LEFT;
        public static KeyCode RIGHT = KeyCode.RIGHT;
        public static KeyCode SPACE = KeyCode.SPACE;

        // preConfig des parties
        public static int STAGES_QTY = 9;

        public static GameRules[] PRECONFIG_GAME_RULES = {
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false, false) };

        public static Ball[] PRECONFIG_GAME_BALL = { new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall() };

        public static Racket[] PRECONFIG_GAME_RACKET = { new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(), new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(), new ClassicRacket(), };

}
