package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem1008ConstructBinarySearchTreeFromPreorderTraversal {

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return recurse(preorder, 0, preorder.length);
    }

    private TreeNode recurse(int[] preorder, int start, int end) {
        if (start >= end) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[start]);
        int i = start + 1;
        while (i < end) {
            if (preorder[i] > node.val) {
                break;
            }
            i++;
        }
        node.left = recurse(preorder, start + 1, i);
        node.right = recurse(preorder, i, end);
        return node;
    }
}
