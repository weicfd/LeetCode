package leetcode.linear.linkedlist;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * <p>
 * Created by tangmh on 17/11/25.
 */
public class CopyRandomList {
    public RandomListNode copyRandomList(RandomListNode head) {
        // 链表的深拷贝
        // 必须修改原始的链表
        // 记得恢复!

        RandomListNode cur1 = head, cur2, dummy = new RandomListNode(0);
        while (cur1 != null) {
            cur2 = new RandomListNode(cur1.label);
            if (cur1 == head) {
                dummy.next = cur2;
            }
            cur2.next = cur1.next;
            cur1.next = cur2;
            cur1 = cur2.next;
        }

        cur1 = head;
        while (cur1 != null) {
            if (cur1.random != null) cur1.next.random = cur1.random.next;
            cur1 = cur1.next.next;
        }

        cur1 = head;
        while (cur1 != null) {
            cur2 = cur1.next;
            cur1.next = cur2.next;
            cur1 = cur2.next;
            if (cur1 != null) cur2.next = cur1.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        RandomListNode d = new RandomListNode(4);

        a.next = b;
        a.random = c;
        b.next = c;
        b.random = null;
        c.next = d;
        c.random = null;
        d.next = null;
        d.random = b;

        print(a);
        RandomListNode res = new CopyRandomList().copyRandomList(a);

        print(a);
        print(res);
    }

    public static void print(RandomListNode a) {
        RandomListNode l = a;
        while (l != null) {
            if (l.next == null) {
                System.out.println(String.format("Label %d, Random %s", l.label, l.random == null ? "null" : l.random.label));
                return;
            }
            System.out.print(String.format("Label %d, Random %s ->", l.label, l.random == null ? "null" : l.random.label));
            l = l.next;
        }
    }
}
