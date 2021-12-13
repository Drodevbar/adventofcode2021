package com.adventofcode2021.day2;

public class HorizontalPosition {
    private final int value;

    private HorizontalPosition(int value) {
        this.value = value;
    }

    public int getAsInt() {
        return value;
    }

    public static HorizontalPosition zero() {
        return new HorizontalPosition(0);
    }

    public static HorizontalPosition fromString(String value) {
        return new HorizontalPosition(Integer.parseInt(value.split(" ")[1]));
    }

    public HorizontalPosition add(HorizontalPosition other) {
        return new HorizontalPosition(this.value + other.value);
    }
}
