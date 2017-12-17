package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tangmh on 17/12/4.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null) {
                TreeNode nnode = new TreeNode(arr[i]);
                if (i % 2 == 1) queue.peek().left = nnode;
                else queue.poll().right = nnode;
                queue.add(nnode);
            }
        }

        this.val = root.val;
        this.left = root.left;
        this.right = root.right;
    }
}
