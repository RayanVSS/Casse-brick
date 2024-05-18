package physics.geometry;

public class Rotation {

    private double angle;

    public Rotation() {
        this.angle = 0;
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

}
