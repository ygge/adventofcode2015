import util.Util;

import java.util.*;

public class Day17 {

    public static void main(String[] args) {
        var input = Util.readInts();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<Integer> input) {
        Map<Integer, Integer> count = new HashMap<>();
        List<Data> data = new ArrayList<>();
        data.add(new Data(150, 0));
        for (Integer v : input) {
            List<Data> newData = new ArrayList<>();
            for (Data d : data) {
                if (d.left == v) {
                    if (count.containsKey(d.num+1)) {
                        count.put(d.num+1, count.get(d.num+1)+1);
                    } else {
                        count.put(d.num+1, 1);
                    }
                } else if (d.left > v) {
                    newData.add(new Data(d.left-v, d.num+1));
                }
            }
            data.addAll(newData);
        }
        var min = count.keySet()
                .stream().min(Comparator.comparingInt(a -> a))
                .orElseThrow();
        return count.get(min);
    }

    private static int part1(List<Integer> input) {
        int count = 0;
        List<Integer> data = new ArrayList<>();
        data.add(150);
        for (Integer v : input) {
            List<Integer> newData = new ArrayList<>();
            for (Integer d : data) {
                if (d.equals(v)) {
                    ++count;
                } else if (d > v) {
                    newData.add(d-v);
                }
            }
            data.addAll(newData);
        }
        return count;
    }

    private static class Data {
        final int left, num;

        private Data(int left, int num) {
            this.left = left;
            this.num = num;
        }
    }
}
