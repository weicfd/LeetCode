package leetcode.linear.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2 );
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * <p>
 * <p>
 * 优化:使用伪头结点和尾节点
 * Created by tangmh on 17/11/26.
 */
public class LRUCache {

    Map<Integer, DListNode> hash;
    DListNode head, tail;
    int capacity, num = 0;

    public LRUCache(int capacity) {
        hash = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (hash.containsKey(key)) {
            refresh(hash.get(key));
            return hash.get(key).val;
        } else return -1;
    }


    public void put(int key, int value) {
        if (hash.containsKey(key)) {
            DListNode a = hash.get(key);
            a.val = value;
            refresh(a);
        } else {
            insert(key, value);
        }
    }

    private void insert(int key, int value) {
        if (num == capacity) {
            delete(tail.key);
        }
        DListNode a = new DListNode(key, value);
        hash.put(key, a);
        if (head != null) {
            a.next = head;
            head.pre = a;
            head = a;
        } else {
            head = tail = a;
        }
        num++;
    }

    private void delete(int key) {
        DListNode dListNode = hash.get(key);
        hash.remove(key);
        num--;
        if (dListNode == head && dListNode == tail) {
            head = null;
            tail = null;
        } else if (dListNode == tail) {
            tail = tail.pre;
            tail.next = null;
        } else if (dListNode == head) {
            head = head.next;
            head.pre = null;
        } else {
            dListNode.pre.next = dListNode.next;
            dListNode.next.pre = dListNode.pre;
        }
    }

    private void refresh(DListNode dListNode) {
        if (dListNode != head) {
            if (dListNode == tail) {
                tail = tail.pre;
                tail.next = null;

            } else {
                dListNode.pre.next = dListNode.next;
                dListNode.next.pre = dListNode.pre;
            }
            dListNode.next = head;
            head.pre = dListNode;
            head = dListNode;
        }
    }

    private class DListNode {
        int val, key;
        DListNode pre, next;

        public DListNode(int key, int val) {
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }
    }

    public static void main(String[] args) {
        /**
         * Your LRUCache object will be instantiated and called as such:
         * LRUCache obj = new LRUCache(capacity);
         * int param_1 = obj.get(key);
         * obj.put(key,value);
         */
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));      // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));     // returns -1 (not found)
        System.out.println(cache.get(3));      // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
