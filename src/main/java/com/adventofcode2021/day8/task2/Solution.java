package com.adventofcode2021.day8.task2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// 0, 6, 9 -> 6 segments
// 2, 3, 5 -> 5 segments
// 1 -> 2 segments
// 7 -> 3 segments
// 4 -> 4 segments
// 8 -> 7 segments

public class Solution {
    private static final int LENGTH_1 = 2;
    private static final int LENGTH_7 = 3;
    private static final int LENGTH_4 = 4;
    private static final int LENGTH_8 = 7;
    private static final int LENGTH_0_OR_6_OR_9 = 6;
    private static final int LENGTH_2_OR_3_OR_5 = 5;

    public static int run(String[] input) {
        var outputValues = Arrays.stream(input)
                .map(v -> v.split("\\|"))
                .toArray(String[][]::new);

        return decode(outputValues);
    }

    public static int decode(String[][] values) {
        var numbers = new LinkedList<Integer>();

        for (String[] line : values) {
            var lettersIn1 = new HashSet<Character>();
            var lettersIn7 = new HashSet<Character>();
            var lettersIn4 = new HashSet<Character>();
            var lettersIn8 = new HashSet<Character>();

            var lettersIn0 = new HashSet<Character>();
            var lettersIn6 = new HashSet<Character>();
            var lettersIn9 = new HashSet<Character>();
            var lettersIn2 = new HashSet<Character>();
            var lettersIn3 = new HashSet<Character>();
            var lettersIn5 = new HashSet<Character>();

            var patterns = line[0].trim().split(" ");
            var toDecode = line[1].trim().split(" ");

            for (String p : patterns) {
                if (p.length() == LENGTH_1) {
                    addCharsToSet(p, lettersIn1);
                } else if (p.length() == LENGTH_7) {
                    addCharsToSet(p, lettersIn7);
                } else if (p.length() == LENGTH_4) {
                    addCharsToSet(p, lettersIn4);
                } else if (p.length() == LENGTH_8) {
                    addCharsToSet(p, lettersIn8);
                }
            }

            for (String p : patterns) {
                if (p.length() == LENGTH_0_OR_6_OR_9) {
                    if (!containsAllChars(p, lettersIn1)) {
                        addCharsToSet(p, lettersIn6);
                    } else if (containsAllChars(p, lettersIn1) && containsAllChars(p, lettersIn4)) {
                        addCharsToSet(p, lettersIn9);
                    } // zero left
                } else if (p.length() == LENGTH_2_OR_3_OR_5) {
                    if (containsAllChars(p, lettersIn1)) {
                        addCharsToSet(p, lettersIn3);
                    } else if (containsAllChars(p, subtract(lettersIn1, lettersIn4))) {
                        addCharsToSet(p, lettersIn5);
                    } // 2 left
                }
            }

            for (String p : patterns) {
                if (p.length() == LENGTH_0_OR_6_OR_9 && !containsAllChars(p, lettersIn6) && !containsAllChars(p, lettersIn9)) {
                    addCharsToSet(p, lettersIn0);
                } else if (p.length() == LENGTH_2_OR_3_OR_5 && !containsAllChars(p, lettersIn3) && !containsAllChars(p, lettersIn5)) {
                    addCharsToSet(p, lettersIn2);
                }
            }

            StringBuilder number = new StringBuilder();

            for (String d : toDecode) {
                if (d.length() == LENGTH_1) {
                    number.append("1");
                } else if (d.length() == LENGTH_7) {
                    number.append("7");
                } else if (d.length() == LENGTH_4) {
                    number.append("4");
                } else if (d.length() == LENGTH_8) {
                    number.append("8");
                } else if (containsAllChars(d, lettersIn0)) {
                    number.append("0");
                } else if (containsAllChars(d, lettersIn6)) {
                    number.append("6");
                } else if (containsAllChars(d, lettersIn9)) {
                    number.append("9");
                } else if (containsAllChars(d, lettersIn2)) {
                    number.append("2");
                } else if (containsAllChars(d, lettersIn3)) {
                    number.append("3");
                } else {
                    number.append("5");
                }
            }

            numbers.add(Integer.parseInt(number.toString()));
        }

        return numbers.stream().reduce(0, Integer::sum);
    }

    private static Set<Character> subtract(Set<Character> a, Set<Character> b) {
        var result = new HashSet<Character>();
        var biggerSet = a.size() > b.size() ? a : b;
        var smallerSet = a.size() > b.size() ? b : a;

        for (Character c : biggerSet) {
            if (!smallerSet.contains(c)) {
                result.add(c);
            }
        }

        return result;
    }

    private static boolean containsAllChars(String s, Set<Character> chars) {
        for (char c : chars) {
            if (s.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    private static void addCharsToSet(String s, Set<Character> set) {
        for (char c : s.toCharArray()) {
            set.add(c);
        }
    }
}
