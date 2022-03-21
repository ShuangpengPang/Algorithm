package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0653TwoSumIVInputIsABST {

    public boolean findTarget0(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum > k) {
                j--;
            } else if (sum < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    public boolean findTarget1(TreeNode root, int k) {
        return preorder(root, k, new HashSet<>());
    }

    private boolean preorder(TreeNode node, int k, HashSet<Integer> set) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return preorder(node.left, k, set) || preorder(node.right, k, set);
    }

    public boolean findTarget2(TreeNode root, int k) {
        return dfs(root, new HashSet<>(), k);
    }

    private boolean dfs(TreeNode root, Set<Integer> set, int k) {
        if (root == null) {
            return false;
        }
        if (dfs(root.left, set, k)) {
            return true;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return dfs(root.right, set, k);
    }

    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null) {
            leftStack.addLast(node);
            node = node.left;
        }
        node = root;
        while (node != null) {
            rightStack.addLast(node);
            node = node.right;
        }
        TreeNode left = leftStack.peekLast(), right = rightStack.peekLast();
        while (left != right) {
            int sum = left.val + right.val;
            if (sum < k) {
                left = getLeft(leftStack);
            } else if (sum > k) {
                right = getRight(rightStack);
            } else {
                return true;
            }
        }
        return false;
    }

    private TreeNode getLeft(Deque<TreeNode> deque) {
        TreeNode node = deque.pollLast().right;
        while (node != null) {
            deque.addLast(node);
            node = node.left;
        }
        return deque.peekLast();
    }

    private TreeNode getRight(Deque<TreeNode> deque) {
        TreeNode node = deque.pollLast().left;
        while (node != null) {
            deque.addLast(node);
            node = node.right;
        }
        return deque.peekLast();
    }
}
