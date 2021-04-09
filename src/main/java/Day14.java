import util.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        List<Reindeer> reindeers = input.stream()
                .map(s -> {
                    var split = s.split(" ");
                    return new Reindeer(Integer.parseInt(split[3]), Integer.parseInt(split[6]), Integer.parseInt(split[13]));
                })
                .collect(Collectors.toList());
        int[] points = new int[reindeers.size()];
        int[] distances = new int[reindeers.size()];
        for (int t = 0; t < 2503; ++t) {
            int max = 0;
            for (int r = 0; r < reindeers.size(); ++r) {
                var reindeer = reindeers.get(r);
                var tt = t%(reindeer.time+reindeer.rest);
                if (tt < reindeer.time) {
                    distances[r] += reindeer.speed;
                }
                max = Math.max(max, distances[r]);
            }
            for (int r = 0; r < reindeers.size(); ++r) {
                if (distances[r] == max) {
                    ++points[r];
                }
            }
        }
        return Arrays.stream(points)
                .max()
                .orElseThrow();
    }

    private static int part1(List<String> input) {
        List<Reindeer> reindeers = input.stream()
                .map(s -> {
                    var split = s.split(" ");
                    return new Reindeer(Integer.parseInt(split[3]), Integer.parseInt(split[6]), Integer.parseInt(split[13]));
                })
                .collect(Collectors.toList());
        return reindeers.stream()
                .map(r -> distance(r, 2503))
                .max(Comparator.comparingInt(a -> a))
                .orElseThrow();
    }

    private static int distance(Reindeer reindeer, int time) {
        int d = 0;
        int t = 0;
        boolean resting = false;
        while (true) {
            int tt = resting ? reindeer.rest : reindeer.time;
            if (t+tt >= time) {
                tt = time-t;
                return d + tt*(resting ? 0 : reindeer.speed);
            }
            d += tt*(resting ? 0 : reindeer.speed);
            t += tt;
            resting = !resting;
        }
    }

    private static class Reindeer {
        int speed, time, rest;

        public Reindeer(int speed, int time, int rest) {
            this.speed = speed;
            this.time = time;
            this.rest = rest;
        }
    }
}
