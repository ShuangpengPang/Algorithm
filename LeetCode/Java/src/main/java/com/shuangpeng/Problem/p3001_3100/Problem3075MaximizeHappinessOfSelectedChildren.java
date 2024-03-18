package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3075MaximizeHappinessOfSelectedChildren（幸福值最大化的选择方案）
 * @date 2024/3/18 12:10 PM
 */
public class Problem3075MaximizeHappinessOfSelectedChildren {

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long sum = 0;
        for (int i = n - 1; i >= n - k; i--) {
            int h = happiness[i] - (n - 1 - i);
            if (h <= 0) {
                break;
            }
            sum += h;
        }
        return sum;
    }
}
