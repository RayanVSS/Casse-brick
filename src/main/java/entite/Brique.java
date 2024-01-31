package entite;
import geometry.*;

public class Brique extends Entite{
    Coordonnee c;
    int durabilite;
    int type;

    public Brique(Coordonnee c, int type) {
        this.c = c;
        this.type = type;
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void setDurabilite(int durabilite) {
        this.durabilite = durabilite;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Coordonnee getC() {
        return c;
    }



}
