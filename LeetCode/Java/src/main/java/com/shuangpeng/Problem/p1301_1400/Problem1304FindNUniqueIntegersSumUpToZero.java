package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1304FindNUniqueIntegersSumUpToZero（和为零的N个不同整数）
 * @date 2024/2/4 12:00 AM
 */
public class Problem1304FindNUniqueIntegersSumUpToZero {

    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1; i < n; i += 2) {
            ans[i - 1] = -i;
            ans[i] = i;
        }
        return ans;
    }
}
