import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        int sum = 0;
        int num = 0;
        boolean negative = false;
        List<Integer> skipped = calcSkip(input);
        for (int i = 0; i < input.length(); ++i) {
            if (skipped.contains(i)) {
                i = skipped.get(skipped.indexOf(i)+1);
                continue;
            }
            char c = input.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (c >= '0' && c <= '9') {
                num = 10*num + c-'0';
            } else if (num != 0 || negative) {
                sum += num * (negative ? -1 : 1);
                num = 0;
                negative = false;
            }
        }
        if (num != 0 || negative) {
            sum += num * (negative ? -1 : 1);
        }
        return sum;
    }

    private static List<Integer> calcSkip(String input) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (true) {
            var p = input.indexOf(":\"red\"", i);
            if (p == -1) {
                break;
            }
            int a, b;
            int num = 1;
            for (a = p; a >= 0; --a) {
                char c = input.charAt(a);
                if (c == '{') {
                    if (--num == 0) {
                        break;
                    }
                } else if (c == '}') {
                    ++num;
                }
            }
            num = 1;
            for (b = p; b < input.length(); ++b) {
                char c = input.charAt(b);
                if (c == '{') {
                    ++num;
                } else if (c == '}') {
                    if (--num == 0) {
                        break;
                    }
                }
            }
            i = b;
            list.add(a);
            list.add(b);
        }
        return list;
    }

    private static int part1(String input) {
        int sum = 0;
        int num = 0;
        boolean negative = false;
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (c >= '0' && c <= '9') {
                num = 10*num + c-'0';
            } else if (num != 0 || negative) {
                sum += num * (negative ? -1 : 1);
                num = 0;
                negative = false;
            }
        }
        if (num != 0 || negative) {
            sum += num * (negative ? -1 : 1);
        }
        return sum;
    }
}
