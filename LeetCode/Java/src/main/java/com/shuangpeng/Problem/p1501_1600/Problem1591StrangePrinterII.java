package com.shuangpeng.Problem.p1501_1600;

import java.util.*;

/**
 * @Description: Problem1591StrangePrinterII（神奇打印机II）
 * @Date 2022/9/2 10:35 AM
 * @Version 1.0
 */
public class Problem1591StrangePrinterII {

    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, int[]> map = new HashMap<>();
        int m = targetGrid.length, n = targetGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = targetGrid[i][j];
                int[] arr = map.computeIfAbsent(num, k -> new int[]{m, -1, n, -1});
                arr[0] = Math.min(arr[0], i);
                arr[1] = Math.max(arr[1], i);
                arr[2] = Math.min(arr[2], j);
                arr[3] = Math.max(arr[3], j);
            }
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int num : map.keySet()) {
            graph.put(num, new HashSet<>());
            inDegree.put(num, 0);
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int num = entry.getKey();
            int[] arr = entry.getValue();
            Set<Integer> set = graph.get(num);
            for (int i = arr[0]; i <= arr[1]; i++) {
                for (int j = arr[2]; j <= arr[3]; j++) {
                    int value = targetGrid[i][j];
                    if (value != num) {
                        if (set.add(value)) {
                            inDegree.put(value, inDegree.get(value) + 1);
                        }
                    }
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            int num = entry.getKey(), value = entry.getValue();
            if (value == 0) {
                q.offer(num);
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int num = q.poll();
            cnt++;
            for (int next : graph.get(num)) {
                int c = inDegree.get(next) - 1;
                inDegree.put(next, c);
                if (c == 0) {
                    q.offer(next);
                }
            }
        }
        return cnt == inDegree.size();
    }
}
