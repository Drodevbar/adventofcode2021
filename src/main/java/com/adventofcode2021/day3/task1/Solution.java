package com.adventofcode2021.day3.task1;

import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var mappedInput = Stream.of(input).mapToInt(value -> Integer.parseInt(value, 2)).toArray();
        var bitMaskLength = input[0].length();

        return countPowerConsumption(mappedInput, bitMaskLength);
    }

    public static int countPowerConsumption(int[] measurements, int bitMaskLength) {
        var gamma = 0;

        for (int i = 0; i < bitMaskLength; i++) {
            var numberOfOneBits = 0;
            var bitMask = 1 << i;

            for (int m : measurements) {
                if ((m & bitMask) > 0) {
                    numberOfOneBits++;
                }
            }

            if (numberOfOneBits > measurements.length / 2) {
                gamma |= bitMask;
            }
        }

        return gamma * (~gamma & 0xFFFFFFFF >>> (32 - bitMaskLength));
    }
}
