package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by tangmh on 18/1/8.
 */
public class Solution {
    /**
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     * <p>
     * For example, given the following triangle
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * <p>
     * Note:
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 子问题是倒着看的!
        // 还可以再优化缓存,用array代替list会更快
        if (triangle == null || triangle.size() == 0) return -1;
        int n = triangle.size();
        List<Integer> last = triangle.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int temp = triangle.get(i).get(j) + Math.min(last.get(j), last.get(j + 1));
                cur.add(j, temp);
            }
            last = cur;
        }
        return last.get(0);
    }

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * <p>
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
     * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] book = new int[n];
        boolean[] isEnd = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && isEnd[i - 1] && book[i - 1] > 0) {
                book[i] = book[i - 1] + nums[i];
                isEnd[i] = book[i] > 0 ? true : false;
            } else {
                book[i] = nums[i];
                isEnd[i] = true;
            }
            max = book[i] > max ? book[i] : max;
        }
        return max;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * <p>
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * <p>
     * For example, given s = "aab",
     * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
//        if (s == null || s.length() == 0) return 0;
//        int n = s.length();
//        boolean[][] book = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            book[i][i] = true;
//        }
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < n - i; j++) {
//                if (s.charAt(j) == s.charAt(j + i)) {
//                    if (i == 1 || book[j + 1][j+ i - 1]) book[j][j+i] = true;
//                }
//            }
//        }

        // 思路1: 递归 TLE
//        return findMin(book, 0, n-1) - 1;

        // 思路2: dp 2-dim O(n3) TLE
//        int min[][] = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n - i; j++) {
//                if (book[j][i+j]) min[j][i+j] = 1;
//                else {
//                    int temp = i+1;
//                    for (int k = j; k < i + j; k++) {
//                        if (min[j][k] + min[k+1][i+j] < temp) temp = min[j][k] + min[k+1][i+j];
//                    }
//                    min[j][j+i] = temp;
//                }
//
//            }
//        }
//        return min[0][n-1]-1;

        // 思路3: dp 1-dim
        // num[i]指从i到末位共有几个回文串
//        int[] num = new int[n+1];
//        num[n] = 0;
//        for (int i = n-1; i >= 0; i--) {
//            int min = n - i;
//            for (int j = i; j < n; j++) {
//                if (book[i][j]) min = Math.min(min, num[j+1] + 1);
//            }
//            num[i] = min;
//        }

        // 合并上面的代码,重构
        // 哇哇哇哇哇,,,,,终于AC了
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] book = new boolean[n][n];
        int[] num = new int[n + 1];
        num[n] = 0;
        // worst cases
        for (int i = 0; i < n + 1; i++) num[i] = n - i;
        // dp 动态规划
        for (int i = n - 1; i >= 0; i--) {
            int min = n - i;
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || book[i + 1][j - 1])) {
                    book[i][j] = true;
                    min = Math.min(min, num[j + 1] + 1);
                }
                num[i] = min;
            }
        }

        return num[0] - 1;
    }

//    private int findMin(boolean[][] book, int i, int j) {
//        if (i == j) return 1;
//        if (book[i][j]) return 1;
//        int min = j - i + 1;
//        for (int k = i; k < j; k++) {
//            int temp = findMin(book, i ,k) + findMin(book, k + 1, j);
//            if (temp < min) min = temp;
//        }
//        return min;
//    }

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     * <p>
     * For example, given the following matrix:
     * <p>
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * Return 6.
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
//        if (matrix.length == 0) return 0;
//        int m = matrix.length, n = matrix[0].length;
//        int max = 0;
//        int[][] heights = new int[m][n];
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == '1') {
//                    heights[i][j] = (i==0 ? 1 : heights[i-1][j] + 1);
//                } else {
//                    heights[i][j] = 0;
//                }
//            }
//            max = Math.max(max,largestRectangleArea(heights[i]));
//        }
//        return max;

        // 这个别人是的dp解法,真的是男默女泪
        int maxArea = 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 0);
        Arrays.fill(right, n);

        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n - 1;
            for (int j = 0; j < n; j++) {
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j - 1;
                }
            }
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();

        int[] arr = Arrays.copyOf(heights, heights.length + 1);
        arr[heights.length] = 0;
        heights = arr;

        int n = heights.length, maxArea = 0;
        for (int i = 0; i < n; ) {
            if (stack.empty() || heights[i] > heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int temp = stack.peek();
                stack.pop();
                int area;
                if (stack.empty()) area = i * heights[temp];
                else area = (i - 1 - stack.peek()) * heights[temp];
                maxArea = maxArea < area ? area : maxArea;
            }
        }
        return maxArea;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * <p>
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int[] f = new int[n], g = new int[n];

        int min = prices[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            f[i] = Math.max(f[i - 1], prices[i] - min);
        }

        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            g[i] = Math.max(g[i + 1], max - prices[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] + g[i] > res) res = f[i] + g[i];
        }
        return res;
    }

    /**
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * <p>
     * <p>
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     * <p>
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (s3.length() != m + n) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
//        for (int i = 1; i <= m + n; i++) {
//            for (int j = 0; j <= i; j++) {
//                if (j < 0 || j > m || i - j < 0 || i - j > n) continue;
//                if (j - 1 >= 0) {
//                    dp[j][i - j] = dp[j - 1][i - j] && (s1.charAt(j - 1) == s3.charAt(i - 1));
//                }
//                if (!dp[j][i - j] && i - j - 1 >= 0) {
//                    dp[j][i - j] = dp[j][i - j - 1] && (s2.charAt(i - j - 1) == s3.charAt(i - 1));
//                }
//            }
//        }

        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int i = 1; i <= n; i++) dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)))
                        || (dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }
        return dp[m][n];
    }

    /**
     * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     * <p>
     * Below is one possible representation of s1 = "great":
     * <p>
     * great
     * /    \
     * gr    eat
     * / \    /  \
     * g   r  e   at
     * / \
     * a   t
     * To scramble the string, we may choose any non-leaf node and swap its two children.
     * <p>
     * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
     * <p>
     * rgeat
     * /    \
     * rg    eat
     * / \    /  \
     * r   g  e   at
     * / \
     * a   t
     * We say that "rgeat" is a scrambled string of "great".
     * <p>
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
     * <p>
     * rgtae
     * /    \
     * rg    tae
     * / \    /  \
     * r   g  ta  e
     * / \
     * t   a
     * We say that "rgtae" is a scrambled string of "great".
     * <p>
     * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
//        return isScrambleDigui(s1.length(),0, 0, s1, s2);

        int n = s1.length();
        if (n == 0) return true;
        boolean[][][] dp = new boolean[n + 1][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int m = 1; m <= n; m++) {
            for (int i = 0; i + m <= n; i++) {
                for (int j = 0; j + m <= n; j++) {
                    for (int k = 1; k < m; k++) {
                        if ((dp[k][i][j] && dp[m - k][i + k][j + k]) || (dp[k][i][j + m - k] && dp[m - k][i + k][j])) {
                            dp[m][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[n][0][0];
    }

//    public boolean isScrambleDigui(int len, int a, int b, String s1, String s2) {
//        // 先写个递归试下
    // TODO: 18/2/28 备忘录法和剪枝
//        if (len == 1) return s1.charAt(a) == s2.charAt(b);
//        for (int i = 1; i < len; i++) {
//            if (isScrambleDigui(i, a, b, s1, s2) &&
//                    isScrambleDigui(len - i, a + i, b + i, s1, s2 )) return true;
//            if (isScrambleDigui(i, a, b + len - i, s1, s2) &&
//                    isScrambleDigui(len - i, a + i, b, s1, s2)) return true;
//        }
//        return false;
//    }

//    public boolean isScrambleDP()

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * <p>
     * Note: You can only move either down or right at any point in time.
     * <p>
     * Example 1:
     * [[1,3,1],
     * [1,5,1],
     * [4,2,1]]
     * Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        // 还可以修改成滚动数组，更省space
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        Solution tc = new Solution();

        // 13.1
//        List<List<Integer>> triangle = new ArrayList<>();
//        triangle.add(Arrays.asList(new Integer[]{2}));
//        triangle.add(Arrays.asList(new Integer[]{3,4}));
//        triangle.add(Arrays.asList(new Integer[]{6,5,7}));
//        triangle.add(Arrays.asList(new Integer[]{4,1,8,3}));
//
//        System.out.println(tc.minimumTotal(triangle));

        // 13.2
//        System.out.println(tc.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

        // 13.3
//        System.out.println(tc.minCut("abbab"));

        // 13.4
//        char[][] a = new char[][] {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
//        System.out.println(tc.maximalRectangle(a));

        // 13.5
//        int[] prices = {2,4,1,7};
//        System.out.println(tc.maxProfit(prices));

        // 13.6
//        String s1 = "aabcc",s2 = "dbbca";
//        System.out.println(tc.isInterleave(s1, s2, "aadbbcbcac"));
//        System.out.println(tc.isInterleave(s1, s2, "aadbbbaccc"));
//        System.out.println(tc.isInterleave("a", "", "a"));

        // 13.7
//        System.out.println(tc.isScramble("abcd", "bcad"));
//        System.out.println(tc.isScramble("abcd", "cadb"));

        // 13.8
        System.out.println(tc.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }
}
