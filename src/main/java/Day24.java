import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 {

    public static void main(String[] args) {
        var input = Util.readInts();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static long part2(List<Integer> input) {
        int sum = input.stream()
                .reduce(0, Integer::sum)/4;
        for (int i = 2; ; ++i) {
            Set<Integer> used = new HashSet<>();
            long res = calc2(input, used, i, 0, sum);
            if (res != -1) {
                return res;
            }
        }
    }

    private static long calc2(List<Integer> input, Set<Integer> used, int num, int index, int goal) {
        if (num == used.size()) {
            if (used.stream().reduce(0, Integer::sum) == goal) {
                return used.stream().map(a -> (long)a).reduce(1L, (a, b) -> a*b);
            }
            return -1;
        }
        if (index == input.size()) {
            return -1;
        }
        var i = input.get(index);
        used.add(i);
        var a = calc2(input, used, num, index+1, goal);
        used.remove(i);
        var b = calc2(input, used, num, index+1, goal);
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return Math.min(a, b);
    }

    private static long part1(List<Integer> input) {
        int sum = input.stream()
                .reduce(0, Integer::sum)/3;
        for (int i = 2; ; ++i) {
            long res = calc(input, i, sum);
            if (res != -1) {
                return res;
            }
        }
    }

    private static long calc(List<Integer> input, int num, int goal) {
        Set<Integer> used = new HashSet<>();
        return calc(input, used, num, 0, goal);
    }

    private static long calc(List<Integer> input, Set<Integer> used, int num, int index, int goal) {
        if (num == used.size()) {
            if (used.stream().reduce(0, Integer::sum) == goal && canMake(input, used, goal)) {
                return used.stream().map(a -> (long)a).reduce(1L, (a, b) -> a*b);
            }
            return -1;
        }
        if (index == input.size()) {
            return -1;
        }
        var i = input.get(index);
        used.add(i);
        var a = calc(input, used, num, index+1, goal);
        used.remove(i);
        var b = calc(input, used, num, index+1, goal);
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return Math.min(a, b);
    }

    private static boolean canMake(List<Integer> input, Set<Integer> used, int goal) {
        var list = new ArrayList<>(input);
        list.removeAll(used);
        Set<Integer> newUsed = new HashSet<>();
        return canMake(list, newUsed, 0, goal, 0);
    }

    private static boolean canMake(List<Integer> list, Set<Integer> newUsed, int index, int goal, int sum) {
        if (index == list.size()) {
            return sum == goal;
        }
        var i = list.get(index);
        if (sum + i == goal) {
            return true;
        }
        if (sum + i < goal) {
            newUsed.add(i);
            if (canMake(list, newUsed, index+1, goal, sum+i)) {
                return true;
            }
            newUsed.remove(i);
        }
        return canMake(list, newUsed, index+1, goal, sum);
    }
}
