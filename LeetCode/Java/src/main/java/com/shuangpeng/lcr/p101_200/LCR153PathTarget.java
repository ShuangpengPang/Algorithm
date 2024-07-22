package com.shuangpeng.lcr.p101_200;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR153PathTarget（LCR 153. 二叉树中和为目标值的路径）
 * @date 2024/7/23 12:11 AM
 */
public class LCR153PathTarget {

    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, new ArrayList<>(), ans, target);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> list, List<List<Integer>> ans, int remain) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        remain -= root.val;
        if (root.left == null && root.right == null) {
            if (remain == 0) {
                ans.add(new ArrayList<>(list));
            }
        } else {
            dfs(root.left, list, ans, remain);
            dfs(root.right, list, ans, remain);
        }
        remain += root.val;
        list.remove(list.size() - 1);
    }
}
