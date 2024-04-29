import static utils.GameConstants.FPS;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.checkerframework.common.value.qual.DoubleVal;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

public class repair_software {
    public static String directoryPath = "src/main/java/save/";
    public static File directory = new File(directoryPath);
    public static File[] files = directory.listFiles();
    public static int nombre_corrompu = 0;
    public static Boolean fini = false;

    public static Scanner sc = new Scanner(System.in);

    // Méthode pour effacer l'écran du terminal
    public static void clearScreen() {
        System.out.print("\033\143");
        for (int i = 0; i < 50; ++i)
            System.out.println();
    }

    public static int nombre_sauvegardes() {
        return files.length - 3;
    }

    public static void liste_sauvegarde() {
        nombre_corrompu = 0;
        System.out.println("            Liste des sauvegardes (" + nombre_sauvegardes() + ")");
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".json") && !file.getName().equals("lastSave.json")){
                    // Validez le fichier de sauvegarde
                    if (isValidSave(file)) {
                        System.out.println("    -" + file.getName() + " ; etat: VALIDE");
                    } else {
                        // Supprimez les sauvegardes corrompues
                        System.out.println("    -" + file.getName() + " ; etat: CORROMPU");
                        nombre_corrompu++;
                    }
                }
            }
            if (nombre_corrompu > 0) {
                System.out.println("Il y a " + nombre_corrompu + " sauvegardes corrompues");
                System.out.println("***********************************************");
            } else {
                System.out.println("Toutes les sauvegardes sont valides.");
                System.out.println("***********************************************");

            }
        } else {
            System.out.println("Le répertoire de sauvegardes est vide ou inaccessible.");
        }

    }

    public static void attendre(int millisecondes) {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.print("[");
                for (int j = 0; j < i; j++) {
                    System.out.print("=");
                }
                for (int k = i; k < 20; k++) {
                    System.out.print(" ");
                }
                System.out.print("] " + ((i + 1) * 5) + "%\r");
                TimeUnit.MILLISECONDS.sleep(150); // Attendez 150 millisecondes
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void affichage() {
        System.out.println("***********************************************");
        System.out.println("***********  REPARATION  LOGICIEL *************");
        System.out.println("***********************************************");
    }

    public static void gestion_corrompu() {
        System.out.println("voulais-vous supprimer les sauvegardes corrompues ? (oui/non) ");
        String reponse = sc.nextLine();
        if (reponse.equals("oui")) {
            for (File file : files) {
                if (file.getName().endsWith(".json") && !file.getName().equals("lastSave.json")) {
                    if (!isValidSave(file)) {
                        file.delete();
                    }
                }
            }
            System.out.println("Suppression en cours...");
            attendre(150);
        }
    }

    public static void gestion_sauvegarde() {
        System.out.println("voulais-vous supprimer toutes les sauvegardes ? (oui/non) ");
        String reponse = sc.nextLine();
        if (reponse.equals("oui")) {
            for (File file : files) {
                if (file.getName().endsWith(".json")) {
                    file.delete();
                }
            }
            System.out.println("Suppression en cours...");
            attendre(120);
        }
    }

    public static void reparation_automatique() {
        System.out.println("voulais-vous réparer toutes les sauvegardes ? (oui/non) ");
        String reponse = sc.nextLine();
        if (reponse.equals("oui")) {
        for (File file : files) {
            if (file.getName().endsWith(".json") && !file.getName().equals("lastSave.json")) {
                if (!isValidSave(file)) {
                    //TODO: Réparer le fichier
                }
            }
        }
        System.out.println("Réparation en cours...");
        attendre(120);
    }
    }

    public static void reparation_manuelle() {
        try {
            // URI avec l'URL de Google
            URI uri = new URI(
                    "https://docs.google.com/document/d/1IMw7YGUrqvP2gnF9YBgn0GafSwBWSItgeerTP6hS_WI/edit?usp=sharing");

            //pris en charge et navigateur par défaut est disponible
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                // Ouvrez l'URL 
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Impossible d'ouvrir le navigateur par défaut");
                System.out.println("Veuillez ouvrir le lien suivant dans votre navigateur: ");
                System.out.println(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ouvrire_fichier() {
        File fichier = new File("src/main/java/save");
        try {
            Desktop.getDesktop().open(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Lire les données & obtenir la map de données
    public static Map<String, Object> getDonnees(File fichier) {
        Gson gson = new Gson();
        Map<String, Object> donnees = new HashMap<>();
        try (FileReader reader = new FileReader(fichier)) {
            Type type = new TypeToken<Map<String, Object>>() {}.getType();
            donnees = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

    public static void enlever_lastSave(){
        Map<String, Object> options = getDonnees(new File("src/main/java/save/lastSave.json"));
        options.put("SAVE", "");
        sauvegarderDonnees("lastSave", options);
    }

    public static void sauvegarderDonnees(String nomUtilisateur, Map<String, Object> donnees) {
        String cheminFichierSauvegarde = directoryPath + nomUtilisateur + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(cheminFichierSauvegarde)) {
            gson.toJson(donnees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void action() {
        System.out.println("            que voulez-vous faire ? ");
        System.out.println("#1# suprimer toutes les sauvegardes");
        System.out.println("#2# supprimer les sauvegardes corrompues");
        System.out.println("#3# reparation automatique de la sauvegarde");
        System.out.println("#4# reparation manuelle de la sauvegarde");
        System.out.println("#5# ouvrir le fichier de sauvegarde");
        System.out.println("#6# enlever la sauvegarde par defaut");
        System.out.println("#7# quitter");
        System.out.println("***********************************************");
        System.out.print("votre choix : ");
        String reponse = sc.nextLine();
        switch (reponse) {
            case "1":
                gestion_sauvegarde();
                break;
            case "2":
                gestion_corrompu();
                break;
            case "3":
                reparation_automatique();
                break;
            case "4":
                reparation_manuelle();
                break;
            case "5":
                ouvrire_fichier();
                break;
            case "6":
                enlever_lastSave();
                break;
            case "7":
                System.out.println("Fin du programme");
                fini = true;
                break;
            default:
                break;
        }
    }

    // Méthode pour valider la sauvegarde
    private static boolean isValidSave(File file) {
        Map<String, Object> options = getDonnees(file);
            if(!(options.get("FPS") instanceof Boolean)) {
                return false;
            }
            if (!(options.get("PATH") instanceof Boolean)) {
                return false;
            }
            if (!(options.get("PARTICLES") instanceof Boolean)) {
                return false;
            }
            if (!(options.get("SOUND") instanceof Number)) {
                return false;
            }
            if (!(options.get("MUSIC") instanceof Number)) {
                return false;
            }
            if (!(options.get("LEFT") instanceof String)) {
                return false;
            }
            if (!(options.get("RIGHT") instanceof String)) {
                return false;
            }
            if (!(options.get("SPACE") instanceof String)) {
                return false;
            }
            if (!(options.get("CSS").equals("DARK") || options.get("CSS").equals("LIGHT") || 
            options.get("CSS").equals("PINK") || options.get("CSS").equals("CLASSIC") || 
            options.get("CSS").equals("BLACK") || options.get("CSS").equals("ACHROMATOPSIE") ||
            options.get("CSS").equals("DEUTERANOPIE") || options.get("CSS").equals("PROTANOPIE") ||
            options.get("CSS").equals("TRITANOPIE"))){
                return false;
            }
            if(!(options.get("TEXTURE") instanceof String)) {
                return false;
            }
            if(!(options.get("EXP_LEVEL") instanceof Number)) { 
                return false;
            }else if(((Number) options.get("EXP_LEVEL")).doubleValue() < 0 ){
                return false;
            }
            if (!(options.get("PROGRESS") instanceof Map)) {
                return false;
            }
            
            Map<String, Object> progress = (Map<String, Object>) options.get("PROGRESS");
            
            if (!progress.containsKey("stages") || !(progress.get("stages") instanceof List)) {
                return false;
            }
            
            List<Map<String, Object>> stages = (List<Map<String, Object>>) progress.get("stages");

            if( stages.size() != 27){
                return false;
            }
            
            for (Map<String, Object> stage : stages) {
                if (!(stage.containsKey("difficulty") && stage.containsKey("unlockLevel") &&
                      stage.containsKey("completed") && stage.containsKey("maxScore") &&
                      stage.containsKey("customGame"))) {
                    return false;
                }
            
                if (!(stage.get("difficulty") instanceof Number && stage.get("unlockLevel") instanceof Number &&
                      stage.get("completed") instanceof Boolean && stage.get("maxScore") instanceof Number &&
                      stage.get("customGame") instanceof Boolean)) {
                    return false;
                }
            }


        return true;
    }

    

    public static void main(String[] args) {
        while (!fini) {
            clearScreen();
            affichage();
            liste_sauvegarde();
            action();
        }
    }

}
