package utils;

import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import java.util.Set;
import java.util.HashSet;

import config.Game;

/***************************************************************************
 *                  Explication de classe pour lire les touches            *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*
 *   Comment l'implementer dans un autre code: (voir "handleKeyPress" dans racket)
 * 
 * 1)ecrivez une fonction qui prend en parametre un "Set<KeyCode> keysPressed":
 * 2)ecrivez une boucle qui parcourt la liste que vous avez mis en parametre
 * 3)ecrivez un switch qui prend en parametre la touche que vous voulez
 *Exemple: 
 * 
 * public void Exemple(Set<KeyCode> keysPressed) {
 *         for (KeyCode key : keysPressed) {
 *           switch (key) {
 *               case (la touche que vous voulez):
 *                   //Le code que vous voulez executer
 *                   break;
 * 
 * 4)appelez cette fonction dans la fonction "handleInput" écrit plus bas
 * 
 * @function touches:
 * @param scene : la scene dans laquelle on veut lire les touches
 * @param game : le jeu dans lequel on veut lire les touches
 * le code permet de lire les touches du clavier et qui l'ajoute dans la liste "keysPressed"
 * 
 * @function handleInput:
 * le code permet de rassembler toutes les fonctions qui agissent avec une touche
 * !!!!!!!C'EST ICI QUE VOUS APPELEZ VOTRE FONCTION!!!!!!!
 *
 *  les autres fonctions sont des getters, setters et des fonctions qui donnent des 
 *                      informations sur la liste de touches:
 * 
 * @function getKeysPressed: retourne la liste de touches
 * @function setKeysPressed: vide la liste de touches et la remplace par une autre
 * @function clear: vide la liste de touches
 * @function add: ajoute une touche a la liste
 * @function remove: enleve une touche de la liste
 * @function contains: retourne true si la liste contient la touche, false sinon
 * @function isEmpty: retourne true si la liste est vide, false sinon
 * @function size: retourne la taille de la liste
 * 
 * 
 * @author Belhassen rayan
 */




public class Key  {
    private final Set<KeyCode> keysPressed = new HashSet<>();


    public Key() {
    }
    
    //code qui permet de lire les touches du clavier et qui l'ajoute dans une liste
    public void touchesM(Scene scene, Game game){
    scene.setOnKeyPressed(event -> {
            keysPressed.add(event.getCode());
        });
    }

    public void touchesR(Scene scene, Game game){
                //quand la touche est relachée, on l'enlève de la liste
                scene.setOnKeyReleased(event -> {
                    keysPressed.remove(event.getCode());
                });
            }

    //fonction qui rassemble toutes les fonctions qui agissent avec une touche
    public void handleInput(Game game) {
        game.getRacket().handleKeyPress(keysPressed);
    }


    //getters,setters et fonctions qui donne des informations sur la liste de touches
    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }


    public void setKeysPressed(Set<KeyCode> keysPressed) {
        this.keysPressed.clear();
        this.keysPressed.addAll(keysPressed);
    }

    public void clear() {
        keysPressed.clear();
    }

    public void add(KeyCode key) {
        keysPressed.add(key);
    }

    public void remove(KeyCode key) {
        keysPressed.remove(key);
    }

    public boolean contains(KeyCode key) {
        return keysPressed.contains(key);
    }

    public boolean isEmpty() {
        return keysPressed.isEmpty();
    }

    public int size() {
        return keysPressed.size();
    }


}
