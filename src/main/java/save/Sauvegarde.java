package save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *sauvegarder les données du jeu:
 *  
 * 1) créer 3 variables de type "Map<String, Object>", "Sauvegarde" et "String"
 *      Sauvegarde sauvegarde = new Sauvegarde();
 *      String nomUtilisateur = "utilisateur1";
 *      Map<String, Object> donnees = new HashMap<>();
 *  
 * 2) ajouter les données à sauvegarder dans la variable "donnees"
 *      donnees.put("niveau", 5);
 *  
 * 3) sauvegarder les données
 *     sauvegarde.sauvegarderDonnees(donnees);
 *  
 * 4) charger les données
 *    Map<String, Object> donneesChargees = sauvegarde.chargerDonnees();
 *    System.out.println("Données chargées : " + donneesChargees);
 * 
 * 
 * Exemple d'utilisation:
 *          
            Sauvegarde sauvegarde = new Sauvegarde();
            String nomUtilisateur = "test";
            Map<String, Object> donnees = new HashMap<>();
            donnees.put("niveau", 5);
            donnees.put("score", 1000);
            sauvegarde.sauvegarderDonnees(nomUtilisateur, donnees);
            Map<String, Object> donneesChargees = sauvegarde.chargerDonnees(nomUtilisateur);
            System.out.println("Donnees chargees : " + donneesChargees);
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

    public Sauvegarde() {
    }

    // Sauvegarde Les données
    public void sauvegarderDonnees(String nomUtilisateur, Map<String, Object> donnees) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomUtilisateur + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(cheminFichierSauvegarde)) {
            gson.toJson(donnees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Lire les données
    public Map<String, Object> chargerDonnees(String nomUtilisateur) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomUtilisateur + ".json";
        Gson gson = new Gson();
        Map<String, Object> donnees = new HashMap<>();
        try (FileReader reader = new FileReader(cheminFichierSauvegarde)) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            donnees = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

    //Lister les sauvegardes
    public List<String> listerSauvegardes() {
        List<String> sauvegardes = new ArrayList<>();
        File repertoireSauvegardes = new File(cheminRepertoireSauvegardes);
        if (repertoireSauvegardes.exists() && repertoireSauvegardes.isDirectory()) {
            File[] fichiers = repertoireSauvegardes.listFiles();
            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (fichier.isFile() && fichier.getName().endsWith(".json")) {
                        sauvegardes.add(fichier.getName());
                    }
                }
            }
        }
        return sauvegardes;
    }
        
    // Supprimer une sauvegarde
    public void supprimerSauvegarde(String nomSauvegarde) {
        String cheminFichierSauvegarde = cheminRepertoireSauvegardes + nomSauvegarde ;
        File fichierSauvegarde = new File(cheminFichierSauvegarde);
        if (fichierSauvegarde.exists() && fichierSauvegarde.isFile() && fichierSauvegarde.getName().endsWith(".json")) {
            if (fichierSauvegarde.delete()) {
                System.out.println("La sauvegarde '" + nomSauvegarde + "' a ete supprimée.");
            } else {
                System.out.println("Impossible de supprimer la sauvegarde '" + nomSauvegarde);
            }
        } else {
            System.out.println("La sauvegarde '" + nomSauvegarde + "' n'existe pas");
        }
    }
}
