package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: Problem1161MaximumLevelSumOfABinaryTree（最大层内元素和）
 * @Date 2022/7/31 1:44 PM
 * @Version 1.0
 */
public class Problem1161MaximumLevelSumOfABinaryTree {

    public int maxLevelSum0(TreeNode root) {
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

    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, 0, list);
        int maxSum = Integer.MIN_VALUE;
        int n = list.size();
        int maxLevel = 0;
        for (int i = 0; i < n; i++) {
            int sum = list.get(i);
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = i;
            }
        }
        return maxLevel + 1;
    }

    private void dfs(TreeNode root, int level, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (level == list.size()) {
            list.add(root.val);
        } else {
            list.set(level, list.get(level) + root.val);
        }
        dfs(root.left, level + 1, list);
        dfs(root.right, level + 1, list);
    }
}

class Problem1161MaximumLevelSumOfABinaryTree0 {

    int maxSum;
    int maxLevel;

    public int maxLevelSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxLevel = 0;
        dfs(1, 1, root);
        return maxLevel;
    }

    private void dfs(int layer, int len, TreeNode... nodes) {
        TreeNode[] arr = new TreeNode[len << 1];
        int sum = 0;
        int size = 0;
        for (int i = 0; i < len; i++) {
            TreeNode node = nodes[i];
            sum += node.val;
            if (node.left != null) {
                arr[size++] = node.left;
            }
            if (node.right != null) {
                arr[size++] = node.right;
            }
        }
        if (sum > maxSum) {
            maxSum = sum;
            maxLevel = layer;
        }
        if (size > 0) {
            dfs(layer + 1, size, arr);
        }
    }
}
