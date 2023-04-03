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

    public int minDays1(int n) {
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

    public int minDays(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        q.offer(new int[]{n, 0});
        map.put(n, 0);
        int INF = Integer.MAX_VALUE;
        while (q.peek()[0] != 1) {
            int[] arr = q.poll();
            int num = arr[0], days = arr[1];
            int minDays = map.get(num);
            if (days <= minDays) {
                if (num == 2 || num == 3) {
                    if (days + 1 < map.getOrDefault(1, INF)) {
                        q.offer(new int[]{1, days + 1});
                        map.put(1, days + 1);
                    }
                } else {
                    int m1 = num >> 1, m2 = num / 3;
                    int d1 = days + num % 2 + 1, d2 = days + num % 3 + 1;
                    if (d1 < map.getOrDefault(m1, INF)) {
                        map.put(m1, d1);
                        q.offer(new int[]{m1, d1});
                    }
                    if (d2 < map.getOrDefault(m2, INF)) {
                        map.put(m2, d2);
                        q.offer(new int[]{m2, d2});
                    }
                }
            }
        }
        return q.poll()[1] + 1;
    }
}
