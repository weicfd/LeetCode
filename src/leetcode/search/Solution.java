package leetcode.search;

/**
 * Created by tangmh on 17/12/16.
 */
public class Solution {

    public static void main(String[] args) {
        Solution tc = new Solution();

        // 7.1
//        System.out.println(Arrays.toString(tc.searchRange(new int[]{5,7,7,8,8,10}, 8)));

        // 7.2
//        System.out.println(tc.searchInsert(new int[] {1,3,5,6}, 5));

        // 7.3
        System.out.println(tc.searchMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 0));
    }

    /**
     * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
     * <p>
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * <p>
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        return searchRange(nums, target, 0, nums.length - 1);
    }

    public int[] searchRange(int[] nums, int target, int start, int end) {
        if (start > end) return new int[]{-1, -1};
        else if (nums[start] == target && nums[end] == target) return new int[]{start, end};
        else if (start == end) return nums[start] == target ? new int[]{start, start} : new int[]{-1, -1};
        else {
            int mid = (start + end) / 2;
            int[] a = searchRange(nums, target, start, mid);
            int[] b = searchRange(nums, target, mid + 1, end);

            int[] res = new int[2];
            res[0] = a[0] > -1 ? a[0] : (a[1] > -1 ? a[1] : (b[0] > -1 ? b[0] : (b[1] > -1 ? b[1] : -1)));
            res[1] = b[1] > -1 ? b[1] : (b[0] > -1 ? b[0] : (a[1] > -1 ? a[1] : (a[0] > -1 ? a[0] : -1)));
            return res;
        }
    }

    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * <p>
     * You may assume no duplicates in the array.
     * <p>
     * Example 1:
     * <p>
     * Input: [1,3,5,6], 5
     * Output: 2
     * Example 2:
     * <p>
     * Input: [1,3,5,6], 2
     * Output: 1
     * Example 3:
     * <p>
     * Input: [1,3,5,6], 7
     * Output: 4
     * Example 1:
     * <p>
     * Input: [1,3,5,6], 0
     * Output: 0
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * <p>
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * For example,
     * <p>
     * Consider the following matrix:
     * <p>
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * Given target = 3, return true.
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length, line = m - 1;
        for (int i = 0; i < m - 1; i++) {
            if (matrix[i + 1][0] > target) {
                line = i;
                break;
            }
        }
        return search(matrix[line], target, 0, n - 1);
    }

    private boolean search(int[] nums, int target, int start, int end) {
        if (start > end) return false;
        else if (start == end) return nums[start] == target;
        else {
            int mid = (start + end) / 2;
            if (nums[mid] == target) return true;
            else if (nums[mid] > target) return search(nums, target, start, mid - 1);
            else return search(nums, target, mid + 1, end);
        }
    }


}
