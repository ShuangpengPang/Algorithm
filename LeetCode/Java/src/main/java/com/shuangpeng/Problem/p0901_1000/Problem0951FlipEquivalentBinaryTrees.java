package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0951FlipEquivalentBinaryTrees（翻转等价二叉树）
 * @date 2023/3/17 4:28 PM
 */
public class Problem0951FlipEquivalentBinaryTrees {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right);
    }
}
