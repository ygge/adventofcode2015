import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        int[][] light = new int[1000][1000];
        for (String row : input) {
            String[] split = row.split(" ");
            if (split[1].equals("on") || split[1].equals("off")) {
                var delta = split[1].equals("on") ? 1 : -1;
                var from = split[2].split(",");
                var to = split[4].split(",");
                int fromX = Integer.parseInt(from[0]);
                int fromY = Integer.parseInt(from[1]);
                int toX = Integer.parseInt(to[0]);
                int toY = Integer.parseInt(to[1]);
                for (int y = fromY; y <= toY; ++y) {
                    for (int x = fromX; x <= toX; ++x) {
                        light[y][x] = Math.max(0, light[y][x]+delta);
                    }
                }
            } else {
                var from = split[1].split(",");
                var to = split[3].split(",");
                int fromX = Integer.parseInt(from[0]);
                int fromY = Integer.parseInt(from[1]);
                int toX = Integer.parseInt(to[0]);
                int toY = Integer.parseInt(to[1]);
                for (int y = fromY; y <= toY; ++y) {
                    for (int x = fromX; x <= toX; ++x) {
                        light[y][x] += 2;
                    }
                }
            }
        }
        int count = 0;
        for (int y = 0; y < light.length; ++y) {
            for (int x = 0; x < light[y].length; ++x) {
                count += light[y][x];
            }
        }
        return count;
    }

    private static int part1(List<String> input) {
        boolean[][] light = new boolean[1000][1000];
        for (String row : input) {
            String[] split = row.split(" ");
            if (split[1].equals("on") || split[1].equals("off")) {
                var on = split[1].equals("on");
                var from = split[2].split(",");
                var to = split[4].split(",");
                int fromX = Integer.parseInt(from[0]);
                int fromY = Integer.parseInt(from[1]);
                int toX = Integer.parseInt(to[0]);
                int toY = Integer.parseInt(to[1]);
                for (int y = fromY; y <= toY; ++y) {
                    for (int x = fromX; x <= toX; ++x) {
                        light[y][x] = on;
                    }
                }
            } else {
                var from = split[1].split(",");
                var to = split[3].split(",");
                int fromX = Integer.parseInt(from[0]);
                int fromY = Integer.parseInt(from[1]);
                int toX = Integer.parseInt(to[0]);
                int toY = Integer.parseInt(to[1]);
                for (int y = fromY; y <= toY; ++y) {
                    for (int x = fromX; x <= toX; ++x) {
                        light[y][x] = !light[y][x];
                    }
                }
            }
        }
        int count = 0;
        for (int y = 0; y < light.length; ++y) {
            for (int x = 0; x < light[y].length; ++x) {
                if (light[y][x]) {
                    ++count;
                }
            }
        }
        return count;
    }
}
