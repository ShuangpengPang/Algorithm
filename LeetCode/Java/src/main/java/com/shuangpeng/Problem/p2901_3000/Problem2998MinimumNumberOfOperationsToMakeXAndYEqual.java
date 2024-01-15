package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2998MinimumNumberOfOperationsToMakeXAndYEqual（使X和Y相等的最少操作次数）
 * @date 2024/1/15 10:10 AM
 */
public class Problem2998MinimumNumberOfOperationsToMakeXAndYEqual {

    private static final int N = Integer.MAX_VALUE >> 1;

    public int minimumOperationsToMakeEqual(int x, int y) {
        Map<Integer, Integer> memo = new HashMap<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, 0});
        memo.put(x, 0);
        int ans = Math.abs(x - y);
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int num = p[0], cnt = p[1];
            if (cnt > memo.getOrDefault(num, N)) {
                continue;
            }
            if (num <= y) {
                ans = Math.min(ans, cnt + y - num);
            } else {
                ans = Math.min(ans, cnt + num - y);
                int m1 = num % 11, m2 = num % 5;
                check(q, memo, (num - m1) / 11, cnt + m1 + 1, ans);
                check(q, memo, (num + 11 - m1) / 11, cnt - m1 + 12, ans);
                check(q, memo, (num - m2) / 5, cnt + m2 + 1, ans);
                check(q, memo, (num - m2 + 5) / 5, cnt - m2 + 6, ans);
            }
        }
        return ans;
    }

    private void check(Queue<int[]> q, Map<Integer, Integer> memo, int num, int cnt, int ans) {
        if (cnt >= ans) {
            return;
        }
        if (cnt < memo.getOrDefault(num, N)) {
            memo.put(num, cnt);
            q.offer(new int[]{num, cnt});
        }
    }
}

class Problem2998MinimumNumberOfOperationsToMakeXAndYEqual0 {

    private static final int N = Integer.MAX_VALUE >> 1;
    private int ans;

    public int minimumOperationsToMakeEqual(int x, int y) {
        ans = N;
        dfs(x, y, 0, new HashMap<>());
        return ans;
    }

    private void dfs(int x, int y, int cnt, Map<Integer, Integer> memo) {
        if (cnt >= ans || cnt > memo.getOrDefault(x, N)) {
            return;
        }
        if (x <= y) {
            ans = Math.min(ans, cnt + y - x);
            return;
        }
        ans = Math.min(ans, cnt + x - y);
        memo.put(x, cnt);
        int m1 = x % 11, m2 = x % 5;
        dfs((x - m1) / 11, y, cnt + m1 + 1, memo);
        dfs((x - m1 + 11) / 11, y, cnt - m1 + 12,  memo);
        dfs((x - m2) / 5, y, cnt + m2 + 1, memo);
        dfs((x - m2 + 5) / 5, y, cnt - m2 + 6, memo);
    }
}
