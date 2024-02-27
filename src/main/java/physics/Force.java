package physics;

import geometry.Coordinates;

public class Force {
    private Coordinates force;
    private double mass;

    public Force(Coordinates force, double mass) {
        this.force = force;
        this.mass = mass;
    }

    public double getX() {
        return force.getX();
    }

    public double getY() {
        return force.getY();
    }

    public Coordinates getForce() {
        return force;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Coordinates getAcceleration() {
        return new Coordinates(force.getX() / mass, force.getY() / mass);
    }

    public void addForce(Force force) {
        this.force.setX(this.force.getX() + force.getX());
        this.force.setY(this.force.getY() + force.getY());
    }

    public void addMass(double mass) {
        this.mass += mass;
    }

    public void removeForce(Force force) {
        this.force.setX(this.force.getX() - force.getX());
        this.force.setY(this.force.getY() - force.getY());
    }

    public void removeMass(double mass) {
        this.mass -= mass;
    }

    public void clearForce() {
        this.force.setX(0);
        this.force.setY(0);
    }
    
}
