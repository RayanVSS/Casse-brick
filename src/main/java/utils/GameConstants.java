package utils;

import java.util.Random;

import config.GameRules;
import config.GameRules.BricksArrangement;
import entity.ball.ClassicBall;
import entity.racket.ClassicRacket;
import entity.racket.DegradeRacket;
import entity.racket.MagnetRacket;
import entity.ball.MagnetBall;
import gui.Item;
import gui.Menu.Menu.Theme;
import javafx.scene.input.KeyCode;
import physics.entity.Ball;
import physics.entity.Racket;
import physics.geometry.Coordinates;
import physics.geometry.Vector;

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
        public static final int DEFAULT_BALL_SPEED = 5;
        public static final int DEFAULT_BALL_RADIUS = 15;

        static Random rand = new Random();
        public static final Vector DEFAULT_BALL_START_DIRECTION = new Vector(
                        new Coordinates(rand.nextBoolean() ? 1 : -1, rand.nextBoolean() ? 1 : -1));
        public static final Coordinates DEFAULT_BALL_START_COORDINATES = new Coordinates(
                        GameConstants.DEFAULT_GAME_ROOT_WIDTH / 2, GameConstants.DEFAULT_WINDOW_HEIGHT / 2);
        public static final double POWER_MAGNET = 0.5; // puissance de l'attraction des aimants

        // fenetre
        // public static final double DEFAULT_WINDOW_WIDTH = MAP_WIDTH * BRICK_WIDTH;
        // public static final double DEFAULT_WINDOW_HEIGHT = MAP_HEIGHT * BRICK_HEIGHT;

        public static final double DEFAULT_WINDOW_WIDTH = 1200.0;
        public static final double DEFAULT_WINDOW_HEIGHT = 800.0;
        public static final double DEFAULT_GAME_ROOT_WIDTH = DEFAULT_WINDOW_WIDTH - 235.0;

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
        // DegradeRacket
        public static final double DEGRADERACKET_TOLERANCE = -0.2;
        public static final double DEGRADERACKET_CHANGE_DIRECTION = 0.3;

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
        // "vitesseP", "vitesseM", "largeurP", "largeurM", "freeze",
        // "zhonya","intensityBall"
        public static final String[] BONUS_LIST = { "vitesseP", "vitesseM", "largeurP", "largeurM", "freeze", "zhonya",
                        "intensityBall" };

        // Options du jeu
        public static boolean FPS = false;
        public static boolean PATH = false;
        public static boolean PARTICLES = true;
        public static int SOUND = 50;
        public static int MUSIC = 50;
        public static KeyCode LEFT = KeyCode.LEFT;
        public static KeyCode RIGHT = KeyCode.RIGHT;
        public static KeyCode SPACE = KeyCode.SPACE;
        public static Theme CSS = Theme.CLASSIC;
        public static Item BALL_PORTE=null;
        public static Item RACKET_PORTE = null;
        public static String TEXTURE = "Null";
        public static String SKIN_BALL = "Null";
        

        // derniere save
        public static String LAST_SAVE;

        // GameRules (GR) options
        public static int GR_REMAINING_TIME = 300; // 5 minutes
        public static int GR_REMAINING_BOUNCES = 50; // rebonds restants
        public static int GR_DEFAULT_QTY_TRANSPARENT = 5; // quantité par défaut de briques qui deviennent transparentes
                                                          // (temporairement)
        public static int GR_DEFAULT_QTY_UNBREAKABLE = 5; // quantité par défaut de briques qui deviennent incassables
                                                          // (temporairement)
        public static final int CHAPTERS_QTY = 3;
        public static final int STAGES_QTY = 9 * CHAPTERS_QTY;

        // SKins
        public static final int RAQUETTES_QTY = 13;
        public static final int BALLES_QTY = 12;
        // preConfig des parties
        public static GameRules[] PRECONFIG_GAME_RULES = {
                        // chapter 1
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, true, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, true, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, true, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, true, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, true,false),
                        new GameRules(BricksArrangement.DEFAULT, true, true, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, true, true, true, true, true,false),
                        // chapter 2
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, true, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, true, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, true, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, true,false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, true, true, true, true,false),
                        // chapter 3
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, false, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, true, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, true, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, true, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, true, false,false),
                        new GameRules(BricksArrangement.DEFAULT, false, false, false, false, false, true,false),
                        new GameRules(BricksArrangement.DEFAULT, true, true, false, false, false, false,false),
                        new GameRules(BricksArrangement.DEFAULT, true, true, true, true, true, true,false)
        };

        public static GameRules INFINITE_MODE = new GameRules(BricksArrangement.INFINITE, false, false, false, false,
                        false,
                        false,true);

        // à changer plus tard
        public static Ball[] PRECONFIG_GAME_BALL = {
                        // chapter 1
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall(),
                        // chapter 2
                        new MagnetBall(), new MagnetBall(), new MagnetBall(), new MagnetBall(),
                        new MagnetBall(), new MagnetBall(), new MagnetBall(), new MagnetBall(),
                        new MagnetBall(),
                        // chapter 3
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall(), new ClassicBall(), new ClassicBall(), new ClassicBall(),
                        new ClassicBall()
        };

        // à changer plus tard
        public static Racket[] PRECONFIG_GAME_RACKET = {
                        // chapter 1
                        new ClassicRacket(), new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(), new ClassicRacket(), new ClassicRacket(), new ClassicRacket(),
                        new ClassicRacket(),
                        // chapter 2
                        new MagnetRacket(), new MagnetRacket(), new MagnetRacket(), new MagnetRacket(),
                        new MagnetRacket(), new MagnetRacket(), new MagnetRacket(), new MagnetRacket(),
                        new MagnetRacket(),
                        // chapter 3
                        new DegradeRacket(), new DegradeRacket(), new DegradeRacket(), new DegradeRacket(),
                        new DegradeRacket(), new DegradeRacket(), new DegradeRacket(), new DegradeRacket(),
                        new DegradeRacket(),

        };

        // DISPLAY VALUES (valeurs servant de stockeur ici pour un accès direct/facile
        // par les afficheurs)
        public static int LAST_WIN_MONEY;

        // TEXTURE:

        public static String[] PATHS_RAQUETTE = {
                        "src/main/ressources/Texture/arc_en_ciel.jpg",
                        "src/main/ressources/Texture/bleu.jpg",
                        "src/main/ressources/Texture/Dark_Matter.jpg",
                        "src/main/ressources/Texture/or.jpg",
                        "src/main/ressources/Texture/rose.jpg",
                        "src/main/ressources/Texture/rouge.jpg",
                        "src/main/ressources/Texture/violet.png",
                        "src/main/ressources/Texture/vert.jpg",
                        "src/main/ressources/Texture/stars.jpg",
                        "src/main/ressources/Texture/black_white.jpg",
                        "src/main/ressources/Texture/grey.jpg",
                        "src/main/ressources/Texture/purple.jpg"
        };
        public static String[] LABELS_RAQUETTE = { "Arc-en-ciel", "Bleu", "DarkMatter", "Or", "Rose", "Rouge", "Violet",
                        "Vert",
                        "Etoile", "Noir&Blanc", "Gris", "PurpleSilk" };

        // SKIN BALLE

        public static String[] PATHS_BALLE = { "src/main/ressources/balle/classic/classic.png",
                        "src/main/ressources/balle/pink/classic.png",
                        "src/main/ressources/balle/black/classic.png",
                        "src/main/ressources/balle/light/classic.png",
                        "src/main/ressources/balle/classic/gravity.png",
                        "src/main/ressources/balle/pink/gravity.png",
                        "src/main/ressources/balle/black/gravity.png",
                        "src/main/ressources/balle/light/gravity.png",
                        "src/main/ressources/balle/classic/hyper.png",
                        "src/main/ressources/balle/pink/hyper.png",
                        "src/main/ressources/balle/black/hyper.png",
                        "src/main/ressources/balle/light/hyper.png", };
        public static String[] LABELS_BALLE = { "ClassicClassic", "ClassicPink", "ClassicBlack", "ClassicLight",
                        "GravityClassic", "GravityPink", "GravityBlack", "GravityLight",
                        "HyperClassic", "HyperPink", "HyperBlack", "Hyperlight",
        };
}
