import util.Util;

import java.util.*;

public class Day13 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        var name = "Henrik";
        Map<String, Map<String, Integer>> happiness = parseInput(input);
        for (Map<String, Integer> h : happiness.values()) {
            h.put(name, 0);
        }
        var any = happiness.keySet().stream().findAny().orElseThrow();
        var people = new ArrayList<>(happiness.keySet());
        var zeros = new HashMap<String, Integer>();
        for (String person : people) {
            zeros.put(person, 0);
        }
        happiness.put(name, zeros);
        Set<String> seated = new HashSet<>();
        seated.add(any);
        return best(happiness, seated, any, any);
    }

    private static int part1(List<String> input) {
        Map<String, Map<String, Integer>> happiness = parseInput(input);
        var any = happiness.keySet().stream().findAny().orElseThrow();
        Set<String> seated = new HashSet<>();
        seated.add(any);
        return best(happiness, seated, any, any);
    }

    private static Map<String, Map<String, Integer>> parseInput(List<String> input) {
        Map<String, Map<String, Integer>> happiness = new HashMap<>();
        for (String row : input) {
            String[] split = row.split(" ");
            var a = split[0];
            var b = split[10].substring(0, split[10].length()-1);
            var c = Integer.parseInt(split[3]);
            if (split[2].equals("lose")) {
                c = -c;
            }
            happiness.putIfAbsent(a, new HashMap<>());
            happiness.get(a).put(b, c);
        }
        return happiness;
    }

    private static int best(Map<String, Map<String, Integer>> happiness, Set<String> seated, String first, String last) {
        if (seated.size() == happiness.size()) {
            return happiness.get(last).get(first) + happiness.get(first).get(last);
        }
        int best = Integer.MIN_VALUE;
        for (Map.Entry<String, Map<String, Integer>> entry : happiness.entrySet()) {
            String person = entry.getKey();
            if (!seated.contains(person)) {
                seated.add(person);
                var ss = happiness.get(person).get(last) + happiness.get(last).get(person);
                var s = ss + best(happiness, seated, first, person);
                if (s > best) {
                    best = s;
                }
                seated.remove(person);
            }
        }
        return best;
    }
}
