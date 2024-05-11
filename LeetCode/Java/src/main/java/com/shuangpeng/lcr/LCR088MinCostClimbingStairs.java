package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR088MinCostClimbingStairs（使用最小花费爬楼梯）
 * @date 2024/5/11 11:47 AM
 */
public class LCR088MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int last1 = 0, last = 0;
        for (int n = cost.length, i = 0; i < n; i++) {
            int tmp = last;
            last = Math.min(last1, last) + cost[i];
            last1 = tmp;
        }
        return Math.min(last1, last);
    }
}
