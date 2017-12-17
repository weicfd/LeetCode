package leetcode.linear.linkedlist;

/**
 * Created by tangmh on 17/11/26.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        // 检测是否有环
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        // 检测到环后再设置一个从头开始的指针,他和slow指针一定会在起点处相遇(数学证明)
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow1 = head;
                while (slow != slow1) {
                    slow = slow.next;
                    slow1 = slow1.next;
                }
                return slow1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListCycle tc = new LinkedListCycle();
        ListNode li = new ListNode(new int[]{1, 2, 3, 4, 5});
        li.next.next.next.next.next = li.next.next;
//        System.out.println(tc.hasCycle(li));
        System.out.println(tc.detectCycle(li).val);
    }
}
