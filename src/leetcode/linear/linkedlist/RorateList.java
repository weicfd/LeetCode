package leetcode.linear.linkedlist;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4->5->NULL and k = 2,
 * <p>
 * return 4->5->1->2->3->NULL.
 * <p>
 * Created by tangmh on 17/11/21.
 */
public class RorateList {

    public ListNode rotateRight(ListNode head, int k) {
        // 先扫一遍, 简化k, 连成环, 先按要求断开
        if (head == null) return head;
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        if (k >= len) k %= len;
        if (k == 0) return head;

        cur.next = head;
        cur = head;

        for (int i = 0; i < len - k - 1; i++) {
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }

    public static void main(String[] args) {
        RorateList tc = new RorateList();

        tc.rotateRight(new ListNode(new int[]{1, 2, 3}), 4).print();

    }
}
