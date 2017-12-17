package leetcode.linear.linkedlist;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Created by tangmh on 17/11/17.
 */
public class ReverseLinkedList2 {
    public static void main(String[] args) {
        int[] arr = {1, 2};
        ListNode li = new ListNode(arr);

        ReverseLinkedList2 tc = new ReverseLinkedList2();
//        tc.reverseAll(li).print();
        tc.reverseBetween(li, 1, 2).print();

    }

    public ListNode reverseAll(ListNode head) {
        ListNode previous = null;
        ListNode cur = head;
        ListNode next = cur.next;

        while (next != null) {
            cur.next = previous;
            previous = cur;
            cur = next;
            next = next.next;
        }
        cur.next = previous;
        return cur;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 1. dummy head : 可以规避开头的一些特殊情况
        // 2. 头插法,逆转链表,可以规避尾部的一些特殊情况
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        for (int i = 0; i < m - 1; i++) {
            cur = cur.next;
        }
        ListNode head2 = cur;
        cur = cur.next;
        ListNode previous = cur;
        cur = cur.next;

        for (int i = m; i < n; i++) {
            previous.next = cur.next;
            cur.next = head2.next;
            head2.next = cur;
            cur = previous.next;
        }

        return dummyHead.next;
    }
}
