import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day20 {

    public static void main(String[] args) {
        var input = Util.readInt();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(int input) {
        int[] data = new int[input/10];
        for (int i = 1; i < data.length; ++i) {
            for (int j = 1; j <= 50 && i*j < data.length; ++j) {
                data[i*j] += i*11;
            }
            if (data[i] >= input) {
                return i;
            }
        }
        throw new RuntimeException("No answer");
    }

    private static int part1(int input) {
        List<Integer> primes = primes(input);
        for (int i = 2; ; ++i) {
            int p = presents(i, primes);
            if (p >= input) {
                return i;
            }
        }
    }

    private static List<Integer> primes(int input) {
        boolean[] isP = new boolean[input];
        for (int i = 2; i < input; ++i) {
            isP[i] = true;
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i*i < input; ++i) {
            if (isP[i]) {
                primes.add(i);
                for (long j = (long)i*i; j < input; j += i) {
                    isP[(int)j] = false;
                }
            }
        }
        return primes;
    }

    private static int presents(int num, List<Integer> primes) {
        var p = 10;
        for (Integer prime : primes) {
            if (prime > num) {
                break;
            }
            if ((num%prime) == 0) {
                p += prime*10;
                for (int j = prime*prime; j <= num; j += prime) {
                    if ((num%j) == 0) {
                        p += j*10;
                    }
                }
            }
        }
        return p;
    }
}
