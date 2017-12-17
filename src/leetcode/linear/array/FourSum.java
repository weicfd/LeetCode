package leetcode.linear.array;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * Created by tangmh on 17/9/24.
 */
public class FourSum {
    public FourSum() {
        // 3sum 扩展到4,  sort还是一定需要的
        // 单是双重循环加左右夹逼的话 是O(n^3)
        // 思路的话 1. for i 0 -> n-3  2. for j i+1 -> n-2 3. lo hi
        // 优化: 用一个hashmap作为cache
    }

    public static void main(String[] args) {
        int[] s = {1, 0, -1, 0, -2, 2};
        FourSum tc = new FourSum();
        List<List<Integer>> res = tc.fourSum(s, 0);
        for (List<Integer> i : res) {
            System.out.println(i);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        Map<Integer, List<Pair>> cache = new HashMap<>();
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int addup = nums[i] + nums[j];
                if (!cache.containsKey(addup)) {
                    cache.put(addup, new ArrayList<>());
                }
                cache.get(addup).add(new Pair(i, j));
            }
        }

        for (int c = 0; c < nums.length; c++) {
            for (int d = c + 1; d < nums.length; d++) {
                int k = target - nums[c] - nums[d];
                if (!cache.containsKey(k)) continue;

                for (Pair pair : cache.get(k)
                        ) {
                    if (pair.getValue() >= c) continue;
                    res.add(Arrays.asList(nums[pair.getKey()], nums[pair.getValue()], nums[c], nums[d]));
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(res);
        return result;
    }

    private class Pair {
        int k, v;

        public Pair(int a, int b) {
            k = a;
            v = b;
        }

        public int getKey() {
            return k;
        }

        public int getValue() {
            return v;
        }
    }


    /**
     * 一个参考的版本
     *
     public class Solution {
     public List<List<Integer>> fourSum(int[] nums, int target) {
     List<List<Integer>> ret=new ArrayList<List<Integer>>();
     Arrays.sort(nums);

     for(int i=0;i<nums.length;i++)
     if(i==0||nums[i]!=nums[i-1])
     for(int j=i+1;j<nums.length;j++)
     if(j==i+1||nums[j]!=nums[j-1])
     {
     int l=j+1;
     int r=nums.length-1;
     while(l<r)
     {
     int sum=nums[i]+nums[j]+nums[l]+nums[r];
     if(sum==target)
     {
     ret.add(Arrays.asList(new Integer[]{nums[i],nums[j],nums[l],nums[r]}));
     while(l<r&&nums[l]==nums[l+1])l++;
     while(l<r&&nums[r]==nums[r-1])r--;
     l++;
     r--;
     }
     else if (sum<target)
     l++;
     else
     r--;
     }
     }

     return ret;
     }
     }
     */
}
