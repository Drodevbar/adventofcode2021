package com.adventofcode2021.day1.task2;

import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var mappedInput = Stream.of(input).mapToInt(Integer::parseInt).toArray();

        return countIncreasesSlidingWindow(mappedInput);
    }

    public static int countIncreasesSlidingWindow(int[] measurements) {
        var slidingWindow = 3;
        var positionsSkipped = slidingWindow - 1;
        var summedMeasurements = new int[measurements.length - positionsSkipped];

        for (int i = positionsSkipped; i < measurements.length; i++) {
            summedMeasurements[i - positionsSkipped] = measurements[i] + measurements[i - 1] + measurements[i - 2];
        }

        return com.adventofcode2021.day1.task1.Solution.countIncreases(summedMeasurements);
    }
}
