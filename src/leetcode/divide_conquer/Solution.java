package leetcode.divide_conquer;

/**
 * Created by tangmh on 18/1/7.
 */
public class Solution {
    /**
     * Implement pow(x, n).
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: 2.00000, 10
     * Output: 1024.00000
     * <p>
     * Example 2:
     * <p>
     * Input: 2.10000, 3
     * Output: 9.26100
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        // 利用数学: x^n = x^(n/2) * x^(n/2) * x^(n%2)
        // 不要忘记n为负的情况
        if (n < 0) {
            if (n == Integer.MIN_VALUE) n += 2; // +1 会出负数的奇偶问题
            return 1.0 / power(x, -n);
        } else return power(x, n);
    }

    private double power(double x, int n) {
        if (n == 1) return x;
        else if (n == 0) return 1;
        else {
//            return myPow(x, n/2) * myPow(x, n/2) * myPow(x, n%2); // 这里算了两次,十分的智障
            double v = myPow(x, n / 2);
            if (n % 2 == 1) return v * v * x;
            else return v * v;
        }
    }

    /**
     * #69
     * Implement int sqrt(int x).
     * <p>
     * Compute and return the square root of x.
     * <p>
     * x is guaranteed to be a non-negative integer.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: 4
     * Output: 2
     * Example 2:
     * <p>
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        // 二分
        if (x < 2) return x;
        int a = 1, b = x / 2;
        int mid = a;
        while (a < b) {
            mid = (a + b) / 2;
            if (x / mid == mid) return mid;
            else if (x / mid < mid) b = mid - 1;
            else a = mid + 1;
        }
        mid = (a + b) / 2;
        if (x / mid < mid) return mid - 1;
        else return mid;
    }

    public static void main(String[] args) {
        // 11.1
        Solution tc = new Solution();
//        System.out.println(tc.myPow(2.00000, 10));
//        System.out.println(tc.myPow(2.10000, 3));
//        System.out.println(tc.myPow(2.00000, -2147483648));

        // 11.2
        System.out.println(tc.mySqrt(4));
        System.out.println(tc.mySqrt(10));
    }

}
