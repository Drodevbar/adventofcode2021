package com.adventofcode2021.day6.task1;

import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
    private static final int NUMBER_OF_DAYS = 80;

    public static long run(String[] input) {
        var firstLine = input[0];
        var splited = firstLine.split(",");
        var mapped = Stream.of(splited).mapToInt(Integer::parseInt).toArray();

        return simulateAndCountLanternfish(mapped, NUMBER_OF_DAYS);
    }

    public static long simulateAndCountLanternfish(int[] input, int numberOfDays) {
        var timers = new long[9];

        for (int in : input) {
            timers[in]++;
        }

        for (int i = 0; i < numberOfDays; i++) {
            var memo = new long[9];

            System.arraycopy(timers, 1, memo, 0, timers.length - 1);

            memo[8] = timers[0];
            memo[6] += timers[0];

            timers = memo;
        }

        return Arrays.stream(timers).reduce(0, Long::sum);
    }
}
