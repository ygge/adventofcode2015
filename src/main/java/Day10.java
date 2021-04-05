import util.Util;

import java.util.*;

public class Day10 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        var str = input;
        for (int i = 0; i < 50; ++i) {
            str = process(str);
        }
        return str.length();
    }

    private static int part1(String input) {
        var str = input;
        for (int i = 0; i < 40; ++i) {
            str = process(str);
        }
        return str.length();
    }

    private static String process(String str) {
        var sb = new StringBuilder();
        char curr = 0;
        int count = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c != curr) {
                if (count > 0) {
                    sb.append(count);
                    sb.append(curr);
                }
                curr = c;
                count = 0;
            }
            ++count;
        }
        sb.append(count);
        sb.append(curr);
        return sb.toString();
    }
}
