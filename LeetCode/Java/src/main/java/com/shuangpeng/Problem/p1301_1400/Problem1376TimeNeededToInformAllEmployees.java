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

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
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
}
