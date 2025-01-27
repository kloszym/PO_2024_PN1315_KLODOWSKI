package agh.ics.oop.model;


import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private Vector2d upperRightGrassesCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private Vector2d lowerLeftGrassesCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int howManyGrasses) {
        int length = (int) Math.ceil(Math.sqrt(howManyGrasses * 10));
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(length, length, howManyGrasses);
        for (Vector2d grassPosition : randomPositionGenerator) {
            upperRightGrassesCorner = grassPosition.upperRight(upperRightGrassesCorner);
            lowerLeftGrassesCorner = grassPosition.lowerLeft(lowerLeftGrassesCorner);
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (Objects.equals(null, super.objectAt(position))) {
            if (grasses.containsKey(position)) {
                return grasses.get(position);
            }
        }
        return super.objectAt(position);
    }


    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll((grasses.values()));
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d upperRightMapCorner = upperRightGrassesCorner;
        Vector2d lowerLeftMapCorner = lowerLeftGrassesCorner;
        for (Vector2d position : animals.keySet()) {
            upperRightMapCorner = position.upperRight(upperRightMapCorner);
            lowerLeftMapCorner = position.lowerLeft(lowerLeftMapCorner);
        }
        return new Boundary(lowerLeftMapCorner, upperRightMapCorner);
    }
}
