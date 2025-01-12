package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void getOrderedListOfAnimals() {
        GrassField field = new GrassField(0);
        Animal animal2 = new Animal(new Vector2d(2, 2));
        Animal animal1 = new Animal(new Vector2d(1, 2));
        Animal animal3 = new Animal(new Vector2d(2, 5));
        Animal animal5 = new Animal(new Vector2d(3, 9));
        Animal animal4 = new Animal(new Vector2d(3, 2));
        try {
            field.place(animal2);
            field.place(animal1);
            field.place(animal3);
            field.place(animal5);
            field.place(animal4);
        } catch (IncorrectPositionException e) {
            System.out.println(e.getMessage());
        }
        List<Animal> actualOrder = field.getOrderedAnimals();

        assertEquals(animal1, actualOrder.get(0));
        assertEquals(animal2, actualOrder.get(1));
        assertEquals(animal3, actualOrder.get(2));
        assertEquals(animal4, actualOrder.get(3));
        assertEquals(animal5, actualOrder.get(4));

    }

    @Test
    void getEmptyListOfAnimalsWhenGettingOrderedList() {
        GrassField field = new GrassField(0);

        List<Animal> animals = field.getOrderedAnimals();

        assertEquals(animals.size(), 0);

    }
}