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

    public void addEffect(char d) {
        if(d=='d'){
            this.angle += 10;
        }
        else{
            this.angle -= 10;
        }
    }

    public double getEffect() {
        return angle;
    }

    public void Collision() {
        if(angle>0){
            angle -= 2;
        }
        else if(angle<0){
            angle += 2;
        }
    }

    public void stopRotation() {
        this.angle = 0;
    }

}
