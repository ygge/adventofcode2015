import util.Util;

import java.util.List;

public class Day23 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part1(List<String> input) {
        int[] reg = new int[2];
        run(input, reg);
        return reg[1];
    }

    private static int part2(List<String> input) {
        int[] reg = new int[2];
        reg[0] = 1;
        run(input, reg);
        return reg[1];
    }

    private static void run(List<String> input, int[] reg) {
        int p = 0;
        while (p >= 0 && p < input.size()) {
            String[] split = input.get(p).split(" ");
            switch (split[0]) {
                case "hlf":
                    reg[split[1].charAt(0)-'a'] /= 2;
                    ++p;
                    break;
                case "tpl":
                    reg[split[1].charAt(0)-'a'] *= 3;
                    ++p;
                    break;
                case "inc":
                    reg[split[1].charAt(0)-'a'] += 1;
                    ++p;
                    break;
                case "jmp":
                    p += Integer.parseInt(split[1]);
                    break;
                case "jie":
                    if (reg[split[1].charAt(0)-'a']%2 == 0) {
                        p += Integer.parseInt(split[2]);
                    } else {
                        ++p;
                    }
                    break;
                case "jio":
                    if (reg[split[1].charAt(0)-'a'] == 1) {
                        p += Integer.parseInt(split[2]);
                    } else {
                        ++p;
                    }
                    break;
                default:
                    throw new RuntimeException(String.format("Command %s not understood at index %d", split[0], p));
            }
        }
    }
}
