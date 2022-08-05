package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1340JumpGameV（跳跃游戏V）
 * @Date 2022/8/5 6:30 PM
 * @Version 1.0
 */
public class Problem1340JumpGameV {

    int[] arr;
    int ans, d, n;

    public int maxJumps(int[] arr, int d) {
        this.d = d;
        this.n = arr.length;
        this.arr = arr;
        ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dfs(dp, i);
        }
        return ans;
    }

    private void dfs(int[] dp, int idx) {
        if (dp[idx] > 0) {
            return;
        }
        int res = 1;
        for (int i = idx - 1; i >= Math.max(0, idx - d) && arr[idx] > arr[i]; i--) {
            dfs(dp, i);
            res = Math.max(res, dp[i] + 1);
        }
        for (int i = idx + 1; i <= Math.min(n - 1, idx + d) && arr[idx] > arr[i]; i++) {
            dfs(dp, i);
            res = Math.max(res, dp[i] + 1);
        }
        dp[idx] = res;
        ans = Math.max(ans, res);
    }
}
