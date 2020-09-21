package com.shuangpeng;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// 题目链接：https://mp.weixin.qq.com/s/tng4c3hgw8eaGxQY23XOqw
// LeetCode链接：https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class MinTreeDepth {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                boolean isLeaf = true;
                if (treeNode.left != null) {
                    isLeaf = false;
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    isLeaf = false;
                    queue.add(treeNode.right);
                }
                if (isLeaf) {
                    return depth;
                }
            }
            depth++;
        }
        return depth;
    }
}
