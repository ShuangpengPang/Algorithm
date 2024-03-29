package com.shuangpeng.competition.第301到310场周赛.第305场周赛;

/**
 * @Description: Problem2369CheckIfThereIsAValidPartitionForTheArray（检查数组是否存在有效划分）
 * @Date 2022/8/29 7:18 PM
 * @Version 1.0
 */
public class Problem2369CheckIfThereIsAValidPartitionForTheArray {

    public boolean validPartition0(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            if (i >= 2 && dp[i - 2] && nums[i - 1] == nums[i - 2]) {
                dp[i] = true;
            } else if (i >= 3 && dp[i - 3] && nums[i - 1] == nums[i - 2] && nums[i - 1] == nums[i - 3]) {
                dp[i] = true;
            } else if (i >= 3 && dp[i - 3] && nums[i - 1] == nums[i - 2] + 1 && nums[i - 1] == nums[i - 3] + 2) {
                dp[i] = true;
            }
        }
        return dp[n];
    }

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        } else if (n == 2) {
            return nums[0] == nums[1];
        }
        boolean a = false, b = nums[0] == nums[1], c = valid(nums, 2);
        for (int i = 3; i < n && (a || b || c); i++) {
            boolean d = b && nums[i] == nums[i - 1] || a && valid(nums, i);
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    private boolean valid(int[] nums, int i) {
        return nums[i] == nums[i - 1] && nums[i] == nums[i - 2] || nums[i] == nums[i - 2] + 2 && nums[i] == nums[i - 1] + 1;
    }
}
