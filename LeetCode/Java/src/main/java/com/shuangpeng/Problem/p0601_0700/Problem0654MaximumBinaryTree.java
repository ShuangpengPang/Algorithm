package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0654MaximumBinaryTree（最大二叉树）
 * @Date 2022/8/20 10:09 AM
 * @Version 1.0
 */
public class Problem0654MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree0(int[] nums) {
        int n = nums.length;
        TreeNode cur = new TreeNode(nums[0]);
        for (int i = 1; i < n; i++) {
            TreeNode node = new TreeNode(nums[i]);
            if (cur.val < node.val) {
                node.left = cur;
                cur = node;
            } else {
                TreeNode pre = cur;
                while (pre.right != null && pre.right.val > node.val) {
                    pre = pre.right;
                }
                node.left = pre.right;
                pre.right = node;
            }
        }
        return cur;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            TreeNode node = new TreeNode(nums[i]);
            TreeNode left = null;
            while (!stack.isEmpty() && stack.peekLast().val < nums[i]) {
                left = stack.pollLast();
            }
            node.left = left;
            if (!stack.isEmpty()) {
                stack.peekLast().right = node;
            }
            stack.addLast(node);
        }
        return stack.peekFirst();
    }
}
