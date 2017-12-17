package leetcode.linear.linkedlist;

/**
 * Created by tangmh on 17/11/18.
 */
public class RemoveDuplicatesfromSortedList2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3};
        RemoveDuplicatesfromSortedList2 tc = new RemoveDuplicatesfromSortedList2();
        tc.deleteDuplicates(new ListNode(arr)).print();
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = head, pre = dummyHead;

        while (cur != null) {
            if ((cur.next != null && cur.val == cur.next.val)) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
