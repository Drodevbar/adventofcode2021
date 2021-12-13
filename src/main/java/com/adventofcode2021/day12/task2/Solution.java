package com.adventofcode2021.day12.task2;

import java.util.*;
import java.util.stream.Stream;

public class Solution {
    private record Vertex(String name) {
        boolean isEnd() {
            return name.equals("end");
        }

        boolean isStart() {
            return name.equals("start");
        }

        boolean canBeVisitedMoreThanOnce() {
            return name.equals(name.toUpperCase());
        }

        boolean canBeVisitedExactlyOnce() {
            return !canBeVisitedMoreThanOnce() && !isEnd() && !isStart();
        }
    }

    public static int run(String[] input) {
        var rows = Stream.of(input)
                .map(line -> line.split("-"))
                .toArray(String[][]::new);
        var neighborsMap = buildNeighborsMap(rows);
        var visited = new LinkedList<Vertex>();
        visited.add(new Vertex("start"));

        return countDistinctPaths(neighborsMap, visited, false);
    }

    private static Map<Vertex, List<Vertex>> buildNeighborsMap(String[][] rows) {
        var map = new HashMap<Vertex, List<Vertex>>();

        for (String[] row : rows) {
            var from = new Vertex(row[0]);
            var to = new Vertex(row[1]);

            if (!map.containsKey(from)) {
                map.put(from, new LinkedList<>());
            }

            map.get(from).add(to);

            if (!map.containsKey(to)) {
                map.put(to, new LinkedList<>());
            }

            map.get(to).add(from);
        }

        return map;
    }

    private static int countDistinctPaths(Map<Vertex, List<Vertex>> neighborsMap, LinkedList<Vertex> visited, boolean isExtraHopTaken) {
        var neighbors = neighborsMap.get(visited.getLast());
        var count = 0;

        for (Vertex vertex : neighbors) {
            if (vertex.isEnd()) {
                count++;
            }
            if (!vertex.isEnd() && !visited.contains(vertex) || vertex.canBeVisitedMoreThanOnce() || (!isExtraHopTaken && vertex.canBeVisitedExactlyOnce())) {
                if (visited.contains(vertex) && vertex.canBeVisitedExactlyOnce()) {
                    isExtraHopTaken = true;
                }
                visited.add(vertex);
                count += countDistinctPaths(neighborsMap, visited, isExtraHopTaken);
                if (vertex.canBeVisitedExactlyOnce() && isExtraHopTaken && visited.contains(vertex)) {
                    isExtraHopTaken = false;
                }
            }
        }

        visited.removeLast();

        return count;
    }
}
