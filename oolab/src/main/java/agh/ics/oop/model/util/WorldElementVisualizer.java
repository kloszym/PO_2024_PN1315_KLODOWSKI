package agh.ics.oop.model.util; // to jest util?

import agh.ics.oop.model.WorldElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WorldElementVisualizer {

    private final Image northImage = new Image("NORTH.png");
    private final Image northEastImage = new Image("NORTH_EAST.png");
    private final Image eastImage = new Image("EAST.png");
    private final Image southEastImage = new Image("SOUTH_EAST.png");
    private final Image southImage = new Image("SOUTH.png");
    private final Image southWestImage = new Image("SOUTH_WEST.png");
    private final Image westImage = new Image("WEST.png");
    private final Image northWestImage = new Image("NORTH_WEST.png");

    private final Image plant = new Image("plant.png");

    //easteregg nawiżujący do TF2 ;)
    private final Image defaultImage = new Image("ananas.png");

    //pusty konstruktor bo nie potrzebujemy przesyłać żadnych argumentów
    public WorldElementVisualizer() {
    }

    public ImageView getImageView(WorldElement element) {
        return switch (element.toString()) {
            case "^" -> new ImageView(northImage);
            case "◥" -> new ImageView(northEastImage);
            case ">" -> new ImageView(eastImage);
            case "◢" -> new ImageView(southEastImage);
            case "v" -> new ImageView(southImage);
            case "◣" -> new ImageView(southWestImage);
            case "<" -> new ImageView(westImage);
            case "◤" -> new ImageView(northWestImage);
            case "#" -> new ImageView(plant);
            default -> new ImageView(defaultImage);
        };
    }

}
