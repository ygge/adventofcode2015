import util.Util;

public class Day1 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        int floor = 0;
        for (int i = 0; i < input.length(); ++i) {
            floor += input.charAt(i) == '(' ? 1 : -1;
            if (floor == -1) {
                return i+1;
            }
        }
        throw new RuntimeException("Never got to floor -1");
    }

    private static int part1(String input) {
        int floor = 0;
        for (int i = 0; i < input.length(); ++i) {
            floor += input.charAt(i) == '(' ? 1 : -1;
        }
        return floor;
    }
}
