package com.adventofcode2021.day9.task2;

import java.util.*;
import java.util.stream.Stream;

public class Solution {
    private record Point(int x, int y) {}

    private record DepthFirstSearch(Integer[][] heightmap, Set<Point> visited) {
        int getGraphSize(Point point) {
            visited.add(point);
            var pointValue = heightmap[point.x][point.y];
            var size = 1;

            if (
                point.x > 0 &&
                !visited.contains(new Point(point.x - 1, point.y)) &&
                heightmap[point.x - 1][point.y] > pointValue &&
                heightmap[point.x - 1][point.y] != 9
            ) {
                size += getGraphSize(new Point(point.x - 1, point.y));
            }
            if (
                point.y > 0 &&
                !visited.contains(new Point(point.x, point.y - 1)) &&
                heightmap[point.x][point.y - 1] > pointValue &&
                heightmap[point.x][point.y - 1] != 9
            ) {
                size += getGraphSize(new Point(point.x, point.y - 1));
            }
            if (
                point.x < heightmap.length - 1 &&
                !visited.contains(new Point(point.x + 1, point.y)) &&
                heightmap[point.x + 1][point.y] > pointValue &&
                heightmap[point.x + 1][point.y] != 9
            ) {
                size += getGraphSize(new Point(point.x + 1, point.y));
            }
            if (
                point.y < heightmap[point.x].length - 1 &&
                !visited.contains(new Point(point.x, point.y + 1)) &&
                heightmap[point.x][point.y + 1] > pointValue &&
                heightmap[point.x][point.y + 1] != 9
            ) {
                size += getGraphSize(new Point(point.x, point.y + 1));
            }

            return size;
        }
    }

    public static int run(String[] input) {
        var mapped = Stream.of(input)
                .map(line -> line.split(""))
                .map(v -> Arrays.stream(v).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        var lowPoints = collectLowPoints(mapped);
        var basinsSizes = collectBasinsSizes(mapped, lowPoints);

        return basinsSizes.stream()
                .sorted(Comparator.comparing(Integer::valueOf, Comparator.reverseOrder()))
                .toList()
                .subList(0, 3).stream()
                    .reduce(1, (a, b) -> a * b);

    }

    private static Set<Point> collectLowPoints(Integer[][] heightmap) {
        var lowPoints = new HashSet<Point>();

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
                    lowPoints.add(new Point(i, j));
                }
            }
        }

        return lowPoints;
    }

    private static List<Integer> collectBasinsSizes(Integer[][] heightmap, Set<Point> lowPoints) {
        var basinsSizes = new LinkedList<Integer>();
        var visited = new HashSet<Point>();

        for (Point lowPoint : lowPoints) {
            basinsSizes.add(new DepthFirstSearch(heightmap, visited).getGraphSize(lowPoint));
        }

        return basinsSizes;
    }
}
