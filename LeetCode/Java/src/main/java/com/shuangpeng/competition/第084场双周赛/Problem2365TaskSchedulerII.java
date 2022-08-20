package com.shuangpeng.competition.第084场双周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2365TaskSchedulerII（任务调度器II）
 * @Date 2022/8/20 5:29 PM
 * @Version 1.0
 */
public class Problem2365TaskSchedulerII {

    public long taskSchedulerII(int[] tasks, int space) {
        long ans = 0;
        Map<Integer, Long> lastDay = new HashMap<>();
        for (int t : tasks) {
            ans++;
            ans = Math.max(ans, lastDay.getOrDefault(t, (long) -space) + space + 1);
            lastDay.put(t, ans);
        }
        return ans;
    }
}
