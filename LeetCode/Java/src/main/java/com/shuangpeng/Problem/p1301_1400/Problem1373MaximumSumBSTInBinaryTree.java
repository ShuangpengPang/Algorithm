package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

/**
 * @Description: Problem1373MaximumSumBSTInBinaryTree（二叉搜索子树的最大键值和）
 * @Date 2022/8/9 5:57 PM
 * @Version 1.0
 */
public class Problem1373MaximumSumBSTInBinaryTree {

    int ans;

    public int maxSumBST(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] left = dfs(root.left), right = dfs(root.right);
        boolean valid = left[0] == 1 && right[0] == 1 && root.val > left[3] && root.val < right[2];
        if (!valid) {
            return new int[]{0, 0, 0, 0};
        }
        int sum = root.val + left[1] + right[1];
        ans = Math.max(ans, sum);
        return new int[]{1, sum, Math.min(root.val, left[2]), Math.max(root.val, right[3])};
    }
}

class Problem1373MaximumSumBSTInBinaryTree0 {

    int ans;

    public int maxSumBST(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(root.left), right = dfs(root.right);
        if (left == null || right == null || left[1] >= root.val || root.val >= right[0]) {
            return null;
        }
        int sum = left[2] + right[2] + root.val;
        ans = Math.max(ans, sum);
        return new int[]{Math.min(root.val, left[0]), Math.max(root.val, right[1]), sum};
    }
}

class Problem1373MaximumSumBSTInBinaryTree1 {

    static int INF = Integer.MAX_VALUE;
    int ans;

    public int maxSumBST(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{INF, -INF, 0};
        }
        int[] left = dfs(root.left), right = dfs(root.right);
        if (left[1] >= root.val || root.val >= right[0]) {
            return new int[]{-INF, INF, 0};
        }
        int s = left[2] + right[2] + root.val;
        ans = Math.max(ans, s);
        return new int[]{Math.min(left[0], root.val), Math.max(right[1], root.val), s};
    }
}
