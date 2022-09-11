package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:（修剪二叉搜索树）
 * @Date 2022/9/10 9:04 PM
 **/
public class Problem0669TrimABinarySearchTree {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private TreeNode dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }
        if (node.val < low) {
            return dfs(node.right, low, high);
        }
        if (node.val > high) {
            return dfs(node.left, low, high);
        }
        node.left = dfs(node.left, low, high);
        node.right = dfs(node.right, low, high);
        return node;
    }

    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Integer> list = new ArrayList<>();
        for (int[] arr : intervals) {
            int l = arr[0], r = arr[1];
            int idx = binarySearch(list, l);
            if (idx < 0) {
                list.add(r);
            } else {
                list.set(idx, r);
            }
            list.sort((a, b) -> a - b);
        }
        return list.size();
    }

    private int binarySearch(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int data = list.get(mid);
            if (num <= data) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return right;
    }

//    public static void main(String[] args) {
//        Problem0669TrimABinarySearchTree a = new Problem0669TrimABinarySearchTree();
//        int[][] group = {{5,10},{6,8},{1,5},{2,3},{1,10}};
//        a.minGroups(group);
//    }
}
