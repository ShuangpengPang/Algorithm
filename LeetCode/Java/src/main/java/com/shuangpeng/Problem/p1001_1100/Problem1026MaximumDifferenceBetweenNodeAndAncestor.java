package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

public class Problem1026MaximumDifferenceBetweenNodeAndAncestor {

    public int maxAncestorDiff(TreeNode root) {
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
}
