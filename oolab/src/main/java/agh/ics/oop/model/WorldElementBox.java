package agh.ics.oop.model;

import agh.ics.oop.World;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class WorldElementBox {

    private Image image;
    private ImageView imageView;
    private Label label;
    private VBox vBox;

    public WorldElementBox(WorldElement worldElement) {;
        image = new Image(worldElement.getStateOfImage());
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        label = new Label(worldElement.getPosition().toString());

        vBox = new VBox();
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getvBox() {
        return vBox;
    }
}
