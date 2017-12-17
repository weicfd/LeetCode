package leetcode.linear.linkedlist;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * <p>
 * Created by tangmh on 17/11/16.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{2, 4, 3});
        ListNode l2 = new ListNode(new int[]{5, 6, 4});

        AddTwoNumbers tc = new AddTwoNumbers();
        ListNode l3 = tc.addTwoNumbers(l1, l2);

        l3.print();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyhead = new ListNode(0);
        ListNode cur = dummyhead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int temp = 0;
            if (l1 == null) {
                temp = l2.val + carry;
                l2 = l2.next;
            } else if (l2 == null) {
                temp = l1.val + carry;
                l1 = l1.next;
            } else {
                temp = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }
            if (temp > 9) {
                temp %= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            cur.next = new ListNode(temp);
            cur = cur.next;
        }

        if (carry != 0) cur.next = new ListNode(carry);

        return dummyhead.next;
    }
}
