package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2679SumInAMatrix（矩阵中的和）
 * @date 2023/7/4 10:19 AM
 */
public class Problem2679SumInAMatrix {

    public int matrixSum0(int[][] nums) {
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

    public int matrixSum(int[][] nums) {
        int m = nums.length, n = nums[0].length, N = 1000;
        int[] arr = new int[n], cnt = new int[N + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[nums[i][j]]++;
            }
            for (int j = N, k = n - 1; k >= 0; j--) {
                while (cnt[j] > 0) {
                    arr[k] = Math.max(arr[k], j);
                    k--;
                    cnt[j]--;
                }
            }
        }
        return Arrays.stream(arr).sum();
    }
}
