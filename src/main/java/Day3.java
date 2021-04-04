import util.Direction;
import util.Pos;
import util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day3 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        Set<Pos> seen = new HashSet<>();
        Pos[] pos = new Pos[]{ new Pos(0, 0), new Pos(0, 0) };
        seen.add(new Pos(0, 0));
        for (int i = 0; i < input.length(); ++i) {
            pos[i%2] = pos[i%2].move(toDir(input.charAt(i)));
            seen.add(pos[i%2]);
        }
        return seen.size();
    }

    private static int part1(String input) {
        Set<Pos> seen = new HashSet<>();
        Pos pos = new Pos(0, 0);
        seen.add(pos);
        for (int i = 0; i < input.length(); ++i) {
            pos = pos.move(toDir(input.charAt(i)));
            seen.add(pos);
        }
        return seen.size();
    }

    private static Direction toDir(char c) {
        return switch (c) {
            case '^' -> Direction.UP;
            case 'v' -> Direction.DOWN;
            case '<' -> Direction.LEFT;
            case '>' -> Direction.RIGHT;
            default -> throw new RuntimeException("Not a direction: " + c);
        };
    }
}
