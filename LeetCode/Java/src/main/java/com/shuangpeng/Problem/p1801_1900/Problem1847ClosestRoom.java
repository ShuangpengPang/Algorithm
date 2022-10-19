package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Description: Problem1847ClosestRoom（最近的房间）
 * @Date 2022/10/19 5:55 PM
 * @Version 1.0
 */
public class Problem1847ClosestRoom {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int m = rooms.length, n = queries.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> queries[b][1] - queries[a][1]);
        int[] ans = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0, j = 0; i < n; i++) {
            int id = ids[i];
            while (j < m && rooms[j][1] >= queries[id][1]) {
                set.add(rooms[j][0]);
                j++;
            }
            Integer r1 = set.floor(queries[id][0]), r2 = set.ceiling(queries[id][0]);
            if (r1 == null && r2 == null) {
                ans[id] = -1;
            } else if (r1 == null) {
                ans[id] = r2;
            } else if (r2 == null) {
                ans[id] = r1;
            } else {
                ans[id] = queries[id][0] - r1 <= r2 - queries[id][0] ? r1 : r2;
            }
        }
        return ans;
    }
}
