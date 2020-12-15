package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0108ConvertSortedArrayToBinarySearchTree {

//    [-10,-3,0,5,9]
//    [2,0,3,null,-3,null,9]
//    [0,-3,9,-10,null,5]

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return recurse(nums, 0, nums.length - 1);
    }

    public TreeNode recurse(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = (start + end) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = recurse(nums, start, mid - 1);
        node.right = recurse(nums, mid + 1, end);
        return node;
    }
}
