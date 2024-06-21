package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR047PruneTree（二叉树剪枝）
 * @date 2024/6/22 12:20 AM
 */
public class LCR047PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }
}
