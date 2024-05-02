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
 * display/systemDisplay -> getQueueHead -> addHistory 
 * 
 * @author GUAN Olivier
 */

public class Console {

    private static ArrayList<String> history;
    private static Queue<String> queue;

    public static void init() {
        history = new ArrayList<>();
        queue = new LinkedList<>();
        systemDisplay("Initialisation de la console...");
        systemDisplay("La console est initialisée.");
    }

    /**
     * Traite le message pouvant contenir une ligne de commande
     */
    public static void process(String message) {
        if (message.charAt(0) == '/') {
            String result = commandProcessing(message.substring(1));
            systemDisplay(result);
        } else {
            display("Moi : " + message);
        }
    }

    /**
     * Envoie le message en queue (affichage direct sans traitement des commandes)
     */
    public static void display(String message) {
        queue.add(message);
    }

    /**
     * Envoie le message en queue avec un tag [System]
     */
    public static void systemDisplay(String message) {
        queue.add("[Système] " + message);
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

    ////////////  PARTIE TRAITEMENT  ////////////

    private static String commandProcessing(String message) {
        return "";
    }
}
