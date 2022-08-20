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

    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return recurse(nums, 0, nums.length - 1);
    }

    private TreeNode recurse(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int max = -1, idx = -1;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode node = new TreeNode(nums[idx]);
        node.left = recurse(nums, start, idx - 1);
        node.right = recurse(nums, idx + 1, end);
        return node;
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

