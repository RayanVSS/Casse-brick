package utils;

import config.GameRules;
import config.GameRules.BricksArrangement;
import entity.ball.ClassicBall;
import gui.Menu.Menu.Theme;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import physics.geometry.Coordinates;
import physics.geometry.Vector;
import physics.entity.*;
import entity.ball.*;
import entity.racket.*;
import java.util.Random;

public final class GameConstants {

        // brick
        public static final int MAP_HEIGHT = 24;
        public static final int MAP_WIDTH = 15;
        public static final int ROWS_OF_BRICKS = 5;
        public static final int COLUMNS_OF_BRICKS = 13;
        public static final int MIN_SPACE_BETWEEN_RACKET_BRICKS = 5;
        public static final int BRICK_WIDTH = 60;
        public static final int BRICK_HEIGHT = 32;

        // ball
        public static final int DEFAULT_BALL_SPEED = 3;
        public static final int DEFAULT_BALL_RADIUS = 10;

        static Random rand = new Random();
        public static final Vector DEFAULT_BALL_START_DIRECTION = new Vector(
                        new Coordinates(rand.nextBoolean() ? 1 : -1, rand.nextBoolean() ? 1 : -1));
        public static final Coordinates DEFAULT_BALL_START_COORDINATES = new Coordinates(
                        GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2, GameConstants.DEFAULT_WINDOW_HEIGHT / 2);
        public static final double POWER_MAGNET = 0.5; // puissance de l'attraction des aimants

        // fenetre
        // public static final double DEFAULT_WINDOW_WIDTH = MAP_WIDTH * BRICK_WIDTH;
        // public static final double DEFAULT_WINDOW_HEIGHT = MAP_HEIGHT * BRICK_HEIGHT;

        public static final double DEFAULT_WINDOW_WIDTH = 1100.0;
        public static final double DEFAULT_WINDOW_HEIGHT = 800.0;
        public static final double DEFAULT_GAME_ROOT_WIDTH = DEFAULT_WINDOW_WIDTH - 200.0;

        // public static final double DEFAULT_WINDOW_WIDTH =
        // Screen.getPrimary().getVisualBounds().getWidth();
        // public static final double DEFAULT_WINDOW_HEIGHT =
        // Screen.getPrimary().getVisualBounds().getHeight();

        public static final int DEFAULT_FPS = 120;

        // particle de traînée
        public static final int DEFAULT_trailLength = 10; // nombre de particules
        public static final double DEFAULT_PARTICLE_RADIUS = 0.5;
        public static final double DEFAULT_FLUCTUATION = 8;
        public static final int DEFAULT_PARTICLE = 10; // taille de la trainée

        // racket
        //DegradeRacket
        public static final double DEGRADERACKET_TOLERANCE = -0.2;
        public static final double DEGRADERACKET_CHANGE_DIRECTION= 0.3;

        // boost
        // temps des boosts
        public static final int BOOST_DURATION_VITESSEP = 5;
        public static final int BOOST_DURATION_VITESSEM = 5;
        public static final int BOOST_DURATION_LARGEURP = 5;
        public static final int BOOST_DURATION_LARGEURM = 5;
        public static final int BOOST_DURATION_FREEZE = 2;
        public static final int BOOST_DURATION_JUMP = 5;
        public static final int BOOST_DURATION_ZHONYA = 2;
        public static final int BOOST_DURATION_INTENSITY_BALL = 5;
        // valeur des boosts
        public static final double BOOST_VITESSEP = 2.0;
        public static final int BOOST_VITESSEM = 5;
        public static final int BOOST_LARGEURP = 100;
        public static final int BOOST_LARGEURM = 100;
        public static final double BOOST_INTENSITY_BALL = 2; // c'est un multiplicateur
        // bonus
        public static final int WIDTH = 20;
        public static final int HEIGHT = 20;
        public static final double BONUS_SPEED = 2;
        public static final double BONUS_CHANCE = 0.2;
        // "vitesseP", "vitesseM", "largeurP", "largeurM", "freeze", "zhonya","intensityBall"
        public static final String[] BONUS_LIST = { "vitesseP", "vitesseM", "largeurP", "largeurM", "freeze", "zhonya","intensityBall" };
        public static final Color COLOR_BONUS = Color.GREEN;
        public static final Color COLOR_MALUS = Color.RED;

        // Options du jeu
        public static boolean FPS = false;
        public static boolean PATH = false;
        public static boolean PARTICLES = true;
        public static int SOUND = 50;
        public static int MUSIC = 50;
        public static KeyCode LEFT = KeyCode.LEFT;
        public static KeyCode RIGHT = KeyCode.RIGHT;
        public static KeyCode SPACE = KeyCode.SPACE;
        public static Theme CSS = Theme.DARK;

        // derniere save
        public static String LAST_SAVE;

        // GameRules (GR) options
        public static int GR_REMAINING_TIME = 300; // 5 minutes
        public static int GR_REMAINING_BOUNCES = 50; // rebonds restants
        public static int GR_DEFAULT_QTY_TRANSPARENT = 5; // quantité par défaut de briques qui deviennent transparentes
                                                          // (temporairement)
        public static int GR_DEFAULT_QTY_UNBREAKABLE = 5; // quantité par défaut de briques qui deviennent incassables
                                                          // (temporairement)
        public static final int STAGES_QTY = 9;

        // preConfig des parties
        public static GameRules[] PRECONFIG_GAME_RULES = {
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, true, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, true, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, true, false, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, true, false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, true),
                        new GameRules(BricksArrangement.DEFAULT, true, true, false, false, false, false),
                        new GameRules(BricksArrangement.DEFAULT, true, true, true, true, true, true) };

        // à changer plus tard
        public static Ball[] PRECONFIG_GAME_BALL = { new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall() };

        // à changer plus tard
        public static Racket[] PRECONFIG_GAME_RACKET = { new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(), new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(), new ClassicRacket(), };

}
