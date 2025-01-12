package agh.ics.oop;

import agh.ics.oop.model.MoveDirections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirections> translateDirections(String[] args) {
        return Stream.of(args)
                    .map(arg->switch (arg) {
                        case "f" -> MoveDirections.FORWARD;
                        case "b" -> MoveDirections.BACKWARD;
                        case "r" -> MoveDirections.RIGHT;
                        case "l" -> MoveDirections.LEFT;
                        default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                    })
                .toList();
    }
}
