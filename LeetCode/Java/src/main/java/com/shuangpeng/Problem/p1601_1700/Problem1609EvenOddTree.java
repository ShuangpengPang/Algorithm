package com.shuangpeng.Problem.p1601_1700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem1609EvenOddTree {

    public boolean isEvenOddTree0(TreeNode root) {
        return dfs(root, 0, new ArrayList<>());
    }

    private boolean dfs(TreeNode root, int level, List<Integer> list) {
        if (root == null) {
            return true;
        }
        if (((root.val & 1) ^ (level & 1)) != 1) {
            return false;
        }
        if (list.size() <= level) {
            list.add(root.val);
        } else if (((level & 1) == 0 && root.val > list.get(level)) || ((level & 1) == 1 && root.val < list.get(level))) {
            list.set(level, root.val);
        } else {
            return false;
        }
        if (!dfs(root.left, level + 1, list)) {
            return false;
        }
        return dfs(root.right, level + 1, list);
    }

    public boolean isEvenOddTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if ((root.val & 1) == 0) {
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean isEven = false;
        while (!q.isEmpty()) {
            int prev = isEven ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = q.size() - 1; i >= 0; --i) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    int val = node.left.val;
                    if ((isEven && val <= prev) || (!isEven && val >= prev) || (isEven && ((val & 1) == 0)) || (!isEven && ((val & 1) == 1))) {
                        return false;
                    }
                    prev = val;
                    q.offer(node.left);
                }
                if (node.right != null) {
                    int val = node.right.val;
                    if ((isEven && val <= prev) || (!isEven && val >= prev) || (isEven && ((val & 1) == 0)) || (!isEven && ((val & 1) == 1))) {
                        return false;
                    }
                    prev = node.right.val;
                    q.offer(node.right);
                }
            }
            isEven = !isEven;
        }
        return true;
    }

    public boolean isEvenOddTree(TreeNode root) {
        TreeNode[] queue = new TreeNode[100_001];
        int front = 0;
        int tail = 0;
        queue[tail++] = root;
        boolean odd = true;
        while (front < tail) {
            int size = tail - front;
            int lastVal = -1;

            for (int i = 0; i < size; i++) {
                TreeNode top = queue[front++];
                if (odd) {
                    if ((top.val & 1) == 0) {
                        return false;
                    }
                    if (lastVal != -1 && lastVal >= top.val) {
                        return false;
                    }
                } else {
                    if ((top.val & 1) == 1) {
                        return false;
                    }
                    if (lastVal != -1 && lastVal <= top.val) {
                        return false;
                    }
                }
                lastVal = top.val;
                if (top.left != null) {
                    queue[tail++] = top.left;
                }
                if (top.right != null) {
                    queue[tail++] = top.right;
                }
            }
            odd = !odd;
        }
        return true;
    }
}
