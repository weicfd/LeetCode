package leetcode.linear.linkedlist;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * <p>
 * Created by tangmh on 17/11/21.
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 设计2个指针p,q, 让p先走n步
        // 注意边界情况 头结点,尾节点,空节点等
        ListNode p = head, q = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }

        if (p == null) return head.next;

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;

        return head;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd tc = new RemoveNthFromEnd();
        tc.removeNthFromEnd(new ListNode(new int[]{1, 2, 3, 4, 5}), 5).print();
    }
}
