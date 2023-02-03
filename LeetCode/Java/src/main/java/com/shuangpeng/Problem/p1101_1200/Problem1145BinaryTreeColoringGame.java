package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

/**
 * @author shuangpeng
 * @description: 二叉树着色游戏
 * @date 2023/2/3 11:10 AM
 **/
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

class Problem1145BinaryTreeColoringGame0 {

    int leftCount, rightCount, x;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        leftCount = rightCount = 0;
        this.x = x;
        dfs(root);
        int count = leftCount + rightCount + 1;
        return leftCount > n - leftCount || rightCount > n - rightCount || n - count > count;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left), right = dfs(root.right);
        if (root.val == x) {
            leftCount = left;
            rightCount = right;
        }
        return left + right + 1;
    }
}
