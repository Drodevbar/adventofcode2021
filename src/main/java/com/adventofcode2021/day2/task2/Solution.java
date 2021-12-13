package com.adventofcode2021.day2.task2;

import com.adventofcode2021.day2.HorizontalPosition;
import com.adventofcode2021.day2.task1.VerticalPosition;

public class Solution {
    public static int run(String[] input) {
        Aim aim = Aim.zero();
        HorizontalPosition horizontal = HorizontalPosition.zero();
        Depth depth = Depth.zero();

        for (String line : input) {
            if (line.contains("forward")) {
                horizontal = horizontal.add(HorizontalPosition.fromString(line));
                depth = depth.add(Depth.fromStringAndMultiplier(line, aim.getAsInt()));
            }
            if (line.contains("up") || line.contains("down")) {
                aim = aim.add(Aim.fromString(line));
            }
        }

        return horizontal.getAsInt() * depth.getAsInt();
    }
}
