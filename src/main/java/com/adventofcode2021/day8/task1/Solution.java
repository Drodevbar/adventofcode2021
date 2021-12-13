package com.adventofcode2021.day8.task1;

import java.util.Arrays;

public class Solution {
    private static final int[] UNIQUE_SEGMENTS_LENGTHS = {2, 3, 4, 7};

    public static int run(String[] input) {
        var outputValues = Arrays.stream(input)
                .map(v -> v.split("\\|")[1])
                .map(v -> v.trim().split(" "))
                .toArray(String[][]::new);

        return countDigitsWithUniqueNumberOfSequence(outputValues);
    }

    public static int countDigitsWithUniqueNumberOfSequence(String[][] values) {
        var count = 0;

        for (String[] line : values) {
            for (String digit : line) {
                if (Arrays.stream(UNIQUE_SEGMENTS_LENGTHS).anyMatch(l -> l == digit.length())) {
                    count++;
                }
            }
        }

        return count;
    }
}
