package leetcode.linear.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * <p>
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * Created by tangmh on 17/9/28.
 */
public class PlusOne {
    public PlusOne() {
    }

    public static void main(String[] args) {
        int[] arr = {9};
        PlusOne tc = new PlusOne();
        System.out.println(Arrays.toString(tc.plusOne(arr)));
    }

    public int[] plusOne(int[] digits) {
        // 优化
        int i = digits.length - 1;
        while (i >= 0) {
            // 因为只有1位,当其小于9时,仅修改这一位即可
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // 否则 此位进位, 即为0, i--,查看下一位
            digits[i--] = 0;
        }
        // 若首位为0, 即为100..0
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }

    public int[] myplusOne(int[] digits) {
        // 大数加法低配版
        int n = digits.length;

        List<Integer> res = new ArrayList<>();

        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int temp = digits[i] + carry;
            if (temp > 9) {
                carry = 1;
                temp = 0;
            } else {
                carry = 0;
            }
            res.add(temp);
            if (i == 0 && carry == 1) {
                res.add(carry);
                n++;
            }
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = res.get(n - 1 - i);

        return arr;
    }

}
