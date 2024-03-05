package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1379FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree（找出克隆二叉树中的相同节点）
 * @date 2024/3/5 2:19 PM
 */
public class Problem1379FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return dfs(original, cloned, target);
    }

    private TreeNode dfs(TreeNode original, TreeNode cloned, TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }
        TreeNode left = dfs(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return dfs(original.right, cloned.right, target);
    }
}
