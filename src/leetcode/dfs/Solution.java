package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangmh on 17/12/29.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 10.1
//        System.out.println(tc.partition("aab"));
//        System.out.println(tc.partition("efe"));

        // 10.2
//        System.out.println(tc.uniquePaths(2,3));

        // 10.3
//        System.out.println(tc.uniquePathsWithObstacles(new int[][] {
//                {0,0,0},
//                {0,1,0},
//                {0,0,0}
//        }));

        // 10.4
//        System.out.println(tc.solveNQueens(4));

        // 10.5
//        System.out.println(tc.totalNQueens(4));

        // 10.6
        System.out.println(tc.restoreIpAddresses("25525511135"));
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * <p>
     * Return all possible palindrome partitioning of s.
     * <p>
     * For example, given s = "aab",
     * Return
     * <p>
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        partition_dfs(s, new ArrayList<>(), res, 0);
        return res;
    }

    private void partition_dfs(String s, List<String> path, List<List<String>> res, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) continue;
            path.add(s.substring(start, i + 1));
            partition_dfs(s, path, res, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * How many possible unique paths are there?
     * <p>
     * Note: m and n will be at most 100.
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
//        if (m == 1 || n == 1) return 1;
//        return uniquePaths(m-1, n) + uniquePaths(m, n-1);

        int[][] book = new int[m][n];
        book[0][0] = 1;
        return uniquePathsDFS(m - 1, n - 1, book);
    }

    private int uniquePathsDFS(int x, int y, int[][] book) {
        if (x < 0 || y < 0) return 0;
        else if (book[x][y] == 0)
            book[x][y] = uniquePathsDFS(x - 1, y, book) + uniquePathsDFS(
                    x, y - 1, book);
        return book[x][y];
    }

    /**
     * Follow up for "Unique Paths":
     * <p>
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * <p>
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * <p>
     * For example,
     * There is one obstacle in the middle of a 3x3 grid as illustrated below.
     * <p>
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * The total number of unique paths is 2.
     * <p>
     * Note: m and n will be at most 100.
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 本题另外可以动规, 效率up
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;

        int book[][] = new int[m][n];
        if (obstacleGrid[0][0] == 1) return 0;
        else book[0][0] = 1;

        return uniquePathWithObs_dfs(m - 1, n - 1, obstacleGrid, book);
    }

    private int uniquePathWithObs_dfs(int x, int y, int[][] obstacleGrid, int[][] book) {
        if (x < 0 || y < 0 || obstacleGrid[x][y] == 1) return 0;
        else if (book[x][y] == 0)
            book[x][y] = uniquePathWithObs_dfs(x - 1, y, obstacleGrid, book) + uniquePathWithObs_dfs(
                    x, y - 1, obstacleGrid, book);
        return book[x][y];
    }


    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     * <p>
     * <p>
     * <p>
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * <p>
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
     * <p>
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     * <p>
     * [
     * [".Q..",  // Solution 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // Solution 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] book = new int[n];
        for (int i = 0; i < n; i++) {
            book[i] = -1;
        }
        solveNQueensDFS(solutions, book, 0, n);
        return solutions;
    }

    private void solveNQueensDFS(List<List<String>> solutions, int[] book, int i, int n) {
        if (i == n) {
            // add new solution
            List<String> solution = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                StringBuilder builder = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    if (book[j] == k) builder.append('Q');
                    else builder.append('.');
                }
                solution.add(builder.toString());
            }
            solutions.add(solution);
        } else {
            for (int j = 0; j < n; j++) {
                if (determineValid(book, i, j)) {
                    book[i] = j;
                    solveNQueensDFS(solutions, book, i + 1, n);
                    book[i] = -1;
                }
            }
        }
    }

    private boolean determineValid(int[] book, int i, int j) {
        for (int k = 0; k < i; k++) {
//            if (k == -1) continue;
            if (book[k] == j) return false; // column check
            if (Math.abs(k - i) == Math.abs(book[k] - j)) return false; // diag check
        }
        return true;
    }

    /**
     * Follow up for N-Queens problem.
     * <p>
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     *
     * @param n
     * @return
     */
    int count;

    public int totalNQueens(int n) {
        count = 0;
        int[] book = new int[n];
        for (int i = 0; i < n; i++) {
            book[i] = -1;
        }
        totalNQueensDFS(book, 0, n);
        return count;
    }

    private void totalNQueensDFS(int[] book, int i, int n) {
        if (i == n) {
            count++;
        } else {
            for (int j = 0; j < n; j++) {
                if (determineValid(book, i, j)) {
                    book[i] = j;
                    totalNQueensDFS(book, i + 1, n);
                    book[i] = -1;
                }
            }
        }
    }

    /**
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * <p>
     * For example:
     * Given "25525511135",
     * <p>
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        StringBuilder builder = new StringBuilder();
        restoreIpAddressesDFS(s, 0, builder, res);
        return res;
    }

    private void restoreIpAddressesDFS(String s, int i, StringBuilder builder, List<String> res) {
        if (i == 4) {
            if (s.equals("")) res.add(builder.toString());
        } else {
            for (int j = 1; j <= 3; j++) {
                if (j > s.length()) break;

                switch (j) {
                    case 1:
                        break;
                    case 2:
                        if (s.charAt(0) == '0') return;
                        break;
                    case 3:
                        if (Integer.parseInt(s.substring(0, 3)) > 255) return;
                        break;
                }

                builder.append(s.substring(0, j));
                if (i < 3) builder.append('.');
                restoreIpAddressesDFS(s.substring(j), i + 1, builder, res);
                if (i < 3) builder.delete(builder.length() - 1, builder.length());
                builder.delete(builder.length() - j, builder.length());
            }
        }
    }

    
}
