import util.Util;

public class Day18 {

    public static void main(String[] args) {
        var input = Util.readBoard('#', '.');
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(boolean[][] input) {
        var board = input;
        for (int i = 0; i < 100; ++i) {
            boolean[][] newBoard = new boolean[board.length][board[0].length];
            for (int y = 0; y < board.length; ++y) {
                for (int x = 0; x < board[y].length; ++x) {
                    if ((y == 0 && x == 0) || (y == 0 && x == board[0].length-1)
                        || (y == board.length-1 && x == 0) || (y == board.length-1 && x == board.length-1)) {
                        newBoard[y][x] = true;
                    } else {
                        int on = countOn(board, x, y);
                        newBoard[y][x] = (board[y][x] && (on == 2 || on == 3))
                                || (!board[y][x] && on == 3);
                    }
                }
            }
            board = newBoard;
        }
        int count = 0;
        for (boolean[] booleans : board) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    ++count;
                }
            }
        }
        return count;
    }

    private static int part1(boolean[][] input) {
        var board = input;
        for (int i = 0; i < 100; ++i) {
            boolean[][] newBoard = new boolean[board.length][board[0].length];
            for (int y = 0; y < board.length; ++y) {
                for (int x = 0; x < board[y].length; ++x) {
                    int on = countOn(board, x, y);
                    newBoard[y][x] = (board[y][x] && (on == 2 || on == 3))
                            || (!board[y][x] && on == 3);
                }
            }
            board = newBoard;
        }
        int count = 0;
        for (boolean[] booleans : board) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    ++count;
                }
            }
        }
        return count;
    }

    private static int countOn(boolean[][] board, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i != 0 || j != 0) {
                    if (y+i >= 0 && y+i < board.length && x+j >= 0 && x+j < board[0].length && board[y+i][x+j]) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
}
