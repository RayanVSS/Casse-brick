package gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Timer;
import java.util.TimerTask;

import gui.Menu.Menu;
import gui.Menu.MenuViews.BoutiqueView;
import gui.Menu.MenuViews.ChapterView;
import gui.Menu.MenuViews.GameCustomizerView;
import gui.Menu.MenuViews.GameModeView;
import gui.Menu.MenuViews.GameOverView;
import gui.Menu.MenuViews.OptionsView;
import gui.Menu.MenuViews.PauseView;
import gui.Menu.MenuViews.SaveView;
import gui.Menu.MenuViews.StageSelectorView;
import gui.Menu.MenuViews.StartMenuView;
import gui.Menu.MenuViews.WinView;
import javafx.application.Platform;
import save.PlayerData;
import save.Sauvegarde;
import utils.GameConstants;

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

    private static void refreshView() {
        if (GameConstants.ACTUAL_VIEW instanceof Menu) {
            ((Menu) GameConstants.ACTUAL_VIEW).update();
        }
    }

    ////////////////////////  PARTIE TRAITEMENT  ////////////////////////

    ////////////////////    PARTIE TRAITEMENT TYPE    ////////////////////
    /////////////////////     ANALYSE : parts[0]    /////////////////////

    /**
     * Traitement du type de commande et envoie aux fonctions de traitement des sous-types.
     * Ne retient que les bonnes parties, tolérant aux erreurs de synthaxe (voir "Commande détectée").
     * Structure permettant une meilleure détection d'erreurs
     */
    private static void commandProcessing(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            systemDisplay("Commande vide : /?");
        } else {

            switch (parts[0].toLowerCase()) {
                case "exit":
                    commandExit();
                    break;

                case "run":
                    commandProcessingRun(parts);
                    break;

                case "get":
                    commandProcessingGet(parts);
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

                case "save":
                    commandSave();
                    break;

                default:
                    systemDisplay("Commande inconnue : '/" + parts[0] + "' n'existe pas.");
                    break;
            }
        }
    }

    /////////////////    PARTIE TRAITEMENT SOUS-TYPE    /////////////////
    ///////////////////      ANALYSE : parts[1]      ///////////////////

    private static void commandProcessingRun(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    private static void commandProcessingGet(String[] parts) {
        switch (parts[1].toLowerCase()) {
            case "viewpos":
                commandGetViewPos();
                break;

            case "pseudo":
                commandGetPseudo();
                break;

            case "exp":
                commandGetExp();
                break;

            case "money":
                commandGetMoney();
                break;

            case "inventory":
                break;

            case "save":
                commandGetSave();
                break;

            case "admin":
                commandGetAdmin();
                break;

            default:
                systemDisplay("Commande /get erronée : '" + parts[1] + "' est un argument inconnu.");
                break;
        }
    }

    private static void commandProcessingSet(String[] parts) {
        switch (parts[1].toLowerCase()) {

            case "pseudo":
                commandSetPseudo(parts);
                break;

            case "exp":
                commandSetExp(parts);
                break;

            case "money":
                commandSetMoney(parts);
                break;

            case "admin":
                commandSetAdmin(parts);
                break;

            default:
                systemDisplay("Commande /set erronée : '" + parts[1] + "' est un argument inconnu.");
                break;
        }
    }

    private static void commandProcessingReset(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    private static void commandProcessingGame(String[] parts) {
        systemDisplay("Pas encore implémenté.");
    }

    //////////////////////////////////////////////////////////////////////
    //////////////   PARTIE TERMINALE (PAS DE TRAITEMENT)   //////////////
    //////////////////////////////////////////////////////////////////////

    private static void commandExit() {
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

    private static void commandGetViewPos() { // Plutôt pour débug
        systemDisplay("Commande détectée : /get viewpos");
        ViewPosition actualView = GameConstants.ACTUAL_VIEW;

        if (actualView == null) {
            systemDisplay("Erreur ! Vue introuvable...");
        } else {
            String result = "Vous êtes sur l'écran : ";

            if (actualView instanceof GameView) {
                result += "En Jeu";
            } else if (actualView instanceof BoutiqueView) {
                result += "Boutique";
            } else if (actualView instanceof ChapterView) {
                result += "Sélection des chapitres";
            } else if (actualView instanceof GameCustomizerView) {
                result += "Personnalisation du jeu";
            } else if (actualView instanceof GameModeView) {
                result += "Sélection du mode de jeu";
            } else if (actualView instanceof GameOverView) {
                result += "Perdu";
            } else if (actualView instanceof OptionsView) {
                result += "Paramètres des options";
            } else if (actualView instanceof PauseView) {
                result += "Pause";
            } else if (actualView instanceof SaveView) {
                result += "Sauvegarde des données";
            } else if (actualView instanceof StageSelectorView) {
                result += "Sélection des niveaux";
            } else if (actualView instanceof StartMenuView) {
                result += "Accueil";
            } else if (actualView instanceof WinView) {
                result += "Gagné";
            } else {
                result += "Non défini";
            }
            systemDisplay(result);
        }
    }

    private static void commandGetPseudo() {
        systemDisplay("Commande détectée : /get pseudo");
        systemDisplay("Votre pseudo est : " + PlayerData.pseudo);
    }

    private static void commandGetExp() {
        systemDisplay("Commande détectée : /get exp");
        systemDisplay("Vous êtes niveau : " + PlayerData.expLevel);
    }

    private static void commandGetMoney() {
        systemDisplay("Commande détectée : /get money");
        systemDisplay("Vous possédez : " + PlayerData.money);
    }

    private static void commandGetSave() {
        systemDisplay("Commande détectée : /get save");
        String name = GameConstants.LAST_SAVE.replace(".json", "");
        if (name.equals("") || name.equals("autoTempSave")) {
            name = "(Temporaire)";
        }
        systemDisplay("Vous êtes sur la sauvegarde : " + name);
    }

    private static void commandGetAdmin() {
        systemDisplay("Commande détectée : /get admin");
        systemDisplay("Vous êtes : " + (PlayerData.isAdmin ? "Administrateur" : "Joueur"));
    }

    private static void commandSetPseudo(String[] parts) {
        if (parts.length < 3) {
            systemDisplay("Argument manquant : /set pseudo ??");
        } else {
            systemDisplay("Commande détectée : /set pseudo " + parts[2]);
            systemDisplay("Ancien pseudo : " + PlayerData.pseudo);
            PlayerData.pseudo = parts[2];
            refreshView();
            systemDisplay("Nouveau pseudo : " + PlayerData.pseudo);
        }
    }

    private static void commandSetExp(String[] parts) {

        if (parts.length < 3) {
            systemDisplay("Argument manquant : /set exp ??");
        } else {

            Pattern pattern = Pattern.compile("^([+-]?\\d+)$");
            Matcher matcher = pattern.matcher(parts[2]);

            if (matcher.matches()) {
                systemDisplay("Commande détectée : /set exp " + parts[2]);
                int value = Integer.parseInt(matcher.group(1));
                systemDisplay("Ancien niveau : " + PlayerData.expLevel);
                switch (parts[2].charAt(0)) {
                    case '+':
                        PlayerData.expLevel += value;
                        break;
                    case '-':
                        PlayerData.expLevel += value; // Meme que + car parseInt gère le -
                        break;
                    default:
                        PlayerData.expLevel = value;
                        break;
                }
                refreshView();
                systemDisplay("Nouveau niveau : " + PlayerData.expLevel);
            } else {
                systemDisplay("Commande /set exp erronée : '" + parts[2] + "' est une valeur invalide.");
            }
        }
    }

    private static void commandSetMoney(String[] parts) {
        if (parts.length < 3) {
            systemDisplay("Argument manquant : /set money ??");
        } else {

            Pattern pattern = Pattern.compile("^([+-]?\\d+)$");
            Matcher matcher = pattern.matcher(parts[2]);

            if (matcher.matches()) {
                systemDisplay("Commande détectée : /set money " + parts[2]);
                int value = Integer.parseInt(matcher.group(1));
                systemDisplay("Argent avant : " + PlayerData.money);
                switch (parts[2].charAt(0)) {
                    case '+':
                        PlayerData.money += value;
                        break;
                    case '-':
                        PlayerData.money += value; // Meme que + car parseInt gère le -
                        break;
                    default:
                        PlayerData.money = value;
                        break;
                }
                refreshView();
                systemDisplay("Argent après : " + PlayerData.money);
            } else {
                systemDisplay("Commande /set money erronée : '" + parts[2] + "' est une valeur invalide.");
            }
        }
    }

    private static void commandSetAdmin(String[] parts) {
        if (parts.length < 3) {
            systemDisplay("Argument manquant : /set admin ??");
        } else {
            String value = parts[2].toLowerCase();
            if (value.equals("false") || value.equals("0")) {
                if (PlayerData.isAdmin == false) {
                    systemDisplay("Vous étiez déjà : Joueur");
                } else {
                    systemDisplay("Vous êtes devenu : Joueur");
                }

            } else if (value.equals("true") || value.equals("1")) {
                if (PlayerData.isAdmin == true) {
                    systemDisplay("Vous étiez déjà : Administrateur");
                } else {
                    systemDisplay("Vous êtes devenu : Administrateur");
                }

            } else {
                systemDisplay("Commande /set admin erronée : '" + parts[2] + "' est une valeur invalide.");
            }
        }
    }

    private static void commandSave() {
        systemDisplay("Commande détectée : /save");
        App.autoSaveAndQuit2();
    }

}
