package com.adventofcode2021.day2.task2;

public class Depth {
    private final int value;

    private Depth(int value) {
        this.value = value;
    }

    public int getAsInt() {
        return value;
    }

    public static Depth zero() {
        return new Depth(0);
    }

    public static Depth fromStringAndMultiplier(String values, int multiplier) {
        var value = Integer.parseInt(values.split(" ")[1]);

        return new Depth(value * multiplier);
    }

    public Depth add(Depth other) {
        return new Depth(this.value + other.value);
    }
}
