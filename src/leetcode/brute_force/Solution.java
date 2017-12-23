package leetcode.brute_force;

import java.util.*;

/**
 * Created by tangmh on 17/12/16.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 8.1
//        System.out.println(tc.subsets(new int[]{1, 2, 3}));
        // 8.2
//        System.out.println(tc.subsetsWithDup(new int[]{1,2,2,2,3}));
        // 8.3
//        System.out.println(tc.permute(new int[]{1,2,3}));
        // 8.4
//        System.out.println(tc.permuteUnique(new int[]{1,1,2,2}));
        // 8.5
//        System.out.println(tc.combine(4,2));
        // 8.6
        System.out.println(tc.letterCombinations(""));
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
        subsets2(nums, path, 0, res);
        return res;
    }
    private void subsets(int[] nums, Stack<Integer> path, int step, List<List<Integer>> result) {
        if (step == nums.length) {
            List<Integer> add = new LinkedList<>(path); // 本句要特别注意,要创建新对象
            result.add(add);
            return;
        }

        subsets(nums, path, step + 1, result);
        path.push(nums[step]);
        subsets(nums, path, step + 1, result);
        path.pop();
    }

    private void subsets2(int[] nums, Stack<Integer> path, int step, List<List<Integer>> result) {
        // 另一版本, 更加简洁美妙,还是扩展题的最简思路
        result.add(new LinkedList<>(path));

        for (int i = step; i < nums.length; i++) {
            path.push(nums[i]);
            subsets2(nums, path, i + 1, result);
            path.pop();
        }
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
        Arrays.sort(nums); // 需要排序
        List<List<Integer>> res = new LinkedList<>();
        Stack<Integer> path = new Stack<>();
        subsetsWithDup(nums, path, 0, res);
        return res;
    }

    private void subsetsWithDup(int[] nums, Stack<Integer> path, int step, List<List<Integer>> result) {
        result.add(new LinkedList<>(path));

        for (int i = step; i < nums.length; i++) {
            if (i != step && nums[i - 1] == nums[i]) continue;
            path.push(nums[i]);
            subsetsWithDup(nums, path, i + 1, result);
            path.pop();
        }
    }

    /**
     * Given a collection of distinct numbers, return all possible permutations.
     * <p>
     * For example,
     * [1,2,3] have the following permutations:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();

//        for (int num : nums) temp.add(num);
//        Collections.sort(temp);
//        do {
//            res.add(new LinkedList<>(temp));
//        } while (nextPermutation(temp));
        boolean[] visited = new boolean[nums.length];
        permute(nums, temp, res, visited);
//        permute_jb(nums, temp, res);
        return res;
    }

    private void permute(int[] nums, List<Integer> temp, List<List<Integer>> res, boolean[] visited) {
        // 暴力遍历,去重剪枝
        // 时间复杂度O(n!)
        if (temp.size() == nums.length) res.add(new LinkedList<>(temp));
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                // 使用一个Boolean记录数组更优
                visited[i] = true;
                temp.add(nums[i]);
                permute(nums, temp, res, visited);
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }

        }
    }

    // 调用之前nextPerm的代码
    private boolean nextPermutation(List<Integer> nums) {
        int a, b, ia = -1, ib;
        for (int i = nums.size() - 1; i > 0; i--) {
            if (nums.get(i) > nums.get(i - 1)) {
                ia = i - 1;
                break;
            }
        }
        if (ia >= 0) {
            a = nums.get(ia);
            ib = ia + 1;
            b = nums.get(ib);
            for (int i = ia + 2; i < nums.size(); i++) {
                if (nums.get(i) <= b && nums.get(i) > a) {
                    ib = i;
                    b = nums.get(ib);
                }
            }

            nums.set(ia, b);
            nums.set(ib, a);
        } else return false;

        int lo = ia + 1, hi = nums.size() - 1;
        while (lo < hi) {
            int temp = nums.get(lo);
            nums.set(lo++, nums.get(hi));
            nums.set(hi--, temp);
        }
        return true;
    }

    private void permute_jb(int[] nums, List<Integer> temp, List<List<Integer>> res) {
        // 我能摸摸你的奖杯吗
        // 一种更巧妙的做法,排列组合中的插入思想
        if (temp.size() == nums.length) {
            res.add(temp);
            return;
        }

        int a = temp.size();
        for (int i = 0; i <= temp.size(); i++) {
            List<Integer> t = new LinkedList<>(temp);
            t.add(i, nums[a]);
            permute_jb(nums, t, res);
        }
    }

    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * <p>
     * For example,
     * [1,1,2] have the following unique permutations:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();

//        for (int num : nums) temp.add(num);
//        Collections.sort(temp);
//
//        do {
//            res.add(new LinkedList<>(temp));
//        } while (nextPermutation(temp));

        // 1,1,1,2  -> 1  1,1  1,1,1  1,1,1,2  1,1,2,1  1,2,1,1  2,1,1,1
        // 1 要排序
        Arrays.sort(nums);
        // 2 创建记录数组
        boolean[] visited = new boolean[nums.length];
        // 递归
        permuteUnique(nums, temp, visited, res);
        return res;
    }

    private void permuteUnique(int[] nums, List<Integer> temp, boolean[] visited, List<List<Integer>> res) {
        if (temp.size() == nums.length) res.add(new LinkedList<>(temp));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
                if (!visited[i]) {
                    visited[i] = true;
                    temp.add(nums[i]);
                    permuteUnique(nums, temp, visited, res);
                    temp.remove(temp.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * <p>
     * For example,
     * If n = 4 and k = 2, a solution is:
     * <p>
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        // 递归的思路
        // k个, 就是从n个里每种取一下再在剩下的里做k-1个
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        combine(1, n, k, temp, res);
        return res;
    }

    private void combine(int start, int n, int k, List<Integer> temp, List<List<Integer>> res) {
        if (k == 0) res.add(new ArrayList<>(temp));
        else {
            for (int i = start; i <= n; i++) {
                temp.add(i);
                combine(i + 1, n, k - 1, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * Given a digit string, return all possible letter combinations that the number could represent.
     * <p>
     * Input:Digit string "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * Note:
     * Although the above answer is in lexicographical order, your answer could be in any order you want.
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] represents = {null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        if (digits.length() == 0) return res;
        letterCombinations(digits, 0, res, stringBuilder, represents);
        return res;
    }

    private void letterCombinations(String digits, int i, List<String> res, StringBuilder stringBuilder, String[] represents) {
        if (i == digits.length()) {
            res.add(String.valueOf(stringBuilder));
            return;
        }
        String target = represents[digits.charAt(i) - '0'];
        if (target == null) return;
        for (int j = 0; j < target.length(); j++) {
            stringBuilder.append(target.charAt(j));
            letterCombinations(digits, i + 1, res, stringBuilder, represents);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
