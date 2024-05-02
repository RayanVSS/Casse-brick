package gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Console interactive pour exécuter les commandes.
 * Full static class & controller class.
 * 
 * @function
 * process -> (action) -> getQueueHead -> addHistory 
 * 
 * @function
 * display -> getQueueHead -> addHistory 
 * 
 * @author GUAN Olivier
 */

public class Console {

    private static ArrayList<String> history;
    private static Queue<String> queue;

    public static void init() {
        history = new ArrayList<>();
        queue = new LinkedList<>();
        display("Initialisation de la console...");
        display("La console est initialisée.");
    }

    /**
     * Traite les messages pouvant contenir des lignes de commandes
     */
    public static void process(String message) {
        //ACTION 
        String result = "";
        display(message);
    }

    /**
     * Envoie les messages en queue (affichage direct sans traitement des commandes)
     */
    public static void display(String message) {
        queue.add(message);
    }

    public static String getQueueMessage() {

        if (!queue.isEmpty()) {
            String head = queue.poll();
            addHistory(head);
            return head;
        }
        return null;
    }

    private static void addHistory(String message) {
        history.add(message);
    }
}
