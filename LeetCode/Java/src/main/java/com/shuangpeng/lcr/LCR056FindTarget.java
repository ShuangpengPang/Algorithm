package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR056FindTarget（两数之和IV-输入二叉搜索树）
 * @date 2024/5/16 7:04 PM
 */
public class LCR056FindTarget {

    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> leftStack = new ArrayDeque<>(), rightStack = new ArrayDeque<>();
        enqueueLeftStack(leftStack, root);
        enqueueRightStack(rightStack, root);
        TreeNode left = leftStack.pollLast(), right = rightStack.pollLast();
        while (left != right) {
            int sum = left.val + right.val;
            if (sum < k) {
                enqueueLeftStack(leftStack, left.right);
                left = leftStack.pollLast();
            } else if (sum > k) {
                enqueueRightStack(rightStack, right.left);
                right = rightStack.pollLast();
            } else {
                return true;
            }
        }
        return false;
    }

    private void enqueueLeftStack(Deque<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.offerLast(node);
            node = node.left;
        }
    }

    private void enqueueRightStack(Deque<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.offerLast(node);
            node = node.right;
        }
    }
}
