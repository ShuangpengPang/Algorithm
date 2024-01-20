package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2552CountIncreasingQuadruplets（统计上升四元组）
 * @date 2024/1/20 5:13 PM
 */
public class Problem2552CountIncreasingQuadruplets {

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] great = new int[n][n];
        for (int j = n - 3; j >= 0; j--) {
            for (int k = n - 1, cnt = 0; k > j; k--) {
                if (nums[k] > nums[j]) {
                    cnt++;
                } else {
                    great[j][k] = cnt;
                }
            }
        }
        long ans = 0;
        for (int k = 2; k < n - 1; k++) {
            long cnt = 0;
            for (int j = 0; j < k; j++) {
                if (nums[j] > nums[k]) {
                    ans += cnt * great[j][k];
                } else {
                    cnt++;
                }
            }
        }
        return ans;
    }
}
