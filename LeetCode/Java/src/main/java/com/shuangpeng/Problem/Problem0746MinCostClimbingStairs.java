package com.shuangpeng.Problem;

public class Problem0746MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int first = 0, second = cost[0];
        for (int i = 1; i < n; i++) {
            int temp = second;
            second = Math.min(second, first) + cost[i];
            first = temp;
        }
        return Math.min(first, second);
    }
}
