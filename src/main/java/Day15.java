import util.Util;

import java.util.List;
import java.util.stream.Collectors;

public class Day15 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static long part2(List<String> input) {
        List<int[]> ing = parseIng(input);
        int[] parts = new int[ing.size()];
        return best2(ing, parts, 0, 100);
    }

    private static long part1(List<String> input) {
        List<int[]> ing = parseIng(input);
        int[] parts = new int[ing.size()];
        return best(ing, parts, 0, 100);
    }

    private static List<int[]> parseIng(List<String> input) {
        return input.stream()
                .map(row -> {
                    String[] split = row.split(" ");
                    int[] data = new int[5];
                    data[0] = Integer.parseInt(split[2].substring(0, split[2].length()-1));
                    data[1] = Integer.parseInt(split[4].substring(0, split[4].length()-1));
                    data[2] = Integer.parseInt(split[6].substring(0, split[6].length()-1));
                    data[3] = Integer.parseInt(split[8].substring(0, split[8].length()-1));
                    data[4] = Integer.parseInt(split[10]);
                    return data;
                })
                .collect(Collectors.toList());
    }

    private static long best2(List<int[]> ing, int[] parts, int index, int left) {
        if (index == parts.length) {
            int num = ing.get(0).length;
            int sum = 0;
            for (int i = 0; i < parts.length; ++i) {
                sum += parts[i] * ing.get(i)[num-1];
            }
            if (sum != 500) {
                return 0;
            }
            long score = 1;
            for (int j = 0; j < num-1; ++j) {
                sum = 0;
                for (int i = 0; i < parts.length; ++i) {
                    sum += parts[i] * ing.get(i)[j];
                }
                if (sum < 0) {
                    sum = 0;
                }
                score *= sum;
            }
            return score;
        }
        long best = 0;
        if (index == parts.length-1) {
            parts[index] = left;
            best = best2(ing, parts, index+1, 0);
        } else {
            for (int i = left; i >= 0; --i) {
                parts[index] = i;
                best = Math.max(best, best2(ing, parts, index+1, left-i));
            }
        }
        return best;
    }

    private static long best(List<int[]> ing, int[] parts, int index, int left) {
        if (index == parts.length) {
            long score = 1;
            for (int j = 0; j < ing.get(0).length-1; ++j) {
                int sum = 0;
                for (int i = 0; i < parts.length; ++i) {
                    sum += parts[i] * ing.get(i)[j];
                }
                if (sum < 0) {
                    sum = 0;
                }
                score *= sum;
            }
            return score;
        }
        long best = 0;
        if (index == parts.length-1) {
            parts[index] = left;
            best = best(ing, parts, index+1, 0);
        } else {
            for (int i = left; i >= 0; --i) {
                parts[index] = i;
                best = Math.max(best, best(ing, parts, index+1, left-i));
            }
        }
        return best;
    }
}
