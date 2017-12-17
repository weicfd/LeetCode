package leetcode.linear.linkedlist;

/**
 * Created by tangmh on 17/11/18.
 */
public class PartitionList {
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 5, 2};
        PartitionList tc = new PartitionList();
        tc.partition(new ListNode(arr), 3).print();
    }

    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);

        ListNode cur = head, cur1 = head1, cur2 = head2;

        while (cur != null) {
            if (cur.val < x) {
                cur1.next = cur;
                cur1 = cur;
            } else {
                cur2.next = cur;
                cur2 = cur;
            }
            cur = cur.next;
        }

        cur1.next = head2.next;
        cur2.next = null;
        return head1.next;
    }
}
