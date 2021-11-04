package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Problem0653TwoSumIVInputIsABST {

    public boolean findTarget0(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum > k) {
                j--;
            } else if (sum < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    public boolean findTarget(TreeNode root, int k) {
        return preorder(root, k, new HashSet<>());
    }

    private boolean preorder(TreeNode node, int k, HashSet<Integer> set) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return preorder(node.left, k, set) || preorder(node.right, k, set);
    }
}
