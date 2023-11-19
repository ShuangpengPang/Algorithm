package com.shuangpeng.Problem.P2201_2300;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2265CountNodesEqualToAverageOfSubtree（统计值等于子树平均值的节点数）
 * @date 2023/11/19 4:03 PM
 */
public class Problem2265CountNodesEqualToAverageOfSubtree {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = dfs(root.left), right = dfs(root.right);
        int count = left[0] + right[0] + 1, sum = left[1] + right[1] + root.val;
        if (root.val == sum / count) {
            ans++;
        }
        return new int[]{count, sum};
    }
}
