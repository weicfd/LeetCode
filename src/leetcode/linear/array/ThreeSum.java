package leetcode.linear.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode #15 : https://leetcode.com/problems/3sum/description/
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * Created by tangmh on 17/9/22.
 */
public class ThreeSum {
    public ThreeSum() {
        // 思路: 由于有三个var,所以不能用简单的map方法
        // 排序 的作用: 夹逼
        // 双循环的效率略低下:
        // 考虑使用左右夹逼(有点quick sort的感觉?)
    }

    public static void main(String[] args) {
        ThreeSum tc = new ThreeSum();
        int[] arr = {0, 0, 0, 0};
        List<List<Integer>> res = tc.threeSum(arr);
        for (List<Integer> item : res
                ) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
            while (lo < hi) {
                if (nums[lo] + nums[hi] == sum) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++;
                    hi--;
                } else if (nums[lo] + nums[hi] < sum) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return result;
    }
}
