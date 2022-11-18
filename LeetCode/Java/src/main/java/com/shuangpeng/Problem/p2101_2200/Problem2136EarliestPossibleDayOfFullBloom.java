package com.shuangpeng.Problem.p2101_2200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2136EarliestPossibleDayOfFullBloom（全部开花的最早一天）
 * @date 2022/11/18 3:12 PM
 */
public class Problem2136EarliestPossibleDayOfFullBloom {

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> (growTime[b] - growTime[a]));
        int ans = 0, t = 0;
        for (int id : ids) {
            ans = Math.max(ans, t + plantTime[id] + growTime[id]);
            t += plantTime[id];
        }
        return ans;
    }
}
