package com.shuangpeng.Problem.p1501_1600;

import java.util.*;

/**
 * @Description: Problem1553MinimumNumberOfDaysToEatNOranges（吃掉N个橘子的最少天数）
 * @Date 2022/8/30 5:42 PM
 * @Version 1.0
 */
public class Problem1553MinimumNumberOfDaysToEatNOranges {

    public int minDays0(int n) {
        Queue<Long> q = new LinkedList<>();
        long min = n;
        int step = 0;
        q.offer((long) n);
        Set<Long> visited = new HashSet<>();
        while (!q.isEmpty()) {
            step++;
            for (int i = q.size() - 1; i >= 0; i--) {
                long num = q.poll();
                if (num == 1) {
                    return step;
                }
                long a = num / 3, b = num >> 1, c = num - 1;
                if (num % 3 == 0 && a < min * 27 && !visited.contains(a)) {
                    visited.add(a);
                    q.offer(a);
                    min = Math.min(min, a);
                }
                if ((num & 1) == 0 && b < min * 27 && !visited.contains(b)) {
                    visited.add(b);
                    q.offer(b);
                    min = Math.min(min, b);
                }
                if (c < min * 27) {
                    visited.add(c);
                    q.offer(c);
                    min = Math.min(min, c);
                }
            }
        }
        return 0;
    }

    public int minDays(int n) {
        return dfs(n, new HashMap<>());
    }

    private int dfs(int n, Map<Integer, Integer> memo) {
        if (n <= 3) {
            return (n >> 1) + 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int ans = Math.min(dfs(n / 3, memo) + n % 3, dfs(n / 2, memo) + n % 2) + 1;
        memo.put(n, ans);
        return ans;
    }
}
