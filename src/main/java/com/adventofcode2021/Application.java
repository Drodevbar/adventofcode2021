package com.adventofcode2021;

import com.adventofcode2021.day12.task2.Solution;
import com.adventofcode2021.util.FileReader;

public class Application {
    public static void main(String[] args) {
        var input = FileReader.read(args[0]);
        var result = Solution.run(input);
        System.out.println(result);
    }
}
