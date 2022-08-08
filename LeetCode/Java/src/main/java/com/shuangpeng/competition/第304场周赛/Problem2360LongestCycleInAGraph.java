package com.shuangpeng.competition.第304场周赛;

/**
 * @Description: Problem2360LongestCycleInAGraph（图中的最长环）
 * @Date 2022/8/6 7:00 PM
 * @Version 1.0
 */
public class Problem2360LongestCycleInAGraph {

    int ans = -1;

    public int longestCycle0(int[] edges) {
        int n = edges.length;
        int[] dis = new int[n];
        ans = -1;
        for (int i = 0; i < n; i++) {
            dfs(edges, dis, i, 1);
        }
        return ans;
    }

    private void dfs(int[] edges, int[] dis, int i, int idx) {
        if (i == -1 || dis[i] == -1) {
            return;
        }
        if (dis[i] == 0) {
            dis[i] = idx;
            dfs(edges, dis, edges[i], idx + 1);
        } else {
            ans = Math.max(ans, idx - dis[i]);
        }
        dis[i] = -1;
    }

    public int longestCycle(int[] edges) {
        int n = edges.length, t = 1, ans = -1;
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            if (time[i] == 0) {
                for (int j = i, start = t; j != -1; j = edges[j]) {
                    if (time[j] > 0) {
                        if (time[j] >= start) {
                            ans = Math.max(ans, t - time[j]);
                        }
                        break;
                    }
                    time[j] = t++;
                }
            }
        }
        return ans;
    }
}



