package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2673MakeCostsOfPathsEqualInABinaryTree（使二叉树所有路径值相等的最小代价）
 * @date 2023/12/12 10:42 AM
 */
public class Problem2673MakeCostsOfPathsEqualInABinaryTree {

    private int ans;

    public int minIncrements(int n, int[] cost) {
        ans = 0;
        dfs(cost, 0, 0);
        return ans;
    }

    private int dfs(int[] cost, int idx, int sum) {
        if (idx >= cost.length) {
            return sum;
        }
        sum += cost[idx];
        idx = (idx << 1) + 1;
        int left = dfs(cost, idx, sum), right = dfs(cost, idx + 1, sum);
        int max = Math.max(left, right);
        ans += (max << 1) - left - right;
        return max;
    }
}
