package leetcode.linear.array;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Note: Given n will be a positive integer.
 * Created by tangmh on 17/9/29.
 */
public class ClimbStairs {
    // 斐波那契数列 f(n) = f(n-1) + f(n-2)
    // 递归 或 斐波那契数列数学公式


    public static void main(String[] args) {
        ClimbStairs tc = new ClimbStairs();
        System.out.println(tc.climbStairs(4));
    }

    public int climbStairs(int n) {
        if (n == 0) return 0;
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int temp = a;
            a = b;
            b = a + temp;
        }
        return b;
    }
}
