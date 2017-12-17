package leetcode.tree;

/**
 * Created by tangmh on 17/11/16.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int[] arr) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i :
                arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        this.val = dummyHead.next.val;
        this.next = dummyHead.next.next;
    }


    public void print() {
        ListNode l = this;
        while (l != null) {
            if (l.next == null) {
                System.out.println(l.val);
                return;
            }
            System.out.print(l.val + "->");
            l = l.next;
        }
    }
}
