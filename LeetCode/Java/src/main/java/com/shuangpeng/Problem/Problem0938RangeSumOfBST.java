package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0938RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        return recurse(root, low, high);
    }

    private int recurse(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        return (node.val >= low && node.val <= high ? node.val : 0)
                + recurse(node.left, low, high)
                + recurse(node.right, low, high);
    }
}
