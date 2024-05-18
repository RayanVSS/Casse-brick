package physics.geometry;

public class Rotation {

    Vector vecteur;
    private double angle;

    public Rotation() {
        this.angle = 0;
        vecteur = new Vector(0,0);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }


    public void addEffect(double angle) {
        if(Math.abs(this.angle) >= 180){
            return;
        }
        this.angle += angle;
    }

    public double getEffect() {
        return angle;
    }

    public void Collision() {
        if(angle>3){
            angle -= 3;
        }
        else if(angle<3){
            angle += 3;
        }
    }

    public void stopRotation() {
        this.angle = 0;
    }

    public double getX() {
        return vecteur.getX();
    }

    public double getY() {
        return vecteur.getY();
    }

    public void setX(double x) {
        vecteur.setX(x);
    }

    public void setY(double y) {
        vecteur.setY(y);
    }

    public void addX(double x) {
        vecteur.setX(vecteur.getX() + x);
    }

    public void addY(double y) {
        vecteur.setY(vecteur.getY() + y);
    }

}
