package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1022SumOfRootToLeafBinaryNumbers {

    private int answer = 0;

    public int sumRootToLeaf0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return answer;
    }

    private void dfs(TreeNode root, int sum) {
        sum = (sum << 1) + root.val;
        if (root.left == null && root.right == null) {
            answer += sum;
            return;
        }
        if (root.left != null) {
            dfs(root.left, sum);
        }
        if (root.right != null) {
            dfs(root.right, sum);
        }
    }

    public int sumRootToLeaf1(TreeNode root) {
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> valueQueue = new ArrayDeque<>();
        int sum = 0;
        int num = 0;
        TreeNode node = root;
        while (!nodeQueue.isEmpty() || node != null) {
            if (node != null) {
                if (node.left == null && node.right == null) {
                    sum += (num << 1) | node.val;
                    node = null;
                } else {
                    num = (num << 1) | node.val;
                    nodeQueue.offerLast(node);
                    valueQueue.offerLast(num);
                    node = node.left;
                }
            } else {
                node = nodeQueue.pollLast().right;
                num = valueQueue.pollLast();
            }
        }
        return sum;
    }

    public int sumRootToLeaf2(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        int ans = 0;
        int val = 0;
        TreeNode node = root;
        TreeNode prev = null;
        while (!deque.isEmpty() || node != null) {
            while (node != null) {
                deque.offerLast(node);
                val = (val << 1) | node.val;
                node = node.left;
            }
            node = deque.peekLast();
            if (node.right == null || node.right == prev) {
                deque.pollLast();
                if (node.left == null && node.right == null) {
                    ans += val;
                }
                if (!deque.isEmpty() && deque.peekLast().right == node) {
                    prev = node;
                }
                val >>= 1;
                node = null;
            } else {
                prev = node.right;
                node = node.right;
            }
        }
        return ans;
    }

    public int sumRootToLeaf3(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int ans = 0;
        int val = 0;
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                val = (val << 1) | root.val;
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if (root.right == null || root.right == prev) {
                if (root.left == null && root.right == null) {
                    ans += val;
                }
                stack.pop();
                val >>= 1;
                prev = root;
                root = null;
            } else {
                root = root.right;
            }
        }
        return ans;
    }

    public int sumRootToLeaf(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                ans += node.val;
            }
            if (node.left != null) {
                node.left.val += node.val << 1;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.val += node.val << 1;
                queue.offer(node.right);
            }
        }
        return ans;
    }
}
