package leetcode.linear.array;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * <p>
 * Created by tangmh on 17/11/16.
 */
public class Candy {
    public static void main(String[] args) {
        Candy tc = new Candy();
        int[] arr = {12, 4, 3, 11, 34, 34, 1, 67};
        System.out.println(tc.candy(arr));
    }

    public int candy(int[] ratings) {
        // 两头的扫描
        // 只关心要求的条件
        int n = ratings.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);
        for (int i = 0; i < n - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                result[i + 1] = result[i] + 1;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                result[i - 1] = Math.max(result[i] + 1, result[i - 1]);
            }
        }

        int sum = 0;
        for (int i : result
                ) {
            sum += i;
        }
        return sum;
    }
}
