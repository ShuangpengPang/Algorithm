package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR052IncreasingBST（递增顺序搜索树）
 * @date 2024/5/17 11:01 AM
 */
public class LCR052IncreasingBST {

    private TreeNode dummy, parent;

    public TreeNode increasingBST(TreeNode root) {
        dummy = new TreeNode();
        parent = dummy;
        inorder(root);
        return dummy.right;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        parent.right = new TreeNode(root.val);
        parent = parent.right;
        inorder(root.right);
    }
}
