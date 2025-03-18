package agh.ics.oop.model;

import agh.ics.oop.model.util.MapChangeListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileMapDisplay implements MapChangeListener {

    @Override
    public void mapChanged(ProjectWorldMap worldMap, String message) {
        File file = new File("map_%s.log".formatted(worldMap.getID()));
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write("%s %s\n".formatted(new Date().toString(),message));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
