package entity.brick;
import entity.Entity;
import geometry.*;

public class Brick extends Entity {
    Cordinates c;
    int durabilite;
    int type;

    public Brick(Cordinates c, int type) {
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

    public Cordinates getC() {
        return c;
    }



}
