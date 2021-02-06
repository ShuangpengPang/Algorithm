package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem1457PseudoPalindromicPathsInABinaryTree {

    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) {
            return 0;
        }
        return recurse(root, new boolean[10]);
    }

    private int recurse(TreeNode node, boolean[] array) {
        int val = node.val;
        array[val] = !array[val];
        if (node.left == null && node.right == null) {
            int count = isPalindromic(array) ? 1 : 0;
            array[val] = !array[val];
            return count;
        }
        int leftCount = 0;
        int rightCount = 0;
        if (node.left != null) {
            leftCount = recurse(node.left, array);
        }
        if (node.right != null) {
            rightCount = recurse(node.right, array);
        }
        array[val] = !array[val];
        return leftCount + rightCount;
    }

    private boolean isPalindromic(boolean[] array) {
        int count = 0;
        for (boolean flag : array) {
            count += flag ? 1 : 0;
        }
        return count <= 1;
    }
}
