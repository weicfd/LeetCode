package leetcode.linear.array;

import java.util.Arrays;

/**
 * Leetcode 04 : https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * Created by tangmh on 17/9/21.
 */
public class FindMedianSortedArrays {
    public FindMedianSortedArrays() {
    }

    public static void main(String[] args) {
        FindMedianSortedArrays solution = new FindMedianSortedArrays();

        int[] num1 = {1};
        int[] num2 = {1};
        double median = solution.findMedianSortedArrays(num1, num2);
        System.out.println(median);
    }

    public double findMedianSortedArrays(int[] num1, int[] num2) {
        int len1 = num1.length, len2 = num2.length;
        int sum = len1 + len2;
        if (sum % 2 == 0) {
            return (find_kth(num1, num2, len1, len2, sum / 2) + find_kth(num1, num2, len1, len2, sum / 2 + 1)) / 2.0;
        } else {
            return find_kth(num1, num2, len1, len2, sum / 2 + 1);
        }
    }

    private double find_kth(int[] num1, int[] num2, int a, int b, int k) {
        if (a > b) return find_kth(num2, num1, b, a, k);
        if (a == 0) return num2[k - 1];
        if (k == 1) return Math.min(num1[0], num2[0]);

        int ia = Math.min(k / 2, a), ib = k - ia;
        if (num1[ia - 1] < num2[ib - 1]) {
            return find_kth(Arrays.copyOfRange(num1, ia, num1.length), num2, a - ia, b, k - ia);
        } else if (num1[ia - 1] > num2[ib - 1]) {
            return find_kth(num1, Arrays.copyOfRange(num2, ib, num2.length), a, b - ib, k - ib);
        } else {
            return num1[ia - 1];
        }

    }


}
