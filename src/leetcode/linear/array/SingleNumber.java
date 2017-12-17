package leetcode.linear.array;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Created by tangmh on 17/11/16.
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {2, 2, 4};
        System.out.println(new SingleNumber().singleNumber(arr));
    }

    public int singleNumber(int[] nums) {
        // 亦或, A^A = 0,  0^A = A
        int x = 0;
        for (int i :
                nums) {
            x ^= i;
        }
        return x;
    }
}
