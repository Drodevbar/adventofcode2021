package com.adventofcode2021.day1.task1;

import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var mappedInput = Stream.of(input).mapToInt(Integer::parseInt).toArray();

        return countIncreases(mappedInput);
    }

    public static int countIncreases(int[] measurements) {
        var counter = 0;
        var previous = Integer.MAX_VALUE;

        for (int current : measurements) {
            if (current > previous) {
                counter++;
            }
            previous = current;
        }

        return counter;
    }
}
