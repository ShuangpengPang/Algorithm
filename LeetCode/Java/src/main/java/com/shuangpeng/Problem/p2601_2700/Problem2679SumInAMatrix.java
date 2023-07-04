package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2679SumInAMatrix（矩阵中的和）
 * @date 2023/7/4 10:19 AM
 */
public class Problem2679SumInAMatrix {

    public int matrixSum(int[][] nums) {
        int m = nums.length, n = nums[0].length;
        for (int i = 0; i < m; i++) {
            Arrays.sort(nums[i]);
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, nums[i][j]);
            }
            ans += max;
        }
        return ans;
    }
}
