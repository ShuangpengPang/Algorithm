package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0129SumRootToLeafNumbers {

    private int sum = 0;

    public int sumNumbers0(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int data) {
        if (node == null) {
            return;
        }
        data = data * 10 + node.val;
        if (node.left == null && node.right == null) {
            sum += data;
        } else {
            dfs(node.left, data);
            dfs(node.right, data);
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> dataQueue = new LinkedList<>();
        nodeQueue.offer(root);
        dataQueue.offer(root.val);
        int sum = 0;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int data = dataQueue.poll();
            if (node.left == null && node.right == null) {
                sum += data;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                dataQueue.offer(data * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                dataQueue.offer(data * 10 + node.right.val);
            }
        }
        return sum;
    }
}
