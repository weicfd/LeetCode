package leetcode.other;

import java.util.*;

public class Solution {

    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Example 1:
     * <p>
     * Input: 123
     * Output:  321
     * Example 2:
     * <p>
     * Input: -123
     * Output: -321
     * Example 3:
     * <p>
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        else if (x < 0) return -reverse(-x);
        long m = x, n = 0;
        while (m > 0) {
            n *= 10;
            n += m % 10;
            m = m / 10;
            if (n > Integer.MAX_VALUE) return 0;
        }
        return (int) n;
    }

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int d = 1;
        while (x / d >= 10) d *= 10;
        System.out.println(d);

        while (x > 0) {
            int l = x / d;
            int r = x % 10;
            if (l != r) return false;
            x = x % d / 10;
            d /= 100;
        }
        return true;
    }

    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * <p>
     * You may assume that the intervals were initially sorted according to their start times.
     * <p>
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * <p>
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * <p>
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        for (int i = 0; i < intervals.size(); i++) {
            if (newInterval.end < intervals.get(i).start) {
                intervals.add(i, newInterval);
                return intervals;
            } else if (newInterval.start > intervals.get(i).end) {
                continue;
            } else {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
                intervals.remove(i);
                i--;
            }
        }
        intervals.add(newInterval);
        return intervals;
    }

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * <p>
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        for (Interval i :
                intervals) {
            insert(list, i);
        }
        return list;
    }

    /**
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     * <p>
     * For example,
     * S = "ADOBECODEBANC"
     * T = "ABC"
     * Minimum window is "BANC".
     * <p>
     * Note:
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * <p>
     * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
     */
    public String minWindow(String s, String t) {
        // 有点难 = =~~
        // 先试下暴力 // emm 超时了
        // 优化成头指针+尾指针
        int min = Integer.MAX_VALUE;
        int res = 0;
        final int ASCII_NUM = 256;
        int[] expected = new int[ASCII_NUM];
        int[] appeared = new int[ASCII_NUM];
        int num = t.length();

        for (int i = 0; i < t.length(); i++) {
            expected[t.charAt(i)]++;
        }

        int count = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (expected[s.charAt(end)] > 0) {
                appeared[s.charAt(end)]++;
                if (appeared[s.charAt(end)] <= expected[s.charAt(end)]) count++;
            }
            if (count == num) {
                while (appeared[s.charAt(start)] > expected[s.charAt(start)] || expected[s.charAt(start)] == 0) {
                    if (appeared[s.charAt(start)] > 0) appeared[s.charAt(start)]--;
                    start++;
                }
                if (end - start + 1 < min) {
                    min = end - start + 1;
                    res = start;
                }
            }
        }

