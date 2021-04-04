import util.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    public static void main(String[] args) {
        var input = Util.readString();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; ; ++i) {
            md.reset();
            md.update((input + i).getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            if (digest[0] == 0 && digest[1] == 0 && digest[2] == 0) {
                return i;
            }
        }
    }

    private static int part1(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; ; ++i) {
            md.reset();
            md.update((input + i).getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            if (digest[0] == 0 && digest[1] == 0 && (digest[2]&0xf0) == 0) {
                return i;
            }
        }
    }
}
