package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0971FlipBinaryTreeToMatchPreorderTraversal（翻转二叉树以匹配先序遍历）
 * @date 2023/3/31 11:24 AM
 */
public class Problem0971FlipBinaryTreeToMatchPreorderTraversal {

    List<Integer> ans;
    int index;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        ans = new ArrayList<>();
        index = 0;
        if (!dfs(root, voyage)) {
            ans.add(-1);
        }
        return ans;
    }

    private boolean dfs(TreeNode root, int[] voyage) {
        if (root == null) {
            return true;
        }
        if (index == voyage.length) {
            return true;
        }
        if (root.val != voyage[index]) {
            return false;
        }
        index++;
        int idx = index;
        int tmp = ans.size();
        if (!dfs(root.left, voyage)) {
            index = idx;
            ans.add(root.val);
            boolean valid = dfs(root.right, voyage) && dfs(root.left, voyage);
            if (!valid) {
                removeData(tmp);
            }
            return valid;
        }
        if (!dfs(root.right, voyage)) {
            removeData(tmp);
            return false;
        }
        return true;
    }

    private void removeData(int idx) {
        for (int i = ans.size() - 1; i >= idx; i--) {
            ans.remove(i);
        }
    }
}
