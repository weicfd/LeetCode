package leetcode.linear.linkedlist;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * <p>
 * Only constant memory is allowed.
 * <p>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Created by tangmh on 17/11/21.
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode a = head, b = null, cur = dummy, head1 = dummy, tail = null;
        int i = 0;

        while (cur != null) {
            if (i == k) {
                b = cur;
                tail = cur.next;
                b.next = null;
                reverse(a);
                head1.next = b;
                a.next = tail;
                head1 = a;
                a = tail;
                cur = tail;
                i = 1;
            } else {
                cur = cur.next;
                i++;
            }
        }
        return dummy.next;
    }

    public void reverse(ListNode head) {
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
    }

    public static void main(String[] args) {
        ReverseKGroup tc = new ReverseKGroup();
        tc.reverseKGroup(new ListNode(new int[]{1, 2, 3, 4, 5}), 2).print();
    }

}
