package leetcode.linear.linkedlist;

/**
 * Created by tangmh on 17/11/18.
 */
public class RemoveDuplicatesfromSortedList {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3};
        RemoveDuplicatesfromSortedList tc = new RemoveDuplicatesfromSortedList();
        tc.deleteDuplicates(new ListNode(arr)).print();
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head, pre = head;
        int temp = cur.val;
        while (cur != null) {
            if (temp != cur.val) {
                temp = cur.val;
                pre.next = cur;
                pre = cur;
            }
            cur = cur.next;
        }

        if (pre.next != null) pre.next = null;

        return head;
    }
}
