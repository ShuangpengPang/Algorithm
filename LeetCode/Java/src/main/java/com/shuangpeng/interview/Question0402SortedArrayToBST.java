package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

public class Question0402SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildTree(nums, 0, nums.length);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(nums, start, mid);
        node.right = buildTree(nums, mid + 1, end);
        return node;
    }
}
