package leetcode.linear.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode #128 : https://leetcode.com/problems/longest-consecutive-sequence/description/
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Created by tangmh on 17/9/22.
 */
public class LongestConsecutive {
    public LongestConsecutive() {
        // 单个哈希表? 是否能实现
        // 只需要的结果: length, 但是必须记录元素
        // 一遍n的扫 跑不掉的
        // 可以有限遍On 结果还是On
        // 思路: 先扫一遍放map, 再对每个元素判断扩展长度,优化:signal位标记过则跳过
    }

    public static void main(String[] args) {
        LongestConsecutive tc = new LongestConsecutive();
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(tc.longestConsecutive(arr));
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        Map<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            hashmap.put(nums[i], 0);
        }
        int maxLen = 1;

        for (int cur : hashmap.keySet()
                ) {
            if (hashmap.get(cur) == 1) continue;
            hashmap.replace(cur, 1);
            int len = 1;
            int left = cur, right = cur;
            while (hashmap.containsKey(--left)) {
                hashmap.replace(left, 1);
                len++;
            }
            while (hashmap.containsKey(++right)) {
                hashmap.replace(right, 1);
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}
