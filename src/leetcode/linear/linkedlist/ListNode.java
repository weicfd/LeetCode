package leetcode.linear.linkedlist;

/**
 * Created by tangmh on 17/11/16.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr) {
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
