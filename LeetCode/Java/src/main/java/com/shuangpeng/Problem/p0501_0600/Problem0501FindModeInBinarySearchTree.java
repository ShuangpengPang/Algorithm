package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0501FindModeInBinarySearchTree（二叉搜索树中的众数）
 * @date 2023/4/23 6:48 PM
 */
public class Problem0501FindModeInBinarySearchTree {

    List<Integer> ans;
    int maxFreq;
    int count;
    int prev;

    public int[] findMode(TreeNode root) {
        ans = new ArrayList<>();
        maxFreq = count = 0;
        prev = Integer.MIN_VALUE;
        dfs(root);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        int val = root.val;
        if (val != prev) {
            count = 0;
        }
        count++;
        if (count >= maxFreq) {
            if (count > maxFreq) {
                maxFreq = count;
                ans.clear();
            }
            ans.add(val);
        }
        prev = val;
        dfs(root.right);
    }
}
