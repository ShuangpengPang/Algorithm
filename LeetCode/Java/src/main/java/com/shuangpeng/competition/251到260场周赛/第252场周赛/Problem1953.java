package com.shuangpeng.competition.第252场周赛;

public class Problem1953 {

    public long numberOfWeeks(int[] milestones) {
        long sum = 0, maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < milestones.length; i++) {
            maxValue = Math.max(maxValue, milestones[i]);
            sum += milestones[i];
        }
        return maxValue <= ((sum - 1) >> 1) + 1 ? sum : ((sum - maxValue) << 1) + 1;
    }
}
