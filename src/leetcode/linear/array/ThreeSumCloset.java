package leetcode.linear.array;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1, 2, 1, -4} , and target = 1
 * The sum that is closest to the target is 2, (-1 + 2 + 1 = 2)
 * Created by tangmh on 17/9/23.
 */
public class ThreeSumCloset {
    public ThreeSumCloset() {
    }

    public static void main(String[] args) {
        int[] arr = {-1, 2, 1, -4};
        ThreeSumCloset tc = new ThreeSumCloset();
        System.out.println(tc.threeSumClosest(arr, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        int minGap = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                int gap = Math.abs(target - sum);
                if (gap < minGap) {
                    minGap = gap;
                    res = sum;
                }
                if (sum < target) lo++;
                else hi--;
            }
        }
        return res;
    }
}
