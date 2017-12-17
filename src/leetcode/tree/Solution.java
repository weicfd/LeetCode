package leetcode.tree;

import java.util.*;

/**
 * Created by tangmh on 17/12/4.
 */
public class Solution {
    public static void main(String[] args) {
        Solution tc = new Solution();

//        TreeNode root = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.right.left = new TreeNode(3);

        // 5.1.1
//        System.out.println(tc.preorderTraversal(root));

//        5.1.2
//        System.out.println(tc.inorderTraversal(new TreeNode(new Integer[]{2,1})));

        // 5.1.3
//        System.out.println(tc.postorderTraversal(root));

        // 5.1.4
//        System.out.println(tc.levelOrder(new TreeNode(new Integer[]{3,9,20,null,null,15,7})));
//        System.out.println(tc.levelOrder(new TreeNode(new Integer[]{2,null,3,null,4,null,5,null,6})));

        // 5.1.5
//        System.out.println(tc.levelOrderBottom(new TreeNode(new Integer[]{3,9,20,null,null,15,7})));

        // 5.1.6
//        System.out.println(tc.zigzagLevelOrder(new TreeNode(new Integer[]{3,9,20,null,null,15,7})));

        // 5.1.7
//        TreeNode tree = new TreeNode(new Integer[]{1,2});
//        tc.recoverTree(tree);
//        System.out.println(tc.inorderTraversal(tree));

        // 5.1.8
//        System.out.println(tc.isSameTree(root, root));

        // 5.1.9
//        System.out.println(tc.isSymmetric(new TreeNode(new Integer[]{1,2,2,3,4,4,3})));
//        System.out.println(tc.isSymmetric(new TreeNode(new Integer[]{1,2,2,null,3,null,3})));

        // 5.1.10
//        System.out.println(tc.isBalanced(new TreeNode(new Integer[]{2,null,3,null,4,null,5,null,6})));

        // 5.1.11
//        TreeNode tree = new TreeNode(new Integer[]{1,2,5,3,4,6});
//        System.out.println(tc.inorderTraversal(tree));
//        tc.flatten(tree);
//        System.out.println(tc.inorderTraversal(tree));


        // 5.1.12

        // 5.2.1
//        System.out.println(tc.preorderTraversal(tc.buildTree(new int[]{1,2,4,5,3,6,7}, new int[]{4,2,5,1,6,3,7})));

        // 5.2.2
//        System.out.println(tc.preorderTraversal(tc.buildTree2(new int[]{3,2,4,1,6,5,7}, new int[]{3,4,2,6,7,5,1})));

        // 5.3.1
//        System.out.println(tc.numTrees(3));
        // 5.3.2
//        List<TreeNode> list = tc.generateTrees(4);
//        for (TreeNode tree :
//                list) {
//            prettyPrintTree(tree);
//        }

        // 5.3.3
//        System.out.println(tc.isValidBST(new TreeNode(new Integer[]{2,1,3})));
//        System.out.println(tc.isValidBST(new TreeNode(new Integer[]{10,5,15,null,null,6,20})));
//        System.out.println(tc.isValidBST(new TreeNode(new Integer[]{2147483647})));

        // 5.3.4
//        prettyPrintTree(tc.sortedArrayToBST(new int[]{-10,-3,0,5,9}));
        // 5.3.5
//        prettyPrintTree(tc.sortedListToBST(new ListNode(new int[]{-10,-3,0,5,9})));

        // 5.4.1 / 2
//        System.out.println(tc.minDepth(new TreeNode(new Integer[]{1,2})));

        // 5.4.3
//        System.out.println(tc.hasPathSum(new TreeNode(new Integer[]{5,4,8,11,13,4,7,2,null, null, null,1}), 22));

        // 5 4.4
//        System.out.println(tc.pathSum(new TreeNode(new Integer[]{5,4,8,11,null,13,4,7,2, 5,1}), 22));

        // 5.4.5
//        System.out.println(tc.maxPathSum(new TreeNode(new Integer[] {1,2,3})));
//        System.out.println(tc.maxPathSum(new TreeNode(new Integer[] {-3})));
        // 5.4.6

        // 5.4.7
        System.out.println(tc.sumNumbers(new TreeNode(new Integer[]{1, 2, 3})));
    }

    /**
     * 打印树
     *
     * @param root
     * @return
     */
    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    /**
     * 画树
     *
     * @param node
     * @param prefix
     * @param isLeft
     */
    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }

    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree [1,null,2,3],
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,2,3].
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // 1 全递归,非常的浪费空间, 可以将list传参来优化,此处省略
//        List<Integer> list = new LinkedList<>();
//        if (root == null) return list;
//        list.add(root.val);
//        list.addAll(preorderTraversal(root.left));
//        list.addAll(preorderTraversal(root.right));
//        return list;

        // 2 用栈来实现
