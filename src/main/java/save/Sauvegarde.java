package save;

import static utils.GameConstants.CSS;
import static utils.GameConstants.FPS;
import static utils.GameConstants.LAST_SAVE;
import static utils.GameConstants.LEFT;
import static utils.GameConstants.MUSIC;
import static utils.GameConstants.PARTICLES;
import static utils.GameConstants.PATH;
import static utils.GameConstants.RIGHT;
import static utils.GameConstants.SOUND;
import static utils.GameConstants.SPACE;
import static utils.GameConstants.TEXTURE;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import config.StagesProgress;
import gui.Console;
import gui.Menu.Menu.Theme;
import javafx.scene.input.KeyCode;
import utils.GameConstants;

/**
 * sauvegarder les données du jeu:
 * 
 * 1) créer 3 variables de type "Map<String, Object>", "Sauvegarde" et "String"
 * Sauvegarde sauvegarde = new Sauvegarde();
 * String nomUtilisateur = "utilisateur1";
 * Map<String, Object> donnees = new HashMap<>();
 * 
 * 2) ajouter les données à sauvegarder dans la variable "donnees"
 * donnees.put("niveau", 5);
 * 
 * 3) sauvegarder les données
 * sauvegarde.sauvegarderDonnees(donnees);
 * 
 * 4) charger les données
 * Map<String, Object> donneesChargees = sauvegarde.chargerDonnees();
 * System.out.println("Données chargées : " + donneesChargees);
 * 
 * 
 * Exemple d'utilisation:
 * 
 * Sauvegarde sauvegarde = new Sauvegarde();
 * String nomUtilisateur = "test";
 * Map<String, Object> donnees = new HashMap<>();
 * donnees.put("niveau", 5);
 * donnees.put("score", 1000);
 * sauvegarde.sauvegarderDonnees(nomUtilisateur, donnees);
 * Map<String, Object> donneesChargees =
 * sauvegarde.chargerDonnees(nomUtilisateur);
 * System.out.println("Donnees chargees : " + donneesChargees);
 * 
 * 
 * @function sauvegarderDonnees: sauvegarde les données dans json
 * @function chargerDonnees: charger les données depuis json
 * @function listerSauvegardes: liste toutes les sauvegardes disponibles
 * 
 * 
 * @author Rayan Belhassen
 */

public class Sauvegarde {

    private final String cheminRepertoireSauvegardes = "src/main/java/save/";
    private static final Map<String, KeyCode> keyCodeMap = new HashMap<>();

    static {
        keyCodeMap.put("LEFT", KeyCode.LEFT);
        keyCodeMap.put("RIGHT", KeyCode.RIGHT);
        keyCodeMap.put("SPACE", KeyCode.SPACE);

    }

    public Sauvegarde() {
    }

    // Sauvegarde Les données
    public void sauvegarderToutesDonnees(String name) {

        Map<String, Object> allData = new HashMap<>();
        allData.putAll(sauvegarderOptionsDuJeu(name));
        allData.putAll(sauvegarderPlayerData(name));
        sauvegarderDonnees(name, allData);
    }

