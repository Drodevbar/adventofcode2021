package com.adventofcode2021.day9.task1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Solution {
    public static int run(String[] input) {
        var mapped = Stream.of(input)
                .map(line -> line.split(""))
                .map(v -> Arrays.stream(v).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        return count(mapped);
    }

    private static int count(Integer[][] heightmap) {
        var lowPoints = new LinkedList<Integer>();

        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[0].length; j++) {
                var current = heightmap[i][j];
                var isLowestPoint = true;

                if (i > 0 && heightmap[i - 1][j] <= current) {
                    isLowestPoint = false;
                }
                if (j > 0 && heightmap[i][j - 1] <= current) {
                    isLowestPoint = false;
                }
                if (i < heightmap.length - 1 && heightmap[i + 1][j] <= current) {
                    isLowestPoint = false;
                }
                if (j < heightmap[i].length - 1 && heightmap[i][j + 1] <= current) {
                    isLowestPoint = false;
                }

                if (isLowestPoint) {
                    lowPoints.add(current);
                }
            }
        }

        return lowPoints.stream().reduce(0, (v1, v2) -> v1 + v2 + 1);
    }
}
