package gui;

import utils.GameConstants;

public class Inventaire {
    private String[] raquettes;
    private String[] balles;
    private boolean[][] inventaire;

    public Inventaire() {
        this.raquettes = new String[GameConstants.RAQUETTES_QTY];
        this.balles = new String[GameConstants.BALLES_QTY];
        this.inventaire = new boolean[2][GameConstants.RAQUETTES_QTY];
    }

    public void addRaquette(String raquette, int i) {
        raquettes[i] = raquette;
    }

    public void addBalle(String balle, int i) {
        balles[i] = balle;
    }

    public String[] getRaquettes() {
        return raquettes;
    }

    public String[] getBalles() {
        return balles;
    }

    public void afficheInventaire() {
        System.out.println("Raquettes : ");
        for (String raquette : raquettes) {
            System.out.println(raquette);
        }
        System.out.println("Balles : ");
        for (String balle : balles) {
            System.out.println(balle);
        }
    }

    public boolean[][] getInventaire() {
        return inventaire;
    }

    public void setInventaire(boolean[][] inventaire) {
        this.inventaire = inventaire;
    }

    public void achatRaquette(int i){
        inventaire[0][i] = true;
    }

    public void achatBalle(int i){
        inventaire[1][i] = true;
    }

}
