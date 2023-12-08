package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2640FindTheScoreOfAllPrefixesOfAnArray（一个数组所有前缀的分数）
 * @date 2023/12/8 11:57 PM
 */
public class Problem2640FindTheScoreOfAllPrefixesOfAnArray {

    public long[] findPrefixScore(int[] nums) {
        int n = nums.length, max = 0;
        long sum = 0;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i] + max;
            ans[i] = sum;
        }
        return ans;
    }
}
