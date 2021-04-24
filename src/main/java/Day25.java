import util.Util;

public class Day25 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(0);
    }

    private static long part1(String input) {
        var split = input.split(" ");
        var row = Integer.parseInt(split[16].substring(0, split[16].length()-1));
        var col = Integer.parseInt(split[18].substring(0, split[18].length()-1));

        var num = calcNum(row, col);
        System.out.println(num);

        long n = 20151125;
        while (num > 1) {
            n = (n*252533)%33554393;
            --num;
        }
        return n;
    }

    private static long calcNum(int row, int col) {
        long num = 1;
        for (int r = 1; r < row; ++r) {
            num += r;
        }
        for (int c = 1; c < col; ++c) {
            num += row +c;
        }
        return num;
    }
}
