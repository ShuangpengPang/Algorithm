package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR194LowestCommonAncestor（二叉树的最近公共祖先）
 * @date 2024/5/22 3:09 PM
 */
public class LCR194LowestCommonAncestor {

    private TreeNode p, q, parent;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        parent = null;
        this.p = p;
        this.q = q;
        dfs(root);
        return parent;
    }

    private int dfs(TreeNode root) {
        if (root == null || parent != null) {
            return 0;
        }
        int ans = dfs(root.left) | dfs(root.right);
        if (root == p) {
            ans |= 1;
        } else if (root == q) {
            ans |= 2;
        }
        if (ans == 3) {
            parent = root;
            return 0;
        }
        return ans;
    }
}
