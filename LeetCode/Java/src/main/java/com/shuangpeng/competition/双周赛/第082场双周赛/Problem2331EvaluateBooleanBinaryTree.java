package com.shuangpeng.competition.双周赛.第082场双周赛;

import com.shuangpeng.common.TreeNode;

/**
 * @Description: Problem2331EvaluateBooleanBinaryTree（计算布尔二叉树的值）
 * @Date 2022/7/14 5:35 PM
 * @Version 1.0
 */
public class Problem2331EvaluateBooleanBinaryTree {

    public boolean evaluateTree(TreeNode root) {
        if (root.val < 2) {
            return root.val == 1;
        }
        boolean left = evaluateTree(root.left), right = evaluateTree(root.right);
        if (root.val == 2) {
            return left || right;
        }
        return left && right;
    }
}

class Problem2331EvaluateBooleanBinaryTree0 {

    // 比赛时写法
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 0 ? false : true;
        }
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        return root.val == 2 ? (left || right) : (left && right);
    }
}

class Problem2331EvaluateBooleanBinaryTree1 {

    public boolean evaluateTree(TreeNode root) {
        if (root.val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        }
        if (root.val == 3) {
            return evaluateTree(root.left) && evaluateTree(root.right);
        }
        return root.val == 1;
    }
}
