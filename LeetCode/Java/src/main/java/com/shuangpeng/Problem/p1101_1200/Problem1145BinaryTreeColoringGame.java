package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

public class Problem1145BinaryTreeColoringGame {

    private int leftCount = 0;
    private int rightCount = 0;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (n <= 1) {
            return false;
        }
        dfs(root, x);
        int half = n >> 1;
        return leftCount > half || rightCount > half || n - leftCount - rightCount - 1 > half;
    }

    private int dfs(TreeNode node, int x) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left, x);
        int right = dfs(node.right, x);
        if (node.val == x) {
            leftCount = left;
            rightCount = right;
        }
        return left + right + 1;
    }
}
