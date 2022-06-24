package com.shuangpeng.competition.第079场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2285MaximumTotalImportanceOfRoads（道路的最大总重要性）
 * @Date 2022/6/24 11:51 AM
 * @Version 1.0
 */
public class Problem2285MaximumTotalImportanceOfRoads {

    // 比赛时写法
    public long maximumImportance0(int n, int[][] roads) {
        int[][] map = new int[n][2];
        for (int[] r : roads) {
            int a = r[0], b = r[1];
            map[a][0] = a;
            ++map[a][1];
            map[b][0] = b;
            ++map[b][1];
        }
        Arrays.sort(map, (a, b) -> {
            return a[1] - b[1];
        });
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i + 1) * map[i][1];
        }
        return ans;
    }

    public long maximumImportance(int n, int[][] roads) {
        int[] count = new int[n];
        for (int[] road : roads) {
            int u = road[0], v = road[1];
            count[u]++;
            count[v]++;
        }
        Arrays.sort(count);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) (i + 1) * count[i];
        }
        return sum;
    }
}
