package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2614PrimeInDiagonal（对角线上的质数）
 * @date 2024/4/5 2:37 PM
 */
public class Problem2614PrimeInDiagonal {

    public int diagonalPrime(int[][] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getCount(nums[i][i]));
            ans = Math.max(ans, getCount(nums[i][n - i - 1]));
        }
        return ans;
    }

    private int getCount(int x) {
        if (x < 2) {
            return 0;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return 0;
            }
        }
        return x;
    }
}
