import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        List<Map<String, Integer>> sues = parseInput(input);
        Map<String, Integer> goal = setupGoal();
        for (int i = 0; i < sues.size(); ++i) {
            var map = sues.get(i);
            boolean ok = true;
            for (Map.Entry<String, Integer> entry : goal.entrySet()) {
                String key = entry.getKey();
                if (map.containsKey(key)) {
                    Integer value = map.get(key);
                    if (key.equals("cats") || key.equals("trees")) {
                        if (value <= entry.getValue()) {
                            ok = false;
                            break;
                        }
                    } else if (key.equals("pomeranians") || key.equals("goldfish")) {
                        if (value >= entry.getValue()) {
                            ok = false;
                            break;
                        }
                    } else if (!value.equals(entry.getValue())) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                return i+1;
            }
        }
        throw new RuntimeException("No Sue found");

    }

    private static int part1(List<String> input) {
        List<Map<String, Integer>> sues = parseInput(input);
        Map<String, Integer> goal = setupGoal();
        for (int i = 0; i < sues.size(); ++i) {
            var map = sues.get(i);
            boolean ok = true;
            for (Map.Entry<String, Integer> entry : goal.entrySet()) {
                if (map.containsKey(entry.getKey()) && !map.get(entry.getKey()).equals(entry.getValue())) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return i+1;
            }
        }
        throw new RuntimeException("No Sue found");
    }

    private static Map<String, Integer> setupGoal() {
        Map<String, Integer> goal = new HashMap<>();
        goal.put("children", 3);
        goal.put("cats", 7);
        goal.put("samoyeds", 2);
        goal.put("pomeranians", 3);
        goal.put("akitas", 0);
        goal.put("vizslas", 0);
        goal.put("goldfish", 5);
        goal.put("trees", 3);
        goal.put("cars", 2);
        goal.put("perfumes", 1);
        return goal;
    }

    private static List<Map<String, Integer>> parseInput(List<String> input) {
        List<Map<String, Integer>> sues = new ArrayList<>();
        for (String row : input) {
            String[] split = row.split(" ");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 2; i < split.length; i += 2) {
                var k = split[i].substring(0, split[i].length()-1);
                var n = Integer.parseInt(split[i+1].substring(0, i == split.length-2 ? split[i+1].length() : split[i+1].length()-1));
                map.put(k, n);
            }
            sues.add(map);
        }
        return sues;
    }
}
