package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2834FindTheMinimumPossibleSumOfABeautifulArray（找出美丽数组的最小和）
 * @date 2023/12/17 11:22 PM
 */
public class Problem2834FindTheMinimumPossibleSumOfABeautifulArray {

    public int minimumPossibleSum(int n, int target) {
        long m = Math.min(n, target >> 1), N = (long) 1e9 + 7;
        return (int) (((m + 1) * m + (n - m - 1 + (target << 1)) * (n - m)) / 2 % N);
    }
}
