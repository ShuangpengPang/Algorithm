package com.shuangpeng.Problem.p1701_1800;


/**
 * @program: Algorithm
 * @description: Problem1712WaysToSplitArrayIntoThreeSubarrays（将数组分成三个子数组的方案数）
 * @author: ShuangPengPang
 * @create: 2025-05-23 11:03
 */
public class Problem1712WaysToSplitArrayIntoThreeSubarrays {

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        long ans = 0;
        int c = preSum[n];
        for (int l = 1, r = 1, i = 2; i < n; i++) {
            int s = (preSum[i] << 1) - c, e = preSum[i] >> 1;
            while (preSum[l] < s) {
                l++;
            }
            while (r < i && preSum[r] <= e) {
                r++;
            }
            ans += Math.max(0, r - l);
        }
        return (int) (ans % ((long) 1e9 + 7));
    }
}
