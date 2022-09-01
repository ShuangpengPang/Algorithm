package com.shuangpeng.competition.双周赛.第084场双周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2365TaskSchedulerII（任务调度器II）
 * @Date 2022/8/20 5:29 PM
 * @Version 1.0
 */
public class Problem2365TaskSchedulerII {

    // 比赛时写法
    public long taskSchedulerII0(int[] tasks, int space) {
        long ans = 0L;
        Map<Integer, Long> map = new HashMap<>();
        long INF = -(long) 1e9 - 10;
        int n = tasks.length;
        for (int i = 0; i < n; i++) {
            int t = tasks[i];
            long p = map.getOrDefault(t, INF);
            ans++;
            if (p + space + 1 > ans) {
                ans = p + space + 1;
            }
            map.put(t, ans);
        }
        return ans;
    }

    public long taskSchedulerII(int[] tasks, int space) {
        long ans = 0;
        Map<Integer, Long> lastDay = new HashMap<>();
        for (int t : tasks) {
            ans = Math.max(ans + 1, lastDay.getOrDefault(t, (long) -space) + space + 1);
            lastDay.put(t, ans);
        }
        return ans;
    }
}
