package agh.ics.oop.model;

import agh.ics.oop.model.util.AnimalBornListener;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProjectWorldMap extends MoveValidator { // czy ten interfejs coś wnosi?

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     *               It throws exception instead of returning
     */
    void place(Animal animal) throws IncorrectPositionException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal);


    /**
     * @return List of <WorldElement>
     */

    List<WorldElement> getElements();

    /**
     * @return id of map
     */

    UUID getID();

    /**
     * Kill an animal - it means move it to delete it from animal map
     *
     * @param animal to kill
     */

    void killAnimal(Animal animal);

    /**
     * Make animals love each other
     */
    void animalsReproducing();

    /**
     * Growing plants on our Globe is very important
     */
    void growPlants();

    /**
     * When out animals are hungry
     */
    void eatingPlants();

    /**
     *
     */
    boolean isPositionMoreDesirableForPlants(Vector2d position);

    /**
     * @return List of animals
     */

    List<Animal> getAnimalsList();

    /**
     * @return List of plants
     */
    List<Plant> getPlantsList();

    /**
     * @return Set of occupied positions on map
     */
    Set<Vector2d> occupiedPositions();

    /**
     * Add AnimalBornListener to list of animalBornListeners
     *
     * @param listener that listen animal birth
     */
    void addAnimalBornListener(AnimalBornListener listener);

}
