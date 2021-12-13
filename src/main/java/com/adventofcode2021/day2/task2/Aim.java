package com.adventofcode2021.day2.task2;

import com.adventofcode2021.day2.Direction;

public class Aim {
    private final int value;

    private Aim(int value) {
        this.value = value;
    }

    public int getAsInt() {
        return value;
    }

    public static Aim zero() {
        return new Aim(0);
    }

    public static Aim fromString(String values) {
        var splited = values.split(" ");
        var direction = Direction.fromString(splited[0]);
        var value = Integer.parseInt(splited[1]);

        return new Aim(direction == Direction.DOWN ? value : -value);
    }

    public Aim add(Aim other) {
        return new Aim(this.value + other.value);
    }
}
