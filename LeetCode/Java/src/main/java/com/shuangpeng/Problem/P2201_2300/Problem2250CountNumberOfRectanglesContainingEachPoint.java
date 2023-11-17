package com.shuangpeng.Problem.P2201_2300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2250CountNumberOfRectanglesContainingEachPoint（统计包含每个点的矩形数目）
 * @date 2023/11/16 3:15 PM
 */
public class Problem2250CountNumberOfRectanglesContainingEachPoint {

    // TreeMap超时
    public int[] countRectangles0(int[][] rectangles, int[][] points) {
        int N = 100;
        TreeMap<Integer, Integer>[] maps = new TreeMap[N + 1];
        Arrays.setAll(maps, i -> new TreeMap());
        for (int[] r : rectangles) {
            for (int y = 0; y <= r[1]; y++) {
                maps[y].merge(r[0], 1, Integer::sum);
            }
        }
        for (int y = 0; y <= 100; y++) {
            int prev = 0;
            for (Map.Entry<Integer, Integer> entry = maps[y].lastEntry(); entry != null; entry = maps[y].lowerEntry(entry.getKey())) {
                prev += entry.getValue();
                maps[y].put(entry.getKey(), prev);
            }
        }
        int n = points.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> entry = maps[points[i][1]].ceilingEntry(points[i][0]);
            ans[i] = entry == null ? 0 : entry.getValue();
        }
        return ans;
    }

    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(points[i][1], k -> new ArrayList<>()).add(new int[]{points[i][0], i});
        }
        int[] ans = new int[n];
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            int y = entry.getKey();
            List<Integer> list = new ArrayList<>();
            for (int[] r : rectangles) {
                if (r[1] >= y) {
                    list.add(r[0]);
                }
            }
            list.sort((a, b) -> b - a);
            int size = list.size();
            for (int[] arr : entry.getValue()) {
                int left = 0, right = size - 1, x = arr[0];
                while (left <= right) {
                    int mid = left + (right - left >> 1);
                    if (list.get(mid) >= x) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans[arr[1]] = left;
            }
        }
        return ans;
    }
}
