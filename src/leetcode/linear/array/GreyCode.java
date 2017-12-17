package leetcode.linear.array;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * <p>
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * <p>
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * <p>
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * <p>
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * <p>
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * <p>
 * <p>
 * Created by tangmh on 17/9/29.
 */
public class GreyCode {
    public static void main(String[] args) {
        GreyCode tc = new GreyCode();
        System.out.println(tc.grayCode(2));
    }

    public List<Integer> grayCode(int n) {
        // 迭代 - strategy: reflex and prefix
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            int high = 1 << i;
            int size = list.size();
            for (int j = 0; j < size; j++) {
                list.add(high | list.get(size - j - 1));
            }
        }
        return list;
    }

    public List<Integer> grayCode1(int n) {
        // 数学公式
        int size = 1 << n;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(i ^ (i / 2));
        }
        return list;
    }
}
