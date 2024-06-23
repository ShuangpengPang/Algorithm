package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR053InorderSuccessor（二叉搜索树中的中序后继）
 * @date 2024/6/23 8:17 PM
 */
public class LCR053InorderSuccessor {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        return dfs(root, p);
    }

    private TreeNode dfs(TreeNode root, TreeNode p) {
        if (root == null || root == p) {
            return null;
        }
        if (root.val < p.val) {
            return dfs(root.right, p);
        }
        TreeNode node = root;
        while (node.left != null && node.left.val > p.val) {
            node = node.left;
        }
        TreeNode ans = dfs(node.left, p);
        return ans == null ? node : ans;
    }
}
