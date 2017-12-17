package leetcode.linear.array;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * Created by tangmh on 17/9/27.
 */
public class TrappingRainWater {
    public TrappingRainWater() {
        // 对每一个坐标, 其所能容纳的水是min(左极大值,右极大值) - h
    }

    public static void main(String[] args) {
        TrappingRainWater tc = new TrappingRainWater();
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(tc.trap(arr));
        ;
    }

    public int trap(int[] height) {
        int res = 0;
        int n = height.length;

        // 思路1: 两个book记录各坐标的左右极大值
        // 再扫一遍计算结果

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        for (int i = 0; i < n - 1; i++) {
            leftMax[i + 1] = Math.max(leftMax[i], height[i]);
            rightMax[n - i - 2] = Math.max(rightMax[n - i - 1], height[n - 1 - i]);
        }

        for (int i = 0; i < n; i++) {
            int temp = Math.min(leftMax[i], rightMax[i]);
            res += temp > height[i] ? temp - height[i] : 0;
        }

        return res;
    }
}
