package com.adventofcode2021.day2.task1;

import com.adventofcode2021.day2.Direction;

public class VerticalPosition {
    private final int value;

    private VerticalPosition(int value) {
        this.value = value;
    }

    public int getAsInt() {
        return value;
    }

    public static VerticalPosition zero() {
        return new VerticalPosition(0);
    }

    public static VerticalPosition fromString(String values) {
        var splited = values.split(" ");
        var direction = Direction.fromString(splited[0]);
        var value = Integer.parseInt(splited[1]);

        return new VerticalPosition(direction == Direction.DOWN ? value : -value);
    }

    public VerticalPosition add(VerticalPosition other) {
        return new VerticalPosition(this.value + other.value);
    }
}
