package com.adventofcode2021.day3.task2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var mappedInput = Stream.of(input).mapToInt(value -> Integer.parseInt(value, 2)).toArray();
        var bitMaskLength = input[0].length();

        return countPowerConsumption(mappedInput, bitMaskLength);
    }

    public static int countPowerConsumption(int[] measurements, int bitMaskLength) {
        var measurementsSet = Arrays.stream(measurements).boxed().collect(Collectors.toSet());

        var oxygenGeneratorRating = get(bitMaskLength, measurementsSet, true);
        var co2ScrubberRating = get(bitMaskLength, measurementsSet, false);

        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private static int get(int bitMaskLength, Set<Integer> measurementsSet, boolean mostCommon) {
        for (int i = bitMaskLength - 1; i >= 0; i--) {
            var bitMask = 1 << i;
            var oneBitsSet = new HashSet<Integer>();
            var zeroBitsSet = new HashSet<Integer>();

            for (int m : measurementsSet) {
                if ((m & bitMask) > 0) {
                    oneBitsSet.add(m);
                } else {
                    zeroBitsSet.add(m);
                }
            }

            if (mostCommon) {
                measurementsSet = oneBitsSet.size() >= zeroBitsSet.size()
                        ? oneBitsSet
                        : zeroBitsSet;
            } else {
                measurementsSet = oneBitsSet.size() >= zeroBitsSet.size()
                        ? zeroBitsSet
                        : oneBitsSet;
            }

            if (measurementsSet.size() == 1) {
                return measurementsSet.stream().findFirst().get();
            }
        }

        throw new RuntimeException();
    }
}
