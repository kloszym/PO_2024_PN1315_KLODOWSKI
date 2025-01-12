package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {

    private Map<Vector2d, Grass> grasses = new HashMap<>();
    private Vector2d upperRightGrassesCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private Vector2d lowerLeftGrassesCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private Vector2d upperRightMapCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private Vector2d lowerLeftMapCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int howManyGrasses) {
        int maxWidth = (int)Math.ceil(Math.sqrt(howManyGrasses * 10));
        int maxHeight = (int)Math.ceil(Math.sqrt(howManyGrasses * 10));
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, howManyGrasses);
        for(Vector2d grassPosition : randomPositionGenerator) {
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
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(super.objectAt(position)).orElse(Optional.ofNullable(grasses.get(position)));
    }




    public List<WorldElement> getElements(){
        return Stream.concat(super.getElements().stream(),grasses.values().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds(){
        upperRightMapCorner=upperRightGrassesCorner;
        lowerLeftMapCorner=lowerLeftGrassesCorner;
        for (Vector2d position : animals.keySet()){
            upperRightMapCorner = position.upperRight(upperRightMapCorner);
            lowerLeftMapCorner = position.lowerLeft(lowerLeftMapCorner);
        }
        return new Boundary(lowerLeftMapCorner, upperRightMapCorner);
    }
}
