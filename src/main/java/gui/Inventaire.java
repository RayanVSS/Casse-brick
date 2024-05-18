package gui;

import java.util.HashSet;


public class Inventaire extends HashSet<String>{

    public Inventaire() {
        super();
    }

    public boolean isBought(String ele) {
        return this.contains(ele);
    }

    public void addItem(String String) {
        this.add(String);
    }

    public void afficheInventaire() {
        for (String s : this) {
            System.out.println(s);
        }
    }
}