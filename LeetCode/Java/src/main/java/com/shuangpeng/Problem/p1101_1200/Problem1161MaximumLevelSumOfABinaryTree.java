package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1161MaximumLevelSumOfABinaryTree（最大层内元素和）
 * @Date 2022/7/31 1:44 PM
 * @Version 1.0
 */
public class Problem1161MaximumLevelSumOfABinaryTree {

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        int maxSum = Integer.MIN_VALUE, maxLevel = 0;
        while (!q.isEmpty()) {
            level++;
            int sum = 0;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }
}
