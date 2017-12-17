package leetcode.linear.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangmh on 17/9/27.
 */
public class IsValidSudoku {
    public IsValidSudoku() {
    }

    public static void main(String[] args) {
        IsValidSudoku tc = new IsValidSudoku();
        char[][] board = new char[9][9];
        System.out.println(tc.isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {

        Map<Character, Boolean> book = new HashMap<>();

        for (int i = 0; i < 9; i++) {

            book.clear();

            // each row check
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (book.containsKey(board[i][j])) return false;
                else book.put(board[i][j], true);
            }

            book.clear();

            // each col check
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') continue;
                if (book.containsKey(board[j][i])) return false;
                else book.put(board[j][i], true);
            }

        }

        // each grid check
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                book.clear();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int ia = a * 3 + i, ib = b * 3 + j;
                        if (board[ia][ib] == '.') continue;
                        if (book.containsKey(board[ia][ib])) return false;
                        else book.put(board[ia][ib], true);
                    }
                }
            }
        }

        return true;
    }
}
