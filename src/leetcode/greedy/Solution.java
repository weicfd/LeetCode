package leetcode.greedy;

/**
 * Created by tangmh on 18/1/7.
 */
public class Solution {
    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * <p>
     * Each element in the array represents your maximum jump length at that position.
     * <p>
     * Determine if you are able to reach the last index.
     * <p>
     * For example:
     * A = [2,3,1,1,4], return true.
     * <p>
     * A = [3,2,1,0,4], return false.
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max; i++) {
            if (max >= nums.length) break;
            if (nums[i] + i > max) max = nums[i] + i;
        }
        if (max >= nums.length - 1) return true;
        else return false;
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * <p>
     * Each element in the array represents your maximum jump length at that position.
     * <p>
     * Your goal is to reach the last index in the minimum number of jumps.
     * <p>
     * For example:
     * Given array A = [2,3,1,1,4]
     * <p>
     * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     * <p>
     * Note:
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int begin = 0, end = nums[0], step = 1;
        if (nums.length == 1) return 0;
        while (end < nums.length - 1) {
            int msx = 0;
            for (int i = begin; i <= end; i++) {
                if (nums[i] + i > msx) {
                    msx = nums[i] + i;
                }
            }
            begin = end + 1;
            end = msx;
            step++;
        }
        return step;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     * <p>
     * Example 1:
     * Input: [7, 1, 5, 3, 6, 4]
     * Output: 5
     * <p>
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
     * Example 2:
     * Input: [7, 6, 4, 3, 1]
     * Output: 0
     * <p>
     * In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0, min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) min = prices[i];
            else if (prices[i] - min > profit) profit = prices[i] - min;
        }
        return profit;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and
     * sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time
     * (ie, you must sell the stock before you buy again).
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        if (prices.length < 2) return 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Examples:
     * <p>
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * <p>
     * Given "bbbbb", the answer is "b", with the length of 1.
     * <p>
     * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int last = 0, max = 0;
        if (s.length() == 0) return max;
        for (int i = 0; i < s.length(); i++) {
            int temp = s.substring(last, i).indexOf(s.charAt(i));
            if (temp < 0) {
                if (i - last + 1 > max) max = i - last + 1;
            } else {
                last = temp + last + 1;
            }
        }
        return max;
    }

    /**
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with
     * x-axis forms a container, such that the container contains the most water.
     * <p>
     * Note: You may not slant the container and n is at least 2.
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        // 妈耶有毒吧,一毛一样的代码,第一次submit的时候TLE,第二次提交AC了, 气得吐血
        if (height.length < 2) return 0;
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            if (max < temp) max = temp;
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }

    public static void main(String[] args) {
        // 12.1
        Solution tc = new Solution();
//        System.out.println(tc.canJump(new int[]{2,3,1,1,4}));
//        System.out.println(tc.canJump(new int[]{3,2,1,0,4}));

        // 12.2
//        System.out.println(tc.jump(new int[]{2,3,1,1,4}));
//        System.out.println(tc.jump(new int[]{0}));

        // 12.3
//        System.out.println(tc.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(tc.maxProfit(new int[]{7, 6, 4, 3, 1}));

        // 12.4
//        System.out.println(tc.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(tc.lengthOfLongestSubstring("dvdf"));
//        System.out.println(tc.lengthOfLongestSubstring("bbtablud"));
//        System.out.println(tc.lengthOfLongestSubstring("pwwkew"));

        // 12.5
        System.out.println(tc.maxArea(new int[]{1, 2, 4, 3}));

    }
}
