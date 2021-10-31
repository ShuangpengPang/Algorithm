package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0230KthSmallestElementInABST {

    public int kthSmallest0(TreeNode root, int k) {
        if (root == null || k == 0) {
            return 0;
        }
        return innorderTraverse(root, k)[1];
    }

    public int[] innorderTraverse(TreeNode root, int k) {
        if (root == null) {
            return new int[]{0, Integer.MIN_VALUE};
        }
        int[] left = innorderTraverse(root.left, k);
        if (left[1] != Integer.MIN_VALUE) {
            return left;
        }
        if (k == left[0] + 1) {
            left[1] = root.val;
            return left;
        }
        int[] right = innorderTraverse(root.right, k - (left[0] + 1));
        return new int[]{left[0] + right[0] + 1, right[1]};
    }

    public int kthSmallest1(TreeNode root, int k) {
        if (root == null || k == 0) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (true) {
            while (root != null) {
                stack.offer(root);
                root = root.left;
            }
            TreeNode node = stack.removeLast();
            if (--k == 0) {
                return node.val;
            }
            root = node.right;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        int[] ans = new int[1];
        ans[0] = -1;
        dfs(root, k, ans);
        return ans[0];
    }

    private int dfs(TreeNode root, int k, int[] ans) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, k, ans);
        if (ans[0] == -1 && k == left + 1) {
            ans[0] = root.val;
        }
        if (ans[0] != -1) {
            return 0;
        }
        return left + dfs(root.right, k - left - 1, ans) + 1;
    }
}
