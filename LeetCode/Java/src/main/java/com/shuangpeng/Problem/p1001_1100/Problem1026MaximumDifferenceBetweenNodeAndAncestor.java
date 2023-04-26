package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

/**
 * @description: 节点与其祖先的最大差值
 * @date 2023/4/26 2:22 PM
 **/
public class Problem1026MaximumDifferenceBetweenNodeAndAncestor {

    public int maxAncestorDiff0(TreeNode root) {
        return recurse(root)[2];
    }

    private int[] recurse(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = recurse(node.left);
        int[] right = recurse(node.right);
        int min = Math.min(node.val, Math.min(left[0], right[0]));
        int max = Math.max(node.val, Math.max(left[1], right[1]));
        int value = Math.max(Math.max(left[2], right[2]), Math.max(Math.abs(node.val - min), Math.abs(node.val - max)));
        return new int[]{min, max, value};
    }

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        int val = root.val;
        min = Math.min(min, val);
        max = Math.max(max, val);
        int d = Math.max(Math.abs(val - min), Math.abs(val - max));
        return Math.max(d, Math.max(dfs(root.left, min, max), dfs(root.right, min, max)));
    }
}

class Problem1026MaximumDifferenceBetweenNodeAndAncestor0 {

    int ans;

    public int maxAncestorDiff(TreeNode root) {
        ans = 0;
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int min, int max) {
        if (root == null) {
            ans = Math.max(ans, max - min);
            return;
        }
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        dfs(root.left, min, max);
        dfs(root.right, min, max);
    }
}
