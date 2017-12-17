package leetcode.linear.array;

/**
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * <p>
 * Created by tangmh on 17/11/16.
 */
public class SingleNumber2 {
    public static void main(String[] args) {
        System.out.println(new SingleNumber2().singleNumber(new int[]{1, 1, 1, 3, 3, 3, 2}));
    }

    public int singleNumber(int[] nums) {
        // 用二进制模拟三进制运算
        // 把出现3次的去掉,剩下的位凑起来
        int one = 0, two = 0, three = 0;
        for (int i :
                nums) {
            two = two | (one & i);
            one = one ^ i;
            // 每次都要把出现了3次的检查下,清零
            three = one & two;
            one = three ^ one;
            two = three ^ two;
        }

        return one | two;
    }
}
