package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0939MinimumAreaRectangle（最小面积矩形）
 * @date 2023/3/11 11:39 AM
 */
public class Problem0939MinimumAreaRectangle {

    public int minAreaRect0(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Set<Integer>> e1 : map.entrySet()) {
            int x1 = e1.getKey();
            for (int y1 : e1.getValue()) {
                for (int y2 : e1.getValue()) {
                    if (y1 >= y2) {
                        continue;
                    }
                    int height = y2 - y1;
                    for (Map.Entry<Integer, Set<Integer>> e2 : map.entrySet()) {
                        int x2 = e2.getKey();
                        Set<Integer> set = e2.getValue();
                        if (x1 >= x2 || !set.contains(y1) || !set.contains(y2)) {
                            continue;
                        }
                        ans = Math.min(ans, (x2 - x1) * height);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minAreaRect1(int[][] points) {
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, Set<Integer>> yMap = new HashMap<>();
        for (int[] p : points) {
            xMap.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            yMap.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> e1 : xMap.entrySet()) {
            List<Integer> yList = e1.getValue();
            int x1 = e1.getKey(), size = yList.size();
            for (int i = 0; i < size; i++) {
                int y1 = yList.get(i);
                for (int j = 0; j < size; j++) {
                    int y2 = yList.get(j);
                    if (y1 >= y2) {
                        continue;
                    }
                    int height = y2 - y1;
                    Set<Integer> xSet = yMap.get(y2);
                    for (int x2 : yMap.get(y1)) {
                        if (x1 < x2 && xSet.contains(x2)) {
                            ans = Math.min(ans, Math.abs(x1 - x2) * height);
                        }
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minAreaRect2(int[][] points) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] p : points) {
            map.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
        }
        Map<Integer, Integer> lastX = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            int x = entry.getKey(), size = list.size();
            for (int i = 0; i < size; i++) {
                int y1 = list.get(i);
                for (int j = i + 1; j < size; j++) {
                    int y2 = list.get(j), code = y1 << 16 | y2;
                    if (lastX.containsKey(code)) {
                        ans = Math.min(ans, (x - lastX.get(code)) * (y2 - y1));
                    }
                    lastX.put(code, x);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minAreaRect3(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for (int[] p : points) {
            set.add(p[0] << 16 | p[1]);
        }
        int n = points.length, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1], area = Math.abs(x1 - x2) * Math.abs(y2 - y1);
                if (x1 == x2 || y1 == y2 || area >= ans) {
                    continue;
                }
                if (set.contains(x1 << 16 | y2) && set.contains(x2 << 16 | y1)) {
                    ans = area;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minAreaRect(int[][] points) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int[] p : points) {
            set.add(new Pair<>(p[0], p[1]));
        }
        int n = points.length, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0], y2 = points[j][1], area = Math.abs(x1 - x2) * Math.abs(y2 - y1);
                if (x1 == x2 || y1 == y2 || area >= ans) {
                    continue;
                }
                if (set.contains(new Pair<>(x1, y2)) && set.contains(new Pair<>(x2, y1))) {
                    ans = area;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
