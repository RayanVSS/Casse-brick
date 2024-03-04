package utils;

import java.util.Random;

import geometry.Coordinates;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public final class GameConstants {

	// brick
	public static final int MAP_HEIGHT = 24;
	public static final int MAP_WIDTH = 15;
	public static final int ROWS_OF_BRICKS = 3;
	public static final int COLUMNS_OF_BRICKS = 13;
	public static final int MIN_SPACE_BETWEEN_RACKET_BRICKS = 5;
	public static final int BRICK_WIDTH = 60;
	public static final int BRICK_HEIGHT = 32;

	// ball
	public static final int DEFAULT_BALL_SPEED = 5;
	public static final int DEFAULT_BALL_RADIUS = 10;


	static Random rand = new Random();
	static double min = -1.0;
	static double max = 1.0;
	static double randomValue = min + (max - min) * rand.nextDouble();
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
	public static final double DEFAULT_GAME_ROOT_WIDTH = 1100.0 - 150.0;

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

    // boost
    // temps des boosts
    public static final int BOOST_DURATION_VITESSEP = 5;
    public static final int BOOST_DURATION_VITESSEM = 5;
    public static final int BOOST_DURATION_LARGEURP = 5;
    public static final int BOOST_DURATION_LARGEURM = 5;
    public static final int BOOST_DURATION_FREEZE = 2;
    public static final int BOOST_DURATION_JUMP = 5;
    // valeur des boosts
    public static final double BOOST_VITESSEP = 2.0;
    public static final int BOOST_VITESSEM = 5;
    public static final int BOOST_LARGEURP = 100;
    public static final int BOOST_LARGEURM = 100;
    // bonus
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final double BONUS_SPEED = 2;
    public static final double BONUS_CHANCE = 0.2;
    public static final String[] BONUS_LIST = { "vitesseP", "vitesseM", "largeurP", "largeurM", "freeze" };
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
    public static String CSS = "/styles/dark.css";

	// derniere save
	public static String LAST_SAVE;

}
