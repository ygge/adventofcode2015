import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        int count = 0;
        for (String row : input) {
            if (isNice2(row)) {
                ++count;
            }
        }
        return count;
    }

    private static boolean isNice2(String input) {
        boolean repeat = false;
        boolean cons = false;
        Set<String> seen = new HashSet<>();
        String lastSubstring = null;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (i > 0) {
                var ss = input.substring(i-1, i+1);
                if (seen.contains(ss)) {
                    repeat = true;
                }
                seen.add(lastSubstring);
                lastSubstring = ss;
            }
            if (i > 1) {
                char ppc = input.charAt(i-2);
                if (ppc == c) {
                    cons = true;
                }
            }
        }
        return cons && repeat;
    }

    private static int part1(List<String> input) {
        int count = 0;
        for (String row : input) {
            if (isNice(row)) {
                ++count;
            }
        }
        return count;
    }

    private static boolean isNice(String input) {
        int vowels = 0;
        boolean cons = false;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ++vowels;
            }

            if (i > 0) {
                char pc = input.charAt(i-1);
                if (pc == c) {
                    cons = true;
                } else if (c == 'b' && pc == 'a') {
                    return false;
                } else if (c == 'd' && pc == 'c') {
                    return false;
                } else if (c == 'q' && pc == 'p') {
                    return false;
                } else if (c == 'y' && pc == 'x') {
                    return false;
                }
            }
        }
        return cons && vowels >= 3;
    }
}
