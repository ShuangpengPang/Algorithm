package com.shuangpeng.Problem.p0501_0600;

/**
 * @Description: Problem0565ArrayNesting（数组嵌套）
 * @Date 2022/7/17 2:30 PM
 * @Version 1.0
 */
public class Problem0565ArrayNesting {

    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(nums, dp, visited, i, 0));
        }
        return ans;
    }

    private int dfs(int[] nums, int[] dp, boolean[] visited, int x, int count) {
        if (dp[x] != 0) {
            return dp[x];
        }
        if (x == nums[x]) {
            dp[x] = 1;
            return 1;
        }
        if (visited[x]) {
            dp[x] = count;
            int y = nums[x];
            while (y != x) {
                dp[y] = count;
                y = nums[y];
            }
            return count;
        }
        visited[x] = true;
        dfs(nums, dp, visited, nums[x], count + 1);
        if (dp[x] == 0) {
            dp[x] = dp[nums[x]] + 1;
        }
        return dp[x];
    }
}
