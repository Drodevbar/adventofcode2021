package com.adventofcode2021.day7.task1;

import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var firstLine = input[0];
        var splited = firstLine.split(",");
        var mapped = Stream.of(splited).mapToInt(Integer::parseInt).toArray();

        return calculateLeastFuel(mapped);
    }

    public static int calculateLeastFuel(int[] positions) {
        Arrays.sort(positions);

        var min = positions[0];
        var max = positions[positions.length - 1];

        var leastCost = Integer.MAX_VALUE;

        for (int i = min; i <= max; i++) {
            var currentCost = 0;

            for (int pos : positions) {
                currentCost += Math.abs(pos - i);
            }

            leastCost = Math.min(leastCost, currentCost);
        }

        return leastCost;
    }
}
