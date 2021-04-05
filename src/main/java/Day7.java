import util.Util;

import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        Map<String, Wire> wires = setupWires(input);
        Map<String, Integer> values = new HashMap<>();
        var a = calc(values, wires, "a");
        wires.put("b", new Wire(Operation.EQ, Integer.toString(a)));
        Map<String, Integer> newValues = new HashMap<>();
        return calc(newValues, wires, "a");
    }

    private static int part1(List<String> input) {
        Map<String, Wire> wires = setupWires(input);
        Map<String, Integer> values = new HashMap<>();
        return calc(values, wires, "a");
    }

    private static Map<String, Wire> setupWires(List<String> input) {
        Map<String, Wire> wires = new HashMap<>();
        for (String row : input) {
            var s = row.split(" -> ");
            String c = s[1];
            var split = s[0].split(" ");
            if (split.length == 1) {
                wires.put(c, new Wire(Operation.EQ, split[0]));
            } else if (split[0].equals("NOT")) {
                wires.put(c, new Wire(Operation.NOT, split[1]));
            } else if (split[1].equals("LSHIFT")) {
                wires.put(c, new Wire(Operation.LSHIFT, split[0], Integer.parseInt(split[2])));
            } else if (split[1].equals("RSHIFT")) {
                wires.put(c, new Wire(Operation.RSHIFT, split[0], Integer.parseInt(split[2])));
            } else if (split[1].equals("AND")) {
                wires.put(c, new Wire(Operation.AND, split[0], split[2]));
            } else if (split[1].equals("OR")) {
                wires.put(c, new Wire(Operation.OR, split[0], split[2]));
            } else {
                throw new RuntimeException(String.format("Operation '%s' not understood", row));
            }
        }
        return wires;
    }

    private static int calc(Map<String, Integer> values, Map<String, Wire> wires, String str) {
        if (values.containsKey(str)) {
            return values.get(str);
        }
        int value;
        Wire wire = wires.get(str);
        if (wire == null) {
            value = Integer.parseInt(str);
        } else {
            value = switch (wire.op) {
                case EQ -> calc(values, wires, wire.dependants.get(0));
                case NOT -> ~calc(values, wires, wire.dependants.get(0));
                case AND -> calc(values, wires, wire.dependants.get(0)) & calc(values, wires, wire.dependants.get(1));
                case OR -> calc(values, wires, wire.dependants.get(0)) | calc(values, wires, wire.dependants.get(1));
                case LSHIFT -> calc(values, wires, wire.dependants.get(0)) << wire.shift;
                case RSHIFT -> calc(values, wires, wire.dependants.get(0)) >> wire.shift;
            };
        }
        values.put(str, value);
        return value;
    }

    private static class Wire {
        int shift;
        List<String> dependants;
        Operation op;

        public Wire(Operation op, String dep) {
            this.dependants = Collections.singletonList(dep);
            this.op = op;
        }

        public Wire(Operation op, String dep, int shift) {
            this.dependants = Collections.singletonList(dep);
            this.op = op;
            this.shift = shift;
        }

        public Wire(Operation op, String dep1, String dep2) {
            this.dependants = Arrays.asList(dep1, dep2);
            this.op = op;
        }
    }

    private enum Operation {
        EQ,
        NOT,
        AND,
        OR,
        LSHIFT,
        RSHIFT
    }
}
