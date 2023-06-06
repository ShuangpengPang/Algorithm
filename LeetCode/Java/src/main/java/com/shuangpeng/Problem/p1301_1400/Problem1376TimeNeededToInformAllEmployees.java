package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1376TimeNeededToInformAllEmployees（通知所有员工所需的时间）
 * @date 2023/5/5 7:23 PM
 */
public class Problem1376TimeNeededToInformAllEmployees {

    public int numOfMinutes0(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (i != headID) {
                g[manager[i]].add(i);
            }
        }
        int[] time = new int[n];
        int ans = 0;
        Queue<Integer> q = new ArrayDeque<>(n);
        q.add(headID);
        while (!q.isEmpty()) {
            int id = q.poll(), m = manager[id];
            time[id] = m == -1 ? 0 : time[m] + informTime[m];
            ans = Math.max(ans, time[id]);
            for (int c : g[id]) {
                q.add(c);
            }
        }
        return ans;
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (i != headID) {
                g[manager[i]].add(i);
            }
        }
        return dfs(g, headID, informTime);
    }

    private int dfs(List<Integer>[] g, int x, int[] informTime) {
        int ans = 0, time = informTime[x];
        for (int y : g[x]) {
            ans = Math.max(ans, time + dfs(g, y, informTime));
        }
        return ans;
    }
}

class Problem1376TimeNeededToInformAllEmployees0 {

    public int numOfMinutes0(int n, int headID, int[] manager, int[] informTime) {
        int[] time = new int[n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (informTime[i] == 0) {
                q.offer(new int[]{i, 0});
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], t = p[1];
            if (x != headID && t >= time[x]) {
                int m = manager[x], s = t + informTime[m];
                if (s > time[m]) {
                    time[m] = s;
                    q.offer(new int[]{m, s});
                }
            }
        }
        return time[headID];
    }

    public int numOfMinutes1(int n, int headID, int[] manager, int[] informTime) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, manager, informTime));
        }
        return ans;
    }

    private int dfs(int x, int[] manager, int[] informTime) {
        int p = manager[x];
        if (p == -1) {
            return informTime[x];
        }
        informTime[x] += dfs(p, manager, informTime);
        manager[x] = -1;
        return informTime[x];
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = i, s = 0;
            while (x != -1) {
                s += informTime[x];
                x = manager[x];
            }
            ans = Math.max(ans, s);
            x = i;
            while (x != -1) {
                int t = informTime[x];
                int p = manager[x];
                informTime[x] = s;
                manager[x] = -1;
                s -= t;
                x = p;
            }
        }
        return ans;
    }
}
