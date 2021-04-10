import util.Util;

import java.util.*;

public class Day19 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        //Util.submitPart1(part1(input));
        part2(input);
    }

    private static void part2(List<String> input) {
        List<Replacement> repl = new ArrayList<>();
        int index = 0;
        for (; index < input.size()-2; ++index) {
            var split = input.get(index).split("\s+");
            repl.add(new Replacement(split[0], split[2]));
        }
        repl.sort((r1, r2) -> r2.to.length() - r1.to.length());
        var goal = input.get(input.size()-1);
        solve(goal, "e", repl, 0, Integer.MAX_VALUE);
    }

    private static int solve(String current, String goal, List<Replacement> repl, int score, int best) {
        if (current.equals(goal)) {
            if (score < best) {
                Util.submitPart2(score);
                return score;
            }
        }
        if (score >= best) {
            return -1;
        }
        for (Replacement replacement : repl) {
            int index = -1;
            while (index < current.length()) {
                index = current.indexOf(replacement.to, index);
                if (index == -1) {
                    break;
                }
                var m = current.substring(0, index) + replacement.from + current.substring(index+replacement.to.length());
                var s = solve(m, goal, repl, score + 1, best);
                best = Math.min(best, s);
                ++index;
            }
        }
        return -1;
    }

    private static int part1(List<String> input) {
        Map<String, List<String>> repl = setupReplacements(input);
        var data = input.get(input.size()-1);
        Set<String> seen = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : repl.entrySet()) {
            int index = -1;
            while (index < data.length()) {
                index = data.indexOf(entry.getKey(), index);
                if (index == -1) {
                    break;
                }
                for (String r : entry.getValue()) {
                    seen.add(data.substring(0, index) + r + data.substring(index+entry.getKey().length()));
                }
                ++index;
            }
        }
        return seen.size();
    }

    private static Map<String, List<String>> setupReplacements(List<String> input) {
        Map<String, List<String>> repl = new HashMap<>();
        int index = 0;
        for (; index < input.size()-2; ++index) {
            var split = input.get(index).split("\s+");
            repl.putIfAbsent(split[0], new ArrayList<>());
            repl.get(split[0]).add(split[2]);
        }
        return repl;
    }

    private static class Node {
        final String current;
        final int turns;

        public Node(String current, int turns) {
            this.current = current;
            this.turns = turns;
        }
    }

    private static class Replacement {
        final String from, to;

        private Replacement(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }
}
