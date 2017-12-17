package leetcode.linear.array;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * Created by tangmh on 17/9/28.
 */
public class RotateImage {
    public RotateImage() {

    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotateImage tc = new RotateImage();
        tc.rotate(matrix);
        for (int[] line : matrix
                ) {
            System.out.println(Arrays.toString(line));
        }
    }

    public void rotate(int[][] matrix) {
        // 先对角线翻转
        // 再垂直翻转
        int n = matrix.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;

            }
        }
    }
}
