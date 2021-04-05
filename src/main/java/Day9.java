import util.Util;

import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        Map<String, Map<String, Integer>> dist = parseGraph(input);
        return calcMax(dist);
    }

    private static int calcMax(Map<String, Map<String, Integer>> dist) {
        int best = 0;
        for (Map.Entry<String, Map<String, Integer>> entry : dist.entrySet()) {
            Set<String> seen = new HashSet<>();
            seen.add(entry.getKey());
            int d = calcMax(dist, entry.getKey(), seen);
            if (d > best) {
                best = d;
            }
        }
        return best;
    }

    private static int calcMax(Map<String, Map<String, Integer>> dist, String curr, Set<String> seen) {
        if (seen.size() == dist.size()) {
            return 0;
        }
        int best = 0;
        Map<String, Integer> currDist = dist.get(curr);
        for (Map.Entry<String, Integer> entry : currDist.entrySet()) {
            if (!seen.contains(entry.getKey())) {
                seen.add(entry.getKey());
                int d = entry.getValue() + calcMax(dist, entry.getKey(), seen);
                if (d > best) {
                    best = d;
                }
                seen.remove(entry.getKey());
            }
        }
        return best;
    }

    private static int part1(List<String> input) {
        Map<String, Map<String, Integer>> dist = parseGraph(input);
        return calcMin(dist);
    }

    private static Map<String, Map<String, Integer>> parseGraph(List<String> input) {
        Map<String, Map<String, Integer>> dist = new HashMap<>();
        for (String row : input) {
            String[] split = row.split(" ");
            if (!dist.containsKey(split[0])) {
                dist.put(split[0], new HashMap<>());
            }
            if (!dist.containsKey(split[2])) {
                dist.put(split[2], new HashMap<>());
            }
            int d = Integer.parseInt(split[4]);
            dist.get(split[0]).put(split[2], d);
            dist.get(split[2]).put(split[0], d);
        }
        return dist;
    }

    private static int calcMin(Map<String, Map<String, Integer>> dist) {
        int best = Integer.MAX_VALUE;
        for (Map.Entry<String, Map<String, Integer>> entry : dist.entrySet()) {
            Set<String> seen = new HashSet<>();
            seen.add(entry.getKey());
            int d = calcMin(dist, entry.getKey(), seen);
            if (d < best) {
                best = d;
            }
        }
        return best;
    }

    private static int calcMin(Map<String, Map<String, Integer>> dist, String curr, Set<String> seen) {
        if (seen.size() == dist.size()) {
            return 0;
        }
        int best = Integer.MAX_VALUE;
        Map<String, Integer> currDist = dist.get(curr);
        for (Map.Entry<String, Integer> entry : currDist.entrySet()) {
            if (!seen.contains(entry.getKey())) {
                seen.add(entry.getKey());
                int d = entry.getValue() + calcMin(dist, entry.getKey(), seen);
                if (d < best) {
                    best = d;
                }
                seen.remove(entry.getKey());
            }
        }
        return best;
    }
}
