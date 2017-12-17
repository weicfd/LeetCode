package leetcode.linear.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place, do not allocate extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * Created by tangmh on 17/9/25.
 */
public class NextPermutation {
    public NextPermutation() {
        // 下一个排列
        // 最小的排列: 正序,  交换最后一个顺序对?
        // 问题: 1. 如何找最后一个顺序对? 2. 如何交换? 3. 边界值

        // 下一个排列算法:
        // 1.倒着找第一个非升序值a
        // 2.在已扫描序列中找比a大的最小值b
        // 3.交换a和b
        // 4.倒序已扫描序列
    }

    public static void main(String[] args) {
        NextPermutation tc = new NextPermutation();
        int[] arr1 = {2, 3, 1, 3, 3};
        tc.nextPermutation(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {3, 2, 1};
        tc.nextPermutation(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {6, 8, 7, 4, 3, 2};
        tc.nextPermutation(arr3);
        System.out.println(Arrays.toString(arr3));

    }

    public void nextPermutation(int[] nums) {
        int a, b, ia = -1, ib;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                ia = i - 1;
                break;
            }
        }
        if (ia >= 0) {
            a = nums[ia];
            ib = ia + 1;
            b = nums[ib];
            for (int i = ia + 2; i < nums.length; i++) {
                if (nums[i] <= b && nums[i] > a) {
                    ib = i;
                    b = nums[ib];
                }
            }

            nums[ia] = b;
            nums[ib] = a;

//            System.out.println(String.format("Debug: a = %d, ia = %d, b = %d, ib = %d",a,ia,b,ib));
        }

        int lo = ia + 1, hi = nums.length - 1;
        while (lo < hi) {
            int temp = nums[lo];
            nums[lo++] = nums[hi];
            nums[hi--] = temp;
        }

    }

}