//        if (root == null) return new LinkedList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        List<Integer> list = new LinkedList<>();
//        stack.push(root);
//
//        while (!stack.empty()) {
//            TreeNode cur = stack.pop();
//            list.add(cur.val);
//            if (cur.right != null) stack.push(cur.right);
//            if (cur.left != null) stack.push(cur.left);
//        }
//
//        return list;

        // 3 Morris Traversal
        TreeNode cur = root;
        List<Integer> list = new LinkedList<>();

        // while没有结束
        while (cur != null) {

            // 如果当前节点没有左后代
            if (cur.left == null) {
                // 访问该节点
                list.add(cur.val);
                // 访问右节点
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                // 找到左后代的最右节点
                while (pre.right != null && pre.right != cur) pre = pre.right;
                if (pre.right != cur) {
                    // 先访问该节点
                    list.add(cur.val);
                    // 指向当前节点
                    pre.right = cur;
                    // 转向左后代节点
                    cur = cur.left;
                } else {
                    // 说明是访问过的节点,由前驱回到了这里
                    // 删除前驱,恢复原状
                    pre.right = null;
                    // 至此左孩子已访问完毕
                    // 访问右后代
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree [1,null,2,3],
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,3,2].
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

//         1 全递归,非常的浪费空间, 可以将list传参来优化,此处省略
//        List<Integer> list = new LinkedList<>();
//        if (root == null) return list;
//        list.addAll(preorderTraversal(root.left));
//        list.add(root.val);
//        list.addAll(preorderTraversal(root.right));
//        return list;
//

        // 2 用栈来实现
        if (root == null) return new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;

        // 3 Morris Traversal
//        TreeNode cur = root;
//        List<Integer> list = new LinkedList<>();
//
//        while (cur != null) {
//            if (cur.left == null) {
//                list.add(cur.val);
//                cur = cur.right;
//            } else {
//                TreeNode pre = cur.left;
//                while (pre.right != null && pre.right != cur) pre = pre.right;
//                if (pre.right != cur) {
//                    pre.right = cur;
//                    cur = cur.left;
//                } else {
//                    pre.right = null;
//                    list.add(cur.val);
//                    cur = cur.right;
//                }
//            }
//        }
//        return list;

    }

    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [3,2,1].
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        //         1 全递归,非常的浪费空间, 可以将list传参来优化,此处省略
//        List<Integer> list = new LinkedList<>();
//        if (root == null) return list;
//        list.addAll(preorderTraversal(root.left));
//        list.addAll(preorderTraversal(root.right));
//        list.add(root.val);
//        return list;

        // 2 用栈来实现
        if (root == null) return new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        TreeNode cur = root.left, pre = null;
        stack.push(root);

        while (!stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            pre = null;
            while (!stack.empty()) {
                cur = stack.peek();
                if (cur.right == pre || cur.right == null) {
                    // 已访问过左\右孩子
                    list.add(cur.val);
                    pre = cur;
                    stack.pop();
                } else {
                    // 转向右孩子
                    cur = cur.right;
                    break;
                }
            }
        }
        return list;

        // Morris 版本...需要逆向输出... 告辞

    }

    /**
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its level order traversal as:
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 层次序遍历
        Queue<TreeNode> now = new LinkedList<>(), next = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        now.add(root);
        while (!now.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            while (!now.isEmpty()) {
                TreeNode temp = now.poll();
                line.add(temp.val);
                if (temp.left != null) next.add(temp.left);
                if (temp.right != null) next.add(temp.right);
            }
            res.add(line);
            now = next;
            next = new LinkedList<>();
        }

        return res;
    }

    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> now = new LinkedList<>(), next = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        now.add(root);
        while (!now.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            while (!now.isEmpty()) {
                TreeNode temp = now.poll();
                line.add(temp.val);
                if (temp.left != null) next.add(temp.left);
                if (temp.right != null) next.add(temp.right);
            }
            res.add(0, line);
            now = next;
            next = new LinkedList<>();
        }

        return res;
    }

    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its zigzag level order traversal as:
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> now = new LinkedList<>(), next = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        now.add(root);
        int i = 1;
        while (!now.isEmpty()) {
            List<Integer> line = new ArrayList<>();
            while (!now.isEmpty()) {
                TreeNode temp = now.poll();
                if (i % 2 == 0) line.add(0, temp.val);
                else line.add(temp.val);
                if (temp.left != null) next.add(temp.left);
                if (temp.right != null) next.add(temp.right);
            }
            res.add(line);
            now = next;
            next = new LinkedList<>();
            i++;
        }

        return res;
    }

    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * <p>
     * Recover the tree without changing its structure.
     * <p>
     * Note:
     * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        // 中序遍历, 找逆序的element
        // 如果要达到O(1)的话只需要把中序遍历替换成Morris遍历即可
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root, swap1 = null, swap2 = null, pre = null;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();

                // determine
                if (pre != null && pre.val > cur.val) {
                    if (swap1 == null) swap1 = pre;
                    swap2 = cur;
                }
                pre = cur;

                cur = cur.right;
            }
        }
//        System.out.println("swap " + swap1.val + " and " + swap2.val);

        int temp = swap1.val;
        swap1.val = swap2.val;
        swap2.val = temp;

        return;
    }

    /**
     * Given two binary trees, write a function to check if they are the same or not.
     * <p>
     * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input:
     * 1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * Output: true
     * Example 2:
     * <p>
     * Input:
     * 1         1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * Output: false
     * Example 3:
     * <p>
     * Input:
     * 1         1
     * / \       / \
     * 2   1     1   2
     * <p>
     * [1,2,1],   [1,1,2]
     * <p>
     * Output: false
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 递归真是爽到
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        else return false;
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * <p>
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        else return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);

    }

    /**
     * Given a binary tree, determine if it is height-balanced.
     * <p>
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return heightBalanced(root) >= 0;
    }

    private int heightBalanced(TreeNode root) {
        if (root == null) return 0;
        int val_left = heightBalanced(root.left);
        int val_right = heightBalanced(root.right);
        if (val_left < 0 || val_right < 0 || Math.abs(val_left - val_right) > 1) return -1;
        return Math.max(val_left, val_right) + 1;
    }

    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * <p>
     * For example,
     * Given
     * <p>
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * The flattened tree should look like:
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        // Morris中序遍历改下即可
//        TreeNode cur = root;
//
//        while (cur != null) {
//            // 如果当前节点没有左后代
//            if (cur.left == null) {
//                // 访问右节点
//                cur = cur.right;
//            } else {
//                TreeNode pre = cur.left;
//                // 找到左后代的最右节点
//                while (pre.right != null && pre.right != cur) pre = pre.right;
//                if (pre.right != cur) {
//                    // 指向当前节点
//                    pre.right = cur;
//                    // 转向左后代节点
//                    cur = cur.left;
//                } else {
//                    // 说明是访问过的节点,由前驱回到了这里
//                    // 节点修剪
//                    TreeNode temp = cur.right;
//                    cur.right = cur.left;
//                    pre.right = temp;
//                    cur.left = null;
//                    // 至此左孩子已访问完毕
//                    // 访问右后代
//                    cur = temp;
//                }
//            }
//        }

        // 还可以用递归
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        if (root.left == null) return;
        TreeNode tail = root.left;
        while (tail.right != null) tail = tail.right;
        tail.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    /**
     * Follow up for problem ”Populating Next Right Pointers in Each Node”.
     * What if the given tree could be any binary tree? Would your previous solution still work? Note: You may only use constant extra space.
     * For example, Given the following binary tree,
     * 1
     * /  \
     * 2    3
     * / \    \
     * 4   5    7
     * After calling your function, the tree should look like:
     * <p>
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \    \
     * 4-> 5 -> 7 -> NULL
     *
     * @param root
     */
    public void connect2(TreeLinkNode root) {
//        TreeLinkNode prev, last = null,next = root;
//
//        while (next != null){
//            prev = next;
//            next = null;
//            last = null;
//            while (prev != null) {
//                if (prev.left != null) {
//                    if (next == null) next = prev.left;
//                    if (last != null) last.next = prev.left;
//                    last = prev.left;
//                }
//                if (prev.right != null) {
//                    if (next == null) next = prev.right;
//                    if (last != null) last.next = prev.right;
//                    last = prev.right;
//                }
//                prev = prev.next;
//            }
//        }
        // 用一个dummyHead 可以把代码改造的更加简洁

        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode travel = dummy;

        while (root != null) {
            if (root.left != null) {
                travel.next = root.left;
                travel = travel.next;
            }
            if (root.right != null) {
                travel.next = root.right;
                travel = travel.next;
            }
            root = root.next;
            if (root == null) {
                root = dummy.next;
                travel = dummy;
                dummy.next = null;
            }
        }
    }

    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * <p>
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 给出先序遍历和中序遍历 构建树
        // 给一个经典的例子,进行研究,寻找头绪
        // 规律1 preorder第一个是root
        // 规律2 inorder中root左的都在左子树中root右的都在右子树
        // ! 考虑递归法,有点快排的partition的感觉
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, map, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> inorder_map, int pre_s, int pre_e, int in_s, int in_e) {
        if (pre_e < 0) return null;
        int value = preorder[pre_s], k = inorder_map.get(value), a = k - in_s, b = in_e - k;
        TreeNode root = new TreeNode(value);
        if (pre_s == pre_e) return root;
        root.left = (a > 0) ? buildTree(preorder, inorder_map, pre_s + 1, pre_s + a, in_s, k - 1) : null;
        root.right = (b > 0) ? buildTree(preorder, inorder_map, pre_e - b + 1, pre_e, k + 1, in_e) : null;
        return root;
    }

    /**
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * <p>
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        // 上一题改一改
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree2(postorder, map, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree2(int[] postorder, Map<Integer, Integer> inorder_map, int post_s, int post_e, int in_s, int in_e) {
        if (post_e < 0) return null;
        int value = postorder[post_e], k = inorder_map.get(value), a = k - in_s, b = in_e - k;
        TreeNode root = new TreeNode(value);
        if (post_s == post_e) return root;
        root.left = (a > 0) ? buildTree2(postorder, inorder_map, post_s, post_s + a - 1, in_s, k - 1) : null;
        root.right = (b > 0) ? buildTree2(postorder, inorder_map, post_s + a, post_e - 1, k + 1, in_e) : null;
        return root;
    }

    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     * <p>
     * For example,
     * Given n = 3, there are a total of 5 unique BST's.
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // 动态规划
        // 1到n各个数作为根节点,左右子树可以计算的,且是子问题,加起来
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int ans = 0;
            for (int j = 1; j <= i; j++) {
                ans += dp[j - 1] * dp[i - j];
            }
            dp[i] = ans;
        }
        return dp[n];
    }

    /**
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     * <p>
     * For example,
     * Given n = 3, your program should return all 5 unique BST's shown below.
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        // 上一题改一改
        int[] dp = new int[n + 1];
        List<TreeNode>[] dp_list = new List[n + 1];

        dp[0] = 1;
        dp_list[0] = new LinkedList<>();
        if (n == 0) return dp_list[0];
        dp_list[0].add(null);

        for (int i = 1; i <= n; i++) {
            int ans = 0;
            dp_list[i] = new LinkedList<>();
            for (int j = 1; j <= i; j++) {
                ans += dp[j - 1] * dp[i - j];
                for (int a = 0; a < dp[j - 1]; a++) {
                    for (int b = 0; b < dp[i - j]; b++) {
                        TreeNode temp = new TreeNode(j);
                        temp.left = dp_list[j - 1].get(a);
                        temp.right = treeCopy(dp_list[i - j].get(b), j);
                        dp_list[i].add(temp);
                    }
                }
            }
            dp[i] = ans;
        }
        return dp_list[n];
    }

    private TreeNode treeCopy(TreeNode root, int offset) {
        if (root == null) return null;
        TreeNode cp = new TreeNode(root.val + offset);
        cp.left = treeCopy(root.left, offset);
        cp.right = treeCopy(root.right, offset);
        return cp;
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * <p>
     * Assume a BST is defined as follows:
     * <p>
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * Example 1:
     * 2
     * / \
     * 1   3
     * Binary tree [2,1,3], return true.
     * Example 2:
     * 1
     * / \
     * 2   3
     * Binary tree [1,2,3], return false.
     */
    public boolean isValidBST(TreeNode root) {
        // 这题可以用中序遍历解
        // 递归解法
        return isValidWithBound(root, null, null);
    }

    private boolean isValidWithBound(TreeNode root, Integer upper, Integer lower) {
        if (root == null) return true;
        if (upper != null && root.val >= upper) return false;
        if (lower != null && root.val <= lower) return false;
        return isValidWithBound(root.left, root.val, lower) && isValidWithBound(root.right, upper, root.val);
    }

    /**
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     * <p>
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     * <p>
     * <p>
     * Example:
     * <p>
     * Given the sorted array: [-10,-3,0,5,9],
     * <p>
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int from, int to) {
        if (from > to) return null;
        int mid = (from + to) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, from, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, to);
//        if (!isValidBST(root)) System.err.println("not valid bst for " + root.val);
//        if (!isBalanced(root)) System.err.println("not balanced for " +root.val );
        return root;
    }

    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     * <p>
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     * <p>
     * <p>
     * Example:
     * <p>
     * Given the sorted linked list: [-10,-3,0,5,9],
     * <p>
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        // Solution1 因为是链表, 所以在下有一个想法,就是用两个指针来遍历,时间复杂度应该是nlgn
        if (head == null) return null;
        return sortedListToBST(head, null);

        // Solution2 自底向上的分治法
    }

    private TreeNode sortedListToBST(ListNode from, ListNode to) {
        // 居然可以诶,AC了惊了
        if (from.next == to) return new TreeNode(from.val);
        ListNode fast = from, slow = from;

        while (fast != to && fast.next != to) {
            fast = fast.next.next;
            slow = slow.next;
        }
//        System.err.println("fast " + (fast == null ? fast : fast.val));
//        System.err.println("slow " + slow.val);
        TreeNode root = new TreeNode(slow.val);
        if (from != slow) root.left = sortedListToBST(from, slow);
        if (slow != to && slow.next != to) root.right = sortedListToBST(slow.next, to);
        if (!isBalanced(root) || !isValidBST(root)) System.err.println("lol");
        return root;
    }

    /**
     * Given a binary tree, find its minimum depth.
     * <p>
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else if (root.left == null) return minDepth(root.right) + 1;
        else if (root.right == null) return minDepth(root.left) + 1;
        else return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * Given a binary tree, find its maximum depth.
     * <p>
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     * <p>
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


    /**
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * <p>
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * return
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                temp.add(root.val);
                res.add(temp);
            }
            return res;
        }

        if (root.left != null) res.addAll(pathSum(root.left, sum - root.val));
        if (root.right != null) res.addAll(pathSum(root.right, sum - root.val));

        for (List<Integer> item :
                res) {
            item.add(0, root.val);
        }
        return res;
    }

    public List<List<Integer>> pathSum_eg(TreeNode root, int sum) {
        // 一个漂亮的DFS实现,值得学习
        List<List<Integer>> result = new LinkedList<>();
        dfs(root, sum, new Stack<>(), result);
        return result;
    }

    void dfs(TreeNode root, int sum, Stack<Integer> temp, List<List<Integer>> result) {
        if (root == null) return;
        temp.push(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(temp));
        } else {
            dfs(root.left, sum - root.val, temp, result);
            dfs(root.right, sum - root.val, temp, result);
        }
        temp.pop();
    }

    /**
     * Given a binary tree, find the maximum path sum.
     * <p>
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
     * the parent-child connections. The path must contain at least one node and does not need to go through the root.
     * <p>
     * For example:
     * Given the below binary tree,
     * <p>
     * 1
     * / \
     * 2   3
     * Return 6.
     *
     * @param root
     * @return
     */
    static int max_val;

    public int maxPathSum(TreeNode root) {
        // 参考最大连续子序列和的思路
        max_val = Integer.MIN_VALUE;
        return maxPathSum_dfs(root) > max_val ? maxPathSum_dfs(root) : max_val;
    }

    private int maxPathSum_dfs(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        int leftMax = maxPathSum_dfs(root.left), rightMax = maxPathSum_dfs(root.right);

        int max = root.val;
        if (leftMax > 0) max += leftMax;
        if (rightMax > 0) max += rightMax;
        if (max > max_val) max_val = max;

        int res = (leftMax > rightMax) ? leftMax : rightMax;
        if (res < 0) return root.val;
        res += root.val;
        return (res > root.val) ? res : root.val;
    }

    /**
     * Given a binary tree
     * <p>
     * struct TreeLinkNode {
     * TreeLinkNode *left;
     * TreeLinkNode *right;
     * TreeLinkNode *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * <p>
     * Initially, all next pointers are set to NULL.
     * <p>
     * Note:
     * <p>
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * For example,
     * Given the following perfect binary tree,
     * 1
     * /  \
     * 2    3
     * / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \  / \
     * 4->5->6->7 -> NULL
     *
     * @param root
     */
    public void connect(TreeLinkNode root) {
        // 只考虑完全二叉树
        if (root == null) return;
        connect(root, null);
    }

    private void connect(TreeLinkNode root, TreeLinkNode sibling) {
        if (root == null) return;
        root.next = sibling;
        connect(root.left, root.right);
        if (sibling != null) connect(root.right, sibling.left);
        else connect(root.right, null);
    }

    /**
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     * <p>
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * <p>
     * Find the total sum of all root-to-leaf numbers.
     * <p>
     * For example,
     * <p>
     * 1
     * / \
     * 2   3
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * <p>
     * Return the sum = 12 + 13 = 25.
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbers_dfs(root, 0);
    }

    private int sumNumbers_dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        int sum_new = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum_new;

        return sumNumbers_dfs(root.left, sum_new) + sumNumbers_dfs(root.right, sum_new);
    }
}