    public void sauvegarderDonnees(String nomUtilisateur, Map<String, Object> donnees) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomUtilisateur + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(cheminFichierSauvegarde)) {
            gson.toJson(donnees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> sauvegarderOptionsDuJeu(String name) {
        Map<String, Object> options = new HashMap<>();
        options.put("FPS", FPS);
        options.put("PATH", PATH);
        options.put("PARTICLES", PARTICLES);
        options.put("SOUND", SOUND);
        options.put("MUSIC", MUSIC);
        options.put("LEFT", LEFT);
        options.put("RIGHT", RIGHT);
        options.put("SPACE", SPACE);
        options.put("CSS", CSS);
        options.put("TEXTURE", TEXTURE);

        return options;
    }

    public Map<String, Object> sauvegarderPlayerData(String name) {

        Map<String, Object> playerData = new HashMap<>();
        playerData.put("PSEUDO", PlayerData.pseudo);
        playerData.put("EXP_LEVEL", PlayerData.expLevel);
        playerData.put("MONEY", PlayerData.money);
        playerData.put("ADMIN", PlayerData.isAdmin);
        playerData.put("PROGRESS", PlayerData.stagesProgress);

        return playerData;
    }

    // Charge toutes les données
    public void chargerToutesDonnees(String name) {
        chargerOptionsDuJeu(name);
        chargerPlayerData(name);
    }

    public void chargerOptionsDuJeu(String name) {
        Map<String, Object> options = getDonnees(name);
        if (options != null) {
            FPS = (boolean) options.getOrDefault("FPS", true);
            PATH = (boolean) options.getOrDefault("PATH", false);
            PARTICLES = (boolean) options.getOrDefault("PARTICLES", true);
            SOUND = ((Number) options.getOrDefault("SOUND", 50)).intValue();
            MUSIC = ((Number) options.getOrDefault("MUSIC", 50)).intValue();
            LEFT = Sauvegarde.getKeyCode((String) options.getOrDefault("LEFT", "LEFT"));
            RIGHT = Sauvegarde.getKeyCode((String) options.getOrDefault("RIGHT", "RIGHT"));
            SPACE = Sauvegarde.getKeyCode((String) options.getOrDefault("SPACE", "SPACE"));
            //CSS =  /*(Theme) options.getOrDefault("CSS", Theme.DARK);*/ Theme.valueOf((String) options.getOrDefault("CSS", "DARK"));
            String cssOption = (String) options.getOrDefault("CSS", "DARK");
            try {
                CSS = Theme.valueOf(cssOption.toUpperCase());
            } catch (IllegalArgumentException e) {
                CSS = Theme.CLASSIC; // valeur par défaut si l'option CSS n'est pas une constante de l'énumération Theme
            }
            TEXTURE = (String) options.getOrDefault("TEXTURE", "Null");
        }
    }

    public void chargerPlayerData(String name) {
        Map<String, Object> playerData = getDonnees(name);
        if (playerData != null) {
            PlayerData.pseudo = (String) playerData.getOrDefault("PSEUDO", "Sans nom");
            PlayerData.expLevel = ((Number) playerData.getOrDefault("EXP_LEVEL", 1)).intValue();
            PlayerData.money = ((Number) playerData.getOrDefault("MONEY", 0)).intValue();
            PlayerData.isAdmin = (boolean) playerData.getOrDefault("ADMIN", false);
            PlayerData.stagesProgress = new Gson().fromJson("" + playerData.get("PROGRESS"),
                    StagesProgress.class);
        }
    }

    public void chargerLastSave() {
        Map<String, Object> lastSave = getDonnees("lastSave.json");
        if (lastSave != null) {
            LAST_SAVE = (String) lastSave.getOrDefault("SAVE", "");
        }
    }

    public void resetLastSave() {
        Map<String, Object> lastSave = new HashMap<>();
        lastSave.put("SAVE", "");
        sauvegarderDonnees("lastSave", lastSave);
    }

    // Lire les données & obtenir la map de données
    public Map<String, Object> getDonnees(String nomUtilisateur) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomUtilisateur;
        Map<String, Object> save = new HashMap<>();
        if (!nomUtilisateur.equals("lastSave.json")) {
            save.put("SAVE", nomUtilisateur);
            LAST_SAVE = nomUtilisateur;
            sauvegarderDonnees("lastSave", save);
        }
        Gson gson = new Gson();
        Map<String, Object> donnees = new HashMap<>();
        try (FileReader reader = new FileReader(cheminFichierSauvegarde)) {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            donnees = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

    // Lister les sauvegardes
    public List<String> listerSauvegardes() {
        List<String> sauvegardes = new ArrayList<>();
        File repertoireSauvegardes = new File(cheminRepertoireSauvegardes);
        if (repertoireSauvegardes.exists() && repertoireSauvegardes.isDirectory()) {
            File[] fichiers = repertoireSauvegardes.listFiles();
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (fichier.isFile() && fichier.getName().endsWith(".json")
                            && !fichier.getName().equals("lastSave.json")) {
                        sauvegardes.add(fichier.getName());
                    }
                }
            }
        }
        return sauvegardes;
    }

    // Supprimer une sauvegarde
    public void supprimerSauvegarde(String nomSauvegarde) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomSauvegarde;
        File fichierSauvegarde = new File(cheminFichierSauvegarde);
        if (fichierSauvegarde.exists() && fichierSauvegarde.isFile() && fichierSauvegarde.getName().endsWith(".json")
                && !fichierSauvegarde.getName().equals("lastSave.json")) {
            if (fichierSauvegarde.delete()) {
                Console.systemDisplay("La sauvegarde '" + nomSauvegarde + "' a été supprimée.");
            } else {
                Console.systemDisplay("Impossible de supprimer la sauvegarde : '" + nomSauvegarde + "'");
            }
        } else {
            Console.systemDisplay("La sauvegarde '" + nomSauvegarde + "' n'existe pas.");
        }
    }

    public static KeyCode getKeyCode(Object key) {
        if (key instanceof KeyCode) {
            return (KeyCode) key;
        } else if (key instanceof String) {
            String keyName = (String) key;
            if (keyName.length() == 1) {
                return KeyCode.valueOf(keyName);
            } else {
                return keyCodeMap.getOrDefault(keyName, KeyCode.UNDEFINED);
            }
        } else {
            return KeyCode.UNDEFINED;
        }
    }

    public void setupLastSave() {
        Console.systemDisplay("Chargement de la dernière sauvegarde...");
        chargerLastSave();
        if (!LAST_SAVE.equals("")) {
            chargerOptionsDuJeu(LAST_SAVE);
            chargerPlayerData(LAST_SAVE);
            Console.systemDisplay("Sauvegarde chargée : '" + LAST_SAVE + "'");
        } else {
            Console.systemDisplay("Impossible de charger la dernière sauvegarde.");
        }
    }

    public void autoSave() {
        String saveName;
        if (GameConstants.LAST_SAVE.equals("")) {
            saveName = "autoTempSave";
            Map<String, Object> lastSave = new HashMap<>();
            lastSave.put("SAVE", saveName + ".json");
            sauvegarderDonnees("lastSave", lastSave);
        } else {
            saveName = GameConstants.LAST_SAVE.replace(".json", "");
        }
        sauvegarderToutesDonnees(saveName);
        GameConstants.LAST_SAVE = saveName + ".json";
        Console.systemDisplay("Sauvegarde automatique de '" + GameConstants.LAST_SAVE + "' effectuée avec succès.");
        Console.systemDisplay("Fermeture du jeu...");
    }
}
