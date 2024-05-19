package gui;

import java.util.HashSet;

/**
 * Classe représentant l'inventaire du joueur dans le jeu.
 * Cette classe est responsable de la gestion des éléments achetés par le joueur.
 * @author Benmalek Majda
 */
public class Inventaire extends HashSet<String>{

    /**
     * Constructeur de la classe Inventaire.
     * Initialise un nouvel inventaire vide.
     */
    public Inventaire() {
        super();
    }

    /**
     * Vérifie si un élément a été acheté.
     *
     * @param ele L'élément à vérifier.
     * @return true si l'élément a été acheté, false sinon.
     */
    public boolean isBought(String ele) {
        return this.contains(ele);
    }

    /**
     * Ajoute un nouvel élément à l'inventaire.
     *
     * @param item L'élément à ajouter à l'inventaire.
     */
    public void addItem(String String) {
        this.add(String);
    }

    /**
     * Affiche tous les éléments de l'inventaire.
     * Cette méthode est utilisée pour le débogage et l'affichage de l'inventaire dans la console.
     */
    public void afficheInventaire() {
        for (String s : this) {
            System.out.println(s);
        }
    }
}