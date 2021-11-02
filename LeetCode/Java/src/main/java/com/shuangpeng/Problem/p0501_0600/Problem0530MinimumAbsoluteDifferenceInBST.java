package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0530MinimumAbsoluteDifferenceInBST {

    public int getMinimumDifference0(TreeNode root) {
        return dfs(root, new ArrayList<>());
    }

    private int dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftValue = dfs(root.left, list);
        int rightValue = dfs(root.right, list);
        int index = addValue(list, root.val);
        if (list.size() == 1) {
            return Integer.MAX_VALUE;
        }
        if (index == 0) {
            return Math.min(Math.abs(list.get(0) - list.get(1)), Math.min(leftValue, rightValue));
        }
        if (index == list.size() - 1) {
            return Math.min(Math.abs(list.get(index - 1) - list.get(index)), Math.min(leftValue, rightValue));
        }
        int diff1 = Math.abs(list.get(index - 1) - list.get(index));
        int diff2 = Math.abs(list.get(index) - list.get(index + 1));
        return Math.min(Math.min(diff1, diff2), Math.min(leftValue, rightValue));
    }

    private int addValue(List<Integer> list, int value) {
        int size = list.size();
        int left = 0;
        int right = size;
        while (left < right) {
            int mid = (left + right) >> 1;
            int data = list.get(mid);
            if (data < value) {
                left = mid + 1;
            } else if (data > value) {
                right = mid;
            } else if (data == value) {
                left = mid + 1;
            }
        }
        list.add(left, value);
        return left;
    }

    private int previous = -1;
    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (previous != -1) {
            min = Math.min(min, Math.abs(node.val - previous));
        }
        previous = node.val;
        inorder(node.right);
    }
}
