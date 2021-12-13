package com.adventofcode2021.day2;

public enum Direction {
    UP("up"),
    DOWN("down");

    public final String label;

    Direction(String label) {
        this.label = label;
    }

    public static Direction fromString(String text) {
        for (Direction d : Direction.values()) {
            if (d.label.equals(text)) {
                return d;
            }
        }
        throw new IllegalArgumentException();
    }
}
