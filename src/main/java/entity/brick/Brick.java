package entity.brick;
import entity.Entity;
import geometry.*;

public class Brick extends Entity {
    Coordonnee c;
    int durabilite;
    int type;

    public Brick(Coordonnee c, int type) {
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
