package leetcode.linear.linkedlist;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * <p>
 * Created by tangmh on 17/11/26.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 找中点
        ListNode a = head, b = head.next;
        while (b != null && b.next != null) {
            a = a.next;
            b = b.next.next;
        }
        // 倒置后半部分
        b = reverse(a.next);
        a.next = null;
        a = head;
        // 合并
        ListNode cur = a;
        while (b != null) {
            a = a.next;
            cur.next = b;
            b = b.next;
            cur.next.next = a;
            cur = a;
        }
    }

    public ListNode reverse(ListNode head) {
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

    public static void main(String[] args) {
        ReorderList tc = new ReorderList();
        ListNode arr = new ListNode(new int[]{1, 2, 3});
        tc.reorderList(arr);
        arr.print();
    }
}
