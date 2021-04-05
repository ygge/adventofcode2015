import util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day11 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static String part2(String input) {
        var str = run(input);
        return run(str);
    }

    private static String part1(String input) {
        return run(input);
    }

    private static String run(String input) {
        var chars = input.toCharArray();
        do {
            next(chars);
        } while (!ok(chars));
        return new String(chars);
    }

    private static void next(char[] chars) {
        for (int i = 0; i < chars.length; ++i) {
            var c = chars[i];
            if (c == 'i' || c == 'o' || c == 'l') {
                chars[i] = (char)(c+1);
                for (int j = i+1; j < chars.length; ++j) {
                    chars[j] = 'a';
                }
                return;
            }
        }
        for (int i = chars.length-1; i >= 0; --i) {
            if (chars[i] == 'z') {
                chars[i] = 'a';
            } else {
                ++chars[i];
                return;
            }
        }
    }

    private static boolean ok(char[] chars) {
        for (char aChar : chars) {
            if (aChar == 'i' || aChar == 'o' || aChar == 'l') {
                return false;
            }
        }
        boolean inc = false;
        for (int i = 2; i < chars.length; ++i) {
            if (chars[i-2]+2 == chars[i] && chars[i-1]+1 == chars[i]) {
                inc = true;
                break;
            }
        }
        if (!inc) {
            return false;
        }
        Set<Character> pairs = new HashSet<>();
        for (int i = 1; i < chars.length; ++i) {
            if (chars[i-1] == chars[i]) {
                pairs.add(chars[i]);
            }
        }
        return pairs.size() > 1;
    }
}
