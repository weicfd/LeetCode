package leetcode.linear.linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * <p>
 * <p>
 * Created by tangmh on 17/11/21.
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (cur.next != null) {
            ListNode p = cur.next;
            if (p.next == null) break;
            ListNode q = p.next;
            cur.next = q;
            p.next = q.next;
            q.next = p;

            cur = p;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        SwapPairs tc = new SwapPairs();
        tc.swapPairs(new ListNode(new int[]{1, 2, 3, 4})).print();
    }
}
