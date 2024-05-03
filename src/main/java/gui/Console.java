package gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import save.Sauvegarde;

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
 * @param command doit etre sous forme "/" + "type(obligatoire)" + "sous-type(si nécessaire)" + "valeur(si nécessaire)"
 * [Voir commandProcessing pour les détails]
 * 
 * @author GUAN Olivier
 */

public class Console {

    private static ArrayList<String> history;
    private static Queue<String> queue;
    // input history à ajouter si le temps

    public static void init() {
        history = new ArrayList<>();
        queue = new LinkedList<>();
        systemDisplay("Initialisation de la console...");
        systemDisplay("La console est initialisée.");
    }

    /**
     * Traite le message 'pouvant' contenir une ligne de commande
     */
    public static void process(String message) {
        if (message.charAt(0) == '/') {
            commandProcessing(message.substring(1));
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

    /**
     * Traitement du type de commande et envoie aux fonctions de traitement des sous-types.
     * Ne retient que les bonnes parties, tolérant aux erreurs de synthaxe (voir "Commande détectée").
     */
    private static void commandProcessing(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "exit":
                commandProcessingExit();
                break;

            case "run":
                commandProcessingRun(parts);
                break;

            case "set":
                commandProcessingSet(parts);
                break;

            case "reset":
                commandProcessingReset(parts);
                break;

            case "game":
                commandProcessingGame(parts);
                break;

            default:
                systemDisplay("Commande inconnue.");
                break;
        }
    }

    private static void commandProcessingExit() {
        systemDisplay("Commande détectée : /exit");
        Sauvegarde save = new Sauvegarde();
        save.autoSave();
        Timer exitTimer = new Timer();
        exitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 500); // Délai de lecture
    }

    public static void commandProcessingRun(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    public static void commandProcessingSet(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    public static void commandProcessingReset(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    public static void commandProcessingGame(String[] parts) {
        // App.menuManager.;
        systemDisplay("Pas encore implémenté.");
    }
}
