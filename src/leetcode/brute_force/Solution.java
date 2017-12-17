package leetcode.brute_force;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tangmh on 17/12/16.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 8.1
        System.out.println(tc.subsets(new int[]{1, 2, 3}));
        // 8.2


    }

    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     * <p>
     * Note: The solution set must not contain duplicate subsets.
     * <p>
     * For example,
     * If nums = [1,2,3], a solution is:
     * <p>
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 二进制法
//        List<List<Integer>> sets = new LinkedList<>();
//        int n = nums.length;
//        for (int i = 0; i < 1 << n; i++) {
//            List<Integer> sub = new LinkedList<>();
//            int m = i, j = 0;
//            while (m > 0) {
//                if (m % 2 == 1) sub.add(nums[j]);
//                j++;
//                m = m / 2;
//            }
//            sets.add(sub);
//        }
//        return sets;

        // 递归深搜
        List<List<Integer>> res = new LinkedList<>();
        Stack<Integer> path = new Stack<>();
        subsets(nums, path, 0, res);
        return res;
    }

    private void subsets(int[] nums, Stack<Integer> path, int step, List<List<Integer>> result) {
        if (step == nums.length) {
            List<Integer> add = new LinkedList<>(path);
            result.add(add);
            return;
        }

        subsets(nums, path, step + 1, result);
        path.push(nums[step]);
        subsets(nums, path, step + 1, result);
        path.pop();
    }

    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     * <p>
     * Note: The solution set must not contain duplicate subsets.
     * <p>
     * For example,
     * If nums = [1,2,2], a solution is:
     * <p>
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        return res;
    }
}
