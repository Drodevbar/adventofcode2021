package com.adventofcode2021.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileReader {
    public static String[] read(String path) {
        var list = new LinkedList<String>();

        try (var scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return list.toArray(String[]::new);
    }
}
