import util.Util;

import java.util.Collections;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        var input = Util.readIntLists("x");
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<List<Integer>> input) {
        int sum = 0;
        for (List<Integer> box : input) {
            Collections.sort(box);
            sum += 2*(box.get(0)+box.get(1)) + box.get(0)*box.get(1)*box.get(2);
        }
        return sum;
    }

    private static int part1(List<List<Integer>> input) {
        int sum = 0;
        for (List<Integer> box : input) {
            Collections.sort(box);
            sum += 2*box.get(0)*box.get(1) + 2*box.get(1)*box.get(2) + 2*box.get(0)*box.get(2) + box.get(0)*box.get(1);
        }
        return sum;
    }
}
