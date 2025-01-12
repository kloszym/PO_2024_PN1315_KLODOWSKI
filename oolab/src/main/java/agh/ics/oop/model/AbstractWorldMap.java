package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {

    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected List<MapChangeListener> observators = new ArrayList<>();
    protected UUID id = UUID.randomUUID();

    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)) {
            throw new IncorrectPositionException(position);
        }else {
            animals.put(animal.getPosition(), animal);
            mapChanged("Zwierzę zotało położone na %s".formatted(animal.getPosition()));
        }
    }

    public void move(Animal animal, MoveDirections direction) {
        if (objectAt(animal.getPosition()).get().equals(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            Vector2d newPosition = animal.getPosition();
            animals.put(animal.getPosition(), animal);
            mapChanged("Zwierze poruszyło sie z %s na %s.".formatted(oldPosition, newPosition));
        }
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public abstract Boundary getCurrentBounds();

    public final String toString() {
        Boundary boundaries = getCurrentBounds();
        return visualizer.draw(boundaries.lowerLeftCorner(),boundaries.upperRightCorner());
    }

    public void addObservator(MapChangeListener observator){
        observators.add(observator);
    }

    public void removeObservator(MapChangeListener observator){
        observators.remove(observator);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener observator : observators) {
            observator.mapChanged(this, message);
        }
    }

    public UUID getID(){
        return id;
    }

    public List<Animal> getOrderedAnimals() {
        return animals.keySet().stream()
                                .sorted(Comparator.comparing(Vector2d::getX)
                                    .thenComparing(Vector2d::getY))
                                .map(vector -> animals.get(vector))
                                .toList();
    }
}
