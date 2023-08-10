package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1382BalanceABinarySearchTree（将二叉搜索树变平衡）
 * @date 2023/8/10 5:53 PM
 */
public class Problem1382BalanceABinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return dfs(list, 0, list.size() - 1);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private TreeNode dfs(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start >> 1);
        TreeNode root = new TreeNode(list.get(mid));
        root.left = dfs(list, start, mid - 1);
        root.right = dfs(list, mid + 1, end);
        return root;
    }
}