//        for (int i = 0; i < s.length(); i++) {
//            int temp = 0;
//            Map<Character, Integer> appeared_map = new HashMap<>();
//            for (int j = i; j < s.length(); j++) {
//                if (expected_map.containsKey(s.charAt(j))) {
//                    if (!appeared_map.containsKey(s.charAt(j))) {
//                        appeared_map.put(s.charAt(j), 1);
//                        temp++;
//                    } else if (appeared_map.get(s.charAt(j)) < expected_map.get(s.charAt(j))) {
//                        appeared_map.put(s.charAt(j), appeared_map.get(s.charAt(j)) + 1);
//                        temp++;
//                    }
//                }
//                if (j - i + 1 > min) break;
//                if (temp == num) {
//                    min = Math.min(min, j - i + 1);
//                    res = i;
//                }
//            }
//        }
        if (min == Integer.MAX_VALUE) return "";
        else return s.substring(res, res + min);
    }

    /**
     * 大数乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        if ((m == 1 && Integer.valueOf(num1) == 0) || (n == 1 && Integer.valueOf(num2) == 0)) return "0";
        int[] temp = new int[m + n - 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                temp[i + j] += (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
            }
        }

        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = m + n - 2; i >= 0; i--) {
            builder.append((temp[i] + carry) % 10);
            carry = (temp[i] + carry) / 10;
        }
        while (carry != 0) {
            builder.append(carry % 10);
            carry /= 10;
        }
        while (builder.charAt(builder.length() - 1) == '0') builder.deleteCharAt(builder.length() - 1);
        return builder.reverse().toString();
    }

    /**
     * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices
     * of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
     * <p>
     * For example, given:
     * s: "barfoothefoobarman"
     * words: ["foo", "bar"]
     * <p>
     * You should return the indices: [0,9].
     * (order does not matter).
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        // 排序大法TLE了嘤嘤嘤

//        List<Integer> result = new ArrayList<>();
//        if (words.length == 0) return result;
//
//        Arrays.sort(words);
//        int wSize = words.length;
//        int wLen = words[0].length();
//
//        for (int i = 0; i <= s.length() - wLen * wSize; i++) {
//            String[] subsub = new String[wSize];
//            for (int j = 0, k = i; j < wSize; j++, k += wLen) {
//                subsub[j] = s.substring(k, k + wLen);
//            }
//            Arrays.sort(subsub);
//
//            boolean flag = true;
//            for (int j = 0; j < wSize; j++) {
//                if (!subsub[j].equals(words[j]))
//                    flag = false;
//            }
//            if (flag) result.add(i);
//        }
//        return result;

        List<Integer> res = new ArrayList<>();
        Map<String, Integer> record = new HashMap<>();
        if (words.length == 0) return res;
        int wSize = words.length;
        int wLen = words[0].length();

        for (String word : words)
            record.put(word, record.getOrDefault(word, 0) + 1);

        for (int i = 0; i <= s.length() - wLen * wSize; i++) {
            Map<String, Integer> temp = new HashMap<>();
            for (int j = 0, k = i; j < wSize; j++, k += wLen) {
                String sub = s.substring(k, k + wLen);
                if (record.containsKey(sub)) {
                    if (temp.getOrDefault(sub, 0) >= record.get(sub)) break;
                    else {
                        temp.put(sub, temp.getOrDefault(sub, 0) + 1);
                        if (j == wSize - 1) {
                            res.add(i);
                        }
                    }
                } else
                    break;
            }
        }
        return res;
    }

    /**
     * Given numRows, generate the first numRows of Pascal's triangle.
     * <p>
     * For example, given numRows = 5,
     * Return
     * <p>
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        List<Integer> path = new ArrayList<>();
        path.add(1);
        res.add(new ArrayList<>(path));
        for (int i = 2; i <= numRows; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 0; j < path.size() - 1; j++) {
                next.add(path.get(j) + path.get(j + 1));
            }
            next.add(1);
            res.add(next);
            path = next;
        }
        return res;
    }

    /**
     * Given an index k, return the kth row of the Pascal's triangle.
     * <p>
     * For example, given k = 3,
     * Return [1,3,3,1].
     * <p>
     * Note:
     * Could you optimize your algorithm to use only O(k) extra space?
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> path = new ArrayList<>();
        path.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 0; j < path.size() - 1; j++) {
                next.add(path.get(j) + path.get(j + 1));
            }
            next.add(1);
            path = next;
        }
        return path;
    }

    /**
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * <p>
     * For example,
     * Given the following matrix:
     * <p>
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int k = 1, l = 0, m = matrix.length, n = matrix[0].length;

        int a = 0, b = 0;
        int direction = 1;
        for (int i = 0; i < matrix.length * matrix[0].length; i++) {
            result.add(matrix[a][b]);
            switch (direction) {
                case 1:
                    if (b + 1 >= n) {
                        direction = 2;
                        a++;
                        n--;
                    } else {
                        b++;
                    }
                    break;
                case 2:
                    if (a + 1 >= m) {
                        direction = 3;
                        b--;
                        m--;
                    } else {
                        a++;
                    }
                    break;
                case 3:
                    if (b - 1 < l) {
                        direction = 4;
                        a--;
                        l++;
                    } else {
                        b--;
                    }
                    break;
                case 4:
                    if (a - 1 < k) {
                        direction = 1;
                        b++;
                        k++;
                    } else {
                        a--;
                    }
                    break;
            }
        }
        return result;
    }

    /**
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * <p>
     * For example,
     * Given n = 3,
     * <p>
     * You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     *
     * @param row
     * @return
     */
    public int[][] generateMatrix(int row) {
        int[][] result = new int[row][row];
        if (row == 0) return result;
        int k = 1, l = 0, m = row, n = row;

        int a = 0, b = 0;
        int direction = 1;
        for (int i = 1; i <= row * row; i++) {
            result[a][b] = i;
            switch (direction) {
                case 1:
                    if (b + 1 >= n) {
                        direction = 2;
                        a++;
                        n--;
                    } else {
                        b++;
                    }
                    break;
                case 2:
                    if (a + 1 >= m) {
                        direction = 3;
                        b--;
                        m--;
                    } else {
                        a++;
                    }
                    break;
                case 3:
                    if (b - 1 < l) {
                        direction = 4;
                        a--;
                        l++;
                    } else {
                        b--;
                    }
                    break;
                case 4:
                    if (a - 1 < k) {
                        direction = 1;
                        b++;
                        k++;
                    } else {
                        a--;
                    }
                    break;
            }
        }
        return result;
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * <p>
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        return s;
    }

    public static void main(String[] args) {
        Solution tc = new Solution();
//        System.out.println(tc.reverse(123));

//        System.out.println(tc.isPalindrome(1001));

//        List<Interval> list = new ArrayList<>();
//        list.add(new Interval(1,5));
//        list.add(new Interval(6,9));
//        System.out.println(tc.insert(list, new Interval(2, 3)));

//        list.add(new Interval(1,3));
//        list.add(new Interval(2,6));
//        list.add(new Interval(8,10));
//        list.add(new Interval(15,18));
//
//        System.out.println(tc.merge(list));

//        System.out.println(tc.minWindow("ADOBECODEBANC","ABC"));
//        System.out.println(tc.minWindow("acbbaca","aab"));

//        System.out.println(tc.multiply("2009", "9999"));

//        System.out.println(tc.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
//        System.out.println(tc.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));

//        System.out.println(tc.generate(5));
//        System.out.println(tc.getRow(0));

//        System.out.println(tc.spiralOrder(new int[][] {
//                {1,2,3},
//                {4,5,6},
//                {7,8,9}
//        }));

        System.out.println(Arrays.deepToString(tc.generateMatrix(3)));
    }

}
