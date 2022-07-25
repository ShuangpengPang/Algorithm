package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:（完全二叉树插入器）
 * @Date 2022/7/25 2:30 PM
 **/
public class Problem0919CompleteBinaryTreeInserter {

    class CBTInserter {

        private TreeNode root;
        private Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.peek();
                if (node.left == null) {
                    break;
                }
                if (node.right == null) {
                    queue.offer(node.left);
                    break;
                }
                queue.poll();
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        public int insert(int v) {
            TreeNode node = new TreeNode(v);
            queue.offer(node);
            TreeNode parent = queue.peek();
            if (parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
                queue.poll();
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
}

class Problem0919CompleteBinaryTreeInserter0 {

    class CBTInserter {
        TreeNode root;
        Deque<TreeNode> deque;
        public CBTInserter(TreeNode root) {
            this.root = root;
            deque = new LinkedList();
            Queue<TreeNode> queue = new LinkedList();
            queue.offer(root);

            // BFS to populate deque
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left == null || node.right == null)
                    deque.offerLast(node);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        public int insert(int v) {
            TreeNode node = deque.peekFirst();
            deque.offerLast(new TreeNode(v));
            if (node.left == null)
                node.left = deque.peekLast();
            else {
                node.right = deque.peekLast();
                deque.pollFirst();
            }

            return node.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}

class Problem0919CompleteBinaryTreeInserter1 {

    class CBTInserter {

        TreeNode root;
        Queue<TreeNode> q;

        public CBTInserter(TreeNode root) {
            this.root = root;
            q = new LinkedList<>();
            q.offer(root);
            while (q.peek().left != null && q.peek().right != null) {
                TreeNode node = q.poll();
                q.offer(node.left);
                q.offer(node.right);
            }
        }

        public int insert(int val) {
            TreeNode current = q.peek();
            TreeNode node = new TreeNode(val);
            if (current.left == null) {
                current.left = node;
            } else {
                current.right = node;
                q.poll();
                q.offer(current.left);
                q.offer(current.right);
            }
            return current.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}

class CBTInserter {

    int cnt;
    TreeNode root;

    public CBTInserter(TreeNode root) {
        cnt = 0;
        this.root = root;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            cnt++;
            TreeNode node = q.poll();
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    public int insert(int val) {
        cnt++;
        int bits = 31 - Integer.numberOfLeadingZeros(cnt);
        TreeNode node = root;
        for (int i = bits - 1; i > 0; i--) {
            if ((cnt & (1 << i)) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        TreeNode cur = new TreeNode(val);
        if ((cnt & 1) == 0) {
            node.left = cur;
        } else {
            node.right = cur;
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}









