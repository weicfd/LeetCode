package leetcode.linear.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note: Given n will be between 1 and 9 inclusive.
 * Created by tangmh on 17/9/26.
 */
public class GetPermutation {
    public GetPermutation() {
    }

    public static void main(String[] args) {
        GetPermutation tc = new GetPermutation();
        System.out.println(tc.getPermutation(4, 5));
    }

    private int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n--;
        }
        return res;
    }

    public String getPermutation(int n, int k) {
        // 康托编码 康托展开的逆运算
        String arr = new String("");
        Map<Integer, Integer> book = new HashMap<>();
        int a = k - 1;
        for (int i = 0; i < n; i++) {
            int fac = factorial(n - 1 - i);
            int temp = a / fac;
            a = a % fac;
            int bit = 1;
            for (int j = 1; j <= n; j++) {
                if (book.containsKey(j)) continue;
                if (temp == 0) {
                    bit = j;
                    break;
                }
                temp--;
            }
            if (bit > n) System.err.println("..");
            book.put(bit, 1);
            arr = arr + bit;
        }

        return arr;
    }

    public String getPermutation_enum(int n, int k) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        for (int i = 0; i < k - 1; i++) {
            nextPermutation.nextPermutation(arr);
        }
        return Arrays.toString(arr);
    }
}
