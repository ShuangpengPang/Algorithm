package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem1315SumOfNodesWithEvenValuedGrandparent {

    public int sumEvenGrandparent(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if ((node.val & 1) == 0) {
            if (node.left != null) {
                if (node.left.left != null) {
                    sum += node.left.left.val;
                }
                if (node.left.right != null) {
                    sum += node.left.right.val;
                }
            }
            if (node.right != null) {
                if (node.right.left != null) {
                    sum += node.right.left.val;
                }
                if (node.right.right != null) {
                    sum += node.right.right.val;
                }
            }
        }
        sum += dfs(node.left);
        sum += dfs(node.right);
        return sum;
    }
}
