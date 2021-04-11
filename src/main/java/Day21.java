import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day21 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        var bhp = getBossValue(input.get(0));
        var bd = getBossValue(input.get(1));
        var ba = getBossValue(input.get(2));

        List<Equipment> weapons = Arrays.asList(
                new Equipment(8, 4, 0),
                new Equipment(10, 5, 0),
                new Equipment(25, 6, 0),
                new Equipment(40, 7, 0),
                new Equipment(74, 8, 0)
        );
        List<Equipment> armor = Arrays.asList(
                new Equipment(13, 0, 1),
                new Equipment(31, 0, 2),
                new Equipment(53, 0, 3),
                new Equipment(75, 0, 4),
                new Equipment(102, 0, 5)
        );
        List<Equipment> rings = Arrays.asList(
                new Equipment(25, 1, 0),
                new Equipment(50, 2, 0),
                new Equipment(100, 3, 0),
                new Equipment(20, 0, 1),
                new Equipment(40, 0, 2),
                new Equipment(80, 0, 3)
        );
        int best = -1;
        for (Equipment weapon : weapons) {
            for (int i = 0; i <= armor.size(); ++i) {
                List<Equipment> list = new ArrayList<>();
                list.add(weapon);
                if (i < armor.size()) {
                    list.add(armor.get(i));
                }
                int numRings = 0;
                int c = addRings2(rings, 0, numRings, list, bhp, bd, ba);
                best = Math.max(best, c);
            }
        }
        return best;
    }

    private static int addRings2(List<Equipment> rings, int index, int numRings, List<Equipment> list, int bhp, int bd, int ba) {
        if (numRings == 2 || index == rings.size()) {
            if (!willWin(list, bhp, bd, ba)) {
                return list.stream().map(e -> e.cost).reduce(0, Integer::sum);
            }
            return -1;
        } else {
            int a = addRings2(rings, index+1, numRings, list, bhp, bd, ba);
            list.add(rings.get(index));
            int b = addRings2(rings, index+1, numRings+1, list, bhp, bd, ba);
            list.remove(rings.get(index));
            return Math.max(a, b);
        }
    }

    private static int part1(List<String> input) {
        var bhp = getBossValue(input.get(0));
        var bd = getBossValue(input.get(1));
        var ba = getBossValue(input.get(2));

        List<Equipment> weapons = Arrays.asList(
                new Equipment(8, 4, 0),
                new Equipment(10, 5, 0),
                new Equipment(25, 6, 0),
                new Equipment(40, 7, 0),
                new Equipment(74, 8, 0)
        );
        List<Equipment> armor = Arrays.asList(
                new Equipment(13, 0, 1),
                new Equipment(31, 0, 2),
                new Equipment(53, 0, 3),
                new Equipment(75, 0, 4),
                new Equipment(102, 0, 5)
        );
        List<Equipment> rings = Arrays.asList(
                new Equipment(25, 1, 0),
                new Equipment(50, 2, 0),
                new Equipment(100, 3, 0),
                new Equipment(20, 0, 1),
                new Equipment(40, 0, 2),
                new Equipment(80, 0, 3)
        );
        int best = -1;
        for (Equipment weapon : weapons) {
            for (int i = 0; i <= armor.size(); ++i) {
                List<Equipment> list = new ArrayList<>();
                list.add(weapon);
                if (i < armor.size()) {
                    list.add(armor.get(i));
                }
                int numRings = 0;
                int c = addRings(rings, 0, numRings, list, bhp, bd, ba);
                if (c != -1 && (best == -1 || c < best)) {
                    best = c;
                }
            }
        }
        return best;
    }

    private static int addRings(List<Equipment> rings, int index, int numRings, List<Equipment> list, int bhp, int bd, int ba) {
        if (numRings == 2 || index == rings.size()) {
            if (willWin(list, bhp, bd, ba)) {
                return list.stream().map(e -> e.cost).reduce(0, Integer::sum);
            }
            return -1;
        } else {
            int a = addRings(rings, index+1, numRings, list, bhp, bd, ba);
            list.add(rings.get(index));
            int b = addRings(rings, index+1, numRings+1, list, bhp, bd, ba);
            list.remove(rings.get(index));
            if (a == -1 && b == -1) {
                return -1;
            }
            if (a == -1 || b == -1) {
                return a == -1 ? b : a;
            }
            return Math.min(a, b);
        }
    }

    private static boolean willWin(List<Equipment> list, int bhp, int bd, int ba) {
        int md = list.stream().map(e -> e.damage).reduce(0, Integer::sum);
        int ma = list.stream().map(e -> e.armor).reduce(0, Integer::sum);
        int mad = Math.max(1, md-ba);
        int bad = Math.max(1, bd-ma);
        int mt = (bhp+mad-1)/mad;
        int bt = (100+bad-1)/bad;
        return mt <= bt;
    }

    private static int getBossValue(String s) {
        var split = s.split(" ");
        return Integer.parseInt(split[split.length-1]);
    }

    private static class Equipment {
        int cost, damage, armor;

        public Equipment(int cost, int damage, int armor) {
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }
    }
}
