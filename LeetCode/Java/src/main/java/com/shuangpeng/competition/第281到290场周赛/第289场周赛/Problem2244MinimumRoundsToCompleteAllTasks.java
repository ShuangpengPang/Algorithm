package com.shuangpeng.competition.第281到290场周赛.第289场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2244MinimumRoundsToCompleteAllTasks（完成所有任务需要的最少轮数）
 * @Date 2022/5/29 7:07 PM
 * @Version 1.0
 */
public class Problem2244MinimumRoundsToCompleteAllTasks {

    // 比赛时写法
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        int ans = 0;
        for (int t : map.keySet()) {
            int c = map.get(t);
            if (c == 1) {
                return -1;
            }
            ans += (c + 2) / 3;
        }
        return ans;
    }
}
