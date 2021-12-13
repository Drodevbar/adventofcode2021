package com.adventofcode2021.day2.task1;

import com.adventofcode2021.day2.HorizontalPosition;

public class Solution {
    public static int run(String[] input) {
        HorizontalPosition horizontal = HorizontalPosition.zero();
        VerticalPosition vertical = VerticalPosition.zero();

        for (String line : input) {
            if (line.contains("forward")) {
                horizontal = horizontal.add(HorizontalPosition.fromString(line));
            }
            if (line.contains("up") || line.contains("down")) {
                vertical = vertical.add(VerticalPosition.fromString(line));
            }
        }

        return horizontal.getAsInt() * vertical.getAsInt();
    }
}
