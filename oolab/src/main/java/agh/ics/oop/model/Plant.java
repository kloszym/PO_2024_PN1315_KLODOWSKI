package agh.ics.oop.model;

public class Plant implements WorldElement {

    private final int energy;
    private final Vector2d position;


    public Plant(int energy, Vector2d position) {
        this.energy = energy;
        this.position = position;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "#";
    }
}
