package leetcode.linear.array;

import java.util.Arrays;

/**
 * Created by tangmh on 17/9/29.
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
        SetMatrixZeroes tc = new SetMatrixZeroes();
        int[][] mat = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        tc.setZeroes(mat);
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    public void setZeroes(int[][] matrix) {
        // 空间复杂度O m+n, 可以优化到O 1 用矩阵的第一行/列代替new 的数组
        int m = matrix.length, n = matrix[0].length;

        int[] rbook = new int[m];
        int[] cbook = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rbook[i] = 1;
                    cbook[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rbook[i] == 1 || cbook[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
