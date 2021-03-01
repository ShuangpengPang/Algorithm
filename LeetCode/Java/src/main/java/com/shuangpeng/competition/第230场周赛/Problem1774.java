package com.shuangpeng.competition.第230场周赛;

import java.util.Arrays;

public class Problem1774 {

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        if (baseCosts == null || baseCosts.length == 0
                || toppingCosts == null || toppingCosts.length == 0) {
            return 0;
        }
        int max = 20000;
        boolean[] costs = new boolean[max + 1];
        for (int base : baseCosts) {
            costs[base] = true;
        }
        int[] copy = Arrays.copyOf(toppingCosts, toppingCosts.length << 1);
        System.arraycopy(toppingCosts, 0, copy, toppingCosts.length, toppingCosts.length);
        for (int topping : copy) {
            for (int i = max; i >= topping; i--) {
                costs[i] = costs[i] || costs[i - topping];
            }
        }
        int answer = Integer.MAX_VALUE;
        int span = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            if (costs[i] && Math.abs(i - target) < span) {
                answer = i;
                span = Math.abs(i - target);
            }
        }
        return answer;
    }
}
