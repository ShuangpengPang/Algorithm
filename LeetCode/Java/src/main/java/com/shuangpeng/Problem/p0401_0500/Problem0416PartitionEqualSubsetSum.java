package com.shuangpeng.Problem.p0401_0500;

public class Problem0416PartitionEqualSubsetSum {

//    public static void main(String[] args) {
//        Problem0416PartitionEqualSubsetSum a = new Problem0416PartitionEqualSubsetSum();
//        int[] nums = {2,2,1,1};
//        a.canPartition(nums);
//    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >>> 1;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) {
                    dp[j] = true;
                }
            }
        }
        return dp[half];
    }
}
