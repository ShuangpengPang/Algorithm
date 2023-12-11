package com.shuangpeng.Problem.p2601_2700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2641CousinsInBinaryTreeII（二叉树的堂兄弟节点II）
 * @date 2023/12/12 12:55 AM
 */
public class Problem2641CousinsInBinaryTreeII {

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int sum = root.val;
        while (!q.isEmpty()) {
            int preSum = sum;
            sum = 0;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                node.val = preSum - node.val;
                int s = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0 : node.right.val);
                sum += s;
                if (node.left != null) {
                    node.left.val = s;
                    q.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = s;
                    q.offer(node.right);
                }
            }
        }
        return root;
    }
}
