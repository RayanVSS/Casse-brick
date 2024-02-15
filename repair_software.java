import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



//ouvrir le navigateur par défaut
import java.awt.Desktop;
import java.net.URI;


public class repair_software {
    public static String directoryPath = "src/main/java/save/";
    public static File directory = new File(directoryPath);
    public static File[] files = directory.listFiles();
    public static int nombre_corrompu = 0;
    public static Boolean fini = false;


    // Méthode pour effacer l'écran du terminal
    public static void clearScreen() {
        System.out.print("\033\143");
        for (int i = 0; i < 50; ++i) System.out.println();
    }


    public static int nombre_sauvegardes(){
        return files.length-1;
    }


    public static void liste_sauvegarde(){
        nombre_corrompu = 0;
        System.out.println("            Liste des sauvegardes ("+ nombre_sauvegardes() + ")");
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".json")) {
                    // Validez le fichier de sauvegarde
                    if (isValidSave(file)) {
                        System.out.println("    -" + file.getName() + " ; état: VALIDE");
                    } else {
                        // Supprimez les sauvegardes corrompues
                        System.out.println("    -" + file.getName() + " ; état: CORROMPU");
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
        public static void attendre(int millisecondes){
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
    

    public static void affichage(){
        System.out.println("***********************************************");
        System.out.println("***********  REPARATION  LOGICIEL *************");
        System.out.println("***********************************************");
    }

    public static void gestion_corrompu(){
        System.out.println("voulais-vous supprimer les sauvegardes corrompues ? (oui/non) ");
        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("oui")) {
            for (File file : files) {
                if (file.getName().endsWith(".json")) {
                    if (!isValidSave(file)) {
                        file.delete();
                    }
                }
            }
            System.out.println("Suppression en cours...");
            attendre(150);     
        }
    }

    public static void gestion_sauvegarde(){
        System.out.println("voulais-vous supprimer toutes les sauvegardes ? (oui/non) ");
        Scanner sc = new Scanner(System.in);
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

    public static void reparation_automatique(){
        //reparation automatique
    }

    public static void reparation_manuelle(){
        try {
            // URI avec l'URL de Google
            URI uri = new URI("https://docs.google.com/document/d/1IMw7YGUrqvP2gnF9YBgn0GafSwBWSItgeerTP6hS_WI/edit?usp=sharing");
            
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
            
    public static void action(){
        System.out.println("            que voulez-vous faire ? ");
        System.out.println("#1# suprimer toutes les sauvegardes");
        System.out.println("#2# supprimer les sauvegardes corrompues");
        System.out.println("#3# reparation automatique de la sauvegarde");
        System.out.println("#4# reparation manuelle de la sauvegarde");
        System.out.println("#5# quitter");
        System.out.println("***********************************************");
        System.out.print("votre choix : ");
        Scanner sc = new Scanner(System.in);
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
                System.out.println("Fin du programme");
                fini = true;
                break;
            default:
                break;
        }
    }

    // Méthode pour valider la sauvegarde
    private static boolean isValidSave(File file) {
        try {
            //contenu du fichier de sauvegarde
            List<String> lines = Files.readAllLines(file.toPath());
            // Implémentez la logique de validation 
            return true;
        } catch (IOException e) {
            System.err.println("    (SUPPRIMER)");
            return false; //comme corrompue en cas d'erreur de lecture
        }
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
