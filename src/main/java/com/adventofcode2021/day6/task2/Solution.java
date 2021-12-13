package com.adventofcode2021.day6.task2;

import java.util.stream.Stream;

import static com.adventofcode2021.day6.task1.Solution.simulateAndCountLanternfish;

public class Solution {
    private static final int NUMBER_OF_DAYS = 256;

    public static long run(String[] input) {
        var firstLine = input[0];
        var splited = firstLine.split(",");
        var mapped = Stream.of(splited).mapToInt(Integer::parseInt).toArray();

        return simulateAndCountLanternfish(mapped, NUMBER_OF_DAYS);
    }
}
