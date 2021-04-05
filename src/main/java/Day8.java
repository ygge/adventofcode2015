import util.Util;

import java.util.*;

public class Day8 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        int count = 0;
        for (String row : input) {
            count += count2(row)-row.length();
        }
        return count;
    }

    private static int count2(String row) {
        int count = 2;
        for (int i = 0; i < row.length(); ++i) {
            ++count;
            char c = row.charAt(i);
            if (c == '"' || c == '\\') {
                ++count;
            }
        }
        return count;
    }

    private static int part1(List<String> input) {
        int count = 0;
        for (String row : input) {
            count += row.length()-count(row);
        }
        return count;
    }

    private static int count(String row) {
        int count = 0;
        for (int i = 1; i < row.length()-1; ++i) {
            ++count;
            char c = row.charAt(i);
            if (c == '\\') {
                char cc = row.charAt(i+1);
                if (cc == 'x') {
                    i += 3;
                } else if (cc == '\\' || cc == '"') {
                    ++i;
                }
            }
        }
        return count;
    }
}
