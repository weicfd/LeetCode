package leetcode.sort;

import leetcode.linear.linkedlist.ListNode;

import java.util.Arrays;

/**
 * Created by tangmh on 17/12/15.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

        // 6.1
//        int[] num1 = new int[]{1,2,5, 0, 0, 0};
//        int[] num2 = new int[]{3,4,6};
//        tc.merge(num1, 3, num2, num2.length);
//        System.out.println(Arrays.toString(num1));

        // 6.2
//        ListNode l1 = new ListNode(new int[]{1,2,5});
//        ListNode l2 = new ListNode(new int[]{3,4,6});
//        tc.mergeTwoLists(l1, l2).print();

        // 6.3
//        ListNode l3 = new ListNode(new int[]{7,8,9});
//        ListNode[] lists = new ListNode[3];
//        lists[0] = l1; lists[1] = l2; lists[2] = l3;
//        tc.mergeKLists(lists).print();

        // 6.4
//        tc.insertionSortList(new ListNode(new int[]{4,5,6,2,1,1,1,8})).print();

        // 6.5
//        tc.sortList(new ListNode(new int[]{4,5,6,2,1,1,1,8})).print();

        // 6.6
//        System.out.println(tc.firstMissingPositive(new int[]{1,2,0}));
//        System.out.println(tc.firstMissingPositive(new int[]{3,4,-1,1}));
//        System.out.println(tc.firstMissingPositive(new int[]{1,1}));

        // 6.7
        int[] nums = new int[]{0, 0, 0, 1, 2, 2, 0, 0, 2};
        tc.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * <p>
     * Note:
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
     * from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (k < m + n) {
            if (i == m) {
                nums[k] = nums2[j];
                j++;
            } else if (j == n) {
                nums[k] = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                nums[k] = nums1[i];
                i++;
            } else {
                nums[k] = nums2[j];
                j++;
            }
            k++;
        }
        for (int l = 0; l < m + n; l++) {
            nums1[l] = nums[l];
        }
    }

    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(0);
//        ListNode i = l1, j = l2, k = dummy;
//        while (i != null || j != null) {
//            if (i == null) {
//                k.next = j;
//                break;
//            } else if (j == null) {
//                k.next = i;
//                break;
//            } else if (i.val < j.val) {
//                k.next = new ListNode(i.val);
//                k = k.next;
//                i = i.next;
//            } else {
//                k.next = new ListNode(j.val);
//                k = k.next;
//                j = j.next;
//            }
//        }
//        return dummy.next;

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode temp;
        if (l1.val < l2.val) {
            temp = l1;
            temp.next = mergeTwoLists(l1.next, l2);
        } else {
            temp = l2;
            temp.next = mergeTwoLists(l1, l2.next);
        }
        return temp;
    }

    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // TLE了
//        for (ListNode list:
//             lists) {
//            temp = mergeTwoLists(list, temp);
//        }

        return partition(lists, 0, lists.length - 1);
    }

    private ListNode partition(ListNode[] lists, int from, int to) {
        if (from > to) return null;
        if (from == to) return lists[from];
        int mid = (from + to) / 2;
        return mergeTwoLists(partition(lists, from, mid), partition(lists, mid + 1, to));
    }

    /**
     * Sort a linked list using insertion sort.
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode next = head, cur;

        while (next != null) {
            ListNode swap = next;
            next = next.next;

            cur = dummy;
            while (cur != null) {
                if (cur.next == null) {
                    cur.next = swap;
                    swap.next = null;
                    break;
                } else if (cur.next.val > swap.val) {
                    swap.next = cur.next;
                    cur.next = swap;
                    break;
                }
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 复用merge代码,归并排序
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l2 = slow.next;
        slow.next = null;

        ListNode l1 = sortList(head);
        l2 = sortList(l2);
        return mergeTwoLists(l1, l2);
    }

    /**
     * Given an unsorted integer array, find the first missing positive integer.
     * <p>
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * <p>
     * Your algorithm should run in O(n) time and uses constant space.
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        // 桶排序
        // 这里要注意的一点: 换后要再次检查当前节点,因为此时是换后未检查的节点
        // 还有一点 注意有[1,1]的情况,小心TLE
        for (int i = 0; i < nums.length; ) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                if (nums[i] == i + 1) i++;
                else {
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            } else i++;
        }

        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return i + 1;
    }

    /**
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * <p>
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * <p>
     * Note:
     * You are not suppose to use the library's sort function for this problem.
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        // 借鉴快排中的partition思想
        part(nums, 0);
        part(nums, 1);
    }

    private void part(int[] nums, int pivot) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] <= pivot) i++;
            while (i < j && nums[j] > pivot) j--;
            if (i != j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }
}
