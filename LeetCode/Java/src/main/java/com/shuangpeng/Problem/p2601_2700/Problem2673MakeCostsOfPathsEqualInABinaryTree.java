package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2673MakeCostsOfPathsEqualInABinaryTree（使二叉树所有路径值相等的最小代价）
 * @date 2023/12/12 10:42 AM
 */
public class Problem2673MakeCostsOfPathsEqualInABinaryTree {

    private int ans;

    public int minIncrements0(int n, int[] cost) {
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

    public int minIncrements1(int n, int[] cost) {
        int ans = 0;
        for (int i = n >> 1, j = n; i < j; i >>= 1, j >>= 1) {
            for (int k = i + 1; k < j; k += 2) {
                int max = Math.max(cost[k - 1], cost[k]);
                ans += (max << 1) - cost[k - 1] - cost[k];
                cost[k - 1 >> 1] += max;
            }
        }
        return ans;
    }

    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for (int i = n >> 1; i > 0; i--) {
            int left = (i << 1) - 1, right = i << 1;
            ans += Math.abs(cost[left] - cost[right]);
            cost[i - 1] += Math.max(cost[left], cost[right]);
        }
        return ans;
    }
}
