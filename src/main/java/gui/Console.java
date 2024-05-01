package gui;

import java.util.ArrayList;

/**
 * Console interactive avec le jeu pour exécuter les commandes.
 * Full static class.
 * @author GUAN Olivier
 */

public class Console {

    private static ArrayList<String> history;

    public static void init() {
        history = new ArrayList<>();
    }

    public static void display(String message) {
        history.add(message);
    }

    public static ArrayList<String> getHistory() {
        return history;
    }

    public static void clearHistory() {
        history.clear();
    }
}
